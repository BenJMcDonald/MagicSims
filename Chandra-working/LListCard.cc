//Simple linked list
//For standardization, should include
//a dummy head node with a null content
//pointer
class LLnodeCd {
    private:
	Card* valueInt;
    public:
	Card* value(){
	    return valueInt;
	};
	LLnodeCd* next;
	LLnodeCd(Card* i){
	    next = 0;
	    valueInt = i;
	};
	static LLnodeCd* newList(Card* i){
	    LLnodeCd* b = new LLnodeCd(0);
	    b->next = new LLnodeCd(i);
	};
};
/*
LLnodeCd::LLnode<Card*>(T* i){
    next = 0;
    value = i;
};
*/

/*
Card* LLnodeCd::value(){
    return valueInt;
}
*/ 
