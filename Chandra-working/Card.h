//Represents a single card. Only one instance of the same
//card should exist.

//Cards contain "AI hints", which tell the AI what the card does
//and how to play it. This is, for now, a keyword:int map.

#include <string>
#include <unordered_map>
using namespace std;

enum CardType {land, burn};

class Card{
    private:
	Effect* E;
	void setSimpleBurn(int, int);
    public:
	int castingCost;
	int CMC;
	CardType type;
	int damage;
	string name;
	Card (CardType);
	Card (string);
	bool cast(LLnodePl*);
	~Card();
};


