class Player;

#include "LList.cc"
#include "Effect.cc"
#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Player.cc"

int main(){
    Effect* n = new Damage(5);
    string strA [] = {"Lightning Bolt"};
    CardEnviron* s = new CardEnviron(strA, 1);
    Deck* d = new Deck(s);
    Player* p = new Player(d);

    LLnode<Player*>* pT = LLnode<Player*>::newList(p);
    Card* c = s->fetch("Lightning Bolt");

    c->cast(pT);
    
    p->print();
}

