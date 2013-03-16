//Functoins to simulate a deck's play, using hardcoded AI.


#include <iostream>
using namespace std;

//Simulate deck once. Loud sets debugging output on/off
int simulate(Deck* d, bool loud){
    if(loud){
	cout<<"Simulating deck- \n";
	d->print();
    };

    //Library* l = new Library(d);
    //Hand* h = new Hand();
    Player* p = new Player(d);

    //draw initial hand
    for(int i=0; i<6; i++){
	//h->add(l->draw());
	p->draw();
    };

    //Randomize going first/second
    if(rand() % 2 == 0){
	p->draw();
    };

    if(loud){
	cout<<"Inital hand-\n";
	p->hand->print();
    };

    int turn = 0;
    int damageDealt = 0;
    int lands = 0;

    while(p->hasLost()==false){
	turn++;
	p->draw();
	//Card* draw = l->draw();
	//if(loud)
	//    cout<<"Card drawn- "<<draw->name<<"\n";
	//h->add(draw);
	
	//If we have a mountain, play it
	Card* mtn = d->fetch("Mountain");
	if(p->hand->drop(mtn)){
	    lands++;
	    if(loud)
		cout<<"Play mountain\n";
	}
	
	/*
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
	*/

	//Play the highest damage card we can afford
	//until we can't
	int mana = lands;
	bool found = true;
	while (found){
	    found = false;
	    LLnode<Card*>* currC = p->hand->first;
	    Card* bestC = 0;
	    int best = 0;
	    while(currC-> next != 0){
		currC = currC->next;
		Card* c = currC->value();
		if((c->type == burn) && (c->castingCost <= mana)){
		    found = true;
		    if(c->damage > best){
			best = c->damage;
			bestC = c;
			//In a tie, prefer the lower mana cost:
			//We're more likely to have more mana next
			//turn
		    }else if((c->damage == best) && (c->castingCost < bestC->castingCost)){
			bestC = c;
		    }
		}
	    }
	    if(bestC != 0){
		if(loud)
		    cout<<"Play "<<bestC->name<<'\n';
		mana -= bestC->castingCost;
		damageDealt+=bestC->damage;
		p->hand->drop(bestC);
	    }
	}



	if(loud){
	    cout<<"End turn "<<turn<<'\n';
	    cout<<"Mountains- "<<lands<<'\n';
	    cout<<"Damage dealt- "<<damageDealt<<'\n';
	    cout<<"Hand-\n";
	    p->print();
	    cout<<'\n';
	}
	if(damageDealt>=20){
	    delete p;
	    return turn;
	}
    }
    delete p;
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
