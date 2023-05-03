package elipses.semantic;
import elipses.analysis.*;
import elipses.node.*;


public class Symbol {
    
    private String name;
    private PType type;
    private int value;

    public Symbol(String name, PType type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Symbol(String name, PType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
    
