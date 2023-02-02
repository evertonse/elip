# from os import system as run
from subprocess import run
from os import mkdir
from pathlib import Path
from shutil import rmtree as rmdir
import build


##############################################################################
##############################################################################
base_path = Path(build.srcpath, build.name)

refreshdirs = [Path(base_path, i)
               for i in {"analysis", "lexer", "node", "parser"}]
refreshdirs.append(Path(build.bindir))

sablecc_jar_path = Path(build.sablejar)
sablecc_grammar_path = Path(build.sablegrammar)
###############################################################################
###############################################################################


def refresh_dirs(dirs: list):
    for dir in dirs:
        if dir.exists() and dir.is_dir():
            result = rmdir(dir)
            print(f"INFO deleted dir {dir}, result code {result }")
            result = mkdir(dir)
            print(f"INFO mkdir {dir}, result code {result }")


def check_result(result: str, project: str = "Project"):
    if result.returncode != 0:
        print(f"Error {result.returncode} on compiling {project}... ")
        exit(1)
    else:
        print(f"Success {result.returncode} on compiling {project}... ")


def __main__():
    global sablecc_grammar_path, sablecc_jar_path, refreshdirs, base_path
    refresh_dirs(dirs=refreshdirs)

    # Method 1 - find the main sablecc manually
    # cmd = f"java -cp {sablecc_jar_path} org.sablecc.sablecc.SableCC {sablecc_grammar_path}"
    # Method 2 - pass the flag -jar and let java find the main
    cmd = ["java", "-jar", str(sablecc_jar_path), str(sablecc_grammar_path)]
    print(f"INFO running cmd: \n{cmd}")

    result: int = run(cmd)
    check_result(result, project="SableCC")

    import compile

    # result: int = run("python compile.py")
    # check_result(result)


if __name__ == "__main__":
    __main__()
