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
- `elip --c file.elip` to compile into C code, already the default behaviour
- `elip --gui file.elip` to see ast in gui 
- `elip --ast file.elip` to see ast in console 
- `elip file.elip` to  compile into executable using gcc  (doesn't work rn)


# Questions
If we have nested signature params, do we only need the identifier of the most shallow signature in a parameter
But in the Grammar we can, how come ? is this an error?

# Propriedades
- Main function might take any number of args as long as it is a basic type ``inteiro`` ``real`` ``boleano``
    - Example 