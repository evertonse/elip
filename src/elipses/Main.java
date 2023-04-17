package elipses;

import elipses.lexer.*;
import elipses.node.*;
import elipses.parser.*;
import elipses.walker.ASTDisplay;
import elipses.walker.ASTPrinter;
import elipses.walker.CCodeGenerator;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class Main {

  static List<String> input_files = new ArrayList<String>();
  static Switch adapter = null;
  static boolean use_gui = false;
  static boolean ast = false;

  public static void main(String[] args) {
    String usage = "Usage: elip [--gui] [--ast] [--help] [-c] <input_file>"; 
    try {   
        if (args.length == 0) {
            System.out.println(usage);
            System.exit(1);
        }

        for (String arg: args) {
            switch (arg) {
                case "--help":
                    System.out.println(usage);
                    System.exit(1);
                    break;
                case "--gui":
                    use_gui = true;
                    System.out.println(
                         "INFO: elip is gonna show you a gui representation of the AST generated from .elip files"
                    );
                    break;  

                case "--ast":
                    ast = true;
                    System.out.println(
                         "INFO: elip is gonna show you a representation of the AST generated from .elip files on the console"
                    );
                    break;  
                case "--c":
                    System.out.println(
                         "INFO: elip is gonna generate C code as Target Language." 
                        +" but this is already the default bevahiour :P (for now)"
                    );
                    break;
                    
                default :
                    input_files.add(arg);
                    System.out.println("INFO: input file" + arg);
                    break;
                
            }
        }

        for (String elip_file : input_files) {
            adapter = new CCodeGenerator(elip_file);
            Debug.debug(elip_file, adapter);
            if (use_gui) {
                adapter = new ASTDisplay(elip_file);
                Debug.debug(elip_file, adapter);
            }
            if (ast) {
                adapter = new ASTPrinter(elip_file);
                Debug.debug(elip_file, adapter);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getClass() + e.getMessage());
    }
  }
}

class Utils {
    public static String 
    pretify(Token token) {
        String token_pos = new String(
            "[" + token.getLine() + "," + token.getPos() + "]:"
        );

        String token_tuple = new String(
            "<" +
            token.getClass().getSimpleName() +
            ", " +
            token.toString().stripTrailing() +
            ">"
        );

        return String.format("Token %10s %s", token_pos, token_tuple);
    }
}

class Debug {

    public static void
    debug(String file, Switch adapter) {
        System.out.println("\nChecking Lexer for file: " + file);
        if (file.endsWith("*")) {
            File folder = new File(file.replace("*", ""));
            File[] child_files = folder.listFiles();

            for (File cf : child_files) {
                if (cf.isFile()) {
                Debug.debug(cf.getPath(),adapter);
                }
            }
            return;
        }

        try {
            String f = file;
            Debug.lexer(f);
            Debug.parser(f, adapter);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass() + e.getMessage());
        }
    }

    public static void
    lexer(String file) throws LexerException, IOException {
        try {
            Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
            Token token;
            while (!((token = lexer.next()) instanceof EOF)) {
                //if (token instanceof TBlank) continue;
                System.out.println(Utils.pretify(token));
            }
        } catch (Exception e) {
            System.out.println(
                "\nError: " +
                e.getClass() +
                e.getMessage() +
                "\nWon't continue with next tokens ...\n"
            );
        }
    }

    public static void 
    parser(String file, Switch adapter) throws Exception {
        Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
        Parser p = new Parser(lexer);
        Start tree = p.parse();
        tree.apply(adapter);
    }

}
