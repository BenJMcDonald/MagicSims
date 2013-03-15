#include <string>
#include <iostream>

using namespace std;

struct LLnode{
    Card* c;
    LLnode* next;
    LLnode (Card*);
};

LLnode::LLnode (Card* i){
    c = i;
    next = 0;
};

class Hand{
    //Keeps only a pointer to cards. There should be only
    //one instance of each card, so it can just compare these
    //pointers. 

    //For now it'll keep it as a linked list
    //Dummy node at head with null card
    public:
    int size;
    LLnode* first;
    Hand ();
    ~Hand();
   
    void print();
    bool contains(Card*);
    void add(Card*); //Adds card to hand
    bool drop(Card*); //Removes card from hand. returns false if it wasn't found.
    //TODO
    //bool drop(string); //Removes card with specified name
    //bool contains(string);
    //Card* fetch(string);//returns a pointer to the card with the given name in handi
};

Hand::Hand(){
    size = 0;
    first = new LLnode(0);
}

Hand::~Hand(){
    LLnode* c;
    while(first != 0){
	c = first;
	first = first->next;
	delete c;
    }
}

void Hand::add(Card* i){
    if(i==0){
	cout<<"Attempted to add null card to hand";
	return;
	//TODO: use exceptions
    }

    LLnode* n = new LLnode(i);
    n->next = first->next;
    first->next = n;
    size++;
};

bool Hand::drop(Card* i){
    if(i==0){
	cout<<"Arrempted to delete null card from hand";
	return false;
	//TODO: exceptions
    }

    LLnode* current = first;
    LLnode* nextN = first->next;
    while(nextN != 0){
	if(nextN->c == i){
	    current->next = nextN->next;
	    delete nextN;
	    size--;
	    return true;
	}
	current = nextN;
	nextN = nextN->next;
    }
    return false;
};

bool Hand::contains(Card* i){
    if(i==0){
	return false;
    }

    LLnode* current = first;
    LLnode* nextN = first->next;
    while(nextN != 0){
	if(nextN->c == i){
	    return true;
	}
	current = nextN;
	nextN = nextN->next;
    }
    
    return false;
};

void Hand::print(){
    cout<<"Hand-\n";
    cout<<"  Size- "<<size<<'\n';
    cout<<"  Cards-\n";
    LLnode* current = first->next;
    while(current!=0){
	cout<<"  "<<current->c->name<<'\n';
	current = current->next;
    }
    cout<<"End hand\n";
};
