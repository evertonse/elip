
package elipses.walker;
import elipses.analysis.*;
import elipses.node.*;
import elipses.semantic.SemanticFlags;
import elipses.util.*;
import java.util.*;
import java.io.*;

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
            System.out.print(exc);
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
    CCodeData code;
    SemanticFlags flags;
    IndentedStringBuilder header;
    IndentedStringBuilder body;
    IndentedStringBuilder footer;
    String filename;
    String curr_block;

    Stack<String> block_return = new Stack<>();

    Stack<IndentedStringBuilder > blocks = new Stack<>();

    int lambda_count = 0;
    private Map<String,String> C;

    public CCodeGenerator(String out_filename) {
        blocks.push(new IndentedStringBuilder());
        filename = new File(out_filename)
            .getName()
            .replaceAll("[^a-zA-Z_]", "_");

        code = new CCodeData(out_filename);
        header = code.header;
        body = code.body;
        footer = code.footer;
        flags = new SemanticFlags();

        C = new HashMap<>();
        C.put("inteiro", "int") ;
        C.put("booleano", "bool") ;
        C.put("real", "float") ;
        C.put("verdadeiro", "true") ;
        C.put("falso", "false") ;
        addDefaultBoilerPlate();
            
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

            +"// Define an enum for boolean values\n"
            +"typedef enum {\n"
            +"    false = 0,\n"
            +"    true = 1\n"
            +"} bool;\n\n"
        );

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

    @Override
    public void caseAEqExp(AEqExp node) {
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
    public void caseABlockExp(ABlockExp node) {
        inABlockExp(node);
        String block_name = new String("block_" + flags.block_count++);
        block_return.push(block_name);

        IndentedStringBuilder block = new IndentedStringBuilder(body); 

        // We might use this strategy of remembering the return site
        // instead of assuming its safe to write on previous line
        int return_index = body.size();

        block.append("\nfloat " + block_name + ";\n")
            .append( "{\n")
            .pushIndent();
        
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
            boolean is_block = exp  instanceof ABlockExp;

            block.append( block_name + " = "  );   
            exp.apply(this);

            block.append(";\n")
                .popIndent()
                .append("}\n");
        }

        body.append( "/*escrevo no block*/" );
        body = temp;
        body.append( "/*escrevo no body*/" );
        body.insertAtPreviousLine(block.toString());
        body.append(block_return.pop());
        
        
        outABlockExp(node);
    }

    public void caseADeclConst(ADeclConst node) {
        inADeclConst(node);

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

        outADeclConst(node);
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

    @Override
    public void caseALambdaExp(ALambdaExp node) {
        flags.lambda_count++;
        String lambda = "lambda_" + filename + "_" +  flags.lambda_count;
        header.append(
            "\n#define " 
            + lambda
            + "("
        );

        inALambdaExp(node);
        {
            List<TIdentifier> copy = new ArrayList<TIdentifier>(node.getId());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                TIdentifier e = copy.get(i); 
                //e.apply(this);
                header.append(sanitize(e));
                if (i != len -1) {

                    header.append(", ");
                }
            }
        }

        header.append(") (");

        if(node.getBody() != null)
        {
            IndentedStringBuilder temp = body;
            body = header;
            node.getBody().apply(this);
            body = temp;

        }

        header.append(" )");
        body.append(" (");
        body.append(lambda);
        body.append("(");
        {
            List<PExp> copy = new ArrayList<PExp>(node.getArgs());
            int len = copy.size();
            for (int i = 0; i < len; i++) {

               PExp e = copy.get(i);
                e.apply(this);
                if (i != len -1) {
                   body.append(",");
                }
            }
        }
        body.append(")");
        body.append(") ");
        outALambdaExp(node);
    }

    // << [Exp]
    

    // >> [Param]
    @Override
    public void caseATypeParam(ATypeParam node)
    {
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
    public void caseASignature(ASignature node) {
        inASignature(node);

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
        outASignature(node);
    }

    @Override
    public void caseATypeSignatureParam(ATypeSignatureParam node) {
        // TODO Auto-generated method stub
        super.caseATypeSignatureParam(node);
    }

    @Override
    public void caseASignatureSignatureParam(ASignatureSignatureParam node) {
        // TODO Auto-generated method stub
        super.caseASignatureSignatureParam(node);
    }

    // << [Param]

    @Override
    public void caseTKwEntry(TKwEntry node) {
        if (! flags.entry_found) {
            flags.entry_found = true;
        }
        else {
            throw new Error("More than one entry function");
        }
    }
    
    @Override
    public void caseADeclFunc(ADeclFunc node) {

        boolean is_entry = node.getKwEntry() != null;
        String id =  sanitize(node.getIdentifier());
        PType return_type = node.getType();
        String type = this.C.get(return_type.toString().strip());

        if (is_entry) {
        
            footer.append(
                "int main(int argc, char *argv[]) {\n"
            + "    setlocale(LC_ALL, \"fr_FR.UTF-8\");\n"
            + "    char *arg;\n"
            + "    int arg_len;\n"
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
        outADeclFunc(node);
    }

    
    public void defaultIn(Node node) {
       //code.append(node.toString());
        //System.out.println(node.toString());
    }

    /*
    * As we leave a non terminal, it's parent is the node before it
    * on the stack, so we pop it off and add it to that node
    */
    public void defaultOut(Node node) {
    }

    /*
    * Terminals - our parent is always on the top of the stack, so we
    * add ourselves to it
    */
    public void defaultCase(Node node) { }

    public void caseEOF(EOF node) {
        footer.append("\n//*** EOF ***//\n");
        code.writeCode();
    }

}
