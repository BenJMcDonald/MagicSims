#include <iostream>
#include <string>
using namespace std;

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "Hand.cc"
#include "Player.cc"
#include "Simulator.cc"

int main(){
    string StandardBurn [] = {"Mountain", "Thunderbolt", "Skullcrack",
    "Brimstone Volley", "Thunderous Wrath", "Pillar of Flame",
    "Flames of the Firebrand", "Searing Spear", "Chandra's Fury",
    "Geistflame", "Annihilating Fire", "Fires of Undeath",
    "Structural Collapse"};

    CardEnviron* StdB = new CardEnviron(StandardBurn, 13);
    srand(time(NULL));
    Deck* D = new Deck(StdB);
    cout<<"Returned "<<simulate(D, true)<<'\n';
}

