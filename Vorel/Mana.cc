#include <string>
#include <iostream>
using namespace std;

const int numTypes = 6;

//Class representing mana, mana costs.
class Mana{
    public:
    Mana();
    Mana (string);
    Mana (Mana*);
    ~Mana();
    bool canPay(Mana*);
    bool pay(Mana*);
    string toString();

    int W = 0;
    int U = 0;
    int R = 0;
    int G = 0;
    int B = 0;
    int L = 0;//colorless
    
    private:
    bool pay(char);
    int* types[numTypes] = {&W, &U, &B, &R, &G, &L};
    string names[numTypes] = {"W", "U", "B", "R", "G", "1"};
};

Mana::Mana(){
    return;
};

//Takes a string of the form RR1 or UUUU11111111
//Doesn't handle split mana or anything else fancy.
Mana::Mana(string n){
    for(int i=0; i<n.size(); i++){
	for(int j = 0; j<6; j++){
	    if(names[j][0]==n[i]){
		*types[j] = *types[j] + 1;
	    }
	}
    }
    return;
};

Mana::~Mana(){
    delete types;
    delete names;
    return;
};

Mana::Mana(Mana* in){
    //TODO
    return;
};

bool Mana::canPay(Mana* cost){
    //TODO
    return true;
};

bool Mana::pay(Mana* cost){
    for(int i=0; i<numTypes; i++){
	int t = *(cost->types[i]);
	while(t>0){
	    if(*types[i]>0){
		*types[i] = *types[i] - 1;
		t--;
	    }else if(names[i]=="1"){
		bool b = pay('1');
		t--;
		if(b == false){
		    return false;
		}
	    }else{
		return false;
	    }
	}
    }
    return true;
};


bool Mana::pay(char cost){
    switch(cost){
	case '1':
	    int max = 0;
	    int* typ = 0;
	    for(int i=0; i<numTypes-1; i++){
		if(*types[i] > max){
		    max = *types[i];
		    typ = types[i];
		}
	    }
	    if(max == 0){
		return false;
	    }else{
		*typ = *typ - 1;
		return true;
	    }
	    break;
    }

    return true;
};

string convertInt(int in)
{
    string output = "";
    while(true){
	switch(in%10)
	{
	    case 1:
		output = '1' + output;
		break;
	    case 2:
		output = '2' + output;
		break;
	    case 3:
		output = '3' + output;
		break;
	    case 4:
		output = '4' + output;
		break;
	    case 5:
		output = '5' + output;
		break;
	    case 6:
		output = '6' + output;
		break;
	    case 7:
		output = '7' + output;
		break;
	    case 8:
		output = '8' + output;
		break;
	    case 9:
		output = '9' + output;
		break;
	    case 0:
		output = '0' + output;
		break;
	}
	if(in<10){
	    return output;
	}else{
	    in = in/10;
	}
    }
    return output;
}

string Mana::toString(){
    string output = "";
    for(int i = 0; i<6; i++){
	if(names[i]=="1"){
	    string add = convertInt(*types[i]);
	    if(add != "0")
		output = output + add;
	}else{
	    for(int j = 0; j<*types[i]; j++){
		output = output + names[i];
	    }
	}
    }
    return output;
};
