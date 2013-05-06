#include "CardInfo.cc"
#include <assert.h>

int main(){
    assert(isBasic("Forest"));
    assert(!isBasic("Howling Mine"));
    assert(!isBasic("Owling Mine"));
    assert(isCardsPerTurn("Howling Mine") == 1);
    assert(isCardsPerTurn("Forest") == 0);
}

