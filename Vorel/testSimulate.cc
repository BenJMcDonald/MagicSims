#include <string>
#include <iostream>
using namespace std;

#include "Deck.cc"
#include "Mana.cc"
#include "Zone.cc"
#include "CardInfo.cc"
#include "simulate.cc"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){

    srand(time(NULL));

    string possible[10] = {"Island", "Time Warp", "Forest", "Capture of Jingzhou", "Temporal Manipulation",
    "Time Stretch", "Walk the Aeons", "Howling Mine", "Rites of Flourishing", "Font of Mythos"};

    cout<<"Generating deck\n";
    Deck* d = new Deck(possible, 10);

    cout<<"Simulating deck\n";

    int i = simulate(d, 2);

    cout<<"Returned "<<i;

}

