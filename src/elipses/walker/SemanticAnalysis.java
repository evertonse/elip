package elipses.walker;

import elipses.analysis.*;
import elipses.node.*;
import elipses.semantic.SemanticFlags;
import elipses.semantic.SymbolTable;
import elipses.util.ElipLogger;
import elipses.semantic.*;
import elipses.semantic.TypeInference;

import java.util.*;


public class SemanticAnalysis extends DepthFirstAdapter {
    List<SemanticError> errors = new ArrayList<>();
    List<SemanticWarning> warnings = new ArrayList<>();
    Token last_if = null;
    SemanticFlags flags = new SemanticFlags();
    SymbolTable table = new SymbolTable();

    TypeInference inference;
    String first_entry_msg;
    boolean all_ok  = true;

    String filename; 

    public boolean allGood() {
        return errors.size() == 0;
    }

    public SemanticAnalysis(String filename) {
        this.filename = filename;
        SemanticError.setFileName(filename);
        SemanticWarning.setFileName(filename);
        this.inference = new TypeInference(table, errors,warnings, this);
    }
    
    static public void addBuiltinFunctions(SymbolTable table) {
        // real seno(real valor) #ângulo em radiano
        // real cosseno(real valor) #ângulo em radiano
        // real tangente(real valor) #ângulo em radiano
        // real logaritmo(real valor) #base 10
        // real potencia(real base | real expoente) #pode ser adaptada para usar como raiz

        List<Symbol.SignatureParam> seno_parameters = new ArrayList<>();
        seno_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        Symbol.Signature seno_signature = new Symbol.Signature(Symbol.Type.REAL, seno_parameters);  
        table.add("seno", new Symbol("seno", seno_signature));

        List<Symbol.SignatureParam> cosseno_parameters = new ArrayList<>();
        cosseno_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        Symbol.Signature cosseno_signature = new Symbol.Signature(Symbol.Type.REAL, cosseno_parameters);  
        table.add("cosseno", new Symbol("cosseno", cosseno_signature));

        List<Symbol.SignatureParam> tangente_parameters = new ArrayList<>();
        tangente_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        Symbol.Signature tangente_signature = new Symbol.Signature(Symbol.Type.REAL, tangente_parameters);  
        table.add("tangente", new Symbol("tangente", tangente_signature));

        List<Symbol.SignatureParam> logaritmo_parameters = new ArrayList<>();
        logaritmo_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        Symbol.Signature logaritmo_signature = new Symbol.Signature(Symbol.Type.REAL, logaritmo_parameters);  
        table.add("logaritmo", new Symbol("logaritmo", logaritmo_signature));


        List<Symbol.SignatureParam> potencia_parameters = new ArrayList<>();
        potencia_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        potencia_parameters.add(new Symbol.SignatureParam(Symbol.Type.REAL));
        Symbol.Signature potencia_signature = new Symbol.Signature(Symbol.Type.REAL, potencia_parameters);  
        table.add("potencia", new Symbol("potencia", potencia_signature));
    }

    @Override
    public void inStart(Start node) {
        addBuiltinFunctions(table);
    }


   // >> [EXP]
    @Override
    public void inACallExp(ACallExp node) {
        String name = node.getId().getText();
        Token t = node.getId();
            

        if (!table.exists(name)) {
            errors.add(new SemanticError(SemanticErrorType.UNDECLARED, t, " trying to call undeclared function " + node.getId()));
            // need to end right here;
            // can't stablish anything else
            caseEOF(null);
        }

        // we can be sue it exists because we've checked it
        table.get(t.getText()).setToken(t);

        SemanticError error = inference.isCorrectUseOfArgs(name, node.getArgs());
        if ( error != null) {
            errors.add(error);
        }
        // the function is defined
    }

    @Override
    public void outACallExp(ACallExp node) {
        inference.getType(node);
    }

    @Override
    public void caseTIdentifier(TIdentifier node) {
        if (table.exists(node.getText())) {
            table.get(node.getText()).setToken(node);
        }
    }


   // << [EXP]
    @Override
    public void inADeclFunc(ADeclFunc node) {
        String name = node.getIdentifier().getText(); 

        boolean ok = table.add(name, new Symbol(
            name, node.getType().toString(), node.getParam() 
        ));

        if (!ok) {
            errors.add(new SemanticError(SemanticErrorType.ALREADY_DECLARED, node.getIdentifier(), " error on " + node.toString())); 
        }
        assert table.existsInGlobalScope(name);

        Symbol s = table.get(name);
        
        ElipLogger.debug( name + " signature -> "  + s.getSignature());

        table.enterScope();

        TKwEntry entry = node.getKwEntry();
        if (entry != null) {
            if (flags.entry_found == false) {
                flags.entry_found = true;

                first_entry_msg =
                    "\n\t First defined in line " 
                    + entry.getLine() 
                    + " position " + entry.getPos()
                    + " on function '" + node.getIdentifier() + "' with params '" + node.getParam() + " '"
                ;
                for (PParam param : node.getParam()) {
                    if (param instanceof ASignatureParam) {
                        errors.add(
                            new SemanticError( SemanticErrorType.SIGANTURE_ON_ENTRY, node.getIdentifier(), " , theres no way to expect a higher order function given from cmd line! " + param.toString())
                        );
                    }
                }
            } 
            else {
                this.errors.add(new SemanticError(
                    SemanticErrorType.DUPLICATE_ENTRY, entry, first_entry_msg
                ));
                all_ok = false;
            }

        }
       
    };

    
    @Override
    public void outADeclFunc(ADeclFunc node) {
        // This check needs to be down on outNode,
        // Since it need to compute various types
        String func_id = node.getIdentifier().getText(); 
        // Check for expression and return
        Symbol.Type expect = table.get(func_id ).getType();
        Symbol.Type got = null;

        PExp exp = node.getExp();
        Symbol identifier_symbol = inference.getSymbolOrNull(exp);
        if (identifier_symbol != null) {
            if (identifier_symbol.isFunction()) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_RETURN_TYPE, node.getIdentifier(), 
                    " on '" + func_id + "' expected: '" + expect 
                    + "' got function signature '" + identifier_symbol.getSignature() + "'"
                )); 
            }
            else {
                got = identifier_symbol.getType();
            }
        }
        else {
            if (got == null) {
                got = inference.getType(exp); 
            }
            if ( !inference.canApplyCoercion(expect, got)) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_RETURN_TYPE, node.getIdentifier(), 
                    " on '" + func_id + "'' expected: '" + expect + "' got: '" + got + "'" 
                )); 
            }
            else if (expect != got) {
                warnings.add( new SemanticWarning(node.getIdentifier(),
                    "on '" + func_id+  "' Coercion from given '" + got +"' to " + " '" + expect + "'"
                ));

            }

        }
        table.exitScope();
    }
    @Override
    public void caseTKwIf(TKwIf node) {
        last_if = node;
    }

    @Override
    public void inALambdaExp(ALambdaExp node) {
        List<PExp> args = node.getArgs();
        List<TIdentifier> ids  = node.getId();
        int ids_count = ids.size();
        int args_count = args.size();

        if (args_count != ids_count) {
            if (ids_count > 0) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCORRECT_NUMBER_OF_ARGUMENTS, ids.get(0),
                    "on lambda expectd: " + ids_count + ", got: " + args_count 
                ));
            }
            else {
                errors.add(new SemanticError(
                    SemanticErrorType.INCORRECT_NUMBER_OF_ARGUMENTS,
                    "on lambda expectd: " + ids_count + ", got: " + args_count 
                ));
            }
        }

        table.enterScope();
        ElipLogger.debug("lambda entered scope");
        
    }
    @Override
    public void caseALambdaExp(ALambdaExp node)
    {
        this.inALambdaExp(node);
        List<PExp> args = node.getArgs();
        List<TIdentifier> ids  = node.getId();
        int ids_count = ids.size();
        int args_count = args.size();
        int count = ids_count > args_count ? args_count : ids_count;
        for(TIdentifier e : ids)
        {
            e.apply(this);
        }
        for(PExp e : args)
        {
            e.apply(this);
        }
        // We need to add the ids and type on next scope
        // so this need to me on inNode and not outNode, ok?
        for (int i = 0; i < count; i++) {
            String id = ids.get(i).getText(); 
            PExp arg = args.get(i);
            Symbol.Type type;
            if (table.existsInCurrentScope(id)) {
                errors.add(new SemanticError(
                    SemanticErrorType.ALREADY_DECLARED,ids.get(i),
                    "" + node.toString()
                ));
                return;
            }
            // If somewhoe it  finds identitifer we need add NEW symbol by COPY
            // because it might be a function and inferente type checking
            // only return the return type of the function
            Symbol identifier_symbol = inference.getSymbolOrNull(arg);
            if (identifier_symbol != null) {
                table.add(id, new Symbol(identifier_symbol));
                ElipLogger.debug(" Lambda -> added id "+ id + " from already defined symbol :" + identifier_symbol);
            }
            else {
                type = inference.getType(arg); 
                table.add(id, new Symbol(id, type));
                ElipLogger.debug(" Lambda -> added id "+ id + " with type" + type);
            }
        }
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        this.outALambdaExp(node);
    }

    @Override
    public void outALambdaExp(ALambdaExp node) {
       ElipLogger.debug("lambda exited scope");
       table.exitScope(); 
    }

    @Override
    public void inASignature(ASignature node) {

        if (node.getIdentifier() == null) {
            return;
        }

        Token t = node.getIdentifier();
        String name = node.getIdentifier().getText();

        if (table.existsInCurrentScope(name)) {
           errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, t, " on parameter  '" + node.toString() + "'"
            )); 
        }
        else {
            table.add(name, new Symbol(name, node.getType().toString(), node.getSignatureParam(), true));
            ElipLogger.debug(table.get(name).getSignature().toString());
        }
    }

    @Override
    public void inATypeParam(ATypeParam node) {
        if (node.getIdentifier() == null) {
            return;
        }

        
        Token t = node.getIdentifier();
        String name = node.getIdentifier().getText();

        assert !table.existsInCurrentScope(name);

        // If the current parameters already exists in current scope 
        // i.e. the function scope, then it's a error, it is an already 
        // declared parameter
        if (table.existsInCurrentScope(name)) {
           errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, t, " on parameter " + node.toString()
            )); 
        }
        else {
            table.add(name, new Symbol(name, node.getType().toString()));

        }
    }

    @Override
    public void defaultOut(Node node) {
        if(node  instanceof PExp ) {
            inference.getType((PExp)node);
            // check for something general about exp
        }
    }
    
    @Override
    public void inABlockExp(ABlockExp node) {
        // Is the resposability of whoever need blockexp to
        // make shure it's the correct type
        ElipLogger.debug("enteredd scope in a block");
        table.enterScope();
    }    

    @Override
    public void outABlockExp(ABlockExp node) {
        //table.printAllScopes();
        table.exitScope();
        ElipLogger.debug("exited scope in a block");
    }    

    @Override
    public void inADeclConst(ADeclConst node) {
        assert node.getIdentifier() != null;

        String name = node.getIdentifier().getText().strip();
        
        boolean exist = table.existsInCurrentScope(name);

        ElipLogger.debug(name + exist );
        if (exist) {
            errors.add(new SemanticError(
                SemanticErrorType.ALREADY_DECLARED, node.getIdentifier(),
                "on '" + node.toString() + "'"
                ));
            return;
        }

    }

    @Override
    public void outADeclConst(ADeclConst node) {
        // After the declaration is finished then we get the type and add to the table
        String name = node.getIdentifier().getText().strip();
        table.add(name, new Symbol(name, node.getType().toString()));
        Symbol.Type expect = inference.fromPType(node.getType());
        Symbol.Type got = null;

        PExp exp = node.getExp();
        Symbol identifier_symbol = inference.getSymbolOrNull(exp);

        if (identifier_symbol != null) {
            if (identifier_symbol.isFunction()) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, node.getIdentifier(), 
                    " on '" + name + "' declaration const: expected: '" + expect + "' got function signature '" + identifier_symbol.getSignature() + "'"
                )); 
            }
            else {
                got = identifier_symbol.getType();
            }
        }
        else {
            if (got == null) {
                got = inference.getType(exp); 
            }
            if ( !inference.canApplyCoercion(expect, got)  ) {
                errors.add(new SemanticError(
                    SemanticErrorType.INCOMPATIBLE_TYPES, node.getIdentifier(), 
                    " on '" + name + "' declaration const: expected: '" + expect + "' got: " + got 
                )); 
            }
            else if (expect != got) {
                warnings.add( new SemanticWarning(
                    node.getIdentifier(),
                    "on '" + name+  "' Coercion from given '" + got +"' to " + " '" + expect + "'"
                ));
            }
        }

        ElipLogger.debug(table.get(name).toString());

    }

    @Override
    public void caseEOF(EOF node) {
        if(flags.entry_found == false) {
            errors.add(new SemanticError(
                SemanticErrorType.NO_ENTRY," missing 'entrada'"
            ));    
        }
        
        for (SemanticError e : errors) {
            e.inform();
        }

        for (SemanticWarning w : warnings) {
            w.inform();
        }

        if (!allGood()){
            System.exit(69);
        } 
    }
    

}
