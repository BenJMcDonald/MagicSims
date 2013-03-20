import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {
	public ArrayList<Card> cardList;
	
	public Deck(){
		this.cardList = new ArrayList<Card>();
	}
	
	public Deck(ArrayList<Card> cardList){
		this.cardList = cardList;
	}

	public ArrayList<Card> getCardList() {
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
	
	public void shuffle(){
		Random r = new Random();
		
		for(int i = 0; i < this.cardList.size(); i++){
			int n = r.nextInt(this.cardList.size() - 1);
			Card temp = this.cardList.get(n);
			this.cardList.set(n, this.cardList.get(i));
			this.cardList.set(i, temp);
		}
	}
	
	public void addCard(Card c){
		this.cardList.add(c);
	}
	
	public void addNCards(Card c, int n){
		for(int i = 0; i < n; i++)
			this.cardList.add(c);
	}
	
	public ArrayList<Card> drawN(int n){
		ArrayList<Card> cards = new ArrayList<Card>();
		
		for(int i = 0; i < n; i++)
			cards.add(this.cardList.remove(0));
		
		return cards;
	}

	public void addBasicLands() {
		int n = 60 - this.cardList.size();
		int[] colors = {0 ,0 ,0 ,0 ,0, 0};
		
		Card mountain = new Card("Mountain", "", 0, new ArrayList<Card.COLOR>(), "", "{R}", "land");
		Card forest = new Card("Forest", "", 0, new ArrayList<Card.COLOR>(), "", "{G}", "land");
		Card island = new Card("Island", "", 0, new ArrayList<Card.COLOR>(), "", "{U}", "land");
		Card swamp = new Card("Swamp", "", 0, new ArrayList<Card.COLOR>(), "", "{B}", "land");
		Card plains = new Card("Plains", "", 0, new ArrayList<Card.COLOR>(), "", "{W}", "land");
		
		for(Card c : this.cardList){
			for(Character chr : c.getManaCost().toCharArray()){
				switch(Character.toUpperCase(chr)){
					case 'R': colors[0]++; colors[5]++; break;
					case 'G': colors[1]++; colors[5]++; break;
					case 'U': colors[2]++; colors[5]++; break;
					case 'B': colors[3]++; colors[5]++; break;
					case 'W': colors[4]++; colors[5]++; break;
				}
			}
		}
		double R = (double) colors[0]/colors[5];
		double G = (double) colors[1]/colors[5];
		double U = (double) colors[2]/colors[5];
		double B = (double) colors[3]/colors[5];
		double W = (double) colors[4]/colors[5];
		
		this.addNCards(mountain, (R - (int) R < .5) ? (int) Math.ceil(R * n) : (int) Math.floor(R * n));
		this.addNCards(forest, (G - (int) G < .5) ? (int) Math.ceil(G * n) : (int) Math.floor(G * n));
		this.addNCards(island, (U - (int) U < .5) ? (int) Math.ceil(U * n) : (int) Math.floor(U * n));
		this.addNCards(swamp, (B - (int) B < .5) ? (int) Math.ceil(B * n) : (int) Math.floor(B * n));
		this.addNCards(plains, (W - (int) W < .5) ? (int) Math.ceil(W * n) : (int) Math.floor(W * n));
		
		while(this.cardList.size() > 60)
			this.cardList.remove(this.cardList.size() - 1);
	}

	public void randomDeck(ArrayList<Card> cards) {
		Random r = new Random();

		r.setSeed(System.currentTimeMillis());

		do {
			int numLands = r.nextInt(15) + 15;

			for (int i = 0; i < (60 - numLands)/4; i++){
				Card c = cards.remove(r.nextInt(cards.size() - 1));
				for(int j = 0; j < 4; j++)
					this.cardList.add(c);
			}

			this.addBasicLands();
		} while (!this.isValid());
	}
	
	public boolean isValid(){
		for(Card c : this.cardList){
			if(c.getType().equalsIgnoreCase("land"))
				continue;
			else if(Collections.frequency(this.cardList, c) > 4)
				return false;
		}
		
		return true;
	}
}
