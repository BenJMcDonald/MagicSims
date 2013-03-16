template <class T>
class LLnode {
    private:
	T valueInt;
    public:
	T value(){
	    return valueInt;
	}
	LLnode<T>* next;
	LLnode<T>(T i){
	    next = 0;
	    valueInt = i;
	}
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
