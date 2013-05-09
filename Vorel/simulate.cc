#include <string>
#include <iostream>
using namespace std;

int tryExtraTurn(Mana*, Zone*);
bool playLand(Mana*, Mana*, Zone*);


int simulate(Deck* d){
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

    while(true){
	if(lib->position>=60){
	    break;
	}
	if(extraTurns >= 4){
	    break;
	}

	manaPool = new Mana(manaBase);
	landsRemaining = landsPerTurn;
	
	for(int i=0; i<cardsPerTurn; i++){
	    hand->add(lib->draw());
	}

	while((landsRemaining > 0) && (playLand(manaBase, manaPool, hand))){
	    landsRemaining--;
	}

	extraTurns += tryExtraTurn(manaPool, hand);
	bool success = true;

	while(success){
	    success = false;
	    ZoneIterator* it = hand->iter();
	    Mana* localCopy = new Mana(manaPool);
	    while(it->hasNext()){
		string s = it->next();
		if(!isLand(s)){
		    Mana* cost = new Mana(getCost(s));
		    bool can = localCopy->pay(cost);
		    if(can){
			manaPool->pay(cost);
			hand->drop(s);
			success = true;
			
			extraTurns += isExtraTurn(s);
			cardsPerTurn += isCardsPerTurn(s);
			int i = isLandsPerTurn(s);
			if(i>0){
			    landsPerTurn += i;
			    landsRemaining += i;
			    while((landsRemaining > 0) && (playLand(manaBase, manaPool, hand))){
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
	    extraTurns--;
	}else{
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



int tryExtraTurn(Mana* pool, Zone* hand){
    ZoneIterator* it = hand->iter();
    Mana* localCopy = new Mana(pool);
    int i = 0;

    while(it->hasNext()){
	string s = it->next();
	i = isExtraTurn(s);
	if(i > 0){
	    Mana* cost = new Mana(getCost(s));
	    
	    bool can = localCopy->pay(cost);
	    if(can){
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



bool playLand(Mana* base, Mana* pool, Zone* hand){
    if(base->G == 0){
	if(hand->drop("Forest")){
	    base->G++;
	    pool->G++;
	    return true;
	}
    }

    if(hand->drop("Island")){
	base->U++;
	pool->U++;
	return true;
    }

    return false;
}

