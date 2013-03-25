
//Library is a single instance of a deck, in a particular order.
//This particularly needs a lot of refactoring.
class Library{
    //TODO: Should be kept in sorted order
    //But, for now, higher priority is closer to top
    //negative priority is not in the deck and is
    //why this shouldn't be an array
    //In case of a tie, card listed first is drawn first
    //This isn't how it should be but is rare enough
    //to be irrelevant.
    public:
    Card** cards;
    int* priority;
    int length;//length of the two arrays above
    int size;//number of cards currently in deck
    Library(Deck*);
    ~Library();
    int arraySum(int*, int);
    Card* draw();//removes the top card from the deck and returns a pointer to it
    void print();
    //void shuffle();
};


