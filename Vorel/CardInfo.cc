#include <string>
using namespace std;

//The goal of this is that you ask it if a card has a particular
//keyword, where keyword might be a keyword as defined in magic-
//i.e. trample, haste or a keyword meant as an AI hint, i.e. 
//ramp, draw, burn. If that keyword has a value associated with it
//i.e. draw 2, it returns that value. If not, it returns 0. If the
//card does not have the keyword, it retruns -1.

//The right way to do this is something like map<string, pair<string, int>>
//However, I have a very small problem size here and I don't think
//the overhead is worth it. Also, the pairings are completely static,
//so I'm going to do it the ugly way.


string validCards[10] = 
    {"Island",
    "Forest",
    "Time Warp",
    "Capture of Jingzhou",
    "Temporal Manipulation",
    "Time Stretch",
    "Walk the Aeons",
    "Howling Mine",
    "Rites of Flourishing",
    "Font of Mythos"
    };

bool isBasic(string s){
    if(s == "Island")
	return true;
    if(s == "Forest")
	return true;
    return false;
};

bool isLand(string s){
    if(s == "Island")
	return true;
    if(s == "Forest")
	return true;
    return false;
};

int isExtraTurn(string s){
    if(s == "Time Warp")
	return 1;
    if(s == "Capture of Jingzhou")
	return 1;
    if(s == "Temporal Manipulation")
	return 1;
    if(s == "Time Stretch")
	return 2;
    if(s == "Walk the Aeons")
	return 1;
    return 0;
};

int isCardsPerTurn(string s){
    if(s == "Howling Mine")
	return 1;
    if(s == "Rites of Flourishing")
	return 1;
    if(s == "Font of Mythos")
	return 2;
    return 0;
};
