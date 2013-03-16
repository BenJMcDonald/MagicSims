#include <iostream>
#include <string>
using namespace std;

#include "Card.cc"
#include "CardEnviron.cc"
#include "Deck.cc"
#include "LList.cc"
#include "Hand.cc"
#include "Player.cc"
#include "Simulator.cc"

int main(){
    string StandardBurn [] = {"Mountain", "Thunderbolt", "Skullcrack",
    "Brimstone Volley", "Thunderous Wrath", "Pillar of Flame",
    "Flames of the Firebrand", "Searing Spear", "Chandra's Fury",
    "Geistflame", "Annihilating Fire", "Fires of Undeath",
    "Structural Collapse"};
    
    string AllBurn [] = {"Mountain", "Thunderbolt", "Skullcrack",
    "Brimstone Volley", "Thunderous Wrath", "Pillar of Flame",
    "Flames of the Firebrand", "Searing Spear", "Chandra's Fury",
    "Geistflame", "Annihilating Fire", "Fires of Undeath",
    "Structural Collapse", "Lightning Bolt", "Fireblast",
    "Chain Lightning", "Magma Jet", "Lava Spike", "Galvanic Blast",
    "Incinerate", "Rift Bolt", "Sudden Shock", "Pulse of the Forge",
    "Flames of the Blood Hand", "Violent Eruption", "Burst Lightning",
    "Flame Wave", "Flame Javelin", "Firey Temper", "Forked Bolt",
    "Firebolt", "Glacial Ray", "Unstable Footing", "Punishing Force",
    "Urza's Rage", "Volt Charge", "Hammer of Bogardan", "Searing Wind"};
    
    //CardEnviron* StdB = new CardEnviron(StandardBurn, 13);
    CardEnviron* StdB = new CardEnviron(AllBurn, 38);
    const int genSize = 3;
    const int trials = 10000;
    const int mutation = 15;
    float globalMin = 600;

    Deck** Adecks = new Deck* [genSize]; //current gen
    Deck** Bdecks = new Deck* [genSize]; //next gen
    
    float* Aperf = new float [genSize];
    float* invPerf = new float [genSize];

    float nextGenPrint = 1;
    float nextGenExp = 1.3;

    cout<<"Building initial decks \n";

    srand(time(NULL));
    for(int i=0; i<genSize; i++){
	Adecks[i] = new Deck(StdB);
    }
    cout<<genSize<<" decks built\n\n";

    int generation = 0;
    while(true){
	generation++;
	float min = 100;
	float max = 0;
	float sum = 0;
	int bestIndex = 0;
	float sumInv = 0;
	
	//simulate decks, keeping statistics
	for(int i=0; i<genSize; i++){
	    Aperf[i] = simulate(Adecks[i], trials);
	    invPerf[i] = 1/Aperf[i];
	    sumInv = sumInv + invPerf[i];
	    float result = Aperf[i];

	    if(result<min){
		min = result;
		bestIndex = i;
	    }
	    if(result>max)
		max = result;
	    sum = sum + result;
	}
	if(generation>nextGenPrint){
	    cout<<"\nDone simulating generation "<<generation<<'\n';
	    cout<<"  min "<<min<<'\n';
	    cout<<"  avg "<<(sum/genSize)<<'\n';
	    cout<<"  max "<<max<<'\n'<<'\n';
	    nextGenPrint = nextGenPrint * nextGenExp;
	}

	if(Aperf[bestIndex] < globalMin){
	    globalMin = Aperf[bestIndex];
	    cout<<"\nNew best deck"<<'\n';
	    Adecks[bestIndex]->print();
	    cout<<'\n';
	}


	//keep best deck
	Bdecks[0] = Adecks[bestIndex];
	
	//fill rest with roulette-wheel chosen unary mutations
	for(int i=1; i<genSize; i++){
	    float n = ((float(rand()))/RAND_MAX) * sumInv;
	    float s = 0;
	    for(int j=0; j<genSize; j++){
		s = s + invPerf[j];
		if(s > n){
		    Bdecks[i] = new Deck(StdB, Adecks[j], mutation);
		    break;
		}
	    }
	}

	for(int i=0; i<genSize; i++){
	    if(i != bestIndex){
		delete Adecks[i];
	    }
	}

	Deck** tmp = Adecks;
	Adecks = Bdecks;
	Bdecks = tmp;
    }
}
