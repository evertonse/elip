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

bool elip_main();
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

bool elip_main()  {
    
    bool block_0;    
    {
        block_0 = (2>3);
    }
    /*escrevo no block*/    bool return_data = (elip_main ? true:/*escrevo no body*/block_0);
    return (return_data);
}
int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "fr_FR.UTF-8");
    char *arg;
    int arg_len;
   if (argc <1) {
       printf("%s expects 0 arguments from cmd line\n", argv[0]);
       printf("The expected types are  ");
        return 1;
    }
    printf("%s\n",(elip_main()) ? "verdadeiro" : "falso" );
}

//*** EOF ***//

