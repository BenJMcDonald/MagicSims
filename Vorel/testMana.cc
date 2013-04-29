#include "Mana.cc"

#include <iostream>
#include <assert.h>
using namespace std;


int main(){
    assert(0==0);

    assert (convertInt(123) == "123");
    assert (convertInt(0) == "0");
    assert (convertInt(6) == "6");

    //Test mana initilization    
    Mana* f = new Mana("UUU1");
    assert(f->toString() == "UUU1");

    Mana* g = new Mana("1111111");
    assert(g->toString() == "7");

    Mana* h = new Mana("");
    cout<<h->toString();
    assert(h->toString() == "");

    
    //test pay(Mana)
    f = new Mana("UUUWWWRRRGGGBBB111111111111111");
    g = new Mana("UU"); 
    assert(f->toString() == "WWWUUUBBBRRRGGG15");
    assert(g->toString() == "UU"); 
    assert(f->pay(g) == true);
    assert(f->toString() == "WWWUBBBRRRGGG15");
    h = new Mana("RRR1111");
    assert(h->toString() == "RRR4");
    assert(f->pay(h) == true);
    assert(f->toString() == "WWWUBBBGGG11");
    assert(f->pay(h) == false); 
    
        
    f = new Mana("UUUWWWRRRGGGBBB111111111111111");
    g = new Mana("WUBRG");
    f->pay(g);
    assert(f->toString() == "WWUUBBRRGG15");
    h= new Mana("11111111111111111111");
    cout<<"\n\n";
    assert(f->pay(h) == true);
    assert(f->toString() == "WUBRG"); 
	
    f = new Mana("WW11111");
    g = new Mana("W111");
    assert(f->pay(g) == true);
    assert(f->toString() == "W2");
    assert(f->pay(g) == false);
    assert(g->toString() == "W3");

    
  

};


