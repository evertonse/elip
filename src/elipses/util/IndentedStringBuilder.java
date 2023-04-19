package elipses.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serial;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.lang.StringBuilder;

/**
 * IndentedStringBuffer
 */

public class IndentedStringBuilder {

    private StringBuilder sb;
    private String indentation;
    private int indentation_level;
    
    public IndentedStringBuilder() {
        sb = new StringBuilder();
        indentation = "";
    }

    public IndentedStringBuilder(IndentedStringBuilder other) {
        sb = new StringBuilder();
        this.indentation_level = other.indentation_level;
        this.indentation = other.indentation.substring(0);
    }
    
    public IndentedStringBuilder pushIndent() {

        this.indentation_level++;
        this.indentation = " ".repeat(this.indentation_level * 4);
        return this;
    }
    
    public  IndentedStringBuilder popIndent() {
        if (this.indentation_level > 0) {
                this.indentation_level--;
                this.indentation = " ".repeat(this.indentation_level * 4);
            }
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