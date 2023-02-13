package elipses;

import java.io.*;

import elipses.lexer.*;
import elipses.node.*;

public class Main
{
  String[] test_files = new String[]{""};
	public static void main(String[] args)
	{
		try
		{
<<<<<<< HEAD
          Debug.debug("test/etapa1/code1.elip");
          Debug.debug("test/etapa1/code2.elip");
          Debug.debug("test/etapa1/code3.elip");
		}
		catch(Exception e) {
            e.printStackTrace();
    	    System.out.println(e.getClass() + e.getMessage());
=======
			String arquivo = "test/number.elip";
			Lexer lexer = new Lexer(
				new PushbackReader(
					new FileReader(arquivo),1024
				)
			); 
			Debug.lexer(lexer);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getClass() + e.getMessage());
>>>>>>> 1567e28fccf00d5e7e9be2809b008684a0aba2c4
		}
	}
}

class Utils {
	public static String pretify(Token token) {
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
<<<<<<< HEAD
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

  public static 
  void debug(String file)
	{
    System.out.println("\nChecking Lexer for file: " + file);
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
=======
	public static void lexer(Lexer lexer) throws LexerException, IOException {
		try 
		{
			Token token;
			while(!((token = lexer.next()) instanceof EOF)) {
	          if (token instanceof TBlank) 
	            continue;
	          System.out.println(Utils.pretify(token));
			}
		}
		catch (Exception e) {
			System.out.println("\nError: " + e.getClass() + e.getMessage() + "\nContinuing with next tokens ...\n");
			Debug.lexer(lexer);
>>>>>>> 1567e28fccf00d5e7e9be2809b008684a0aba2c4
		}
	}
}
