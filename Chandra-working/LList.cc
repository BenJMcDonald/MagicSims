//Simple linked list
//For standardization, should include
//a dummy head node with a null content
//pointer
template <class T>
class LLnode {
    private:
	T valueInt;
    public:
	T value(){
	    return valueInt;
	};
	LLnode<T>* next;
	LLnode<T>(T i){
	    next = 0;
	    valueInt = i;
	};
	static LLnode<T>* newList(T i){
	    LLnode<T>* b = new LLnode<T>(0);
	    b->next = new LLnode<T>(i);
	};
};
/*
LLnode<T>::LLnode<T>(T* i){
    next = 0;
    value = i;
};
*/

/*
T LLnode<T>::value(){
    return valueInt;
}
*/ 
