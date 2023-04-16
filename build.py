srcpath = "src/"
package = "elipses"
bin     = 'elip'
bindir  = "bin/"
classpath = bindir
sablejar = "vendor/sablecc.jar"
mainclass = package + ".Main"

sablegrammar = "src/ElipsesCalc.sable"
sablegrammar = "src/ElipsesC99.sable"
sablegrammar = "docs\grammar-examples\grace.sable"
sablegrammar = "src/grupo_10.sable"
sablegrammar = "src/Elipses.sable"

autorun = True 
runargs = [
    r"test/analysis/exp.elip",
    r"test/etapa1/code2.elip",
    r"test/etapa1/code3.elip"
]