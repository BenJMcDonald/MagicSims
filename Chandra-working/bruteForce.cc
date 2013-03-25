#include <iostream>
#include <string>
using namespace std;

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Simulator.cc"

int main(){
    string StandardBurn [] = {"Mountain", "Thunderbolt", "Skullcrack",
    "Brimstone Volley", "Thunderous Wrath", "Pillar of Flame",
    "Flames of the Firebrand", "Searing Spear", "Chandra's Fury",
    "Geistflame", "Annihilating Fire", "Fires of Undeath",
    "Structural Collapse"};

    CardEnviron* StdB = new CardEnviron(StandardBurn, 13);
    
    float best = 100000;
    int count = 0;
    srand (time (NULL));
    while(true){
	count++;
	Deck* d = new Deck(StdB);
	float current = simulate(d, 1000);
	if(current<best){
	    cout<<"\n\nNew best deck-\n";
	    cout<<"Deck number "<<count<<'\n';
	    d->print();
	    cout<<"Average win: "<<current<<'\n';
	    best = current;
	}
	delete d;
    }
    delete StdB;
    //delete StandardBurn;
    //compiler says this is done by default
}

