class Deck{
    //TODO: proper privacy policies everywhere
    public:
    Card** cards;
    int* quantity;
    int length;
    int numCards;
    Deck();
    Deck(CardEnviron*);
    Deck(CardEnviron*, Deck*, int);
    ~Deck();
    void print();
    Card* fetch(string);
};
