#include <iostream>
//#include "Player.h"

Player::Player(Deck* d){
    deck = d;
    libr = new Library(deck);
    hand = new Hand();
    graveyard = 0;
    life = 20;
    lost = false;
};

Player::~Player(){
    delete libr;
    delete hand;
};

void Player::draw(){
    Card* drawn = libr->draw();
    if(drawn == 0){
	lost = true;
	return;
    }
    hand->add(drawn);
};

void Player::damage(int n){
    life = life - n;
    if(life <= 0)
	lost = true;
};

void Player::print(){
    cout<<"Printing player-\n";
    cout<<"  Lost: "<<lost<<'\n';
    cout<<"  Life: "<<life<<'\n';
    deck->print();
    hand->print();
    cout<<"End player\n";
};

bool Player::hasLost(){
    return lost;
}
