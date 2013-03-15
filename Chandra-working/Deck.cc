#include <iostream>


//Deck is a deck concept, i.e. an unordered list with quantities.
//Once particular instance of a deck is a library

class Deck{
    //TODO: proper privacy policies everywhere
    public:
    Card** cards;
    int* quantity;
    int length;
    int numCards;
    Deck();
    Deck(CardEnviron*);
    Deck(CardEnviron*, Deck*, int);
    ~Deck();
    void print();
    Card* fetch(string);
};

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

//Library is a single instance of a deck, in a particular order.
//This particularly needs a lot of refactoring.
class Library{
    //TODO: Should be kept in sorted order
    //But, for now, higher priority is closer to top
    //negative priority is not in the deck and is
    //why this shouldn't be an array
    //In case of a tie, card listed first is drawn first
    //This isn't how it should be but is rare enough
    //to be irrelevant.
    public:
    Card** cards;
    int* priority;
    int length;//length of the two arrays above
    int size;//number of cards currently in deck
    Library(Deck*);
    ~Library();
    int arraySum(int*, int);
    Card* draw();//removes the top card from the deck and returns a pointer to it
    void print();
    //void shuffle();
};

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
