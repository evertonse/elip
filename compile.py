import os
from glob import glob
from pathlib import Path
from shutil import copyfile
import build

####################################################################################################################
####################################################################################################################
EXCLUDE_FOLDERS: set = {"bin", "test", "vendor", "__pycache__", "docs"}

def dos2unix(filepath):
    with open(filepath, "r+b") as file:
        contents = file.read().replace(b"\r\n", b"\n")
        file.seek(0)
        file.write(contents)
        file.truncate()

def allfiles(start: Path, extension: str, ignore: set) -> list:
    """
    @return all files with extension 'extension' recursevely find from 'start'
    @input 'start' is the dir which we'll start the walk
    @input 'extension' should include the dot '.'
    @input 'ignore' is a iterable that contains folders that should be ignored
    """
    files: list = [
        Path(y)
        for x in os.walk(Path(start))
        for y in glob(os.path.join(x[0], f"*{extension}"))
        if not any([y.count(e) > 0 for e in ignore])
    ]
    return files


flags = {"run": build.autorun or False, "shellbin": True}

classpath = Path(build.bindir or None)
srcpath = Path(build.srcpath) or None
if srcpath is None:
    print("INFO srcpath is not define, can't compile build.py")
    exit(1)

mainclass = build.mainclass or None
if mainclass is None:
    print("INFO mainclass is not defined, will only compile and not run")
    flags["run"] = False

argsfilename: str = "java.args"

javafiles: list = allfiles(
    start=srcpath, extension=".java", ignore=EXCLUDE_FOLDERS)
datfiles: list = allfiles(
    start=srcpath, extension=".dat", ignore=EXCLUDE_FOLDERS)
txtfiles: list = allfiles(
    start=srcpath, extension=".txt", ignore=EXCLUDE_FOLDERS)

os.makedirs(classpath, exist_ok=True)
####################################################################################################################
####################################################################################################################


# Adiciona a lista de arquivos para compila-sourcepath "./"r
# apenas se o path do arquivo não contem os folders
# especificados em EXCLUDE_FOLDERS
with open(argsfilename, "w+") as f:
    for javafile in sorted(javafiles):
        f.write(f"{javafile} \n")

cmd_compile: str = (
    f'javac -classpath  "{classpath}" -sourcepath "{srcpath}" -d "{classpath}" -encoding UTF-8 '
    + f"@{argsfilename}"
)

print(cmd_compile)
code: int = os.system(cmd_compile)


if len(datfiles) > 0:
    print("INFO .dat files moved ")
    for file in datfiles:
        old = Path(file)
        new = Path(str(file).replace(str(srcpath), str(classpath)))

        print(f"\tfrom: {old}\n\tinto: {new}")

        new.touch(exist_ok=True)
        copyfile(src=Path(old), dst=Path(new))


if len(datfiles) > 0:
    print("INFO .dat files moved ")
    for file in datfiles:
        old = Path(file)
        new = Path(str(file).replace(str(srcpath), str(classpath)))

        print(f"\tfrom: {old}\n\tinto: {new}")

        new.touch(exist_ok=True)
        copyfile(src=Path(old), dst=Path(new))



if flags['shellbin']:
    shell_bin = Path(build.bindir, build.bin + '.cmd')
    print(f"INFO: Creating shellbin at {shell_bin}")
    shell_bin.touch()
    shell_bin.write_text(
          '@echo off\n'
        + 'setlocal\n'
        +f'java -classpath %~dp0 {mainclass} %*\n'
        + 'endlocal'
    )

    shell_bin = Path(build.bindir, build.bin)
    shell_bin.touch()
    shell_bin.write_text(
        '#!/usr/bin/env bash\n'
        +'script_path="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"\n'
        +f'exec java -classpath ${{script_path}} {mainclass} "$@"\n'
    )
    dos2unix(shell_bin)


if flags["run"]:
    cmd_run: str = f'java -classpath "{classpath}" {mainclass} {" ".join(build.runargs) or ""}'
    if code == 0:
        print(cmd_run)
        code: int = os.system(cmd_run)