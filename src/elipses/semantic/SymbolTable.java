package elipses.semantic;

import java.util.HashMap;
import java.util.Stack;



public class SymbolTable {
    private Stack<HashMap<String, Symbol>> stack;

    public SymbolTable() {
        stack = new Stack<>();
        stack.push(new HashMap<>());
    }

    public void enterScope() {
        stack.push(new HashMap<>());
    }

    public void exitScope() {
        stack.pop();
    }

    public boolean add(String name, Symbol symbol) {
        HashMap<String, Symbol> current_scope = stack.peek();
        if (current_scope.containsKey(name)) {
            return false; // Symbol with the same name already exists
        } else {
            current_scope.put(name, symbol);
            return true; // Symbol added successfully
        }
    }

    public boolean add(Symbol symbol) {
        HashMap<String, Symbol> current_scope = stack.peek();
        if (current_scope.containsKey(symbol.getName())) {
            return false; // Symbol with the same name already exists
        } else {
            current_scope.put(symbol.getName(), symbol);
            return true; // Symbol added successfully
        }
    }

    public Symbol get(String name) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            HashMap<String, Symbol> current_scope = stack.get(i);
            if (current_scope.containsKey(name)) {
                return current_scope.get(name);
            }
        }
        return null; 
    }

    public boolean remove(String name) {
        HashMap<String, Symbol> current_scope = stack.peek();
        if (current_scope.containsKey(name)) {
            current_scope.remove(name);
            return true; 
        }
        else {
            return false;
        }
    }

    // If exists in current scope
    public boolean existsInCurrentScope(String name) {
        HashMap<String, Symbol> current_scope = stack.peek();
        return current_scope.containsKey(name);
    }

    // If exists in any scope 
    public boolean exists(String name) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).containsKey(name)) {
                return true;
            }
        }
        return false;
    }

    // Hashes a string using the djb2 algorithm
    private int hash(String s) {
        int hash = 5381;
        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 5) + hash) + s.charAt(i);
        }
        return hash;
    }
}
    
