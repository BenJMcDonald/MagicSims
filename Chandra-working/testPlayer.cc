#include <iostream>
#include <string>
using namespace std;

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Simulator.cc"
#include "Player.cc"

int main(){
    string testEnv [] = {"Mountain", "Lightning Bolt", "Flame Wave", "Volt Charge"};
    
    CardEnviron* cd = new CardEnviron(testEnv, 4);
    Deck* d = new Deck(cd);
    Player* p = new Player(d);

    p->print();

    for(int i=0; i<3; i++)
	p->draw();

    p->print();

    while(p->hasLost() == false){
	p->draw();
	cout<<p->hand->size<<' ';
	p->damage(1);
    }

    cout<<'\n';

    p->print();
}
