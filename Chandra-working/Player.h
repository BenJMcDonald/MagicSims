class Player{
    private:
	Deck* deck;
	Library* libr;
	bool lost;
	int life;
    public:
	Hand* hand;
	void* graveyard;
	void draw();
	void damage(int);
	Player(Deck*);
	~Player();
	void print();
	bool hasLost();
};

