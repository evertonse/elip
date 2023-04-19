srcpath = "src/"
package = "elipses"
bin     = 'elip'
bindir  = "bin/"
classpath = bindir
sablejar = "vendor/sablecc.jar"
mainclass = package + ".Main"

sablegrammar = "src/ElipsesCST.sable"
sablegrammar = "src/Elipses.sable"

autorun = True 
runargs = [
    #"--gui",
    #r"test/IR/ir.elip",
    r"test/IR/block.elip",
    #r"test/analysis/exp.elip",
    #r"test/etapa1/code2.elip",
    #r"test/etapa1/code3.elip"
]