package elipses.semantic;
import elipses.node.*;
import elipses.util.ElipLogger;

import java.util.ArrayList;
import java.util.List;



public class Symbol {

    static public enum Type {
        UNKOWN, INT,BOOL,REAL, FUNCTION, COUNT;

        @Override
        public String toString() {
            switch (this) {
                case UNKOWN: return "unkown";
                case INT: return "inteiro";
                case BOOL: return "booleano";
                case REAL: return "real";
                case FUNCTION: return "function type";
                default: return "<default>";
            }
        }
    }

    static public class SignatureParam {
        Type type = null;
        Signature sig = null;

        public SignatureParam(Type type) {
            this.type = type;
        }    

        public SignatureParam(Signature sig) {
            this.sig = sig;
        }    
        public Signature getSignature() {
            return sig;
        }
        public Type getType() {
            return type;
        }

    }

    static public class Signature {
        Type return_type;
        List<SignatureParam> parameters = new ArrayList<>();

        public Signature (Type return_type, List<SignatureParam> params) {
            this.return_type = return_type;
            this.parameters = params;
        }

        public boolean equals(Signature other) {
            if (return_type != other.return_type) {
                return false;
            }
            if (parameters.size() != other.parameters.size()) {
                return false;
            }
            for (int i = 0; i < parameters.size(); i++) {
                SignatureParam this_param = parameters.get(i);
                SignatureParam other_param = other.parameters.get(i);
                // Here param.type can be null, which means this param is 
                // a Signature, so if they are differnte, is done
                if (this_param.type != other_param.type) {
                    return false;
                }
                if (this_param.sig == null && other_param.sig == null) {
                    continue;
                }
                // If we get here, it means one of them arent null, which
                // means they're different
                if (this_param.sig == null || other_param.sig == null) {
                    return false;
                }
                // Otherwise, recursively check if it is equal
                if (!this_param.sig.equals(other_param.sig)) {
                    return false;
                }
            }
            // If all cases havent return , then it passes everything, return true
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(return_type);
            sb.append(" (");
            for (int i = 0; i < parameters.size(); i++) {
                if (i > 0) {
                    sb.append("|");
                }
                SignatureParam param = parameters.get(i);
                if (param.sig != null) {
                    sb.append(param.sig);
                } else {
                    sb.append(param.type);
                }
            }
            sb.append(")");
            return sb.toString();
        }
        
        public List<SignatureParam> getParameters() {
            return parameters;
        }
    }




    private String name;
    private Type type;
    private Signature signature;
    private int value;
    private Token token;


    public Symbol(Symbol other) { 
        this.name = other.name;
        this.type = other.type;
        this.signature = other.signature;
        this.value = other.value;
        this.token = other.token;
    }

    public Symbol(String name, Signature signature) {
        this.name = name;
        this.type = Type.FUNCTION;
        this.signature = signature;
    }

    public Symbol(String name, String return_type, List<PParam> params) {
        this.name = name;
        this.type = Type.FUNCTION;
        this.signature = toSignature(toType(return_type),params);
    }

    public Symbol(String name, String return_type, List<PSignatureParam>  params, boolean isarg) {
        this.name = name;
        this.type = Type.FUNCTION;
        this.signature = toSignatureFromPSignatureParam(toType(return_type),params);
    }


    public Symbol(String name, Type type) {
        this.name = name.strip();
        this.type = type;
    }

    public Symbol(String name, String type) {
        this.name = name.strip();
        this.type =  toType(type.strip());
    }

    private Type toType(String type) {
        type = type.strip();
        if (type.equalsIgnoreCase("inteiro")) {
            return Type.INT;
        }
        else if (type.equalsIgnoreCase("real")) {
            return Type.REAL;
        }
        else if (type.equalsIgnoreCase("booleano")) {
            return Type.BOOL;
        }
        else {
            assert false; // You fucked up
            ElipLogger.debug(" Unkown type at construction of an object, this aint right");
            return Type.UNKOWN;
        }
    }

    Signature toSignatureFromPSignatureParam(Type return_type, List<PSignatureParam> params) {
    List<SignatureParam> signature_params = new ArrayList<>();
        for (PSignatureParam param : params) {
            if (param instanceof ATypeSignatureParam) {
                ATypeSignatureParam node = (ATypeSignatureParam)param;
                signature_params.add(new SignatureParam(toType(node.getType().toString())));
            } 
            else if (param instanceof ASignatureSignatureParam) {
                ASignatureSignatureParam node = (ASignatureSignatureParam)param;
                PSignature sig_param = node.getSignature();
                if (sig_param instanceof ASignature){
                   ASignature sig_param_node = (ASignature)sig_param ;
                   signature_params.add(new SignatureParam(toSignature(sig_param_node)));
                }
            }
            else {

                ElipLogger.debug(" PParam should always be an instanceof one of those two above, how did this happens?");
                System.exit(69);
            }
        }
        return new Signature(return_type, signature_params);
    }

    // @important Both toSignature deserver full well testing;
    Signature toSignature(Type return_type, List<PParam> params) {
        List<SignatureParam> signature_params = new ArrayList<>();
        for (PParam param : params) {
            if (param instanceof ATypeParam) {
                ATypeParam node = (ATypeParam)param;
                signature_params.add(new SignatureParam(toType(node.getType().toString())));
            } 
            else if (param instanceof ASignatureParam) {
                ASignatureParam node = (ASignatureParam)param;
                PSignature sig_param = node.getSignature();
                if (sig_param instanceof ASignature){
                   ASignature sig_param_node = (ASignature)sig_param ;
                   signature_params.add(new SignatureParam(toSignature(sig_param_node)));
                }
            }
            else {

                ElipLogger.debug(" PParam should always be an instanceof one of those two above, how did this happens?");
                System.exit(69);
            }
        }
        return new Signature(return_type, signature_params);
    }
    
    Signature toSignature(ASignature node) {

        Type type = toType(node.getType().toString());
        List<PSignatureParam>  params = node.getSignatureParam();
        List<SignatureParam>  sig_params = new ArrayList<>();

        for (PSignatureParam param: params) {
            if (param instanceof ATypeSignatureParam){
                ATypeSignatureParam p = (ATypeSignatureParam) param;
                sig_params.add(new SignatureParam(toType(p.getType().toString()))); 
            }

            else if (param instanceof ASignatureSignatureParam) {

                ASignatureSignatureParam p = (ASignatureSignatureParam) param;
                sig_params.add(
                    new SignatureParam(
                        toSignature( (ASignature)p.getSignature())
                    )
                ); 

            }
        }
        return new Signature(type, sig_params);


    }

    public String getName() {
        return name;
    }

    public Type getType() {
        assert type != Type.UNKOWN;

        if (this.type == Type.FUNCTION) {
            return this.signature.return_type;
        }
        
        return type;
    }

    public Signature getSignature() {
       
        return this.signature;
    }
    public boolean isFunction() {
        return this.type == Type.FUNCTION;
    }

    public Token getToken() {
        return this.token;
    }
    public Token setToken(Token token) {
        return this.token = token;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (this.isFunction()) {
            return this.name + "->" + this.signature.toString();
        }
        return this.name + "->" + this.type.toString();
    }

}
    
