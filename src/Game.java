import java.util.Collections;


public class Game {
	public Player p1;
	
	public Game(){
		
	}
	
	public Game(Player p1){
		this.p1 = p1;
	}

	public void init() {
		this.p1.setLife(20);

		this.p1.getDeck().shuffle();

		try {
			this.p1.setHand(this.p1.getDeck().drawN(7));
		} catch (Exception e) {
			this.p1.setLife(-999);
		}
	}

	public void upkeep(){
		for(Creature c : p1.getCritters())
			c.setSumSick(false);
	}
	
	public void untap(){
		for(Creature c : p1.getCritters())
			c.setTapped(false);
	}
	
	public void draw() {
		try {
			this.p1.drawN(1);
		} catch (Exception e) {
			this.p1.setLife(-999);
		}
	}
	
	public void mainPhase(){
		this.playLand();
		this.playSpells();
	}
	
	public void combat(){
		int combatDamage = 0;
		
		for(Creature c : p1.getCritters())
			if(c.canAtk())
				combatDamage += c.getPower();
		
		//System.out.println("Combat Damage " + combatDamage);
		p1.damage(combatDamage);
	}
	
	public void playLand(){
		for(int i = 0; i < p1.getHand().size(); i++){
			if(this.p1.getHand().get(i).type.equals("land")){
				//System.out.println("Playing " + this.p1.getHand().get(i).cardName);
				this.p1.addLand(p1.getHand().remove(i));
				break;
			}
		}
	}
	
	public void playSpells(){
		String manaPool = "";
		
		Collections.sort(p1.getHand());
		
		for(Land l : this.p1.getLands())
			if(!l.isTapped())
				manaPool += l.getManaTypes();
		
		for(int i = 0; i < this.p1.getHand().size(); i++){
			Card c = this.p1.getHand().get(i);
			if(!c.getType().equals("land"))
				if(c.canPlay(manaPool)){
					this.playSpell(c);
					manaPool = c.removeMana(manaPool);
					this.p1.getHand().remove(c);
				}
		}
	}

	public void playSpell(Card c) {
		// System.out.println("Playing " + c.getCardName());
		if (c.getType().equalsIgnoreCase("creature"))
			p1.addCreature(c);
		if (c.getKeywords().contains("damage"))
			p1.damage(Character.getNumericValue(c.getKeywords().charAt(0)));
		if (c.getKeywords().contains("draw")) {
			try {
				p1.drawN(Character.getNumericValue(c.getKeywords().charAt(0)));
			} catch (Exception e) {
				this.p1.setLife(-999);
			}
		}
	}
}
