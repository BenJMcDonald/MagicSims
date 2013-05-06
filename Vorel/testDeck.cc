#include "Deck.cc"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main(){
    srand(time(NULL));
    string* n = new string[3];
    n[0] = "Forest";
    n[1] = "Island";
    n[2] = "Maze's End";

    Deck* d = new Deck(n, 3);
    d->print();

    Library* l = new Library(d);
    l->print();
    
    cout<<"Drawing 5 cards\n";
    for(int i=0; i<5; i++){
	cout<<l->draw()<<'\n';
    }
    return 0;
};

