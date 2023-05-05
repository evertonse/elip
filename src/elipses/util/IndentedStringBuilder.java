package elipses.util;
import java.lang.StringBuilder;
import java.util.Stack;

/**
 * IndentedStringBuffer
 */

public class IndentedStringBuilder {

    private StringBuilder sb;
    private String indentation;
    private int indentation_level;
    Stack<Integer> index_stack = new Stack<>();
    
    public IndentedStringBuilder() {
        sb = new StringBuilder();
        indentation = "";
        index_stack.add(0);
    }

    public IndentedStringBuilder(IndentedStringBuilder other) {
        sb = new StringBuilder();
        this.indentation_level = other.indentation_level;
        this.indentation = other.indentation.substring(0);
        this.index_stack = other.index_stack;
    }
        
    public IndentedStringBuilder pushIndex() { 
        this.index_stack.push(this.size());
        return this;
    }

    public IndentedStringBuilder popIndex() { 
        this.index_stack.pop();
        return this;
    }

    public int getCurrentIndex() {
        return this.index_stack.peek();
    }

    public IndentedStringBuilder pushIndent() {
        this.indentation_level++;
        this.indentation = " ".repeat(this.indentation_level * 4);
        return this;
    }
    
    public IndentedStringBuilder popIndent() {
        if (this.indentation_level > 0) {
                this.indentation_level--;
                this.indentation = " ".repeat(this.indentation_level * 4);
            }
        return this;
    }

    public IndentedStringBuilder insertAtCurrentIndex(String str) {
        int i = index_stack.peek();
        this.insert(i,str);
        return this;
    }
    
    public IndentedStringBuilder append(String str) {

        int index = sb.length() -1; 
        if (index >= 0 ) {
            char previous_char = sb.charAt(index);
            if (previous_char  == '\n') {
                sb.append(indentation); 
            }
        }

        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);    
            sb.append(c);
            if (c == '\n' && i < len -1) {
                sb.append(indentation); 
            }
        }
        return this;
    }

    public IndentedStringBuilder insertAtBeginningOfPreviousLine(String str) {
        int index = sb.lastIndexOf("\n");
        if (index == -1) {
            sb.append(this.indentation +str);
        } else {
            sb.insert(index + 1, this.indentation + str);
        }
        return this;
    }

    public IndentedStringBuilder insertAtPreviousLine(String str) {
        int index = sb.lastIndexOf("\n");
        if (index == -1) {
            sb.append(this.indentation +str);
        } else {
            sb.insert(index + 1, this.indentation + str);
        }
        return this;
    }
    public IndentedStringBuilder insert(int index, String str) {
        if (index < 0) {
            index = 0;
        }
        sb.insert(index, this.indentation + str);
        return this;
    }
    
    public IndentedStringBuilder appendln(String str) {
        this.append(str);
        sb.append("\n");
        return this;
    }

    public IndentedStringBuilder clear() {
        sb.setLength(0);
        return this;
    }
    
    public String toString() {
        return sb.toString();
    }
    public int size() {
        return sb.length();
    }
}