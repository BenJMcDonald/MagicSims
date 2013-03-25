#include <string>
#include <iostream>
//#include "Hand.h"

using namespace std;

/*
struct LLnode{
    Card* c;
    LLnode* next;
    LLnode (Card*);
};

LLnode::LLnode (Card* i){
    c = i;
    next = 0;
};
*/


Hand::Hand(){
    size = 0;
    first = new LLnodeCd(0);
}

Hand::~Hand(){
    LLnodeCd* c;
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

    LLnodeCd * n = new LLnodeCd(i);
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

    LLnodeCd* current = first;
    LLnodeCd* nextN = first->next;
    while(nextN != 0){
	if(nextN->value() == i){
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

    LLnodeCd* current = first;
    LLnodeCd* nextN = first->next;
    while(nextN != 0){
	if(nextN->value() == i){
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
    LLnodeCd* current = first->next;
    while(current!=0){
	cout<<"  "<<current->value()->name<<'\n';
	current = current->next;
    }
    cout<<"End hand\n";
};
