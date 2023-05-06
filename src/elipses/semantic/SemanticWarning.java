package elipses.semantic;
import elipses.node.*;
import elipses.util.*;

public class SemanticWarning {
    private static String filename;
    String custom_msg;
    Token node;

    public SemanticWarning(String custom_msg) {
        this.custom_msg = custom_msg;
    }

    public SemanticWarning(Token node) {
        this.node = node;
    }

    public SemanticWarning (Token node, String custom_msg) {
        this.node = node;
        this.custom_msg = custom_msg;
    }


    static public void setFileName(String filename) {
        SemanticWarning.filename = filename;
    }

    static public String getFileName(String filename) {
        return SemanticWarning.filename;
    }
    public void inform() {
        filename = filename == null ? "" : filename;
        if (node != null){
            ElipLogger.warn(filename, node.getLine(), node.getPos(), " " + ((custom_msg != null) ? custom_msg:""));
        }
        else {

            ElipLogger.warn(filename + " " + ((custom_msg != null) ? custom_msg:""));
        }
    }
}