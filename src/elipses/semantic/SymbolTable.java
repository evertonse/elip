package elipses.semantic;

import java.util.Map;
import java.util.Hashtable;
import java.util.Stack;



public class SymbolTable {
    private Stack<Map<String, Symbol>> stack;
    private Map<String, Symbol> global;

    public SymbolTable() {
        stack = new Stack<>();
        stack.push(new Hashtable<>());
        global = stack.peek();
    }

    public void enterScope() {
        stack.push(new Hashtable<>());
    }

    public void exitScope() {
        stack.pop();
    }

    public boolean add(String name, Symbol symbol) {
        Map<String, Symbol> current_scope = stack.peek();
        if (current_scope.containsKey(name)) {
            return false; // Symbol with the same name already exists
        } else {
            current_scope.put(name, symbol);
            return true; // Symbol added successfully
        }
    }

    public boolean add(Symbol symbol) {
        Map<String, Symbol> current_scope = stack.peek();
        if (current_scope.containsKey(symbol.getName())) {
            return false; // Symbol with the same name already exists
        } else {
            current_scope.put(symbol.getName(), symbol);
            return true; // Symbol added successfully
        }
    }

    public Symbol get(String name) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Map<String, Symbol> current_scope = stack.get(i);
            if (current_scope.containsKey(name)) {
                return current_scope.get(name);
            }
        }
        return null; 
    }

    public boolean remove(String name) {
        Map<String, Symbol> current_scope = stack.peek();
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
        Map<String, Symbol> current_scope = stack.peek();
        boolean exist = current_scope.containsKey(name);
        return exist;
    }

    public Symbol getInCurrentScope(String name) {
        Map<String, Symbol> current_scope = stack.peek();
        return current_scope.getOrDefault(name, null);
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

    // If exists in Global Scope
    public boolean existsInGlobalScope(String name) {
        if (global.containsKey(name)) {
            return true;
        }
        return false;
    }

    public Symbol getInGlobalScope(String name) {
        return global.getOrDefault(name,null);
    }

    public void printCurrentScope() {
        Map<String, Symbol> current_scope = stack.peek();
        System.out.println("Current scope:");
        for (String name : current_scope.keySet()) {
            System.out.println(name + ": " + current_scope.get(name));
        }
    }

    public void printAllScopes() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Map<String, Symbol> current_scope = stack.get(i);
            int depth = stack.size() - i - 1;
            String indent = "  ".repeat(depth);
            System.out.println(indent + "Scope " + (i + 1) + ":");
            for (String name : current_scope.keySet()) {
                Symbol symbol = current_scope.get(name);
                System.out.println(indent + "  " + name + ": " + symbol.getType());
            }
        }
    }

}
    
