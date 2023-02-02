package elipses;

import java.io.*;

import elipses.lexer.*;
import elipses.node.*;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			String arquivo = "test/comment.elip";
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

class Utils {
  public static 
  String pretify(Token token) {
    String token_pos = new String("[" + token.getLine() + "," + token.getPos()+ "]:"); 
    String token_tuple = new String(
      " < "  
      + token.getClass().getSimpleName() 
      + ", "   + token.toString() 
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
          if (token instanceof TBlank) 
            continue;
          System.out.println(Utils.pretify(token));
      }
    } catch (Exception e) {
      System.out.println("\nError: " + e.getClass() + e.getMessage() + "\nContinuing with next tokens ...\n");
      Debug.lexer(lexer); 
    }
  }
  
}
