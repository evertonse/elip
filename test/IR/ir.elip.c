#define OP_MULT(A,B) ((A)*(B))
#define OP_ADD(A,B) ((A)+(B))
#define OP_DIV(A,B) ((A)/(B))
// Define an enum for boolean values
typedef enum {
  false = 0,
  true = 1
} bool;


#define lambda_1(elip_x,elip_y) ((2||(elip_x + (elip_y*5))) )
int main(){

  elip_main();
}

float elip_main ( )  {
  return ((((1*2)*5)*6));
}
float elip_another (  int elip_a, int elip_b)  {
  return ((true?(1 + ((2*5)*6)):(true?false:elip_xelip_y (lambda_1(1,2)) )));
}
//*** EOF ***//
