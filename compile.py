import os
from glob import glob
from pathlib import Path
from shutil import copyfile


####################################################################################################################
####################################################################################################################
EXCLUDE_FOLDERS: set = {"bin", "test", "vendor", "__pycache__", "docs"}

def allfiles(start: Path, extension:str, ignore:set) -> list:
  """
  @return all files with extension 'extension' recursevely find from 'start'
  @input 'start' is the dir which we'll start the walk 
  @input 'extension' should include the dot '.'
  @input 'ignore' is a iterable that contains folders that should be ignored
  """
  files : list = [
      Path(y)
      for x in os.walk(Path(start))
      for y in glob(os.path.join(x[0], f"*{extension}"))
      if not any([y.count(e) > 0 for e in ignore])
  ]
  return files

classpath = Path("./bin")
srcpath   = Path("./src")

mainclass = "Elipses.Main"
argsfilename: str = "java.args"

javafiles: list =  allfiles(start=srcpath, extension='.java', ignore=EXCLUDE_FOLDERS)
datfiles:  list =  allfiles(start=srcpath, extension='.dat', ignore=EXCLUDE_FOLDERS)
txtfiles:  list =  allfiles(start=srcpath, extension='.txt', ignore=EXCLUDE_FOLDERS)

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
    print(f"INFO .dat files moved ")
    for file in datfiles:
        old = Path(file)
        new = Path(str(file).replace(str(srcpath), str(classpath)))

        print(f"\tfrom: {old}\n\tinto: {new}")

        new.touch(exist_ok=True)
        copyfile(src=Path(old), dst=Path(new))


if len(datfiles) > 0:
    print(f"INFO .dat files moved ")
    for file in datfiles:
        old = Path(file)
        new = Path(str(file).replace(str(srcpath), str(classpath)))

        print(f"\tfrom: {old}\n\tinto: {new}")

        new.touch(exist_ok=True)
        copyfile(src=Path(old), dst=Path(new))


cmd_run: str = f'java -classpath "{classpath}" {mainclass}'
if code == 0:
    print(cmd_run)
    code: int = os.system(cmd_run)
