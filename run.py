#!/usr/bin/env python3
# -*- coding: utf-8 -*-
from subprocess import run
from os import mkdir
from pathlib import Path
from shutil import rmtree as rmdir



####################################################################################################################
####################################################################################################################
base_path = Path("src/Elipses")
dirs = [Path(base_path, i) for i in {'analysis', 'lexer', 'node', 'parser'}]
dirs.append(Path('bin'))

sablecc_jar_path        = Path("vendor/sablecc.jar")
sablecc_grammar_path    = Path("src/Elipses.sable")
####################################################################################################################
####################################################################################################################


def refresh_dirs(dirs: Path):
  for dir in dirs:
    if dir.exists():
      result = rmdir(dir)
      print(f'INFO deleted dir {dir}, result code {result }')
      result = mkdir(dir)
      print(f'INFO mkdir {dir}, result code {result }')

def check_result(result:str, project:str = "Project"): 
  if result.returncode != 0 :
    print(f"Error {result.returncode} on compiling {project}... ")
    exit(1)
  else:
    print(f"Success {result.returncode} on compiling {project}... ")

def __main__():
  global sablecc_grammar_path,sablecc_jar_path,dirs,base_path
  refresh_dirs(dirs=dirs)
  
  # Method 1 - find the main sablecc manually
  #cmd = f"java -cp {sablecc_jar_path} org.sablecc.sablecc.SableCC src/calculadora.sable"
  # Method 2 - pass the flag -jar and let java find the main
  cmd = f"java -cp bin -jar {sablecc_jar_path} {sablecc_grammar_path}"
  print(f'INFO running cmd: \n{cmd}')

  result: int = run(cmd)
  check_result(result, project="SableCC")

  #import compile
  result: int = run("python compile.py")
  check_result(result)


if __name__ == '__main__':
  __main__()