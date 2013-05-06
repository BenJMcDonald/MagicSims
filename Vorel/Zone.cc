#include <string>
#include <iostream>
using namespace std;

//This is an unordered collection of strings
//Mean to be used for hand, graveyard, etc.

//Linked list for fast insertion/deletion (most common operations)
//Also, when we want to search, we're likely searching for a match
//based on some function, so no order is meaningful.

class LLnode{
    public:
    string e;
    LLnode* next;
    LLnode(string);
    ~LLnode();
};

LLnode::LLnode(string s){
    e = s;
    next = 0;
};

LLnode::~LLnode(){
    delete next;
};

class Zone{
    public:
    int getSize();
    Zone();
    ~Zone();
    
    void print();
    bool contains(string);
    bool drop(string);
    void add(string);

    private:
    int size;
    LLnode* first;
};

Zone::Zone(){
    size = 0;
    first = 0;
};

int Zone::getSize(){
    return size;
}

bool Zone::contains(string s){
    LLnode* t = first;
    while(t!=0){
	if (t->e == s){
	    return true;
	}
	t = t->next;
    }
    return false;
};

bool Zone::drop(string s){
    if(size==0){
	return false;
    }else if(first->e == s){
	size--;
	LLnode* t = first;
	first = first->next;
	t->next = 0;
	delete t;
	return true;
    }

    LLnode* t = first->next;
    LLnode** here = &first;
    while(t != 0){
	if(t->e == s){
	    size--;
	    *here = t->next;
	    t->next = 0;
	    delete t;
	    return true;
	}
	here = &t->next;
	t = t->next;
    }
    
    return false;
};

void Zone::add(string s){
    LLnode* n = new LLnode(s);
    n->next = first;
    first = n;
    size++;
    return;
};

#include "convertInt.cc"

void Zone::print(){
    cout<<"Zone:\n";
    cout<<"  Size: "<<convertInt(size)<<'\n';
    LLnode* t = first;
    while(t!=0){
	cout<<"  "<<t->e<<'\n';
	t = t->next;
    }
};
