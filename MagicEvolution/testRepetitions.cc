#include <iostream>
#include <string>
using namespace std;

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Simulator.cc"

int main(){
    string StandardBurn [] = {"Mountain", "Thunderbolt", "Skullcrack",
    "Brimstone Volley", "Thunderous Wrath", "Pillar of Flame",
    "Flames of the Firebrand", "Searing Spear", "Chandra's Fury",
    "Geistflame", "Annihilating Fire", "Fires of Undeath",
    "Structural Collapse"};

    CardEnviron* StdB = new CardEnviron(StandardBurn, 13);
    Deck* D = new Deck(StdB);
    //D->print();
    cout<<"Average turns till win- "<<simulate(D, 10000)<<'\n';
    delete D;
}

