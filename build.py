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
sablegrammar = "src/Elipses.sable"

autorun = True 
runargs = [
    r"test/analysis/exp.elip"
]