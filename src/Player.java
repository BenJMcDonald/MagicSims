import java.util.ArrayList;


public class Player {
	public int life;
	public Deck deck;
	public ArrayList<Card> hand;
	public ArrayList<Land> lands;
	public ArrayList<Creature> critters;
	
	public Player(){
		deck = new Deck();
		hand = new ArrayList<Card>();
		lands = new ArrayList<Land>();
		critters = new ArrayList<Creature>();
	}
	
	public Player(Deck deck){
		this.deck = deck;
		hand = new ArrayList<Card>();
		lands = new ArrayList<Land>();
		critters = new ArrayList<Creature>();
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public ArrayList<Land> getLands() {
		return lands;
	}

	public void setLands(ArrayList<Land> lands) {
		this.lands = lands;
	}

	public void drawN(int n){
		this.hand.addAll(this.deck.drawN(n));
	}
	
	public ArrayList<Creature> getCritters() {
		return critters;
	}

	public void setCritters(ArrayList<Creature> critters) {
		this.critters = critters;
	}

	public void damage(int d){
		this.life -= d;
	}
	
	public void gainLife(int h){
		this.life += h;
	}
	
	public void addLand(Card c){
		Land l = new Land(c);
		
		this.lands.add(l);
	}
	
	public void addCreature(Card c){
		Creature critter = new Creature(c);
		
		this.critters.add(critter);
	}
}
