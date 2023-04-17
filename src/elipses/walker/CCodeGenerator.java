package elipses.walker;

import elipses.analysis.*;
import elipses.node.*;
import java.util.*;
import java.io.*;
import java.util.HashMap;


public class CCodeGenerator extends DepthFirstAdapter {
    StringBuffer header = new StringBuffer();
    StringBuffer code = new StringBuffer();
    boolean entry_found = false;
    int lambda_count = 0;
    private Map<String,String> C;

    private Stack parents = new Stack();
    private PrintWriter out;
 
    public CCodeGenerator(String out_filename) {
        C = new HashMap<>();
        C.put("inteiro", "int") ;
        C.put("booleano", "bool") ;
        C.put("real", "float") ;
        C.put("verdadeiro", "true") ;
        C.put("falso", "false") ;
        System.out.println("printing from contructor" + C.get("real"));
        addDefaultBoilerPlate();
        try{
            out = new PrintWriter(out_filename + ".c" );
        }
        catch(IOException exc) {
            System.out.print(exc);
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
            "#define OP_MULT(A,B) ((A)*(B))\n"
            +"#define OP_ADD(A,B) ((A)+(B))\n"
            +"#define OP_DIV(A,B) ((A)/(B))\n"

            +"// Define an enum for boolean values\n"
            +"typedef enum {\n"
            +"  false = 0,\n"
            +"  true = 1\n"
            +"} bool;\n\n"
        );

        code.append(
            "int main(){\n"
        );
    }

    public String getHeader(){
        return this.header.toString();
    }
    public String getCode(){
        return this.code.toString();
    }

    public void outStart(Start node) {


    }

    public void writeCode() {
        out.print(getHeader() + '\n');
        out.print(getCode());
        out.flush();
        out.close(); 
    }
    
    @Override
    public void caseAOrExp(AOrExp node) {
        // TODO Auto-generated method stub
        inAOrExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("||"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
        outAOrExp(node);
    }
    @Override
    public void caseAAndExp(AAndExp node) {
        code.append("("); 
        inAAndExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("&&"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
        outAAndExp(node);
    }

    @Override
    public void caseAEqExp(AEqExp node) {
        inAEqExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append(" == "); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqExp(node);
        code.append(")"); 
    }
   
    @Override
    public void caseALtExp(ALtExp node) {
        inALtExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("<"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALtExp(node);
        code.append(")"); 
    }


    @Override
    public void caseAGtExp(AGtExp node) {
        inAGtExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append(">"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
        outAGtExp(node);
    }
    @Override
    public void caseAPlusExp(APlusExp node)
    {
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append(" + ");
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
    }

    @Override
    public void caseAMinusExp(AMinusExp node) {
        inAMinusExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("-"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
        outAMinusExp(node);
    }

    @Override
    public void caseAMultExp(AMultExp node)
    {
        
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("*"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
    }

    @Override
    public void caseADivExp(ADivExp node) {
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("/"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        code.append(")"); 
    }

    
    @Override
    public void caseAModExp(AModExp node) {
        inAModExp(node);
        code.append("("); 
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        code.append("%"); 
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAModExp(node);
    }

    @Override
    public void caseANegativeExp(ANegativeExp node) {
            inANegativeExp(node);
            code.append("-("); 
            if(node.getExp() != null)
            {
                node.getExp().apply(this);
            }
            code.append(")"); 
            outANegativeExp(node);
    }
    @Override
    public void caseANotExp(ANotExp node) {
        inANotExp(node);
        code.append("!(");
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        code.append(")");
        outANotExp(node);
    }
    @Override
    public void caseAIfExp(AIfExp node) {
        inAIfExp(node);

        code.append("(");
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
        }

        code.append("?");
        if(node.getTruthy() != null)
        {
            node.getTruthy().apply(this);
        }
        code.append(":");
        if(node.getFalsy() != null)
        {
            node.getFalsy().apply(this);
        }
        code.append(")");
        outAIfExp(node);
    }

    @Override
    public void caseAIdExp(AIdExp node) {
        super.caseAIdExp(node);
    }

    @Override
    public void caseTKwReal(TKwReal node) {
        code.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTKwBool(TKwBool node) {
        super.caseTKwBool(node);
        code.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTKwInteger(TKwInteger node) {
        code.append(C.get(node.getText().strip()));
    }

    @Override
    public void caseTIdentifier(TIdentifier node) {
        code.append(sanitize(node));
    }

    @Override
    public void inABinExp(ABinExp node) {
        
        code.append(node.getNumberBin().getText());
        
    }

    @Override
    public void inARealExp(ARealExp node) {
        code.append(
            node.getNumberFrac()
                .getText()
                .replace(',', '.')
        );
    }

    @Override
    public void inAIntExp(AIntExp node) {
        code.append(node.getNumberInt().getText());
    }

    @Override
    public void caseATrueExp(ATrueExp node)
    {
        code.append("true");
    }

    @Override
    public void caseAFalseExp(AFalseExp node)
    {
        code.append("false");
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
        code.append("(");
        {
            List<PExp> copy = new ArrayList<PExp>(node.getArgs());
            int i = 0;
            for(PExp e : copy)
            {
                e.apply(this);
                i++;
                if (i != copy.size()-1)
                    code.append(',');
            }
        }
        code.append(")");
        outACallExp(node);
    }

    @Override
    public void caseALambdaExp(ALambdaExp node) {
        lambda_count++;
        String lambda = "lambda_" + lambda_count;
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
                e.apply(this);
                header.append(sanitize(e));
                if (i != len -1) {

                    header.append(",");
                }
            }
        }

        header.append(") (");

        if(node.getBody() != null)
        {
            StringBuffer temp = code;
            code = header;
            node.getBody().apply(this);
            code = temp;

        }

        header.append(" )");
        code.append(" (");
        code.append(lambda);
        code.append("(");
        {
            List<PExp> copy = new ArrayList<PExp>(node.getArgs());
            int len = copy.size();
            for (int i = 0; i < len; i++) {

               PExp e = copy.get(i);
                e.apply(this);
                if (i != len -1) {
                   code.append(",");
                }
            }
        }
        code.append(")");
        code.append(") ");
        outALambdaExp(node);
    }

    @Override
    public void caseATypeParam(ATypeParam node)
    {
        code.append(" ");
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        code.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
    }

    @Override
    public void caseTKwEntry(TKwEntry node) {
        if (! entry_found) {
            entry_found = true;
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
            code.append("\n  " + id + "();\n"
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
        code.append('\n');
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        code.append(" ");
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }

        code.append(" ( ");
        {
            List<PParam> copy = new ArrayList<PParam>(node.getParam());
            int len = copy.size();
            for (int i = 0; i < len; i++) {
                PParam e = copy.get(i); 
                e.apply(this);
                if (i != len -1)
                    code.append(",");
            }
        }

        code.append(") ");

        code.append(
            " {\n"
            +"  return ("
        );

        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }

        code.append(");\n}");
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
        code.append("\n//*** EOF ***//\n");
        this.writeCode();
        
    }

}
