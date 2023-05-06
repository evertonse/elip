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
    #"test/codegen/block.elip"
    "test/codegen/lambda.elip"
]

semantic_files = [
    "test/semantic/incorrect_duplicate_entry.elip",
    #"test/semantic/already_defined.elip",
    #"test/semantic/undeclared.elip",
    #"test/semantic/signature.elip",
    #"test/semantic/incorrect_args.elip",
    #"test/semantic/incorrect_return_type.elip",
    #"test/semantic/incorrect_decl_const_type.elip",
    #"test/semantic/correct_builtins.elip",
    #"test/semantic/incorrect_lambda_args.elip",
    #"test/semantic/incorrect_use_of_bool.elip",
]

runargs = [
    #"--gui",
    "--c",
    #"--exe",
    *semantic_files
    #*codegen_files
]