package elipses;

import elipses.lexer.*;
import elipses.node.*;
import elipses.parser.*;
import elipses.walker.ASTDisplay;
import elipses.walker.ASTPrinter;
import elipses.walker.CCodeGenerator;
import elipses.walker.SemanticAnalysis;
import elipses.util.ElipLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static List<String> input_files = new ArrayList<String>();
    static Switch adapter  = null;

    static boolean use_gui            = false;
    static boolean print_ast          = false;
    static boolean generate_exe       = false;
    static boolean generate_c         = false;
    static boolean print_tokens       = false;
    static boolean semantic_analisys  = true;

    static boolean DEBUG = 
        //true
        false
    ;
    static boolean DEBUG_LOG = 
        true
        //false
    ;

    public static void main(String[] args) {
        if(DEBUG) {
            //input_files.add("test/IR/block.elip");
            input_files.add("test/codegen/lambda.elip");
        }

        ElipLogger.setDebugMode(DEBUG_LOG);

        String usage = "Usage: elip [--gui] [--ast] [--help] [--c] [--info] <input_file>"; 
        String format = "   %-25s %s%n";
        String help = 
            "Usage: elip [--gui] [--ast] [--help] [-c] <input_files>\n\n"
            +"If no flags are specified, elip will create C code and compile it using gcc for all <input_files>, "
            +"if gcc is not on the path, an error will occur.\n"
            + String.format(format, 
                "--gui", "JavaFX gui representation of the AST for <input_files>.")
            + String.format(format, 
                "--help", "This Help Section.")
            + String.format(format, 
                "--ast", "print the AST of <input_files> into the console.")
            + String.format(format, 
                "--token,--tokens", "print Tokens of <input_files into the console>.")
            + String.format(format, 
                "--c", "Compile into <input_files> into C target language.")
            + String.format(format, 
                "--info", "Will print information about the current state of this compiler,") 
            + String.format(format, 
                "", "if something isn't supported, it'll be stated there,")
            + String.format(format, 
                "", "plus whatever other info deemed important.");



    try {   
        if (args.length == 0 && ! DEBUG) {
            ElipLogger.info(usage);
            System.exit(1);
        }

        for (String arg: args) {
            switch (arg) {
                case "--help":
                    ElipLogger.info(help);
                    System.exit(1);
                    break;
                case "--gui":
                    use_gui = true;
                    ElipLogger.info(
                         "INFO: elip is gonna show you a gui representation of the AST generated from .elip files"
                    );
                    break;  

                case "--ast":
                    print_ast = true;
                    ElipLogger.info(
                         "INFO: elip is gonna show you a representation of the AST generated from .elip files on the console"
                    );
                    break;  
                case "--exe":
                    generate_c  = true;
                    generate_exe = true;
                    ElipLogger.info(
                         "INFO: elip is gonna generate C code as Target Language." 
                        +" but this is already the default bevahiour :P (for now)"
                    );
                case "--c":
                    generate_c = true;
                    ElipLogger.info(
                         "INFO: elip is gonna generate C code as Target Language." 
                        +" but this is already the default bevahiour :P (for now)"
                    );
                    break;
                case "--token":
                case "--tokens":
                    ElipLogger.info(
                         "INFO: elip print tokens found"
                    );
                    print_tokens = true;
                    break;
                    
                default :
                    input_files.add(arg);
                    ElipLogger.info("INFO: input file" + arg);
                    break;
                
            }
        }

            for (String elip_file : input_files) {
                if (use_gui) {
                    adapter = new ASTDisplay(elip_file);
                    Debug.debug(elip_file, adapter,print_tokens);
                }
                if (print_ast) {
                    adapter = new ASTPrinter(elip_file);
                    Debug.debug(elip_file, adapter,print_tokens);
                }
                if (semantic_analisys) {
                    adapter = new SemanticAnalysis(elip_file);
                    Debug.debug(elip_file, adapter, print_tokens);
                    ElipLogger.info( "" + ((SemanticAnalysis)adapter).allGood());
                }
                boolean ok = false;
                String cfile = elip_file + ".c";
                if (generate_c) {
                    adapter = new CCodeGenerator(elip_file);
                    Debug.debug(elip_file, adapter, print_tokens);
                    ElipLogger.success("C code generated at " + cfile);
                    ok = CCompiler.check(cfile); 

                }
                if (ok && generate_exe){
                    CCompiler.compile(cfile);
                }
            }

    } catch (Exception e) {
        e.printStackTrace();
        ElipLogger.info(e.getClass() + e.getMessage());
        System.exit(1);
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
    debug(String file, Switch adapter, boolean print_tokens) {
        ElipLogger.info("Tokenizing file: " + file);
        try {
            String f = file;
            if (print_tokens) {
                Debug.lexer(f, print_tokens);
            }
            ElipLogger.success(" Tokenized with success.");
            Debug.parser(f, adapter);
        } catch (Exception e) {

            e.printStackTrace();
            ElipLogger.info(e.getClass() + e.getMessage());
            System.exit(1);
        }
    }

    public static void
    lexer(String file, boolean print_tokens) throws LexerException, IOException {
        try {
            Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
            Token token;
            while (!((token = lexer.next()) instanceof EOF)) {
                if (print_tokens) {
                    ElipLogger.info(Utils.pretify(token));
                }
            }
        }
        catch (LexerException e) {
            int line = e.getToken().getLine();
            int pos = e.getToken().getPos();
            ElipLogger.error(file, line,pos, e.getMessage());
            System.exit(69);
        } 
        catch (Exception e) {
            ElipLogger.error(
                e.getMessage() +
                " Won't continue with next tokens ...\n"
            );
            System.exit(420);
        }
    }

    public static void 
    parser(String file, Switch adapter)  {
        
        try {

            Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 4024));
            Parser p = new Parser(lexer);
            Start tree = p.parse();
            ElipLogger.success(" Parsed into AST with success.");
            tree.apply(adapter);
        } catch (ParserException e) {
            int line = e.getToken().getLine();
            int pos = e.getToken().getPos();
            ElipLogger.error(file, line,pos, e.getMessage());
            System.exit(69);
        }
        catch (LexerException e) {
            int line = e.getToken().getLine();
            int pos = e.getToken().getPos();
            ElipLogger.error(file, line,pos, e.getMessage());
            System.exit(420);
        } 
        catch (Exception e) {
            ElipLogger.error(
                e.getMessage() +
                "Won't continue"
            );
            System.exit(1);
        }
    }

}



class CCompiler {

    public static boolean check(String filepath) {
        try {
            String source = filepath;
            String[] command = {"gcc", "-fsyntax-only", source};

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
                ElipLogger.info("C code " + source + " is compilable");
                // Call the method to compile the C code from Java
                return true;
            } else {
                ElipLogger.error("C code is not compilable. Exit code: " + code);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }

    public static void compile(String filepath) {
        try {
            // Command to compile the C code
            String source = filepath;
            String executable = filepath.replace(".c", "").replace(".elip", "");
            String[] command = {"gcc",  source, "-o", executable };

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
                ElipLogger.success( source + " C code compiled into executable " + executable+ " successfully ");
            } else {
                ElipLogger.error("Failed to compile C code. Exit code: " + code);
            }
        } catch (Exception e) {
            e.printStackTrace(ElipLogger.stack.peek());
            ElipLogger.stack.peek().flush();
            System.exit(1);
        }
    }
}