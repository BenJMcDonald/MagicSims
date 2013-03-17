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


class Card;
class LLnodeCd;
class CardEnviron;
class Deck;
class Library;
class Hand;
class Player;
class LLnodePl;
class Effect;

#include "Card.h"
#include "LListCard.cc"
#include "CardEnviron.h"
#include "Deck.h"
#include "Library.h"
#include "Hand.h"
#include "Player.h"
#include "LListPlayer.cc"
#include "Effect.h"

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Library.cc"
#include "Hand.cc"
#include "Player.cc"
#include "Effect.cc"

using namespace std;

int main(){
     

    Effect* n = new Damage(5);
    string strA [] = {"Mountain", "Searing Spear", "Flames of the Firebrand", "Lightning Bolt"};
    CardEnviron* s = new CardEnviron(strA, 4);
    Deck* d = new Deck(s);
    Player* p = new Player(d);

    LLnodePl* pT = LLnodePl::newList(p);
    Card* c = s->fetch("Lightning Bolt");

    c->cast(pT);
    
    p->print();
    
    return 0;
}

