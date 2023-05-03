package elipses.walker;

import elipses.analysis.*;
import elipses.node.*;
import elipses.semantic.SemanticFlags;
import elipses.semantic.SymbolTable;
import elipses.util.ElipLogger;
import elipses.semantic.*;

import java.util.*;

enum SemanticErrorType {
    DUPLICATE_ENTRY("Duplicate entry"),
    ALREADY_DECLARED("Identifier already declared in this scope"),
    UNDECLARED_VARIABLE("Undeclared variable"),
    DUPLICATE_VARIABLE("Duplicate variable"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    UNDEFINED_FUNCTION("Undefined function"),
    DUPLICATE_FUNCTION("Duplicate function"),
    INCORRECT_NUMBER_OF_ARGUMENTS("Incorrect number of arguments"),
    INVALID_ASSIGNMENT("Invalid assignment"),
    INVALID_OPERATOR("Invalid operator"),
    INVALID_EXPRESSION("Invalid expression"),
    INVALID_STATEMENT("Invalid statement");

    private final String message;
    
    private SemanticErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class SemanticError {
    SemanticErrorType type;
    public static String filename;
    String custom_msg;
    int line, pos;
    Token node;
   
    public SemanticError(SemanticErrorType error_type, Token node) {
        this.type = error_type;
        this.node = node;
    }


    public SemanticError (SemanticErrorType error_type, Token node, String custom_msg) {
        this.type = error_type;
        this.node = node;
        this.custom_msg = custom_msg;
    }

    public void inform() {
       ElipLogger.error(filename, node.getLine(), node.getPos(), type.getMessage() + " " + ((custom_msg != null) ? custom_msg:""));
    }
}

public class SemanticAnalysis extends DepthFirstAdapter {
    List<SemanticError> errors = new ArrayList<>();
    SemanticFlags flags = new SemanticFlags();
    SymbolTable table = new SymbolTable();
    
    String first_entry_msg;
    boolean all_ok  = true;

    String filename; 

    public boolean allGood() {
        return all_ok;
    }

    public SemanticAnalysis(String filename) {
        this.filename = filename;
        SemanticError.filename = filename;
    }

    @Override
    public void inADeclFunc(ADeclFunc node) {
        // TODO add this to the global scope table.add()
        table.enterScope();

        TKwEntry entry = node.getKwEntry();
        if (entry != null) {
            if (flags.entry_found == false) {
                flags.entry_found = true;

                first_entry_msg =
                    "\n\t First defined in line " 
                    + entry.getLine() 
                    + " position " + entry.getPos()
                    + " on function " + node.getIdentifier() + node.getParam()
                ;
            } 
            else {
                this.errors.add(new SemanticError(
                    SemanticErrorType.DUPLICATE_ENTRY, entry, first_entry_msg
                ));
                all_ok = false;
            }

        }
    };
    
    @Override
    public void outADeclConst(ADeclConst node) {

        table.exitScope();
    }

    @Override
    public void inATypeParam(ATypeParam node) {
        if (node.getIdentifier() == null) {
            return;
        }

        String name = node.getIdentifier().getText();
        if (table.exists(name)) {
           errors.add(new SemanticError(
            SemanticErrorType.ALREADY_DECLARED, 
            node.getIdentifier(), name)); 
        }
        else {
            table.add(name, new Symbol(name, node.getType()));

        }
    }

    @Override
    public void defaultIn(Node node) {
        if(node  instanceof PExp ) {
            // check for something general about exp
        }
    }
    
    @Override
    public void inABlockExp(ABlockExp node) {
        table.enterScope();
    }    

    @Override
    public void outABlockExp(ABlockExp node) {
        table.exitScope();
    }    

    @Override
    public void inADeclConst(ADeclConst node) {
        assert node.getIdentifier() != null;

        String name = node.getIdentifier().getText();
        
        if (table.existsInCurrentScope(name)) {
            System.out.print(name);
            errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, node.getIdentifier(),
                "on '" + node.toString() + "'"
                ));
            return;
        }

        table.add( new Symbol(name, node.getType()));
    }

    @Override
    public void caseEOF(EOF node) {
        
        for (SemanticError e : errors) {
            e.inform();
        }
        if (!allGood()){
            System.exit(1);
        } 
        
    }
    
    
}
