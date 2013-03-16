/*
class Effect;
class Damage;
class Player;
class Card;
class CardEnviron;
class Deck;
class Library;
class Hand;
*/
//include "LList.cc"

class Damage;
class Card;
class LLnodeCd;
class CardEnviron;
class Deck;
class Library;
class Hand;
class Player;
class LLnodePl;
class Effect;

#include "Player.cc"
#include "Effect.cc"
#include "Card.cc"
#include "LListCard.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "LListPlayer.cc"

int main(){
    Effect* n = new Damage(5);
    string strA [] = {"Lightning Bolt"};
    CardEnviron* s = new CardEnviron(strA, 1);
    Deck* d = new Deck(s);
    Player* p = new Player(d);

    LLnodePl* pT = LLnodePl::newList(p);
    Card* c = s->fetch("Lightning Bolt");

    c->cast(pT);
    
    p->print();
}

