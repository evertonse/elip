# Rebuild Sabblecc ``.java`` files, Compile Project and Run by one of the following from root of the project 

``
python3 run.py
``

``
python run.py
``

``
.\run.py
``

``
./run.py
``

# Add the Compiler to Path
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