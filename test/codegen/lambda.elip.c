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

int elip_mdc( int elip_a,  int elip_b);
int elip_home( int (*elip_mdc)( int (*elip_md)(int, int, int)));
int elip_a( int elip_b,  int elip_c);
int elip_higher_order_lambda_wrapped_in_block();
int elip_higher_order_lambda();
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

int elip_mdc( int elip_a, int elip_b)  {
    int return_data = ((elip_b == 0) ? elip_a:elip_mdc(elip_b, (elip_a%elip_b)));
    return (return_data);
}
int elip_home( int (*elip_mdc)( int (*elip_md)(int, int, int)))  {
    int return_data = 2;
    return (return_data);
}
int elip_a( int elip_b, int elip_c)  {
    
    int lambda_1;    
    {
        
        int lambda_2;        
        {
            lambda_2 = 2;
        }
        /*antes do temp*/        int elip_x = /*depois*/lambda_2 ;
        
        int lambda_3;        
        {
            lambda_3 = 2;
        }
        /*antes do temp*/        lambda_1 = /*depois*/lambda_3;
    }
    /*antes do temp*/    int return_data = /*depois*/lambda_1;
    return (return_data);
}
int elip_higher_order_lambda_wrapped_in_block()  {
    
    int lambda_4;    
    {
        
        int(*block_0)(int(*)(int(*)(int, int, int)));        
        {
            block_0 = elip_home;
        }
        /*escrevo no block*/        int(*elip_wraped)(int(*)(int(*)(int, int, int)))  = /*escrevo no body*/block_0 ;
        lambda_4 = 2;
    }
    /*antes do temp*/    int return_data = /*depois*/lambda_4;
    return (return_data);
}
int elip_higher_order_lambda()  {
    
    int lambda_5;    
    {
        int elip_y = 2 ;
        
        int(*block_1)(int, int);        
        {
            block_1 = elip_mdc;
        }
        /*escrevo no block*/        int(*elip_fn)(int, int)  = /*escrevo no body*/block_1 ;
        lambda_5 = ((elip_y/elip_fn(elip_y, 2))*2);
    }
    
    int lambda_6;    
    {
        
        int(*block_2)(int(*)(int(*)(int, int, int)));        
        {
            
            int(*block_3)(int(*)(int(*)(int, int, int)));            
            {
                block_3 = elip_home;
            }
            /*escrevo no block*/            block_2 = /*escrevo no body*/block_3;
        }
        /*escrevo no block*/        int(*elip_y)(int(*)(int(*)(int, int, int)))  = /*escrevo no body*/block_2 ;
        lambda_6 = 2;
    }
    /*antes do temp*/    /*antes do temp*/    int return_data = (/*depois*/lambda_5 + /*depois*/lambda_6);
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
    printf("%d\n",((int)elip_higher_order_lambda()));
}

//*** EOF ***//

