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

int elip_block_creation();
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

int elip_block_creation()  {
    
    int block_0;    
    {
        const int elip_a = 2  ;
        const int elip_b = 2  ;
        
        int block_1;        
        {
            const int elip_ba = 2  ;
            
            int block_2;            
            {
                block_2 = 2;
            }
            /*escrevo no block*/            const int elip_bb = /*escrevo no body*/block_2  ;
            block_1 = (elip_ba + elip_bb);
        }
        /*escrevo no block*/        block_0 = ((elip_a + elip_b) + /*escrevo no body*/block_1);
    }
    
    int block_3;    
    {
        
        int block_4;        
        {
            block_4 = 2;
        }
        /*escrevo no block*/        block_3 = /*escrevo no body*/block_4;
    }
    /*escrevo no block*/    /*escrevo no block*/    int return_data = (/*escrevo no body*/block_0 + /*escrevo no body*/block_3);
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
    printf("%d\n",((int)elip_block_creation()));
}

//*** EOF ***//

