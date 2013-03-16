#include "LList.cc"
#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Player.cc"
#include "Effect.cc"

int main(){
    Effect* n = new Damage(5);
    string strA [] = {"Mountian"};
    CardEnviron* s = new CardEnviron(strA, 1);
    Deck* d = new Deck(s);
    Player* p = new Player(d);

    LLnode<Player*>* pT = LLnode<Player*>::newList(p);
    n->resolve(pT);
    p->print();
}

