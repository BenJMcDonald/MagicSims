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
    int verbosity = 0;
    int genSize = 200;
    int trials = 2000;
    int mutationRate = 10;
    int crossoverRate = 2;
    int inversionRate = 3;
    int generations = 10000;
    const int size = 60;

    Deck* best = 0;
    float bestPerf = 60;

    srand(time(NULL));

    int numLegal =  validCardsLength;
    string* possible = validCards;
    //string possible[10] = {"Island", "Time Warp", "Forest", "Capture of Jingzhou", "Temporal Manipulation",
    //"Time Stretch", "Walk the Aeons", "Howling Mine", "Rites of Flourishing", "Font of Mythos"};

    Deck** gen = new Deck*[genSize];
    float* perf = new float[genSize];
    float perfSum;
    
    cout<<"Generating inital decks\n";
    for(int i=0; i<genSize; i++){
	gen[i] = new Deck(possible, numLegal);
	validate(gen[i], possible, numLegal);
    }


    while(true){

	if(generations<=0){
	    break;
	}else{
	    generations--;
	}

	if(verbosity>1){
	    cout<<"Beginning generation "<<generations<<" \n";
	}
	 
	//Simulate
	perfSum = 0;
	bestPerf = 0;
	for(int i=0; i<genSize; i++){
	    perf[i] = 0;
	    for(int j = 0; j<trials; j++){
		perf[i] += simulate(gen[i], 0);
	    }
	    perf[i] = perf[i] / trials;
	    if(verbosity>2){
		cout<<perf[i]<<'\n';
	    }
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

	//Elitism
	newGen[0] = new Deck(best);

	//Clear old decks
	for(int i=0; i<genSize; i++){
	    delete gen[i];
	}
	delete gen;
	gen = newGen;

	//Crossover
	for(int i=1; i<(genSize-1); i+=2){
	    if(rand()%crossoverRate == 0){
		gen[i]->crossover(gen[i+1]);
	    }
	}

	//Mutate
	for(int i=1; i<genSize; i++){
	    while((rand()%mutationRate)==0){
		int sp = rand()%numLegal;
		string s = possible[sp];
		int r = rand()%60;
		gen[i]->cards[r] = s;
	    }
	}

	//Inversion
	for(int i=1; i<genSize; i++){
	    if(rand()%inversionRate==0){
		int a = rand()%60;
		int b = rand()%60;
		if(a > b){
		    int t = b;
		    b = a;
		    a = t;
		}

		while(a<b){
		    string temp = gen[i]->cards[a];
		    gen[i]->cards[a] = gen[i]->cards[b];
		    gen[i]->cards[b] = temp;
		    a++;
		    b--;
		}
	    }
	}


	//Verify decks legal
	for(int i=0; i<genSize; i++){
	    validate(gen[i], possible, numLegal);
	}
    }

    for(int i=0; i<genSize; i++){
	delete gen[i];
    }
    delete perf;
    delete gen;
};
