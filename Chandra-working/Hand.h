class Hand{
    //Keeps only a pointer to cards. There should be only
    //one instance of each card, so it can just compare these
    //pointers. 

    //For now it'll keep it as a linked list
    //Dummy node at head with null card
    public:
    int size;
    LLnodeCd * first;
    Hand ();
    ~Hand();
   
    void print();
    bool contains(Card*);
    void add(Card*); //Adds card to hand
    bool drop(Card*); //Removes card from hand. returns false if it wasn't found.
    //TODO
    //bool drop(string); //Removes card with specified name
    //bool contains(string);
    //Card* fetch(string);//returns a pointer to the card with the given name in handi
    //TODO- Implement a function that takes a function pointer and returns either the first card the function gives true on or the one it gives he highest float on.
};
