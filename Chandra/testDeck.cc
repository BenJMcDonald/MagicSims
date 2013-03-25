#include <iostream>
#include <string>
#include "Deck.cc"

using namespace std;

int main(){
    
    Deck* d1 = new Deck();
    d1->print();
    Library* l1 = new Library(d1);
    
    l1->print();

    cout<<"Drawing 30 cards: \n";
    for(int i = 0; i<30; i++){
	l1->draw();
    }

    l1->print();
    return 0;
}
