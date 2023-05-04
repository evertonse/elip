package elipses.walker;

import elipses.analysis.*;
import elipses.node.*;
import elipses.semantic.SemanticFlags;
import elipses.semantic.SymbolTable;
import elipses.semantic.Symbol.Type;
import elipses.util.ElipLogger;
import elipses.semantic.*;

import java.lang.reflect.Method;
import java.util.*;

enum SemanticErrorType {
    DUPLICATE_ENTRY("Duplicate entry"),
    ALREADY_DECLARED("Identifier already declared in this scope"),
    UNDECLARED("Use of undeclared indentifier"),
    UNDEFINED_FUNCTION("Undefined function"),
    DUPLICATE_VARIABLE("Duplicate variable"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    INCOMPATIBLE_RETURN_TYPE("Incompatible Return Type"),
    DUPLICATE_FUNCTION("Duplicate function"),
    INCORRECT_NUMBER_OF_ARGUMENTS("Incorrect number of arguments"),
    INCORRECT_USE_OF_ARGUMENTS("Incorrect use of arguments in a function call"),
    INVALID_OPERATOR("Invalid operator"),
    INVALID_EXPRESSION("Invalid expression"),
    INVALID_STATEMENT("Invalid statement");

    private final String message;
    
    private SemanticErrorType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return " " + message + " ";
    }
}

class SemanticError {
    SemanticErrorType type;
    public static String filename;
    String custom_msg;
    int line, pos;
    Token node;

    public SemanticError(SemanticErrorType error_type) {
        this.type = error_type;
    }

    public SemanticError(SemanticErrorType error_type, String custom_msg) {
        this.type = error_type;
        this.custom_msg = custom_msg;
    }

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
        if (node != null){
            ElipLogger.error(filename, node.getLine(), node.getPos(), type.getMessage() + " " + ((custom_msg != null) ? custom_msg:""));
        }
        else {

            ElipLogger.error(filename + type.getMessage() + " " + ((custom_msg != null) ? custom_msg:""));
        }
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
 
   // >> [EXP]
    @Override
    public void inACallExp(ACallExp node) {
        String name = node.getId().getText();
        Token t = node.getId();
            
        if (!table.exists(name)) {
            errors.add(new SemanticError(SemanticErrorType.UNDECLARED, t, " trying to call undeclared function " + node.getId()));
            // need to end right here;
            // can't stablish anything else
            caseEOF(null);
            //System.exit(1);
        }

        if (!isCorrectUseOfArgs(name,node.getArgs())) {
            errors.add(new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS, t, "" + node.getId()));
        }

        // TODO check arguments passed with the function definition in case
        // the function is defined
        
    }

   // << [EXP]

    @Override
    public void inADeclFunc(ADeclFunc node) {
        String name = node.getIdentifier().getText(); 

        boolean ok = table.add(name, new Symbol(
            name, node.getType().toString(), node.getParam() 
        ));

        if (!ok) {
            errors.add(new SemanticError(SemanticErrorType.ALREADY_DECLARED, node.getIdentifier(), " error on " + node.toString())); 
        }
        assert table.existsInGlobalScope(name);

        Symbol s = table.get(name);
        
        ElipLogger.debug( name + " signature -> "  + s.getSignature());

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
    public void outADeclFunc(ADeclFunc node) {
        // This check needs to be down on outNode,
        // Since it need to compute various types
        String name = node.getIdentifier().getText(); 
        // Check for expression and return
        Symbol.Type got = getType(node.getExp());
        Symbol.Type expect = table.get(name).getType();
        if ( got != expect  ) {
           errors.add(new SemanticError(
                SemanticErrorType.INCOMPATIBLE_RETURN_TYPE, node.getIdentifier(), 
                " on " + name + " expected: " + expect + " got: " + got 
            )); 
        }
        table.exitScope();
    }

    @Override
    public void inALambdaExp(ALambdaExp node) {
        List<PExp> args = node.getArgs();
        List<TIdentifier> ids  = node.getId();
        int ids_count = ids.size();
        int args_count = args.size();
        int count = ids_count > args_count ? args_count : ids_count;

        if (args_count != ids_count) {
            if (ids_count > 0) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCORRECT_NUMBER_OF_ARGUMENTS, ids.get(0),
                    "on lambda expectd: " + ids_count + " got: " + args_count 
                ));
            }
            else {
                errors.add(new SemanticError(
                    SemanticErrorType.INCORRECT_NUMBER_OF_ARGUMENTS,
                    "on lambda expectd: " + ids_count + " got: " + args_count 
                ));
            }
        }

        table.enterScope();
        // We need to add the ids and type on next scope
        // so this need to me on inNode and not outNode, ok?
        for (int i = 0; i < count; i++) {
           String id = ids.get(i).getText(); 
           Symbol.Type type = getType(args.get(i)); 
            if (table.existsInCurrentScope(id)) {
                errors.add(new SemanticError(
                    SemanticErrorType.ALREADY_DECLARED,ids.get(i),
                    "" + node.toString()
                ));
            }
            else {
                table.add(id, new Symbol(id, type));
            }
        }
    }

    @Override
    public void outALambdaExp(ALambdaExp node) {
       table.exitScope(); 
    }

    @Override
    public void inASignature(ASignature node) {

        if (node.getIdentifier() == null) {
            return;
        }

        Token t = node.getIdentifier();
        String name = node.getIdentifier().getText();
        assert !table.existsInGlobalScope(name);

        if (table.exists(name)) {
           errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, t, " on parameter " + node.toString()
            )); 
        }
        else {
            table.add(name, new Symbol(name, node.getType().toString(), node.getSignatureParam(), true));
            ElipLogger.debug(table.get(name).getSignature().toString());
        }
    }

    @Override
    public void inATypeParam(ATypeParam node) {
        if (node.getIdentifier() == null) {
            return;
        }

        
        Token t = node.getIdentifier();
        String name = node.getIdentifier().getText();

        assert !table.existsInGlobalScope(name);

        if (table.exists(name)) {
           errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, t, " on parameter " + node.toString()
            )); 
        }
        else {
            table.add(name, new Symbol(name, node.getType().toString()));

        }
    }

    @Override
    public void defaultIn(Node node) {
        if(node  instanceof PExp ) {
            getType((PExp)node);
            // check for something general about exp
        }
    }
    
    @Override
    public void inABlockExp(ABlockExp node) {
        // Is the resposability of whoever need blockexp to
        // make shure it's the correct type
        ElipLogger.debug("enteredd scope in a block");
        table.enterScope();
    }    

    @Override
    public void outABlockExp(ABlockExp node) {
        //table.printAllScopes();
        table.exitScope();
        ElipLogger.debug("exited scope in a block");
    }    

    @Override
    public void inADeclConst(ADeclConst node) {
        assert node.getIdentifier() != null;

        String name = node.getIdentifier().getText().strip();
        
        boolean exist = table.existsInCurrentScope(name);

        ElipLogger.debug(name + exist );
        if (exist) {
            errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, node.getIdentifier(),
                "on '" + node.toString() + "'"
                ));
            return;
        }

    }

    @Override
    public void outADeclConst(ADeclConst node) {
        // After the declaration is finished then we get the type and add to the table
        String name = node.getIdentifier().getText().strip();
        table.add(name, new Symbol(name, node.getType().toString()));
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

    public Symbol.Type getType(PExp node) {
        // Atomic
        if (node instanceof ATrueExp || node instanceof AFalseExp) {
           return Symbol.Type.BOOL;
        }

        else if (node instanceof ABinExp || node instanceof AIntExp) {
            return Symbol.Type.INT;
        }

        else if (node instanceof ARealExp) {
            return Symbol.Type.REAL;
        }

        else if(node instanceof AIdExp) {
            String name = ((AIdExp)node).getIdentifier().getText();
            if(!table.exists(name)) {
                Token t = ((AIdExp)node).getIdentifier();
                errors.add(new SemanticError(
                    SemanticErrorType.UNDECLARED,t, " " + t ));
                return Symbol.Type.UNKOWN;
            }
            return table.get(name).getType();
        }

        else if(node instanceof ACallExp) {
            Token t = ((ACallExp)node).getId();
            String name = t.getText();
            if(!table.exists(name)) {
                errors.add(new SemanticError(
                    SemanticErrorType.UNDECLARED,t, "  " + ((ACallExp)node).getId()));
                return Symbol.Type.UNKOWN;
            }
            return table.get(name).getType();
        }
        
        // Unary
        else if(node instanceof ANegativeExp) {
            Symbol.Type type = getType(((ANotExp)node).getExp());
            if (type == Symbol.Type.BOOL) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, "  - Trying to use '-' negate operator on boolean expression " + node.toString()));
                return Symbol.Type.UNKOWN;
            }
            return type;
        }

        else if(node instanceof ANotExp) {
            Symbol.Type type = getType(((ANotExp)node).getExp());
            if (type != Symbol.Type.BOOL) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, " - Trying to use 'no' boolean negate operator on type " + type + " " + node.toString()));
                return Symbol.Type.UNKOWN;
            }
            return Symbol.Type.BOOL; 
        }
        // Binaries Relational
        else if (
            node instanceof AOrExp 
            || node instanceof AAndExp) {
            return getTypeBinaryLogic(node);
        }
        else if ( node instanceof AEqExp ) {
            return getTypeBinaryComparison(node, " with equality operator '='" );
        }
        else if (node instanceof ALtExp) {
            return getTypeBinaryComparison(node, " with less than operator '<'");
        }
        else if (node instanceof AGtExp) {
            return getTypeBinaryComparison(node, " with greater than operator '>'");
        }
        // Binaries Operation
        else if (
            node instanceof APlusExp 
            || node instanceof AMinusExp 
            || node instanceof AMultExp 
            || node instanceof AModExp 
            || node instanceof AGtExp) {

            return getTypeBinaryOperation(node);
        }

        else if (node instanceof ABlockExp) {
            this.inABlockExp((ABlockExp)node);
            Symbol.Type type = getType(((ABlockExp)node).getExp());
            this.outABlockExp((ABlockExp)node);
            return type;
        } 
        else if (node instanceof AIfExp) {
            Symbol.Type cond = getType(((AIfExp)node).getCond());
            Symbol.Type truthy = getType(((AIfExp)node).getTruthy());
            Symbol.Type falsy = getType(((AIfExp)node).getFalsy());
            if (cond != Type.BOOL) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, " trying to use non boolean expression of if condition when type is" + cond + " " + node.toString()));
                    return Type.UNKOWN;
            }
            if (truthy != falsy) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, " in a if expression both possible values must have the same type " + " " + node.toString()));
                    return Type.UNKOWN;
            } 
            assert truthy == falsy;
            return truthy;
        } 

        // @important this assumes that each id* is mapped to a type from exp*
        // and it is properly place on a new scope inside the lambda, otherwise 
        // the body expr won't be able to properly assert its type  
        // recall :  exp = {lambda}  [id]:T.identifier* [body]:exp [args]:exp*   
        else if (node instanceof ALambdaExp) {
            this.inALambdaExp( (ALambdaExp)(node));
            Symbol.Type type = getType(((ALambdaExp)node).getBody());
            this.outALambdaExp( (ALambdaExp)(node));
            return type;
        }
        else {
            ElipLogger.debug(" We should never get here  !!!! Look at console");
            return Type.UNKOWN;
        }
    }

    public <T extends PExp> Symbol.Type getTypeBinaryLogic(T node) {
        Method getRight;
        Method getLeft;

        PExp right_node;
        PExp left_node;
        try {
            getRight = node.getClass().getMethod("getRight");
            getLeft = node.getClass().getMethod("getLeft");

            right_node = (PExp)getRight.invoke(node);
            left_node = (PExp)getLeft.invoke(node);;

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if (left == right && right == Symbol.Type.BOOL) {
                return Symbol.Type.BOOL;
            }
            else if(left == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " right side is not a boolean, on " + node.toString()));
                return Type.UNKOWN;
            }
            else if(right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " left side is not a boolean, on " + node.toString()));
                return Type.UNKOWN;
            }
            else {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " neither side  expression is not a boolean, on " + node.toString()));
                return Type.UNKOWN;
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Type.UNKOWN;
    }

    public <T extends PExp> Symbol.Type getTypeBinaryComparison(T node, String extra_msg) {
        Method getRight;
        Method getLeft;

        PExp right_node;
        PExp left_node;
        try {
            getRight = node.getClass().getMethod("getRight");
            getLeft = node.getClass().getMethod("getLeft");

            right_node = (PExp)getRight.invoke(node);
            left_node = (PExp)getLeft.invoke(node);;

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if (left == right && right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, 
                    "comparison of two booleans"  + extra_msg 
                    + " right: " + right.toString() 
                    + " left: " + left.toString() 
                ));
                return Type.UNKOWN;
            }
            else if(left == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "left side of a comparison is a boolean"  + extra_msg));
                return Type.UNKOWN;
            }
            else if(right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "right side of a comparison a boolean" + extra_msg));
                return Type.UNKOWN;
            }
            else if (
                (right == Symbol.Type.INT ||right == Symbol.Type.REAL) 
                & (left == Symbol.Type.INT ||left == Symbol.Type.REAL)) {
                return Type.BOOL;
            }
            else {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "" + node.toString()));
                return Type.UNKOWN;
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return Type.UNKOWN;
    }

    public <T extends PExp> Symbol.Type getTypeBinaryOperation(T node) {
        Method getRight;
        Method getLeft;
        PExp right_node;
        PExp left_node;
        try {
            getRight = node.getClass().getMethod("getRight");
            getLeft = node.getClass().getMethod("getLeft");

            right_node = (PExp)getRight.invoke(node);
            left_node = (PExp)getLeft.invoke(node);;

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if (left == Symbol.Type.BOOL || right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " binary operation on bool  " + node.toString()));
                return Symbol.Type.UNKOWN;
            }
            else if(left == Symbol.Type.REAL || right == Symbol.Type.REAL) {
                return Type.REAL;
            }
            else if(right == Symbol.Type.INT && left == Symbol.Type.INT) {
                return Type.INT;
            }
            else if(right == Symbol.Type.UNKOWN ){ 
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " Binary Operation with Unkown Type " + node.toString()));
            }
            else {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " Binary Operation with Unkown Type " + node.toString()));
                return Type.UNKOWN;
            }
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Type.UNKOWN;
    }
    
    public boolean isCorrectUseOfArgs(String function_id, List<PExp> args) {
        Symbol fn_symbol = table.get(function_id);
        List<Symbol.SignatureParam> params = fn_symbol.getSignature().getParameters();

        if (params.size() != args.size()){
            // report incorrect amount of args
            return false;
        }
        
        int count = params.size(); 
        for (int i = 0; i < count; i++) {
            Symbol.SignatureParam param = params.get(i);
            PExp arg = args.get(i);
            if (arg instanceof AIdExp) {
                Symbol id_symbol = table.get(((AIdExp) arg).getIdentifier().getText());  
                if (id_symbol.isFunction()) {
                    if (!id_symbol.getSignature().equals(param.getSignature())){

                        return false;
                    }
                }
                else {
                    if (param.getType() != id_symbol.getType()) {
                        return false;
                    }

                }
            } 
            else {
                if (param.getType() != getType(arg)) {
                    return false;
                }

            }
            
        }
        return true;
    }
}
