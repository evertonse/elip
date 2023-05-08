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

int elip_other( int elip_x);
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

int elip_other( int elip_x)  {
    
    float lambda_1;    
    {
        bool elip_x = true ;
        int elip_y = 2 ;
        
        float lambda_3;        
        {
            bool elip_r = true ;
            float elip_p = 2.1 ;
            float elip_q = 3.1 ;
            
            float if_1;            
            {
                if (elip_r) {
                    
                    float block_1;                    
                    {
                        block_1 = elip_p;
                    }
                    if_1 = block_1;
                } else {
                    if_1 = (-((-(elip_q))));
                }
            }
            lambda_3 = if_1;
        }
        float elip_z = lambda_3 ;
        
        float if_2;        
        {
            if (elip_x) {
                
                float block_2;                
                {
                    block_2 = elip_z;
                }
                if_2 = block_2;
            } else {
                if_2 = (-((-(3.1))));
            }
        }
        lambda_1 = if_2;
    }
    int return_data = lambda_1;
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

    printf("%d\n",((int)elip_other(x)));
}

//*** EOF ***//

