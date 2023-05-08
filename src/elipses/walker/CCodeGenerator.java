
package elipses.walker;
import elipses.analysis.*;
import elipses.node.*;
import elipses.semantic.SemanticFlags;
import elipses.util.*;

import java.util.*;
import java.io.*;
import elipses.semantic.TypeInference;
import elipses.semantic.Symbol.Signature;
import elipses.semantic.Symbol.Type;
import elipses.semantic.Symbol;
import elipses.semantic.SymbolTable;
import elipses.semantic.*;



class CCodeData {

    private PrintWriter out;

    public IndentedStringBuilder header   = new IndentedStringBuilder();
    public IndentedStringBuilder body     = new IndentedStringBuilder();
    public IndentedStringBuilder footer   = new IndentedStringBuilder();
    public String filepath;

    public CCodeData(String filename) {
        try{
            this.filepath =  new String(filename);
            out = new PrintWriter(filename + ".c" );
        }
        catch(IOException exc) {
            ElipLogger.error(exc.getMessage());
        }
    }

    public void writeCode() {
        out.print(header.toString()   + '\n');
        out.print(body.toString()   + '\n');
        out.print(footer.toString()   + '\n');
        out.flush();
        out.close(); 
    }
}




public class CCodeGenerator extends DepthFirstAdapter {
    Map<Signature,String> sig2str = new HashMap<>();
    int typedef_count = 0;
    CCodeData code;
    SemanticFlags flags;
    IndentedStringBuilder header;
    IndentedStringBuilder body;
    IndentedStringBuilder footer;
    SymbolTable table = new SymbolTable();
    TypeInference inference;
    String filename;
    String curr_block;

    Stack<String> block_return = new Stack<>();
    Stack<String> if_return = new Stack<>();
    Stack<String> lambda_return = new Stack<>();
    IndentedStringBuilder lambda_header = new IndentedStringBuilder();
    IndentedStringBuilder lambda_temp;

    Stack<IndentedStringBuilder > blocks = new Stack<>();

    private Map<String,String> C;

    public CCodeGenerator(String out_filename) {
        blocks.push(new IndentedStringBuilder());
        filename = new File(out_filename)
            .getName()
            .replaceAll("[^a-zA-Z_]", "_");

        code   = new CCodeData(out_filename);
        header = code.header;
        body   = code.body;
        footer = code.footer;
        flags  = new SemanticFlags();
        inference = new TypeInference(table, new ArrayList<SemanticError>(), this);

        C = new HashMap<>();
        C.put("inteiro", "int") ;
        C.put("booleano", "bool") ;
        C.put("real", "float") ;
        C.put("verdadeiro", "true") ;
        C.put("falso", "false") ;
        addDefaultBoilerPlate();
            
    }

    public void setUpBuiltins(IndentedStringBuilder header, IndentedStringBuilder buffer) {
        header.append(
            "float elip_cosseno(float x);\n"
            +"float elip_seno(float x);\n"
            +"float elip_logaritmo(float x);\n"
            +"float elip_logaritmo(float x);\n"
            +"float elip_potencia(float x, float y);\n"
        );

        buffer.append( 
            "float elip_cosseno(float x) {\n"
            + "    return cosf(x);\n"
            + "}\n"
            + "\n"
            + "float elip_seno(float x) {\n"
            + "    return sinf(x);\n"
            + "}\n"
            + "\n"
            + "float elip_tangente(float x) {\n"
            + "    return tanf(x);\n"
            + "}\n"
            + "\n"
            + "float elip_logaritmo(float x) {\n"
            + "    return log10f(x);\n"
            + "}\n"
            + "\n"
            + "float elip_potencia(float x, float y) {\n"
            + "    return powf(x,y) ;\n"
            + "}\n"
        );
    }

    public void setUpArgv(PParam e, IndentedStringBuilder buffer, int argv_index) {
        
        String[] data = e.toString().split(" ");
        String type = C.get(data[0].strip()) ;
        String id = data[1].strip();
        buffer.append("\n    arg = argv["+ argv_index +"];\n");
        buffer.append("\n    arg_len = strlen(arg);\n");

        if (type .equals("int")) {
            buffer.append(
                "    " + type+ " " + id + " = " + "atoi(arg);\n"
            ); 
        }
        else if (type .equals("float")) {
            // Replace ',' with '.'
            buffer.append(
                  "    for (int i = 0; arg[i]; i++) {\n"
                + "        if (arg[i] == ',') {\n"
                + "            arg[i] = '.';\n"
                + "        }\n"
                + "     }\n"
            );

            buffer.append(
                "    " + type+ " " + id + " = " + "atof(arg);\n"
            );
        }
        else if (type .equals("bool")) {
            buffer.append(
                  "    for (int i = 0; arg[i]; i++) {\n"
                + "        arg[i] = tolower(arg[i]);\n"
                + "    }\n"
            );
            buffer.append(
                "\n    " + type+ " " + id + ";\n");
            buffer.append( 
                  "    if (strcmp(arg, \"falso\") == 0) {\n"
                + "        "+ id + " = false;\n"
                + "    } else if (strcmp(arg, \"verdadeiro\") == 0) {\n"
                + "        " + id + "= true;\n"
                + "    } else {\n"
                + "        printf(\"Invalid boolean value: %s\\n\", arg);\n"
                + "        return 1;\n"
                + "    }\n"
            );
        }
    }

    public String sanitize(TIdentifier id) {
        return "elip_" + id.getText(); 
    }

    public String sanitize(String id) {
        return "elip_" + id;
    }

    public String sanitize(PType type) {
        return C.get(type.toString().strip());
    }

    public String sanitize(TKwBool booleano) {
        return C.get(booleano.toString().strip());
    }

    public void addDefaultBoilerPlate() {
        header.append(
              "#include <string.h>\n"
            + "#include <stdio.h>\n"  
            + "#include <stdlib.h>\n"  
            + "#include <locale.h>\n"  
            + "#include <ctype.h>\n"  
            + "#include <math.h>\n"  

            +"// Define an enum for boolean values\n"
            +"typedef enum {\n"
            +"    false = 0,\n"
            +"    true = 1\n"
            +"} bool;\n\n"
        );
        setUpBuiltins(header, body);
    }

    public String getHeader(){
        return this.header.toString();
    }

    public String getFooter(){
        return this.footer.toString();
    }

    public String getCode(){
        return this.body.toString();
    }

   @Override public void 
    inStart(Start node) {
        SemanticAnalysis.addBuiltinFunctions(table);
    } 

    // >>  [Exp]
    @Override public void 
    caseAOrExp(AOrExp node) {
        inAOrExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("||"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
        outAOrExp(node);
    }
    @Override
    public void caseAAndExp(AAndExp node) {
        body.append("("); 
        inAAndExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("&&"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
        outAAndExp(node);
    }

    @Override public void 
    caseAEqExp(AEqExp node) {
        inAEqExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append(" == "); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqExp(node);
        body.append(")"); 
    }
   
    @Override
    public void caseALtExp(ALtExp node) {
        inALtExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("<"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALtExp(node);
        body.append(")"); 
    }


    @Override
    public void caseAGtExp(AGtExp node) {
        inAGtExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append(">"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
        outAGtExp(node);
    }
    @Override
    public void caseAPlusExp(APlusExp node)
    {
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append(" + ");
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
    }

    @Override
    public void caseAMinusExp(AMinusExp node) {
        inAMinusExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("-"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
        outAMinusExp(node);
    }

    @Override
    public void caseAMultExp(AMultExp node)
    {
        
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("*"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
    }

    @Override
    public void caseADivExp(ADivExp node) {
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("/"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
    }

    
    @Override
    public void caseAModExp(AModExp node) {
        inAModExp(node);
        body.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        body.append("%"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        body.append(")"); 
        outAModExp(node);
    }

    @Override
    public void caseANegativeExp(ANegativeExp node) {
            inANegativeExp(node);
            body.append("(-("); 
            if(node.getExp() != null)
            {
                node.getExp().apply(this);
            }
            body.append("))"); 
            outANegativeExp(node);
    }
    @Override
    public void caseANotExp(ANotExp node) {
        inANotExp(node);
        body.append("(!(");
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        body.append("))");
        outANotExp(node);
    }
    
    @Override
    public void caseAIfExp(AIfExp node) {
        this.inAIfExp(node);
        String if_name = new String("if_" + (flags.if_count++));
        if_return.push(if_name );

        IndentedStringBuilder block = new IndentedStringBuilder(body); 

        block
            .append("\n")
            .pushIndex()
            .append("\n")
            .append("{\n")
            .pushIndent()
        ;
        
        IndentedStringBuilder temp = body;
        body = block;
        body.append("if (");
        node.getCond().apply(this);
        body.append(") {").pushIndent().append("\n");

        block.append(if_name + " = " );   
        node.getTruthy().apply(this);
        block.append(";\n")
             .popIndent()
             .append("} else {\n")
             .pushIndent();
        block.append(if_name + " = " );   
            node.getFalsy().apply(this);
        block.append(";\n")
            .popIndent()
            .append("}\n")
            .popIndent()
            .append("}\n");
        

        String if_type = "float";
        boolean write_name = true;
        Symbol s = inference.getSymbolOrNull(node.getTruthy());
        if (s != null) {
            if (s.isFunction()){
                if_type = fromSignatureToCType(s.getSignature(), if_name);
                write_name = false;
            }
            else {
                if_type = C.get(s.getType().toString().strip());
            }
        }
        else {
            Type t = inference.getType(node.getFalsy()); 
            if (t != Type.UNKOWN){
                if_type = C.get(t.toString());
            }
        }
        ElipLogger.debug("[C] we got the if type as " + if_type);
        
        if (write_name) {
            block
                .insertAtCurrentIndex(if_type + " " + if_name+ ";")
                .popIndex()
            ;
        } 
        else {
            block
                .insertAtCurrentIndex(if_type + ";")
                .popIndex()
            ;

        }

        body = temp;
        body.insertAtPreviousLine(block.toString());
        body.append(if_return.pop());
        this.outAIfExp(node);
    }

    //@Override
    public void oldCaseAIfExp(AIfExp node) {
        inAIfExp(node);

        body.append("(");
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
        }

        body.append(" ? ");
        if(node.getTruthy() != null)
        {
            node.getTruthy().apply(this);
        }
        body.append(":");
        if(node.getFalsy() != null)
        {
            node.getFalsy().apply(this);
        }
        body.append(")");
        outAIfExp(node);
    }

    @Override
    public void caseAIdExp(AIdExp node) {
        super.caseAIdExp(node);
    }

    @Override
    public void caseTKwReal(TKwReal node) {
        body.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTKwBool(TKwBool node) {
        body.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTKwInteger(TKwInteger node) {
        body.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTIdentifier(TIdentifier node) {
        body.append(sanitize(node));
    }

    @Override
    public void inABinExp(ABinExp node) {
        
        body.append(node.getNumberBin().getText());
        
    }

    @Override
    public void inARealExp(ARealExp node) {
        body.append(
            node.getNumberFrac()
                .getText()
                .replace(',', '.')
        );
    }

    @Override
    public void inAIntExp(AIntExp node) {
        body.append(node.getNumberInt().getText());
    }

    @Override
    public void caseATrueExp(ATrueExp node)
    {
        body.append("true");
    }

    @Override
    public void caseAFalseExp(AFalseExp node)
    {
        body.append("false");
    }
    
    
    @Override
    public void inABlockExp(ABlockExp node) {
        // Is the resposability of whoever need blockexp to
        // make shure it's the correct type
        ElipLogger.debug("enteredd scope in a block");
        table.enterScope();
    }
    @Override
    public void caseABlockExp(ABlockExp node) {
        this.inABlockExp(node);
        String block_name = new String("block_" + flags.block_count++);
        block_return.push(block_name);

        IndentedStringBuilder block = new IndentedStringBuilder(body); 

        // We might use this strategy of remembering the return site
        // instead of assuming its safe to write on previous line

        block
            .append("\n")
            .pushIndex()
            .append("\n")
            .append("{\n")
            .pushIndent()
        ;
        
        IndentedStringBuilder temp = body;
        body = block;
        {
            List<PDeclConst> copy = new ArrayList<PDeclConst>(node.getDeclConst());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PDeclConst e = copy.get(i);
                e.apply(this);
            }
        }

        PExp exp = node.getExp();
        if(exp != null) {
            block.append(block_name + " = " );   
            exp.apply(this);
        }
        block.append(";\n")
            .popIndent()
            .append("}\n");

        String block_type = "float";
        boolean write_name = true;
        Symbol s = inference.getSymbolOrNull(exp);
        if (s != null) {
            if (s.isFunction()){
                block_type = fromSignatureToCType(s.getSignature(),block_name);
                write_name = false;
            }
            else {
                block_type = C.get(s.getType().toString().strip());
            }
        }
        else {
            Type t = inference.getType(node.getExp()); 
            if (t != Type.UNKOWN){
                block_type = C.get(t.toString());
            }
        }
        ElipLogger.debug("from c we got the block type as " + block_type);
        
        if (write_name) {
            block
                //.insertAtCurrentIndex("/*lê  "+ block_name + " " + block_type +" */" )
                .insertAtCurrentIndex(block_type + " " + block_name + ";")
                .popIndex()
            ;
        } 
        else {
            block
                .insertAtCurrentIndex(block_type + ";")
                .popIndex()
            ;

        }

        body = temp;
        body.insertAtPreviousLine(block.toString());
        body.append(block_return.pop());
        this.outABlockExp(node);
    }
   @Override
    public void outABlockExp(ABlockExp node) {
        //table.printAllScopes();
        table.exitScope();
        ElipLogger.debug("from C exited scope in a block");
    } 

    @Override
    public void inADeclConst(ADeclConst node) {
        assert node.getIdentifier() != null;
        //String name = node.getIdentifier().getText().strip();
        //assert table.existsInCurrentScope(name);
    }
    public void caseADeclConst(ADeclConst node) {
        this.inADeclConst(node);

        body.append("const ");
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        body.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }

        body.append(" = ");
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        body.append("  ;\n");

        this.outADeclConst(node);
    }
    @Override
    public void outADeclConst(ADeclConst node) {
        // After the declaration is finished then we get the type and add to the table
        String name = node.getIdentifier().getText().strip();
        table.add(name, new Symbol(name, node.getType().toString()));
        ElipLogger.debug(table.get(name).toString());
    }


    @Override
    public void caseACallExp(ACallExp node) {
        inACallExp(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        body.append("(");
        {
            List<PExp> copy = new ArrayList<PExp>(node.getArgs());
            int len = copy.size();

            for (int i = 0; i <  len; i++) {
                PExp e = copy.get(i);
                
                e.apply(this);

                if (i != len -1) {
                    body.append(", ");
                }
            }
        }
        body.append(")");
        outACallExp(node);
    }
    
     String fromSignatureToCType(Symbol.Signature s,String func_name) {
        Symbol.Signature signature = s;
        StringBuilder sb = new StringBuilder();
        sb.append( C.get(signature.getReturnType().toString()));
        if(func_name != null){
            sb.append("(*" + func_name + ")");
        }
        else {
            sb.append("(*)");
        }
        sb.append("(");
        List<Symbol.SignatureParam> parameters = signature.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            Symbol.SignatureParam param = parameters.get(i);
            if (i > 0) {
                sb.append(", ");
            }
            if (param.isSignature()) {
                Symbol.Signature param_sig = param.getSignature();
                sb.append(fromSignatureToCType(param_sig,null));
            } 
            else {
                sb.append(C.get(param.getType().toString()));
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void inALambdaExp(ALambdaExp node) {

        table.enterScope();
        ElipLogger.debug("lambda entered scope");
    }

    @Override 
    public void 
    caseALambdaExp(ALambdaExp node) {
        this.inALambdaExp(node);
        //lambda_temp = header; 
        //header = def_header;
        String lambda_name = new String("lambda_" + flags.lambda_count++);

        lambda_return.push(lambda_name);
        IndentedStringBuilder block = new IndentedStringBuilder(body); 

        List<TIdentifier> ids = new ArrayList<TIdentifier>(node.getId());
        List<PExp> args = new ArrayList<PExp>(node.getArgs());


        IndentedStringBuilder temp = body;
        IndentedStringBuilder void_block = new IndentedStringBuilder(body); 
        body = void_block;
        for(TIdentifier e : ids)
        {
            e.apply(this);
        }
        for(PExp e : args)
        {
            e.apply(this);
        }
        body = temp;

        block
            .append("\n")
            .pushIndex()
            .append("\n")
            .append("{\n")
            .pushIndent()
        ;


        
        temp = body;
        body = block;
        int len = args.size();
        boolean apply_id = true;

        for (int i = 0; i < len; i++) {
            TIdentifier id = ids.get(i); 
            PExp arg = args.get(i); 
            String ctype = "float";

            Symbol.Type type;
            Symbol identifier_symbol = inference.getSymbolOrNull(arg);
            if (identifier_symbol != null) {
                table.add(id.getText(), new Symbol(identifier_symbol));
                ElipLogger.debug(" Lambda -> added id "+ id + " from already defined symbol :" + identifier_symbol);
            }
            else {
                type = inference.getType(arg); 
                table.add(id.getText(), new Symbol(id.getText(), type));
                ElipLogger.debug(" Lambda -> added id "+ id + " with type" + type);
            }

            if(table.exists(id.getText())){
                Symbol s = table.get(id.getText());
                if (s.isFunction()){
                    ctype = fromSignatureToCType(s.getSignature(),sanitize(id.getText()));
                    ElipLogger.debug(" [C] >> " + id.getText()+ " does exist in table and its a function" + ctype);
                    apply_id = false;
                }
                else {
                    ctype = C.get(s.getType().toString().strip());
                    ElipLogger.debug(" [C] >> " + id.getText()+ " does exist in table and its not a function");
                }
            }
            else {
                ctype = C.get(inference.getType(arg).toString().strip());
                ElipLogger.debug(" [C] >> " + id.getText()+ " does not exist in table");
            }

            block.append(ctype + " ");
            if(apply_id){
                id.apply(this);
            }
            block.append( " = " );
            arg.apply(this);
            block.append( " ;\n" );
        }

        PExp exp = node.getBody();
        if(exp != null) {
            block.append( lambda_name + " = "  );   
            exp.apply(this);
        }
        block.append(";\n")
            .popIndent()
            .append("}\n");

            
        Type type = inference.getType(exp);
        String lambda_type = C.get(type.toString());
        ElipLogger.debug("from c we got the lambda type as " + lambda_type + 
            " original type is " + type);

        body = temp;
        block 
            .insertAtCurrentIndex(lambda_type  + " " + lambda_name + ";")
            .popIndex()
        ;

        body.insertAtBeginningOfPreviousLine(
            block.toString()
        );
        body.append(lambda_return.pop());
        this.outALambdaExp(node);
       
    }

    @Override
    public void outALambdaExp(ALambdaExp node) {
       ElipLogger.debug("lambda exited scope");
       table.exitScope(); 
    }
    // << [Exp]
    

    // >> [Param]
    @Override
    public void inATypeParam(ATypeParam node) {
        if (node.getIdentifier() == null) {
            return;
        }
        String name = node.getIdentifier().getText();
        assert !table.existsInGlobalScope(name);
        table.add(name, new Symbol(name, node.getType().toString()));
    }

    @Override
    public void caseATypeParam(ATypeParam node)
    {
        this.inATypeParam(node);
        body.append(" ");
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        body.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        this.outATypeParam(node);
    }

    @Override
    public void caseASignatureParam(ASignatureParam node)
    {
        inASignatureParam(node);
        if(node.getSignature() != null)
        {
            node.getSignature().apply(this);
        }
        outASignatureParam(node);
    }

    @Override
    public void inASignature(ASignature node) {
        if (node.getIdentifier() == null) {
            return;
        }
        String name = node.getIdentifier().getText();
        assert !table.existsInGlobalScope(name);

        table.add(name, new Symbol(name, node.getType().toString(), node.getSignatureParam(), true));
        ElipLogger.debug(table.get(name).getSignature().toString());
    }

    @Override
    public void caseASignature(ASignature node) {
        this.inASignature(node);

        body.append(" ");
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }

        body.append(" (*");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        body.append(")(");
        {
            List<PSignatureParam> copy = new ArrayList<PSignatureParam>(node.getSignatureParam());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PSignatureParam e = copy.get(i);
                e.apply(this);
                if (i != len -1)
                    body.append(", ");
            }
        }
        body.append(")");
        this.outASignature(node);
    }

    // << [Param]

    @Override public void 
    // This is redundant since is already garanteed by semantic analisys
    caseTKwEntry(TKwEntry node) {
        if (! flags.entry_found) {
            flags.entry_found = true;
        }
        else {
            throw new Error("More than one entry function");
        }
    }
    // >> [Function Declaration]
    @Override
    public void inADeclFunc(ADeclFunc node) {
        String name = node.getIdentifier().getText(); 
        table.add(name, new Symbol(
            name, node.getType().toString(), node.getParam() 
        ));
        assert table.existsInGlobalScope(name);
        table.enterScope();
    } 

    @Override
    public void caseADeclFunc(ADeclFunc node) {
        this.inADeclFunc(node);
        boolean is_entry = node.getKwEntry() != null;
        String id =  sanitize(node.getIdentifier());
        PType return_type = node.getType();
        String type = this.C.get(return_type.toString().strip());

        if (is_entry) {
        
            footer.append(
                "int main(int argc, char *argv[]) {\n"
            + "    setlocale(LC_ALL, \"fr_FR.UTF-8\");\n"
            + "    char *arg;\n"
            + "    size_t arg_len;\n"
            );
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            int argc = copy.size();
            footer.append(
                  "   if (argc <" + (argc+1) + ") {\n"
                + "       printf(\"%s expects " + argc + " arguments from cmd line\\n\", argv[0]);\n"
                + "       printf(\"The expected types are "
            );
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                footer.append(e.toString().strip());
                if (i != len -1)
                    footer.append(", ");
            }
            footer.append(" \");\n");
            footer.append("        return 1;\n    }");

            
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                setUpArgv(e, footer, i+1);
            }
            if (type.equals("float")){
                footer.append(
                  "\n    printf(\"%f\\n\",((float)" + id + "("
                );   
            }
            else if (type.equals("int")){
                footer.append(
                  "\n    printf(\"%d\\n\",((int)" + id + "("
                );   
            }
            else if (type.equalsIgnoreCase("bool")) {

                footer.append(
                  "\n    printf(\"%s\\n\",("+ id + "("
                );   
            }

            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                String[] data = e.toString().split(" ");
                String arg_id  = data[1].strip();
                footer.append(arg_id);
                if (i != len -1)
                    footer.append(" ,");
            }
            if (type .equals("bool")){
                footer.append(
                  "))"
                  +" ? \"verdadeiro\" : \"falso\" );\n"
                  + "}\n"
                );        
            }
            else {
                footer.append(
                  ")));\n"
                  + "}\n"
                );        
            }
        }

        if (type == null) {
            String method_name = new Object(){}.getClass().getEnclosingMethod().getName();
            throw new Error("Type when converted from " + return_type.toString() + " resulted in  null in "+ method_name);
        }

        // Define the function on Header to use functions outta order   

        header.append("\n");
        IndentedStringBuilder temp = body;
        body = header;
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        header.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }

        header.append("(");
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                e.apply(this);
                if (i != len -1)
                    header.append(", ");
            }
        }

        header.append(");");
        
        body = temp;
        // Define the function on code body
        if(node.getKwEntry() != null)
        {
            node.getKwEntry().apply(this);
        }
        body.append("\n");
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        body.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }

        body.append("(");
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                e.apply(this);
                if (i != len -1)
                    body.append(",");
            }
        }

        body.append(") ");

        body.pushIndent();
        body.append(
              " {\n"
        );

        body.append( 
              type + " return_data = "
        );

        PExp exp = node.getExp();
        if(exp != null)
        {
            exp.apply(this);
        }
        body = temp;
        Stack<IndentedStringBuilder> reversed = new Stack<>();        
        while(!blocks.empty()) {
            IndentedStringBuilder sb = blocks.pop();
            reversed.push(sb);
        }
        while(!reversed.empty()) {

            IndentedStringBuilder sb = reversed.pop();
            body.append(sb.toString());
        } 
        body.append(";\nreturn (return_data);");

        body.popIndent();
        body.append("\n}");
        this.outADeclFunc(node);
    }

    @Override
    public void outADeclFunc(ADeclFunc node) {
        table.exitScope();
        flags.block_count = 0;
        flags.if_count  = 0;
        flags.lambda_count = 0;
    }

    // << [Function Declaration]
    
    public void defaultIn(Node node) {
       //code.append(node.toString());
        //System.out.println(node.toString());
    }

    /*
    * As we leave a non terminal, it's parent is the node before it
    * on the stack, so we pop it off and add it to that node
    */
    @Override
    public void defaultOut(Node node) {
        if(node  instanceof PExp ) {
            inference.getType((PExp)node);
            // check for something general about exp
        }
    }


    public void caseEOF(EOF node) {
        footer.append("\n//*** EOF ***//\n");
        code.writeCode();
    }

}
