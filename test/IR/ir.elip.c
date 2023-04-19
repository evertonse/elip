#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <ctype.h>
// Define an enum for boolean values
typedef enum {
    false = 0,
    true = 1
} bool;


int elip_main( int elip_a);
float elip_another( int elip_a,  int elip_b);
#define lambda_ir_elip_1(elip_x, elip_y) ((2||(elip_x + (elip_y*5))) )
int elip_fibonacci( int elip_x);
int elip_exemploAltaOrdem( int elip_digito,  float (*elip_multiplicarPorPi)( int (*elip_random)(), float));
int elip_number();
float elip_modificarDigito( int (*elip_random)(),  float elip_pi);

int elip_main( int elip_a)  {
    int return_data = elip_exemploAltaOrdem(2, elip_modificarDigito);
    return (return_data);
}
float elip_another( int elip_a, int elip_b)  {
    float return_data = (true ? (1 + ((2*5)*6)):(true ? false: (lambda_ir_elip_1(1,2)) ));
    return (return_data);
}
int elip_fibonacci( int elip_x)  {
    
    float block_0;
    {
        block_0 = (elip_fibonacci((elip_x-1)) + elip_fibonacci((elip_x-2)));
    }
    /*escrevo no block*/    int return_data = (((elip_x<1)||(elip_x == 1)) ? elip_x:/*escrevo no body*/block_0);
    return (return_data);
}
int elip_exemploAltaOrdem( int elip_digito, float (*elip_multiplicarPorPi)( int (*elip_random)(), float))  {
    
    float block_1;
    {
        block_1 = elip_multiplicarPorPi(elip_number, 3.1415);
    }
    /*escrevo no block*/    int return_data = /*escrevo no body*/block_1;
    return (return_data);
}
int elip_number()  {
    int return_data = 2;
    return (return_data);
}
float elip_modificarDigito( int (*elip_random)(), float elip_pi)  {
    float return_data = (elip_random()*elip_pi);
    return (return_data);
}
int main(int argc, char *argv[]) {
    setlocale(LC_ALL, "fr_FR.UTF-8");
    char *arg;
    int arg_len;
   if (argc <2) {
       printf("%s expects 1 arguments from cmd line\n", argv[0]);
       printf("The expected types are inteiro a ");
        return 1;
    }
    arg = argv[1];

    arg_len = strlen(arg);
    int a = atoi(arg);

    printf("%d\n",((int)elip_main(a)));
}

//*** EOF ***//

