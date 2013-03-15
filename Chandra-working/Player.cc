#include <iostream>

class Player{
    private:
	Deck* deck;
	Library* libr;
    public:
	Hand* hand;
	void* graveyard;
	void draw();
	int life;
	Player(Deck*);
	~Player();
	void print();
}

Player::Player(Deck* d){
    deck = d;
    libr = new Library(deck);
    hand = new Hand();
    graveyard = 0;
    life = 20;
}

Player::~Player(){
    delete libr;
    delete hand;
}


