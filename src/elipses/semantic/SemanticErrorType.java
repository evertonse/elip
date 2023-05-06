package elipses.semantic;

public enum SemanticErrorType {
    NONE(""),
    DUPLICATE_ENTRY("Duplicate entry"),
    NO_ENTRY("No entry point"),
    ALREADY_DECLARED("Identifier already declared in this scope"),
    UNDECLARED("Use of undeclared indentifier"),
    UNDEFINED_FUNCTION("Undefined function"),
    DUPLICATE_VARIABLE("Duplicate variable"),
    INCOMPATIBLE_TYPES("Incompatible types"),
    INCOMPATIBLE_RETURN_TYPE("Incompatible Return Type"),
    DUPLICATE_FUNCTION("Duplicate function"),
    INCORRECT_NUMBER_OF_ARGUMENTS("Incorrect number of arguments"),
    INCORRECT_USE_OF_ARGUMENTS("Incorrect use of arguments in a function call"),
    SIGANTURE_ON_ENTRY("Higher order function on entry"),
    INVALID_OPERATION("Invalid operation"),
    INVALID_OPERATION_ON_FN_REFENRECE("Invalid operation with function reference operand"),
    INVALID_MODULUS_OPERATION("Invalid use of '%' modulus operator");

    private String message;
    
    private SemanticErrorType(String message) {
        this.message = message;
    }

    public void appendExtraMessage(String extra_msg) {
        this.message  = message + extra_msg;
    }
    public String getMessage() {
        return " " + message + " ";
    }
}