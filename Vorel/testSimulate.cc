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

    string possible[2] = {"Island", "Time Warp"};

    cout<<"Generating deck\n";
    Deck* d = new Deck(possible, 2);

    cout<<"Simulating deck\n";

    int i = simulate(d, 3);

    cout<<"Returned "<<i;

}

