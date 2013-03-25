#include <string>
//#include "Card.h"
using namespace std;




bool Card::cast(LLnodePl* t){
    return E->resolve(t);
};

void Card::setSimpleBurn(int c, int d){
    castingCost = c;
    CMC = c;
    type = burn;
    //damage = d; -shouldn't- be necessary anymore
    E = new Damage(d);
    HintsMap = new map<string, int>();
    HintsMap->insert(std::pair<string, int>("burn", d)); 
};

Card::~Card(){
    delete E;
    delete HintsMap;
};

bool Card::hintHas(string s){
    return (HintsMap->count(s)>0);
};

int Card::hintValue(string s){
    if(this->hintHas(s))
	return (HintsMap->at(s));
    return -1;
};

#include "cardDb.cc"
//right now, this is a constructor with a giant if-else
//Eventually, it should use function pointers instead
//with a function that performs the card's actions


//Depreciated, creates only mountains and lightning bolts.
Card::Card (CardType mtype){
    if(mtype==land){
	castingCost = -1;
	CMC = 0;
	type = mtype;
	damage = -1;
	name = "Mountain";
    }else{
	castingCost = 1;
	CMC = 1;
	type = mtype;
	damage = 3;
	name = "Lightning Bolt";
    }
};
