# Build

Rebuild Sabblecc ``.java`` files, Compile Project and Run by one of the following from root of the project

    python3 run.py


Needs java 11 version at least to compile and run.

# To run as a script first do ``chmod +x ./run.py`` first

    ./run.py


# Add the Compiler to Path
- First run the command ``chmod +x ./bin/elipc`` to let the ``elipc`` be used as an executable.
- Add the below export to your `~/.bashrc`, or equivalente
- `export PATH=<path-where-you've-cloned-this-repo>/elip/bin:$PATH`

# Usage
- `elipc --c file.elip` to compile into C code, already the default behaviour
- `elipc --exe file.elip` to  compile into executable using gcc
- `elipc --gui file.elip` to see AST in gui
- `elipc --ast file.elip` to see AST in console


# Add the Compiler to Path (Windows)
- Add ``<path-where-you've-cloned-this-repo>/elip/bin``



# Propriedades
- Main function might take any number of args as long as it is a basic type ``inteiro`` ``real`` ``boleano``
