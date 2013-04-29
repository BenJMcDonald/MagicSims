#include "Mana.cc"

#include <iostream>
#include <assert.h>
using namespace std;


int main(){
    assert(0==0);

    assert (convertInt(123) == "123");
    assert (convertInt(0) == "0");
    assert (convertInt(6) == "6");
    
    Mana* f = new Mana("UUU1");
    assert(f->toString() == "UUU1");

    Mana* g = new Mana("1111111");
    assert(g->toString() == "7");

    Mana* h = new Mana("");
    cout<<h->toString();
    assert(h->toString() == "0"); 

};


