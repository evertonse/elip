package elipses.semantic;

import elipses.node.*;
import elipses.semantic.Symbol.Type;
import elipses.util.ElipLogger;
import java.lang.reflect.Method;
import java.util.*;

import elipses.analysis.DepthFirstAdapter;


class TypeCache {
    private static Map<PExp, Symbol.Type> cache = new HashMap<>();

    public static boolean contains(PExp e) {
        return cache.containsKey(e);
    }

    public static Symbol.Type get(PExp e) {
        if (cache.containsKey(e)) {
            return cache.get(e);
        }
        return null;
    }

    public static Symbol.Type add(PExp e, Symbol.Type t) {
        return cache.put(e, t);
    }

    public static void clear(){
        cache.clear();
    }
}


public class TypeInference {

    SymbolTable table = null;
    List<SemanticError> errors = null;
    List<SemanticWarning> warnings = null;
    DepthFirstAdapter adapter;
    
    public TypeInference(SymbolTable  table, List<SemanticError> error_list) {
        this.table = table;
        this.errors = error_list;
    }

    public TypeInference(SymbolTable  table, List<SemanticError> error_list, List<SemanticWarning> warnings_list, DepthFirstAdapter adapter) {
        this.table = table;
        this.errors = error_list;
        this.warnings = warnings_list;
        this.adapter = adapter;
    }
    
    public TypeInference(SymbolTable  table, List<SemanticError> error_list, List<SemanticWarning> warnings_list) {
        this.table = table;
        this.errors = error_list;
        this.warnings = warnings_list;
    }
    
    public TypeInference(SymbolTable  table, List<SemanticError> error_list, DepthFirstAdapter adapter) {
        this.table = table;
        this.errors = error_list;
        // here we reserve the  right to travel in and out of nodes from the adptare travel
        this.adapter = adapter;
    }

    public Symbol.Type fromPType(PType p) {
        if (p instanceof AIntType) {
            return Type.INT;
        }
        else if (p instanceof ARealType) { 
            return Type.REAL;
        }
        else if (p instanceof ABoolType) { 
            return Type.BOOL;
        }
        else {
            return Type.UNKOWN;
        }
    }

    public boolean canApplyCoercion (Type t1, Type t2) {
        if (t1 == Type.UNKOWN || t2 == Type.UNKOWN){
            return false;
        }
        else if (t1 == t2) {
            return true;
        }
        else if (t1 == Type.REAL && t2 == Type.INT) {
            return true;
        }
        else if (t1 == Type.INT && t2 == Type.REAL) {
            return true;
        }
        return false;
    }

    public boolean isFunctionPointer(PExp node) { 
        Symbol s = getSymbolOrNull(node);
        if (s!=null) {
            return s.isFunction();
        }
        return false;
    }

    public Symbol isFunctionPointerOrNull(PExp node) { 
        Symbol s = getSymbolOrNull(node);
        if (s!=null) {
            if (s.isFunction()) {
                return s;
            }
        }

        return null;
    }

    public Symbol getSymbolOrNull(PExp node) {
        Symbol return_symbol = null;
        if (node instanceof ALambdaExp) {
            ALambdaExp e = (ALambdaExp)node;
            return_symbol = getSymbolOrNull(e.getBody());
        } 
        else if (node instanceof ABlockExp) {
            ABlockExp e = (ABlockExp)node;
            return_symbol = getSymbolOrNull(e.getExp());
        } 
        else if (node instanceof AIdExp) {
            AIdExp e = (AIdExp)node;
            String id = e.getIdentifier().getText();
            return_symbol = table.get(id);
        } 
        else if (node instanceof AIfExp) {
            AIfExp e = (AIfExp)node;
            Symbol truthy = getSymbolOrNull(e.getTruthy());
            Symbol falsy  = getSymbolOrNull(e.getFalsy());
            // Both truthy and Falsy MUST have the same type
            // and there fore if any of them are not null
            // hey must have the same signtura, but not necessarily the same
            // symbol, but for all purposes we only call the functin to get
            // the Signature / type not the id itsself
            if (truthy != null ) {
                return_symbol = truthy;
            }
            else if (falsy != null ) {
                return_symbol = falsy;
            }
        } 
        // Cant do node instanceof ACallExp because a call expr
        // can't ever return a ID and therefore can't have symbol

        // Any binary operator return an R-Value and therefore
        // can't have a symbol storage 
        // Unary operation as well, negate/not an id returns an R-Value

        return return_symbol;
    }

    public Symbol.Type getType(PExp node) {
        if (TypeCache.contains(node)){
            return TypeCache.get(node);
        }
        Symbol.Type return_symbol = Type.UNKOWN;
        // Atomic
        if (node instanceof ATrueExp || node instanceof AFalseExp) {
           return_symbol = Symbol.Type.BOOL;
        }

        else if (node instanceof ABinExp || node instanceof AIntExp) {
            return_symbol = Symbol.Type.INT;
        }

        else if (node instanceof ARealExp) {
            return_symbol = Symbol.Type.REAL;
        }

        else if(node instanceof AIdExp) {
            String name = ((AIdExp)node).getIdentifier().getText();
            if(!table.exists(name)) {
                Token t = ((AIdExp)node).getIdentifier();
                errors.add(new SemanticError(
                    SemanticErrorType.UNDECLARED,t, " " + t ));
                return_symbol  = Symbol.Type.UNKOWN;
            }
            else {
                return_symbol  = table.get(name).getType();
            }
        }

        else if(node instanceof ACallExp) {
            Token t = ((ACallExp)node).getId();
            String name = t.getText();
            if(!table.exists(name)) {
                errors.add(new SemanticError(
                    SemanticErrorType.UNDECLARED,t, "  " + ((ACallExp)node).getId()));
                return_symbol  = Symbol.Type.UNKOWN;
            }
            else {
                return_symbol  = table.get(name).getType();
            }
        }
        
        // Unary
        else if(node instanceof ANegativeExp) {
            Symbol s = isFunctionPointerOrNull(((ANegativeExp)node).getExp()); 
            if (s != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INVALID_OPERATION_ON_FN_REFENRECE, s.getToken(), ". Trying to use '-' negate  on a function reference'" 
                    + s.getName() + "' with signature " + s.getSignature()
                ));
                return_symbol = Symbol.Type.UNKOWN;
            }
            else {
                Symbol.Type type = getType(((ANegativeExp)node).getExp());
                if (type == Symbol.Type.BOOL) {
                    errors.add(new SemanticError(
                        SemanticErrorType.INCOMPATIBLE_TYPES, ". Trying to use '-' negate operator on boolean expression " + node.toString()));
                    return_symbol = Symbol.Type.UNKOWN;
                }
                else {
                    return_symbol = type;
                }
            }
        }

        else if(node instanceof ANotExp) {
            Symbol s = isFunctionPointerOrNull(((ANotExp)node).getExp()); 
            if (s != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INVALID_OPERATION_ON_FN_REFENRECE, s.getToken(), ". Trying to use 'no' boolean negate operator  on a function reference'" 
                    + s.getName() + "' with signature " + s.getSignature()
                ));
                return_symbol = Symbol.Type.UNKOWN;
            }
            else {

                Symbol.Type type = getType(((ANotExp)node).getExp());
                if (type != Symbol.Type.BOOL) {
                    errors.add(new SemanticError(
                        SemanticErrorType.INCOMPATIBLE_TYPES, ". Trying to use 'no' boolean negate operator on type " + type + " " + node.toString()));
                        return_symbol = Symbol.Type.UNKOWN;
                }
                else {
                    return_symbol = Symbol.Type.BOOL; 
                }
            }
        }
        // Binaries Relational
        else if (
            node instanceof AOrExp 
            || node instanceof AAndExp) {
           return_symbol = getTypeBinaryLogic(node);
        }
        else if ( node instanceof AEqExp ) {
            return_symbol = getTypeBinaryComparison(node, " with equality operator '='" );
        }
        else if (node instanceof ALtExp) {
            return_symbol = getTypeBinaryComparison(node, " with less than operator '<'");
        }
        else if (node instanceof AGtExp) {
            return_symbol = getTypeBinaryComparison(node, " with greater than operator '>'");
        }
        // Binaries Operation
        else if (
            node instanceof APlusExp 
            || node instanceof AMinusExp 
            || node instanceof AMultExp 
            || node instanceof AModExp 
            || node instanceof ADivExp 
            ) {

            return_symbol = getTypeBinaryOperation(node);
        }

        else if (node instanceof ABlockExp) {
            if (this.adapter != null) {
                //adapter.inABlockExp((ABlockExp)node);
            }
            Symbol.Type type = getType(((ABlockExp)node).getExp());

            if (this.adapter != null) {
                //adapter.outABlockExp((ABlockExp)node);
            }

            return_symbol = type;
        } 
        else if (node instanceof AIfExp) {
            Symbol cond_symbol = isFunctionPointerOrNull(((AIfExp)node).getCond()); 
            Symbol truthy_symbol = isFunctionPointerOrNull(((AIfExp)node).getTruthy()); 
            Symbol falsy_symbol = isFunctionPointerOrNull(((AIfExp)node).getFalsy()); 
            Symbol.Type cond = getType(((AIfExp)node).getCond());
            Symbol.Type truthy = getType(((AIfExp)node).getTruthy());
            Symbol.Type falsy = getType(((AIfExp)node).getFalsy());
            if (cond_symbol != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, cond_symbol.getToken(), ". Trying to use function reference as a boolean on if-expression " 
                    + "func: '" + cond_symbol.getName() + "' with signature " + cond_symbol.getSignature()
                ));
                return_symbol = Symbol.Type.UNKOWN;
            }
            else if (truthy_symbol != null && cond == Type.BOOL) {
                if (falsy_symbol != null ){
                    if (truthy_symbol.getSignature().equals(falsy_symbol.getSignature())) {
                        return_symbol = truthy_symbol.getType();
                    }
                    else {
                        errors.add(new SemanticError(
                            SemanticErrorType.INCOMPATIBLE_TYPES, falsy_symbol.getToken(), ". both branches of the the if-expression has to have the same type even if it's a function reference" 
                        + "  then-branch: '" + truthy_symbol.getName() + "' with signature " + truthy_symbol.getSignature()
                        + "  else-branch: '" + falsy_symbol.getName() + "' with signature " + falsy_symbol.getSignature()
                        ));
                        return_symbol = Type.UNKOWN;
                    }
                }
                else {
                    errors.add(new SemanticError(
                        SemanticErrorType.INCOMPATIBLE_TYPES, truthy_symbol.getToken(), ". both branches of the the if-expression has to have the same type" 
                    + "  then-branch: '" + truthy_symbol.getName() + "' with signature " + truthy_symbol.getSignature()
                    + "  else-branch: with type '" + falsy  + "'"
                    ));
                    return_symbol = Type.UNKOWN;
                }

            }
            else {
                if (cond != Type.BOOL) {
                    errors.add(new SemanticError(
                        SemanticErrorType.INCOMPATIBLE_TYPES, ". Trying to use non boolean expression of if condition  on " 
                            + "'" + node.toString()+ "'"
                            +"when type is '" + cond + "'' " 
                        ));
                        return_symbol = Type.UNKOWN;
                }
                else if (truthy != falsy) {
                    errors.add(new SemanticError(
                        SemanticErrorType.INCOMPATIBLE_TYPES, 
                            " in a if expression both possible values must have the same type " 
                            + " on '" + node.toString() + "' "
                            + " then-branch: with type '" +  truthy  + "' "
                            + " else-branch: with type '" +  falsy + "' "  
                        ));
                        return_symbol = Type.UNKOWN;
                }
                else {
                    assert truthy == falsy;
                    return_symbol = truthy;
                } 
            }

        } 
        // @important this assumes that each id* is mapped to a type from exp*
        // and it is properly place on a new scope inside the lambda, otherwise 
        // the body expr won't be able to properly assert its type  
        // recall :  exp = {lambda}  [id]:T.identifier* [body]:exp [args]:exp*   
        else if (node instanceof ALambdaExp) {
            if (this.adapter != null ) {
                //adapter.inALambdaExp( (ALambdaExp)(node));
            }
            Symbol.Type type = getType(((ALambdaExp)node).getBody());

            if (this.adapter != null ) { 
                //adapter.outALambdaExp( (ALambdaExp)(node));
            }
            return_symbol = type;
        }
        else {
            ElipLogger.debug(" We should never get here  !!!! Look at console" +node.getClass() );
            return_symbol = Type.UNKOWN;
        }
        TypeCache.add(node, return_symbol);
        return return_symbol;
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

            Symbol right_symbol = isFunctionPointerOrNull(right_node); 
            Symbol left_symbol = isFunctionPointerOrNull(left_node); 
            String error_msg = (right_symbol != null ? " right:'" + right_symbol.getName()+"' " : "")
                                +(left_symbol != null ? " left:'" + left_symbol.getName()+"' " : "")
                            ;
            Token fn_token = (right_symbol != null ? right_symbol.getToken(): (left_symbol != null? left_symbol.getToken(): null ));
            if (right_symbol != null || left_symbol != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INVALID_OPERATION_ON_FN_REFENRECE, fn_token,
                    "  - Trying to use boolean binary operator  on a function reference'" 
                    + error_msg
                ));
                return Symbol.Type.UNKOWN;
            }

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if (left == right && right == Symbol.Type.BOOL) {
                return Symbol.Type.BOOL;
            }
            else if(left == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " Incorrect logic operator usage. right side is not a boolean, on "
                    + " left: " + left_node
                    + " right: " + right_node
                ));
                return Type.UNKOWN;
            }
            else if(right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " Incorrect logic operator usage. left side is not a boolean, on "
                    + " left: " + left_node
                    + " right: " + right_node
                ));
                return Type.UNKOWN;
            }
            else {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "Incorrect logic operator usage. neither side  expression is not a boolean, on " 
                    + " left: " + left_node
                    + " right: " + right_node
                ));
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


            Symbol right_symbol = isFunctionPointerOrNull(right_node); 
            Symbol left_symbol = isFunctionPointerOrNull(left_node); 
            String error_msg = (right_symbol != null ? " right:'" + right_symbol.getName()+"' " : "")
                                +(left_symbol != null ? " left:'" + left_symbol.getName()+"' " : "")
                            ;

            Token fn_token = (right_symbol != null ? right_symbol.getToken(): (left_symbol != null? left_symbol.getToken(): null ));
            if (right_symbol != null || left_symbol != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INVALID_OPERATION_ON_FN_REFENRECE,fn_token, "  - Trying to use boolean binary operator  on a function reference'" 
                    + error_msg
                ));
                return Symbol.Type.UNKOWN;
            }

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if (left == right && right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, 
                    "comparison of two booleans"
                    + " left: " + left_node
                    + " right: " + right_node
                    + extra_msg
                ));
                return Type.UNKOWN;
            }
            else if(left == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "left side of a relational operator is a boolean"  
                    + " left: " + left_node
                    + " right: " + right_node
                    + extra_msg
                ));
                return Type.UNKOWN;
            }
            else if(right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, "right side of a relational operator a boolean"
                    + " left: " + left_node
                    + " right: " + right_node
                    + extra_msg
                ));
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

            Symbol right_symbol = isFunctionPointerOrNull(right_node); 
            Symbol left_symbol = isFunctionPointerOrNull(left_node); 
            String error_msg = (right_symbol != null ? " right:'" + right_symbol.getName()+"' " : "")
                                +(left_symbol != null ? " left:'" + left_symbol.getName()+"' " : "")
                            ;

            Token fn_token = (right_symbol != null ? right_symbol.getToken(): (left_symbol != null? left_symbol.getToken(): null ));
            if (right_symbol != null || left_symbol != null) {
                errors.add(new SemanticError(
                    SemanticErrorType.INVALID_OPERATION_ON_FN_REFENRECE,fn_token, "  - Trying to use boolean binary operator  on a function reference'" 
                    + error_msg
                ));
                return Symbol.Type.UNKOWN;
            }

            Symbol.Type left = getType(left_node);
            Symbol.Type right = getType(right_node);
            if( node instanceof AModExp
            && ((left !=  Type.INT) || (right != Type.INT))
            ) {
                errors.add(new SemanticError(SemanticErrorType.INVALID_MODULUS_OPERATION, ". Modulus operator of non-integral types is undefined"  
                    + " left: '"  + left_node   + "'"
                    + " right: '" + right_node  + "'"
                ));
                return Symbol.Type.UNKOWN;
            }
            if (left == Symbol.Type.BOOL || right == Symbol.Type.BOOL) {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " binary operation on bool"  
                    + " left: '" + left_node + "'"
                    + " right: '" + right_node + "'"
                ));
                return Symbol.Type.UNKOWN;
            }
            else if(left == Symbol.Type.REAL || right == Symbol.Type.REAL) {
                return Type.REAL;
            }
            else if(right == Symbol.Type.INT && left == Symbol.Type.INT) {
                return Type.INT;
            }
            else if(right == Symbol.Type.UNKOWN ){ 
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " binary Operation with Unkown Type "
                    + " left: '" + left_node + "'"
                    + " right: '" + right_node + "'"
                ));
            }
            else {
                errors.add(new SemanticError(SemanticErrorType.INCOMPATIBLE_TYPES, " binary Operation with Unkown Type "
                    + " left: " + left_node
                    + " right: " + right_node
                ));
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
    
    public SemanticError isCorrectUseOfArgs(String function_id, List<PExp> args) {
        Symbol fn_symbol = table.get(function_id);
        // Might be null, let the error class handle that
        Token token = fn_symbol.getToken();
        if (! fn_symbol.isFunction()) {
            return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                "'"  +function_id + "' is NOT a function but it's used as one."
            );
        }

        List<Symbol.SignatureParam> params = fn_symbol.getSignature().getParameters();

        int params_count = params.size();
        int args_count = args.size();
        int count = Math.min(params_count, args_count);

        if (params_count != args_count){
            // report incorrect amount of args
            if (params_count < args_count) {
                return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                    "on function '"  +function_id + "':"
                    + " passing too much arguments, expected: " + params_count
                    + " got: "  + args_count
                );
            }
            if (params_count > args_count) {
                return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                    "on function '"  +function_id + "':"
                    + " missing arguments, expected: " + params_count + " arguments "
                    + " got: "  + args_count
                );
            }
        }
        
        for (int i = 0; i < count; i++) {
            Symbol.SignatureParam param = params.get(i);
            PExp arg = args.get(i);
            Symbol id_symbol = getSymbolOrNull(arg);
            if (id_symbol != null) {
                if (id_symbol.isFunction()) {
                    if (!id_symbol.getSignature().equals(param.getSignature())){
                        return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                        
                            "on function '" + function_id + "'"
                            + " the " + (i+1)+"th argument:  " + id_symbol.getSignature().toString()
                            + " is different from expected : "  + param.getSignature().toString()
                        );
                    }
                }
                else {
                    Type param_type = param.getType();
                    Type id_symbol_type =  id_symbol.getType();
                    if (canApplyCoercion(param_type, id_symbol_type) && param_type != id_symbol_type) {
                        if(warnings != null){
                            warnings.add( new SemanticWarning(
                                token,
                                "on '" + id_symbol.getName()
                                + " the " +(i+1) + "th arguments:   " 
                                +  "' Coercion from given argument'" + id_symbol_type 
                                +"' to " + " '" + param_type + "'"
                            ));
                        }
                    }
                    else if (param_type  != id_symbol_type) {
                        return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                            "on function '" + function_id+ "'"
                            + " the " +(i+1) + "th arguments:   " + id_symbol.getType()
                            + " is different from expected: "  + param.getType()
                        );
                    }
                }
            } 
            else {
                Type arg_type = getType(arg);
                Type param_type = param.getType();

                if (canApplyCoercion(param_type, arg_type) && param_type != arg_type) {
                    if(warnings != null){
                        warnings.add( new SemanticWarning(
                            token,
                            "on '" + arg.toString()
                            + "' the " +(i+1) + "th argument " 
                            +  "' was coerced from '" +arg_type 
                            +"' to " + "'" + param_type + "'"
                        ));
                    }
                }
                else if (arg_type != param_type  ) {
                    return new SemanticError(SemanticErrorType.INCORRECT_USE_OF_ARGUMENTS,token,
                        "on function '" + function_id+ "'"
                        + " the " +(i+1)+ "th parameter expected type: " + param_type
                        + " is different from given type: "  +  arg_type
                    );
                }
            }
        }
        // No error
        return null;
    }
    
}
