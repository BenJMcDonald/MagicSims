//Apparently, this has to exist because templates
//are handled wierdly and as such can't actually
//be used in a nice object-oriented way

//Simple linked list
//For standardization, should include
//a dummy head node with a null content
//pointer
class LLnodePl {
    private:
	Player* valueInt;
    public:
	Player* value(){
	    return valueInt;
	};
	LLnodePl* next;
	LLnodePl(Player* i){
	    next = 0;
	    valueInt = i;
	};
	static LLnodePl* newList(Player* i){
	    LLnodePl* b = new LLnodePl(0);
	    b->next = new LLnodePl(i);
	};
};
/*
LLnodePl::LLnode<Player*>(T* i){
    next = 0;
    value = i;
};
*/

/*
Player* LLnodePl::value(){
    return valueInt;
}
*/ 
