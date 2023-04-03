package elipses;

import elipses.lexer.*;
import elipses.node.*;
import elipses.parser.*;
import java.io.*;

public class Main {

  static String[] test_files = new String[] {
    //"test/etapa1/code1.elip",
    //"test/etapa1/code2.elip",
    "test/etapa1/code3.elip",
    //"test/comment.elip",
    //"test/analysis/exp.elip",
  };

  public static void main(String[] args) {
    try {
      for (String elip_file : test_files) {
        Debug.debug(elip_file);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getClass() + e.getMessage());
    }
  }
}

class Utils {

  public static String pretify(Token token) {
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

  public static void lexer(String file) throws LexerException, IOException {
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
      //Debug.lexer(lexer);
    }
  }

  public static void parser(String file) throws Exception {
    Lexer lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
    Parser p = new Parser(lexer);

    Boolean use_gui = true;
    Switch adapter;

    if (use_gui) {
      // on gui
      adapter = new ASTDisplay();
    } else {
      // on console
      adapter = new ASTPrinter();
    }

    Start tree = p.parse();
    tree.apply(adapter);
  }

  public static void debug(String file) {
    System.out.println("\nChecking Lexer for file: " + file);

    if (file.endsWith("*")) {
      File folder = new File(file.replace("*", ""));
      File[] child_files = folder.listFiles();

      for (File cf : child_files) {
        if (cf.isFile()) {
          Debug.debug(cf.getPath());
        }
      }
      return;
    }

    try {
      String f = file;
      Debug.lexer(f);
      Debug.parser(f);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getClass() + e.getMessage());
    }
  }
}
