//Functoins to simulate a deck's play, using hardcoded AI.


#include <iostream>
using namespace std;

//Simulate deck once. Loud sets debugging output on/off
int simulate(Deck* d, bool loud){
    if(loud){
	cout<<"Simulating deck- \n";
	d->print();
    };

    Library* l = new Library(d);
    Hand* h = new Hand();

    //draw initial hand
    for(int i=0; i<6; i++){
	h->add(l->draw());
    };

    if(loud){
	cout<<"Inital hand-\n";
	h->print();
    };

    int turn = 0;
    int damageDealt = 0;
    int lands = 0;

    while(l->size>0){
	turn++;
	Card* draw = l->draw();
	if(loud)
	    cout<<"Card drawn- "<<draw->name<<"\n";
	h->add(draw);
	
	//If we have a mountain, play it
	Card* mtn = d->fetch("Mountain");
	if(h->drop(mtn)){
	    lands++;
	    if(loud)
		cout<<"Play mountain\n";
	}
	
	//Play the first card we can afford to play
	//until we can't
	int mana = lands;
	LLnode* currC = h->first;
	while(currC->next != 0){
	    currC = currC->next;
	    Card* c = currC->c;
	    if((c->type == burn) && (c->castingCost<=mana)){
		if(loud)
		    cout<<"Play "<<c->name<<'\n';
		mana -= c->castingCost;
		damageDealt+= c->damage;
		h->drop(c);
	    }
	}
	
	//Play the highest damage card we can afford
	//until we can't
	int mana = lands;
	bool found = true;
	while (found){
	    found = false;
	    LLnode* currC = h->first;
	    Card* bestC = 0;
	    int best = 0;
	    while(currC-> next != 0){
		currC = currC->next;
		Card* c = currC->c;
		if((c->type == burn) && (c->castingCost <= mana)){
		    found = true;
		    if(c->damage > best){
			best = c->damage;
			bestC = c;
		    }
		}
	    }

	    if(bestC != 0){
		cout<<"Play"<<bestC->name<<'\n';
	//TODO	



	if(loud){
	    cout<<"End turn "<<turn<<'\n';
	    cout<<"Mountains- "<<lands<<'\n';
	    cout<<"Damage dealt- "<<damageDealt<<'\n';
	    cout<<"Hand-\n";
	    h->print();
	    cout<<'\n';
	}
	if(damageDealt>=20){
	    delete l;
	    delete h;
	    return turn;
	}
    }
    delete l;
    delete h;
    //600 is approximately never
    return 600;
};

//Simulate many times, return the average win time
float simulate(Deck* d, int n){
    float total = 0;
    for(int i=0; i<n; i++){
	total += simulate(d, false);
    }
    float avg = total/n;
    return avg;
}








