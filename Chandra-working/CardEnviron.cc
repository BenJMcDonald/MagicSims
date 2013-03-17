//This is a set of cards that exist and may be used
//i.e. a format. Intended for passing to deck generator
//so that the simulator has pointers to these cards

//Main point of this existing is so that we can have only
//one instance of a given card, and we can compare cards
//by comparing pointers to it. This class exists so different
//functions can fetch the correct card pointers.

#include <iostream>
#include <string>
//#include "CardEnviron.h"
using namespace std;


CardEnviron::~CardEnviron(){
    for(int i; i<length; i++){
	delete cards[i];
    }
    delete cards;
};

CardEnviron::CardEnviron(string* names, int l){
    cards = new Card* [l];
    length = l;
    for(int i=0; i<l; i++){
	cards[i] = new Card(names[i]);
    }
};

//Fetch card by name
Card* CardEnviron::fetch(string name){
    for(int i = 0; i<length; i++){
	if(cards[i]->name == name){
	    return cards[i];
	}
    }
    return 0;
};

//Fetch card by index into cards array
Card* CardEnviron::fetch(int index){
    if((index<0) || (index>=length)){
	return 0;
    }
    return cards[index];
};

//Print, for debugging
void CardEnviron::print(){
    cout<<"Card Environment-\n";
    for(int i=0; i<length; i++){
	cout<<"  "<<cards[i]->name<<'\n';
    }
    cout<<"End Card Environment\n";
};
