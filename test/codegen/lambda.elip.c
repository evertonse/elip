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
int elip_main();
#define def_lambda_1()\
\
\
    int lambda_1;    \
    {\
        lambda_1 = 2;\
    }\
    /*escrevo no block*/
#define def_lambda_2(elip_x, elip_y)\
\
\
    int lambda_2;    \
    {\
        \
        int block_2;        \
        {\
            \
            int block_3;            \
            {\
                \
                int block_4;                \
                {\
                    block_4 = elip_x;\
                }\
                /*escrevo no block*/                block_3 = /*escrevo no body*/block_4;\
            }\
            /*escrevo no block*/            block_2 = /*escrevo no body*/block_3;\
        }\
        \
        int block_5;        \
        {\
            block_5 = elip_y;\
        }\
        /*escrevo no block*/        /*escrevo no block*/        lambda_2 = ((/*escrevo no body*/block_2/elip_mdc(elip_x, /*escrevo no body*/block_5))*elip_y);\
    }\
    /*escrevo no block*/
#define def_lambda_3(elip_x, elip_mdc)\
\
\
    int lambda_3;    \
    {\
        lambda_3 = ((elip_x/elip_mdc(elip_x, 2))*2);\
    }\
    /*escrevo no block*/
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
int elip_main()  {
    
    int block_0;    
    {
        block_0 = 2;
    }
    
    int block_1;    
    {
        block_1 = 2;
    }
    def_lambda_1();
    def_lambda_2(15,20);
    def_lambda_3(15,elip_mdc);
    /*escrevo no block*/    /*escrevo no block*/    int return_data = ((((/*escrevo no body*/block_0*/*escrevo no body*/block_1) + /*escrevo no body*/lambda_1) + /*escrevo no body*/lambda_2) + /*escrevo no body*/lambda_3);
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
    printf("%d\n",((int)elip_main()));
}

//*** EOF ***//

