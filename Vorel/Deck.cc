#include <iostream>
#include <string>
using namespace std;

#include <stdio.h>
#include <stdlib.h>

//Deck represents a cardlist, uses repitition
//Fixed to 60 cards.

class Deck{
    public:
    string* cards;
    static const int size = 60;
    string name;
    Deck();
    Deck(string*, int);
    Deck(Deck*);
    ~Deck();
    void print();
    void crossover(Deck*);
};

Deck::Deck(){
    cards = new string [size];
    for(int i=0; i<size; i++){
	cards[i] = "nothing";
    }
    name = "";
};

Deck::Deck(Deck* other){
    cards = new string[size];
    for(int i=0; i<size; i++){
	cards[i] = other->cards[i];
    }
    name = "";
}

Deck::Deck(string* possible, int length){
    cards = new string [size];
    for(int i=0; i<size; i++){
	int r = rand() % length;
	cards[i] = possible[r];
    }
    name = "";
};

Deck::~Deck(){
    delete[] cards;
};

void Deck::crossover(Deck* other){
    int a = rand() % size;
    int b = rand() % size;
    if(a > b){
	int t = b;
	b = a;
	a = t;
    }
    
    string t = "nothing";
    for(int i = a; i<=b; i++){
	t = other->cards[i];
	other->cards[i] = cards[i];
	cards[i] = t;
    }
}

/*
void Deck::print(){
    cout<<"Deck "<<name<<":\n";
    for(int i=0; i<size; i++){
	cout<<"  "<<cards[i]<<'\n';
    }
};*/

void Deck::print(){
    //Slow as all hell but very rarely called
    cout<<"Deck:\n";
    string* names = new string[size];
    int* counts = new int[size];
    int position = 0;

    for(int i=0; i<size; i++){
	for(int j=0; j<=position; j++){
	    if(j==position){
		names[j] = cards[i];
		counts[j] = 1;
		position++;
		break;
	    }else{
		if(cards[i] == names[j]){
		    counts[j]++;
		    break;
		}
	    }
	}
    }

    for(int i=0; i< position; i++){
	cout<<convertInt(counts[i])<<" "<<names[i]<<"\n";
    }

    delete counts;
    //delete names;
}

class Library{
    public:
    string* cards;
    static const int size = 60;
    int position;
    Library(Deck*);
    ~Library();
    void shuffle();
    void print();
    string draw();
};

Library::Library(Deck* d){
    cards = new string[size];
    position = 0;
    for(int i=0; i<size; i++){
	cards[i] = d->cards[i];
    }
    this->shuffle();
};

Library::~Library(){
    delete[] cards;
};

void Library::shuffle(){
    for(int i=position; i<size; i++){
	int r = rand() % (size-position);
	string t = cards[i];
	cards[i] = cards[r];
	cards[r] = t;
    }
};

string Library::draw(){
    if(position>=size){
	return "";
    }
    string s = cards[position];
    cards[position] = "";
    position ++;
    return s;
};

void Library::print(){
    cout<<"Library:\n";
    for(int i=0; i<size; i++){
	cout<<"  "<<cards[i]<<'\n';
    }
};

void validate(Deck* d, string* legalCards, int length){
    //TODO this could be faster if the arrays were sorted
    //Then again, how likely are they every to be longer than 20, anyway?
    //I'm not sure the common assumptions are valid here.

    int* quantities = new int[length];
    for(int i=0; i<length; i++){
	quantities[i] = 0;
    }

    for(int i=0; i<Deck::size; i++){
	for(int j=0; j<length; j++){
	    if(d->cards[i] == legalCards[j]){
		if(! isBasic(d->cards[i])){	
		    quantities[j]++;
		    if(quantities[j] > 4){
			d->cards[i] = "Island";
		    }
		}
		break;
	    }
	}
    }

    delete quantities;
};



















