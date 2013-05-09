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
    ~Deck();
    void print();
};

Deck::Deck(){
    cards = new string [size];
    for(int i=0; i<size; i++){
	cards[i] = "nothing";
    }
    name = "";
};

Deck::Deck(string* possible, int length){
    cards = new string [size];
    for(int i=0; i<size; i++){
	int r = rand() % length;
	cards[i] = possible[r];
    }
    name = "";
};

Deck::~Deck(){
    delete cards;
};

void Deck::print(){
    cout<<"Deck "<<name<<":\n";
    for(int i=0; i<size; i++){
	cout<<"  "<<cards[i]<<'\n';
    }
};

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
    delete cards;
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
