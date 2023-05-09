# Rebuild Sabblecc ``.java`` files, Compile Project and Run by one of the following from root of the project 

``
python3 run.py
``

``
python run.py
``

# To run as a script first do ``chmod +x ./run.py`` first

``
.\run.py
``

``
./run.py
``

# Add the Compiler to Path
- First run the command ``chmod +x ./bin/elipc`` to let the ``elipc`` be used as an executable.
- Add the below export to your `~/.bashrc`, or equivalente
- `export PATH=<path-where-you've-cloned-this-repo>/elip/bin:$PATH`

# Add the Compiler to Path (Windows)
- Add ``<path-where-you've-cloned-this-repo>/elip/bin``

# Usage
- `elipc --c file.elip` to compile into C code, already the default behaviour
- `elipc --gui file.elip` to see AST in gui 
- `elipc --ast file.elip` to see AST in console 
- `elipc --exe file.elip` to  compile into executable using gcc


# Propriedades
- Main function might take any number of args as long as it is a basic type ``inteiro`` ``real`` ``boleano``