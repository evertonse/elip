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

int elip_fibonacci( int elip_x);
int elip_number();
int elip_exemploAltaOrdem( int elip_digito,  float (*elip_multiplicarPorPi)( int (*elip_random)(), float));
float elip_modificarDigito( int (*elip_random)(),  float elip_pi);
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

int elip_fibonacci( int elip_x)  {
    
    int if_0;    
    {
        if (((elip_x<1)||(elip_x == 1))) {
            if_0 = elip_x;
        } else {
            
            int block_0;            
            {
                block_0 = (elip_fibonacci((elip_x-1)) + elip_fibonacci((elip_x-2)));
            }
            if_0 = block_0;
        }
    }
    int return_data = if_0;
    return (return_data);
}
int elip_number()  {
    int return_data = 2;
    return (return_data);
}
int elip_exemploAltaOrdem( int elip_digito, float (*elip_multiplicarPorPi)( int (*elip_random)(), float))  {
    
    float block_0;    
    {
        block_0 = elip_multiplicarPorPi(elip_number, 3.1415);
    }
    int return_data = block_0;
    return (return_data);
}
float elip_modificarDigito( int (*elip_random)(), float elip_pi)  {
    float return_data = (elip_random()*elip_pi);
    return (return_data);
}
int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "fr_FR.UTF-8");
    char *arg;
    size_t arg_len;
   if (argc <2) {
       printf("%s expects 1 arguments from cmd line\n", argv[0]);
       printf("The expected types are inteiro x ");
        return 1;
    }
    arg = argv[1];

    arg_len = strlen(arg);
    int x = atoi(arg);

    printf("%d\n",((int)elip_fibonacci(x)));
}

//*** EOF ***//

