package elipses;

import elipses.analysis.*;
import elipses.node.*;
import java.util.*;
import java.io.*;

public class CCode extends DepthFirstAdapter {

  private Stack parents = new Stack();
  PrintWriter out;
 
  public CCode() {
    try{
        out = new PrintWriter("weball.c");
     }
     catch(IOException exc) {
       System.out.println(exc);
     }
  }

  public void outStart(Start node) {

  }

  /*
   * As we come across non terminals, push them onto the stack
   */
  public void defaultIn(Node node) {
    out.append(node.toString());
    out.flush();
      System.out.println(node.toString());
  }

  /*
   * As we leave a non terminal, it's parent is the node before it
   * on the stack, so we pop it off and add it to that node
   */
  public void defaultOut(Node node) {
    out.append("\n");
    out.flush();
  }

  /*
   * Terminals - our parent is always on the top of the stack, so we
   * add ourselves to it
   */
  public void defaultCase(Node node) {
  }

  public void caseEOF(EOF node) {}
    @Override
    protected void finalize() {
            out.flush();
            out.close();
    }
}
