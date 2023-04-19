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


int elip_shifted_fib( int elip_x);

int elip_shifted_fib( int elip_x)  {
    
    float block_0;
    {
        const int elip_a = 2  ;
        
        float block_1;
        {
            const int elip_q = 2  ;
            const int elip_r = 3  ;
            block_1 = (elip_q + elip_r);
        }
        /*escrevo no block*/        const int elip_b = /*escrevo no body*/block_1  ;
        
        float block_2;
        {
            
            float block_3;
            {
                const int elip_ba = 2  ;
                const int elip_bb = 2  ;
                block_3 = (elip_ba||elip_bb);
            }
            /*escrevo no block*/            block_2 = /*escrevo no body*/block_3;
        }
        /*escrevo no block*/        block_0 = (elip_a + /*escrevo no body*/block_2);
    }
    /*escrevo no block*/    int return_data = (/*escrevo no body*/block_0 + (123*(true ? 2:3)));
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

