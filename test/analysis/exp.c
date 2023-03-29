#define EXP 1+2--3

#include "stdio.h"

enum bool {
  false = 0,  true = 1
};

#define make_func(name) int name() {\
  static int n = 0;\
  printf("%s = %d\n",__PRETTY_FUNCTION__,n);\
  return n++;\
} \

make_func(cond);
make_func(truthy);
make_func(falsy);

int main(int argc, char const *argv[]) {
  
  int num = 2;

  int word =
   true 
    ? 6
    : false || false && false < 2 > 2;
  printf("user> %d", word);
}
