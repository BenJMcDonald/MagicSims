class Effect{
    //return false if the effect failed
    //i.e. countered on resolution
    public:
	virtual bool resolve(LLnodePl*) = 0;
};

class Damage: public Effect{
    private:
	int quantity;
    public:
	bool resolve(LLnodePl*);
	Damage(int);
	Damage(int, int, int);
};
