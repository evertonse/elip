#include <string.h>
#include <stdio.h>
#include <stdlib.h>
// Define an enum for boolean values
typedef enum {
    false = 0,
    true = 1
} bool;


#define lambda_1(elip_x, elip_y) ((2||(elip_x + (elip_y*5))) )

float elip_main (  int elip_a)  {
  return ((((1*2)*5)*6));
}
float elip_another (  int elip_a, int elip_b)  {
  return ((true ? (1 + ((2*5)*6)):(true ? false: (lambda_1(1,2)) )));
}
int main(int argc, char *argv[]) {
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

  elip_main(a);
}

//*** EOF ***//

