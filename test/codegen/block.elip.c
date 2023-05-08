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
int elip_shifted_fib( int elip_x);
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
            const int elip_bb = block_2  ;
            block_1 = (elip_ba + elip_bb);
        }
        
        int block_3;        
        {
            
            int block_4;            
            {
                block_4 = 2;
            }
            
            int block_5;            
            {
                
                int lambda_1;                
                {
                    
                    int block_7;                    
                    {
                        const int elip_a = 2  ;
                        const int elip_b = 2  ;
                        block_7 = (elip_a + elip_b);
                    }
                    int elip_x = (elip_a + block_7) ;
                    lambda_1 = elip_a;
                }
                block_5 = lambda_1;
            }
            block_3 = ((elip_b>1) ? block_4:block_5);
        }
        block_0 = (((elip_a + elip_b) + block_1) + block_3);
    }
    int return_data = block_0;
    return (return_data);
}
int elip_shifted_fib( int elip_x)  {
    
    int block_8;    
    {
        const int elip_a = 2  ;
        
        int block_9;        
        {
            const int elip_q = 2  ;
            const int elip_r = 3  ;
            block_9 = elip_shifted_fib((elip_q + elip_r));
        }
        const int elip_b = block_9  ;
        
        int block_10;        
        {
            
            int block_11;            
            {
                const int elip_ba = 2  ;
                const int elip_bb = 2  ;
                block_11 = (elip_ba*elip_bb);
            }
            block_10 = block_11;
        }
        block_8 = (elip_a + block_10);
    }
    
    int block_12;    
    {
        const int elip_ca = 2  ;
        const int elip_cb = 2  ;
        block_12 = (elip_ca%elip_cb);
    }
    
    int block_13;    
    {
        const int elip_ca = 2  ;
        const int elip_cb = 2  ;
        block_13 = (40*elip_cb);
    }
    int return_data = (block_8 + (123*(true ? block_12:block_13)));
    return (return_data);
}
int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "fr_FR.UTF-8");
    char *arg;
    int arg_len;
   if (argc <2) {
       printf("%s expects 1 arguments from cmd line\n", argv[0]);
       printf("The expected types are inteiro x ");
        return 1;
    }
    arg = argv[1];

    arg_len = strlen(arg);
    int x = atoi(arg);

    printf("%d\n",((int)elip_shifted_fib(x)));
}

//*** EOF ***//

