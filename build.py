srcpath = "src/"
package = "elipses"
bin     = 'elipc'
bindir  = "bin/"
classpath = bindir
sablejar = "vendor/sablecc.jar"
mainclass = package + ".Main"

sablegrammar = "src/ElipsesCST.sable"
sablegrammar = "src/Elipses.sable"
autorun = True 

codegen_files = [
    "examples/codegen/block.elip",
    #"examples/codegen/function_references.elip",
    #"examples/codegen/lambda.elip",
    #"examples/codegen/ir.elip",
]

semantic_files = [
    "examples/semantic/incorrect_duplicate_entry.elip",
    #"examples/semantic/incorrect_already_defined.elip",
    #"examples/semantic/incorrect_undeclared.elip",
    #"examples/semantic/incorrect_operation.elip",
    #"examples/semantic/incorrect_if_expr.elip",
    #"examples/semantic/incorrect_args.elip",
    #"examples/semantic/incorrect_return_type.elip",
    #"examples/semantic/incorrect_decl_const_type.elip",
    #"examples/semantic/correct_builtins.elip",
    #"examples/semantic/incorrect_lambda_args.elip",
    #"examples/semantic/incorrect_use_of_bool.elip",
]

runargs = [
    #"--gui",
    #"--c",
    #"--exe",
    #*semantic_files
    *codegen_files
]
