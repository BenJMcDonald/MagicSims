#include <string>
using namespace std;


enum CardType {land, burn};

//Represents a single card. Only one instance of the same
//card should exist.
struct Card{
    int castingCost;
    int CMC;
    CardType type;
    int damage;
    string name;
    Card (CardType);
    Card (string);
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
