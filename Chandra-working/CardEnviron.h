class CardEnviron{
    Card** cards;
    public:
    int length;
    CardEnviron(string*, int);
    ~CardEnviron();
    Card* fetch(string);
    Card* fetch(int);
    void print();
};
