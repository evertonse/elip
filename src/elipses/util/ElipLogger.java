package elipses.util;
import java.io.PrintWriter;
import java.time.Year;
import java.util.Stack;

public class ElipLogger {
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";

    public static final String prefix = "[elip] " ; 
    public static Stack<PrintWriter> stack = new Stack<PrintWriter>();
    public static PrintWriter logger = new PrintWriter(System.out);
    public static PrintWriter old_logger;


    public static void push(PrintWriter out) {
        stack.push(out);
        ElipLogger.logger = stack.peek();
    }

    public static void pop() {
        ElipLogger.logger = stack.pop();
    }


    public static void info(String message) {
        logger.println( CYAN + prefix + RESET + message );
        logger.flush();
    }

    public static void error(String message) {
        logger.println( RED + prefix + RESET + "ERROR:" + message );
        logger.flush();
    }

    public static void warn(String message) {
        logger.println( YELLOW + prefix + RESET + "ERROR:" + message );
        logger.flush();
    }

    public static void success(String message) {
        logger.println(GREEN + prefix+ "SUCCESS: " + message + RESET);
        logger.flush();
    }

    public static void erro_at(String filename, int line, String err_msg) {
        error(String.format("%s:%d: %s", filename, line, err_msg));
    }
 
}
