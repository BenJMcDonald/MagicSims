#include <iostream>
#include <stdlib.h>

//#include "Deck.h"
//Deck is a deck concept, i.e. an unordered list with quantities.
//Once particular instance of a deck is a library



Deck::~Deck(){
    delete cards;
    delete quantity;
};

//Create a legal deck randomly
Deck::Deck (CardEnviron* E){
    //TODO: Seriously just use vectors
    length = E->length;
    cards = new Card* [length];
    quantity = new int [length];
    numCards = 0;
    
    //Fill deck with 0<=x<=4 of each card
    for(int i=0; i<length; i++){
	int r = rand() % 5;
	cards[i] = E->fetch(i);
	quantity[i] = r;
	numCards+=r;
    }

    //If deck is <60 cards, add mountains such that
    //is exactly 60
    if(numCards<60){
	Card* m = E->fetch("Mountain");
	for(int i=0; i<length; i++){
	    if(cards[i] == m){
		quantity[i] = quantity[i] + (60-numCards);
		numCards = 60;
	    	return;
	    }
	}
    }
}

//Construct a deck simular to a provided deck
//With a given mutation rate
Deck::Deck (CardEnviron* E, Deck* D, int mutationRate){
    length = E->length;
    cards = new Card* [length];
    quantity = new int [length];
    numCards = 0;

    //copy over quantaties +-1
    for(int i=0; i<length; i++){
	cards[i] = D->cards[i];
	quantity[i] = D->quantity[i];
	
	//Modulate quantity by +-1, possibly repeatedly
	while(rand() % mutationRate == 0){
	    int mod = (rand () % 3) - 1;
	    quantity[i] = quantity[i] + mod;

	    if(quantity [i] <0)
		quantity[i] = -quantity[i];
	    
	    if(cards[i]->type != land)
		if(quantity[i]>4)
		    quantity[i] = 4;
	    }
	numCards += quantity[i];
    }

    //fill with mountains if necessary
    if(numCards<60){//fill with mountains
	Card* m = E->fetch("Mountain");
	for(int i=0; i<length; i++){
	    if(cards[i] == m){
		quantity[i] = quantity[i] + (60-numCards);
		numCards = 60;
	    	break;
	    }
	}
    }
    
    //most of the time, trim deck to 60 cards
    if(numCards>60){
	if(rand() % mutationRate == 0){
	    Card* m = E->fetch("Mountain");
	    for(int i=0; i<length; i++){
		if(cards[i] == m){
		    quantity[i] = quantity[i] + (60-numCards);
		    numCards = 60;
		    if(quantity[i] < 0){
			numCards = numCards - quantity [i];
			quantity[i] = 0;
		    }
		    break;
		}
	    }
	}
    }
}

//Fetch the pointer to the card with the given name.
//This should be called on the cardEnviron instead
Card* Deck::fetch(string s){
    for(int i=0; i<length; i++){
	if(cards[i]->name == s)
	    return cards[i];
	}
    return 0;
}

void Deck::print(){
    cout<<"Deck:\n";
    cout<<"  Arrays length- "<<length<<'\n';
    cout<<"  Cards ("<<numCards<<")\n";
    for(int i=0; i<length; i++)
	if(quantity[i]!=0)
	    cout<<"    "<<quantity[i]<<' '<<cards[i]->name<<'\n';
    cout<<"End deck.\n";
}

