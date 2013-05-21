#include <string>
#include <iostream>
using namespace std;

#include "convertInt.cc"
#include "CardInfo.cc"
#include "Deck.cc"
#include "Mana.cc"
#include "Zone.cc"
#include "simulate.cc"

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){

    srand(time(NULL));
    
    int l = validCardsLength;
    string* possible = validCards; //{"Island", "Time Warp", "Forest", "Capture of Jingzhou", "Temporal Manipulation", "Time Stretch", "Walk the Aeons", "Howling Mine", "Rites of Flourishing", "Font of Mythos"};

    cout<<"Generating deck\n";
    Deck* d = new Deck(possible, l);

    cout<<"Simulating deck\n";
    
    for(int j=0; j<1; j++){
	int i = simulate(d, 3);

	cout<<"Returned "<<i<<'\n';
    }
   
    delete d;
}

