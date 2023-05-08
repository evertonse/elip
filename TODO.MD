 # TODO
- [x] Acompanha o significado dos nomes estabelecidos
nas declarações
- [x] Efetua inferência de tipos
- [x] Verifica consistência de tipos em expressões e
declarações
- [x] Inferencia & Checagem de tipos
- [x] Parametro com mesmo identifier
- [x] Function com mesmo identifier
- [x] Const declaration com mesmo identifier
- [x] Check if right hand side is the expected type in a const declt
- [x] Check if return is the same type and signature
- [x] Test for double entry
- [x] Warn about operating real with integer
- [x] Add SignatureParam identifier to the symbol table
- [x] Check arguments (number of them and their types) when passing to a function
- [ ] Ask yourself, do we need to delete from the stack when it goes outta scope? or just decrement a pointer? Better yet create a tree
- [x] In CCODE gen if type can't be determined we call simply operate as float, just in case
- [ ] Check for type inference on nested lambdas, or nested block scopes
- [x] Listen you gotta check for passing a function name expression to lambda, in Elipses any id is allowed to pass as expression 

- [ ] Lambda: pass as argument a lambda
- [x] Labmda: pass as argument functions 
- [x] Labmda: use another lambda in the body of a lambda 
- [x] Labmda: make sure as function can be used and arguments for a labmda
- [ ] Lambda: Make sure the identifier shadows outter scope identifier
- [ ] Lambda: Be certain that whenever a lambda_exp is used that it takes into account that the return of a lambda can be an identifier and therefore the type inference of the lambda has to take into account the possibility that is a signature type
- [ ] Show case if a function call is being used on something that is not a function
- [ ] IFEXP check if we can return a identifier from if and if we can put an identifier of a function as both func pointer and call exp to an if condition exp;
- [ ] IFEXP check is signature of truthy and falsy are the same type
- [ ] Make sure signature inference works for nested blocks
- [ ] Make sure signature inference works for nested lambdas 
- [ ] Make sure signature inference works arbitratily complex function signatures
- [x] teste decl const return type ad add error if return type is a function and its not a callexp
- [x] add type coercion in in return, in call and declare const
- [ ] add checking for signature on main function
- [ ] Make sure operation on mod, is only defined for integer, see what what be the best for div as well
