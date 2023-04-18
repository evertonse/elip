
package elipses.walker;
import elipses.analysis.*;
import elipses.node.*;
import java.util.*;
import java.io.*;
import elipses.util.ElipLogger;
import java.util.HashMap;

class CCodeData {

    private PrintWriter out;

    public StringBuffer header = new StringBuffer();
    public StringBuffer body   = new StringBuffer();
    public StringBuffer footer = new StringBuffer();
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

class SemanticFlags {
    public boolean entry_found = false;
    public int lambda_count = 0;
};

public class CCodeGenerator extends DepthFirstAdapter {
    CCodeData code;
    SemanticFlags flags;
    StringBuffer header;
    StringBuffer body;
    StringBuffer footer;

    int lambda_count = 0;
    private Map<String,String> C;

    public CCodeGenerator(String out_filename) {
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

    public void setUpArgv(PParam e, StringBuffer buffer, int argv_index) {
        
        String[] data = e.toString().split(" ");
        String type = C.get(data[0].strip()) ;
        String id = data[1].strip();
        buffer.append("\n    arg = argv["+ argv_index +"];\n");
        buffer.append("\n    arg_len = strlen(arg);\n");

        if (type == "int") {
            buffer.append(
                "    " + type+ " " + id + " = " + "atoi(arg);\n"
            ); 
        }
        else if (type == "float") {
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
        else if (type == "bool") {
            buffer.append(
                  "    for (int i = 0; arg[i]; i++) {\n"
                + "        arg[i] = tolower(arg[i]);"
                + "    \n}"
            );
            buffer.append(type+ " " + id + ";");
            buffer.append( 
                  "    if (strcmp(arg, \"falso\") == 0) {\n"
                + "        "+ id + " = false;\n"
                + "    } else if (strcmp(arg, \"verdadeiro\") == 0) {\n"
                + "        " + id + "= true;\n"
                + "    } else {\n"
                + "        printf(\"Invalid boolean value: %s\n\", arg);\n"
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

            +"// Define an enum for boolean values\n"
            +"typedef enum {\n"
            +"    false = 0,\n"
            +"    true = 1\n"
            +"} bool;\n\n"
        );

        footer.append(
            "int main(int argc, char *argv[]) {\n"
          + "    char *arg;\n"
          + "    int arg_len;\n"
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

    
    @Override
    public void caseAOrExp(AOrExp node) {
        // TODO Auto-generated method stub
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
        outAModExp(node);
    }

    @Override
    public void caseANegativeExp(ANegativeExp node) {
            inANegativeExp(node);
            body.append("-("); 
            if(node.getExp() != null)
            {
                node.getExp().apply(this);
            }
            body.append(")"); 
            outANegativeExp(node);
    }
    @Override
    public void caseANotExp(ANotExp node) {
        inANotExp(node);
        body.append("!(");
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        body.append(")");
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
        super.caseTKwBool(node);
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
        // TODO Auto-generated method stub
        super.caseABlockExp(node);
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
            int i = 0;
            for(PExp e : copy)
            {
                e.apply(this);
                i++;
                if (i != copy.size()-1)
                    body.append(',');
            }
        }
        body.append(")");
        outACallExp(node);
    }

    @Override
    public void caseALambdaExp(ALambdaExp node) {
        flags.lambda_count++;
        String lambda = "lambda_" + flags.lambda_count;
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
            StringBuffer temp = body;
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
    public void caseTKwEntry(TKwEntry node) {
        if (! flags.entry_found) {
            flags.entry_found = true;
        }
        else {
            throw new Error("More than one entry function");
        }
    }
    
    @Override
    public void caseADeclFunc(ADeclFunc node)
    {

        boolean is_entry = node.getKwEntry() != null;
        String id =  sanitize(node.getIdentifier());
        PType return_type = node.getType();
        
        if (is_entry) {
        
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

            footer.append(
                ""
              + "\n  " + id + "("
            );   
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                String[] data = e.toString().split(" ");
                String arg_id  = data[1].strip();
                footer.append(arg_id);
                if (i != len -1)
                    footer.append(" ,");
            }
            footer.append(
              ");\n"
              + "}\n"
            );        
        }

        String type = this.C.get(return_type.toString().strip());
        if (type == null) {
            String method_name = new Object(){}.getClass().getEnclosingMethod().getName();
            throw new Error("Type when converted from " + return_type.toString() + " resulted in  null in "+ method_name);
        }


        if(node.getKwEntry() != null)
        {
            node.getKwEntry().apply(this);
        }
        body.append('\n');
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        body.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }

        body.append(" ( ");
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

        body.append(
            " {\n"
            +"  return ("
        );

        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }

        body.append(");\n}");
        outADeclFunc(node);
    }

    
    public void defaultIn(Node node) {
       //code.append(node.toString());
        System.out.println(node.toString());
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
