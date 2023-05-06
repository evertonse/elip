package elipses.semantic;
import elipses.node.*;
import elipses.util.*;

public class SemanticError {
    SemanticErrorType type;
    private static String filename;
    String custom_msg;
    int line, pos;
    Token node;

    static public void setFileName(String filename) {
        SemanticError.filename = filename;
    }

    static public String getFileName(String filename) {
        return SemanticError.filename;
    }

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