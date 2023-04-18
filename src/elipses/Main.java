package elipses;

import elipses.lexer.*;
import elipses.node.*;
import elipses.parser.*;
import elipses.walker.ASTDisplay;
import elipses.walker.ASTPrinter;
import elipses.walker.CCodeGenerator;
import elipses.util.ElipLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            ElipLogger.info(usage);
            System.exit(1);
        }

        for (String arg: args) {
            switch (arg) {
                case "--help":
                    ElipLogger.info(usage);
                    System.exit(1);
                    break;
                case "--gui":
                    use_gui = true;
                    ElipLogger.info(
                         "INFO: elip is gonna show you a gui representation of the AST generated from .elip files"
                    );
                    break;  

                case "--ast":
                    ast = true;
                    ElipLogger.info(
                         "INFO: elip is gonna show you a representation of the AST generated from .elip files on the console"
                    );
                    break;  
                case "--c":
                    ElipLogger.info(
                         "INFO: elip is gonna generate C code as Target Language." 
                        +" but this is already the default bevahiour :P (for now)"
                    );
                    break;
                    
                default :
                    input_files.add(arg);
                    ElipLogger.info("INFO: input file" + arg);
                    break;
                
            }
        }

        for (String elip_file : input_files) {
            adapter = new CCodeGenerator(elip_file);
            Debug.debug(elip_file, adapter);
            CCompiler.check(elip_file + ".c"); 
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
        ElipLogger.info(e.getClass() + e.getMessage());
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
        ElipLogger.info("\nChecking Lexer for file: " + file);
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
            ElipLogger.info(e.getClass() + e.getMessage());
        }
    }

    public static void
    lexer(String file) throws LexerException, IOException {
        try {
            Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
            Token token;
            while (!((token = lexer.next()) instanceof EOF)) {
                //if (token instanceof TBlank) continue;
                ElipLogger.info(Utils.pretify(token));
            }
        } catch (Exception e) {
            ElipLogger.info(
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



class CCompiler {

    public static void check(String filepath) {
        try {
            String[] command = {"gcc", "-fsyntax-only", filepath};

            // Create a ProcessBuilder object with the command
            ProcessBuilder proc = new ProcessBuilder(command);

            // Redirect the standard error stream to the standard output stream
            proc.redirectErrorStream(true);

            // Start the process
            Process process = proc.start();

            // Get the input stream of the process to read the output
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read and print the output of the command
            String line;
            while ((line = input.readLine()) != null) {
                ElipLogger.info(line);
            }

            // Wait for the process to complete
            int code = process.waitFor();

            if (code == 0) {
                ElipLogger.info("C code is compilable");
                // Call the method to compile the C code from Java
                compile(filepath);
            } else {
                ElipLogger.info("C code is not compilable. Exit code: " + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void compile(String filepath) {
        try {
            // Command to compile the C code
            String[] command = {"gcc",  filepath, "-o", filepath.replace(".c", "").replace(".elip", "")};

            // Create a ProcessBuilder object with the command
            ProcessBuilder proc = new ProcessBuilder(command);

            // Redirect the standard error stream to the standard output stream
            proc.redirectErrorStream(true);

            // Start the process
            Process process = proc.start();

            // Get the input stream of the process to read the output
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read and print the output of the command
            String line;
            while ((line = input.readLine()) != null) {
                ElipLogger.info(line);
            }

            // Wait for the compilation process to complete
            int code = process.waitFor();

            if (code == 0) {
                ElipLogger.info("C code compiled successfully");
            } else {
                ElipLogger.info("Failed to compile C code. Exit code: " + code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}