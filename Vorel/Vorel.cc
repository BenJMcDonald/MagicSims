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
    int genSize = 100;
    int trials = 1000;
    int mutationRate = 3;
    int generations = 10;
    const int size = 60;

    Deck* best = 0;
    float bestPerf = 60;

    srand(time(NULL));

    int numLegal = 10;
    string possible[10] = {"Island", "Time Warp", "Forest", "Capture of Jingzhou", "Temporal Manipulation",
    "Time Stretch", "Walk the Aeons", "Howling Mine", "Rites of Flourishing", "Font of Mythos"};

    Deck** gen = new Deck*[genSize];
    float* perf = new float[genSize];
    float perfSum;

    for(int i=0; i<genSize; i++){
	gen[i] = new Deck(possible, numLegal);
    }

    while(true){

	if(generations<=0){
	    break;
	}else{
	    generations--;
	}
    
	//Simulate
	perfSum = 0;
	bestPerf = 0;
	for(int i=0; i<genSize; i++){
	    perf[i] = 0;
	    for(int j = 0; i<trials; j++){
		perf[i] += simulate(gen[i], 0);
	    }
	    perf[i] = perf[i] / genSize;
	    cout<<perf[i]<<'\n';
	    perf[i] = 1/perf[i];
	    perfSum += perf[i];

	    if(perf[i] > bestPerf){
		bestPerf = perf[i];
		best = gen[i];
	    }
	}

	cout<<"Done simulation generation "<<generations<<"\n";
	cout<<"Best deck:\n";
	best->print();
	cout<<"Average opponent turns: "<<(1/bestPerf)<<"\n\n";

	//Populate

	Deck** newGen = new Deck*[genSize];
	for(int i=0; i<genSize; i++){
	    float n = ((float(rand()))/RAND_MAX) * perfSum;
	    float s = 0;
	    for(int j=0; j<genSize; j++){
		s = s + perf[j];
		if(s > n){
		    newGen[i] = new Deck(gen[j]);
		    break;
		}
	    }
	}

	//Clear old decks
	for(int i=0; i<genSize; i++){
	    delete gen[i];
	}
	delete gen;
	gen = newGen;

	//Crossover
	for(int i=0; i<genSize; i+=2){
	    gen[i]->crossover(gen[i+1]);
	}

	//Mutate
	for(int i=0; i<genSize; i++){
	    while((rand()%mutationRate)==0){
		int sp = rand()%numLegal;
		string s = possible[sp];
		int r = rand()%60;
		gen[i]->cards[size] = s;
	    }
	}
    }

    for(int i=0; i<genSize; i++){
	delete gen[i];
	delete gen;
	delete possible;
	delete perf;
    }
};
