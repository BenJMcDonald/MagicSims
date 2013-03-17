//include "Library.h"

//Creates a library for a deck.
Library::Library(Deck* n){
    length = arraySum(n->quantity, n->length);
    cards = new Card* [length];
    priority = new int[length];
    int position = 0;
    for(int i = 0; i<n->length; i++){
	for(int j = 0; j<n->quantity[i]; j++){
	    cards[position] = n->cards[i];
	    priority[position] = rand();
	    position++;
	}
    }
    size = position;
};

Library::~Library(){
    delete cards;
    delete priority;
}

//Simple sum of values in an array. For calculating number of cards.
int Library::arraySum(int* array, int length){
    int result = 0;
    for(int i=0; i<length; i++){
	result += array[i];
    }
    return result;
};

//Draws the top card.
Card* Library::draw(){
    if(size < 1)
	return 0;
    int max = -1;
    int position = -1;
    for(int i=0; i<length; i++){
	if (priority[i]>max){
	    max = priority[i];
	    position = i;
	}
    }
    if(max < 0 || position < 0)
	return 0;
    Card* result = cards[position];
    cards[position] = 0;
    priority[position] = -1;
    size--;
    return result;
};

void Library::print(){
    cout<<"Library:\n";
    cout<<"  Arrays length- "<<length<<"\n";
    cout<<"  Size- "<<size<<"\n";

    cout<<"  Cards-\n";
    for(int i=0; i<length; i++){
	if(priority[i]>-1){
	    cout<<"    "<<cards[i]->name<<'\n';
	    cout<<"      Priority- "<<priority[i]<<'\n';
	}
    }
    cout<<"End library.\n";
}
