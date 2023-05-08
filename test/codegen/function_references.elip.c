#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <ctype.h>
#include <math.h>
// Define an enum for boolean values
typedef enum {
    false = 0,
    true = 1
} bool;

float elip_cosseno(float x);
float elip_seno(float x);
float elip_logaritmo(float x);
float elip_logaritmo(float x);
float elip_potencia(float x, float y);

int elip_fibonacci( int elip_n);
int elip_two();
int elip_one();
int elip_three( int (*elip_fn)());
int elip_if_function_reference();
int elip_main( int elip_fib);
float elip_cosseno(float x) {
    return cosf(x);
}

float elip_seno(float x) {
    return sinf(x);
}

float elip_tangente(float x) {
    return tanf(x);
}

float elip_logaritmo(float x) {
    return log10f(x);
}

float elip_potencia(float x, float y) {
    return powf(x,y) ;
}

int elip_fibonacci( int elip_n)  {
    
    int if_0;    
    {
        if ((elip_n<2)) {
            if_0 = elip_n;
        } else {
            
            int block_0;            
            {
                block_0 = (elip_fibonacci((elip_n-1)) + elip_fibonacci((elip_n-2)));
            }
            if_0 = block_0;
        }
    }
    int return_data = if_0;
    return (return_data);
}
int elip_two()  {
    int return_data = 2;
    return (return_data);
}
int elip_one()  {
    int return_data = 1;
    return (return_data);
}
int elip_three( int (*elip_fn)())  {
    int return_data = elip_fn();
    return (return_data);
}
int elip_if_function_reference()  {
    
    int(*if_0)();    
    {
        if (true) {
            if_0 = elip_one;
        } else {
            if_0 = elip_two;
        }
    }
    int return_data = elip_three(if_0);
    return (return_data);
}
int elip_main( int elip_fib)  {
    int return_data = elip_fibonacci(elip_fib);
    return (return_data);
}
int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "fr_FR.UTF-8");
    char *arg;
    size_t arg_len;
   if (argc <2) {
       printf("%s expects 1 arguments from cmd line\n", argv[0]);
       printf("The expected types are inteiro fib ");
        return 1;
    }
    arg = argv[1];

    arg_len = strlen(arg);
    int fib = atoi(arg);

    printf("%d\n",((int)elip_main(fib)));
}

//*** EOF ***//

