package elipses;

import java.io.*;

import elipses.lexer.*;
import elipses.node.*;

public class Main
{
  static String[] test_files = new String[] {
    //"test/grupo10/*",
    //"test/grupo10/codigo1_grupo10.elip",
    //"test/grupo10/codigo2_grupo10.elip",
    //"test/grupo10/codigo3_grupo10.elip",
    //"test/etapa1/code1.elip", 
    //"test/etapa1/code2.elip", 
    //"test/etapa1/code3.elip", 
    "test/comment.elip"
  };

	public static void main(String[] args)
	{
		try {
      for (String elip_file : test_files) {
        Debug.debug(elip_file);
      }
		}
		catch(Exception e) {
            e.printStackTrace();
    	    System.out.println(e.getClass() + e.getMessage());
		}
	}
}


class Utils {
	public static String pretify(Token token) {
		String token_pos = new String("[" + token.getLine() + "," + token.getPos()+ "]:"); 
		String token_tuple = new String(
	    	"<"
	    	+ token.getClass().getSimpleName()
	    	+ ", "   + token.toString().stripTrailing()
	    	+ ">"
	    );

		return String.format("Token %10s %s", token_pos, token_tuple);
	}
}


class Debug {
  public static 
  void lexer(Lexer lexer) throws LexerException, IOException {
    try {
      Token token;
      while(!((token = lexer.next()) instanceof EOF)) {
          //if (token instanceof TBlank) continue;
          System.out.println(Utils.pretify(token));
      }
    } catch (Exception e) {
      System.out.println("\nError: " + e.getClass() + e.getMessage() 
        + "\nWon't continue with next tokens ...\n");
      //Debug.lexer(lexer); 
    }
  }

  public static 
  void debug(String file)
	{
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

		try
		{
			String arquivo = file;
			Lexer lexer =   
					new Lexer(
							new PushbackReader(  
									new FileReader(arquivo), 1024)); 
      Debug.lexer(lexer);

		}
		catch(Exception e) {
      e.printStackTrace();
			System.out.println(e.getClass() + e.getMessage());
		}
	}
}
