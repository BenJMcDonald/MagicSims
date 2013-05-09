#include <string>
#include <iostream>
using namespace std;

int tryExtraTurn(Mana*, Zone*, int);
bool playLand(Mana*, Mana*, Zone*, int);


int simulate(Deck* d, int verb){
    if(verb>2){
	cout<<"Initalizing\n";
    }
    Library* lib = new Library(d);
    Zone* hand = new Zone();
    Zone* grave = new Zone();
    
    Mana* manaBase = new Mana();
    Mana* manaPool = 0;
    int extraTurns = 0;
    int landsPerTurn = 1;
    int landsRemaining = 0;
    int cardsPerTurn = 1;
    
    for(int i=0; i<6; i++){
	hand->add(lib->draw());
    }

    int opponentTurns = 0;

    if(rand()%2 == 0){
	opponentTurns++;
	hand->add(lib->draw());
    }

    if(verb>1){
	cout<<"Inital Hand\n";
	ZoneIterator* it = hand->iter();
	while(it->hasNext()){
	    cout<<it->next()<<'\n';
	}
	delete it;
    }

    while(true){
	if(verb>1){
	    cout<<"new turn\n";
	}

	if(lib->position>=60){
	    break;
	}
	if(extraTurns >= 4){
	    break;
	}

	manaPool = new Mana(manaBase);
	landsRemaining = landsPerTurn;
	if(verb>2){
	    cout<<"Mana for turn "<<manaPool->toString()<<'\n';
	}

	for(int i=0; i<cardsPerTurn; i++){
	    hand->add(lib->draw());
	}

	while((landsRemaining > 0) && (playLand(manaBase, manaPool, hand, verb))){
	    landsRemaining--;
	}
	
	if(verb>2){
	    cout<<"trying to play extra turn\n";
	}

	extraTurns += tryExtraTurn(manaPool, hand, verb);
	bool success = true;

	if(verb>2){
	    cout<<"Begginning plays for turn\n";
	}

	while(success){
	    success = false;
	    ZoneIterator* it = hand->iter();
	    Mana* localCopy = new Mana(manaPool);
	    while(it->hasNext()){
		string s = it->next();
		if(verb>2){
		    cout<<"Trying to play "<<s<<"\n";
		}

		if(!isLand(s)){
		    Mana* cost = new Mana(getCost(s));
		    bool can = localCopy->pay(cost);
		    if(can){
			if(verb>1){
			    cout<<"Play "<<s<<'\n';
			}
			manaPool->pay(cost);
			hand->drop(s);
			success = true;
			
			extraTurns += isExtraTurn(s);
			cardsPerTurn += isCardsPerTurn(s);
			int i = isLandsPerTurn(s);
			if(i>0){
			    landsPerTurn += i;
			    landsRemaining += i;
			    while((landsRemaining > 0) && (playLand(manaBase, manaPool, hand, verb))){
				landsRemaining--;
			    }
			}

			break;
		    }else{
			localCopy = new Mana(manaPool);
		    }
		}
	    }
	    delete localCopy;
	    delete it;
	}

	if(extraTurns>0){
	    if(verb>1){
		cout<<"Taking extra turn\n";
	    }
	    extraTurns--;
	}else{
	    if(verb>1){
		cout<<"Allowing opponent to take a turn :( \n";
	    }
	    opponentTurns++;
	}

	delete manaPool;
    }

    delete lib;
    delete hand;
    delete grave;
    delete manaBase;
    
    return opponentTurns;
}



int tryExtraTurn(Mana* pool, Zone* hand, int verb){
    ZoneIterator* it = hand->iter();
    Mana* localCopy = new Mana(pool);
    int i = 0;

    while(it->hasNext()){
	string s = it->next();
	i = isExtraTurn(s);
	if(verb>2){
	    cout<<"Trying to play "<<s<<"\n";
	}
	if(i > 0){
	    Mana* cost = new Mana(getCost(s));
	    
	    bool can = localCopy->pay(cost);
	    if(can){
		if(verb>1){
		    cout<<"Play "<<s<<"\n";
		}
		hand->drop(s);
		pool->pay(cost);
		break;
	    }else{
		localCopy = new Mana(pool);
	    }
	}
    }

    delete it;
    delete localCopy;
    return i;
}



bool playLand(Mana* base, Mana* pool, Zone* hand, int verb){
    if(verb>2){
	cout<<"Trying to play land\n";
    }
    if(base->G == 0){
	if(hand->drop("Forest")){
	    if(verb>1){
		cout<<"Play Forest\n";
	    }
	    base->G++;
	    pool->G++;
	    return true;
	}
    }

    if(hand->drop("Island")){
	if(verb>1){
	    cout<<"Play Island\n";
	}
	base->U++;
	pool->U++;
	return true;
    }

    return false;
}

