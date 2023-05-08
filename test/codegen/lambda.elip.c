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
int elip_mdc( int elip_a,  int elip_b);
int elip_a( int elip_b,  int elip_c);
int elip_home( int (*elip_mdc)( int (*elip_md)(int, int, int)));
int elip_higher_order_lambda_wrapped_in_block();
int elip_higher_order_lambda();
int elip_complex_lambda();
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
    
    float lambda_0;    
    {
        bool elip_x = true ;
        int elip_y = 2 ;
        
        float lambda_2;        
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
            lambda_2 = if_1;
        }
        float elip_z = lambda_2 ;
        
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
        lambda_0 = if_2;
    }
    int return_data = lambda_0;
    return (return_data);
}
int elip_mdc( int elip_a, int elip_b)  {
    
    int if_0;    
    {
        if ((elip_b == 0)) {
            if_0 = elip_a;
        } else {
            if_0 = elip_mdc(elip_b, (elip_a%elip_b));
        }
    }
    int return_data = if_0;
    return (return_data);
}
int elip_a( int elip_b, int elip_c)  {
    
    int lambda_0;    
    {
        
        int lambda_2;        
        {
            lambda_2 = 2;
        }
        int elip_x = lambda_2 ;
        
        int lambda_3;        
        {
            lambda_3 = 2;
        }
        lambda_0 = lambda_3;
    }
    int return_data = lambda_0;
    return (return_data);
}
int elip_home( int (*elip_mdc)( int (*elip_md)(int, int, int)))  {
    int return_data = 2;
    return (return_data);
}
int elip_higher_order_lambda_wrapped_in_block()  {
    
    int lambda_0;    
    {
        
        int(*block_1)(int(*)(int(*)(int, int, int)));        
        {
            block_1 = elip_home;
        }
        int(*elip_wraped)(int(*)(int(*)(int, int, int)))  = block_1 ;
        lambda_0 = 2;
    }
    int return_data = lambda_0;
    return (return_data);
}
int elip_higher_order_lambda()  {
    
    int lambda_0;    
    {
        int elip_y = 2 ;
        
        int(*block_1)(int, int);        
        {
            block_1 = elip_mdc;
        }
        int(*elip_fn)(int, int)  = block_1 ;
        lambda_0 = ((elip_y/elip_fn(elip_y, 2))*2);
    }
    
    int lambda_1;    
    {
        
        int(*block_4)(int(*)(int(*)(int, int, int)));        
        {
            
            int(*block_5)(int(*)(int(*)(int, int, int)));            
            {
                block_5 = elip_home;
            }
            block_4 = block_5;
        }
        int(*elip_y)(int(*)(int(*)(int, int, int)))  = block_4 ;
        lambda_1 = 2;
    }
    int return_data = (lambda_0 + lambda_1);
    return (return_data);
}
int elip_complex_lambda()  {
    
    int block_0;    
    {
        block_0 = 2;
    }
    
    int block_1;    
    {
        block_1 = 2;
    }
    
    int lambda_0;    
    {
        lambda_0 = 2;
    }
    
    int lambda_1;    
    {
        int elip_x = 2 ;
        int elip_y = 20 ;
        
        int lambda_2;        
        {
            
            int lambda_3;            
            {
                
                int lambda_4;                
                {
                    int elip_x = 15 ;
                    int(*elip_mdc)(int, int)  = elip_mdc ;
                    lambda_4 = ((elip_x/elip_mdc(elip_x, 2))*2);
                }
                lambda_3 = lambda_4;
            }
            lambda_2 = lambda_3;
        }
        
        int block_2;        
        {
            
            int block_3;            
            {
                
                int block_4;                
                {
                    block_4 = elip_x;
                }
                block_3 = block_4;
            }
            block_2 = block_3;
        }
        
        int block_5;        
        {
            block_5 = elip_y;
        }
        lambda_1 = (lambda_2 + ((block_2/elip_mdc(elip_x, block_5))*elip_y));
    }
    
    int lambda_5;    
    {
        
        int lambda_7;        
        {
            lambda_7 = 2;
        }
        int elip_x = lambda_7 ;
        int(*elip_mdc)(int, int)  = elip_mdc ;
        lambda_5 = ((elip_x/elip_mdc(elip_x, 2))*2);
    }
    int return_data = ((((block_0*block_1) + lambda_0) + lambda_1) + lambda_5);
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

