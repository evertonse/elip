#include <string.h>
#include <stdio.h>
#include <stdlib.h>
// Define an enum for boolean values
typedef enum {
    false = 0,
    true = 1
} bool;


#define lambda_1(elip_x, elip_y) ((2||(elip_x + (elip_y*5))) )

float elip_main ( )  {
  return ((((1*2)*5)*6));
}
float elip_another (  int elip_a, int elip_b)  {
  return ((true ? (1 + ((2*5)*6)):(true ? false: (lambda_1(1,2)) )));
}
int main(int argc, char *argv[]) {
    char *arg;
    int arg_len;
   if (argc <1) {
       printf("%s expects 0 arguments from cmd line\n", argv[0]);
       printf("The expected types are  ");
        return 1;
    }
  elip_main();
}

//*** EOF ***//

