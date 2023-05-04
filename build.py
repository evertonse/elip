srcpath = "src/"
package = "elipses"
bin     = 'elip'
bindir  = "bin/"
classpath = bindir
sablejar = "vendor/sablecc.jar"
mainclass = package + ".Main"

sablegrammar = "src/ElipsesCST.sable"
sablegrammar = "src/Pedro.sable"
sablegrammar = "src/Elipses.sable"

autorun = True 
runargs = [
    #"--gui",
    #"test/semantic/duplicate_entry.elip",
    #"test/semantic/already_defined.elip",
    #"test/semantic/undeclared.elip",
    #"test/semantic/signature.elip",
    #"test/semantic/incorrect_args.elip",
    #"test/semantic/incorrect_return_type.elip",
    #"test/semantic/incorrect_lambda_args.elip",
    "test/semantic/incorrect_use_of_bool.elip",
    #"test/IR/ir.elip",
    #"test/IR/block.elip",
    #"test/analysis/exp.elip",
    #r"test/etapa1/code2.elip",
    #r"test/etapa1/code3.elip"
]