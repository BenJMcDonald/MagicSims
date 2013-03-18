import java.util.ArrayList;

public class Creature extends Card {
	int power;
	int toughness;
	String subtypes;
	boolean sumSick;
	boolean tapped;
	
	public Creature(Card c){
		super(c.getCardName(), c.getManaCost(), c.getCmc(), c.getColors(), c.getKeywords(), c.getCardText(), "creature");
		
		this.sumSick = true;
		this.tapped = false;
		
		String[] keywords = c.getKeywords().split(", ");
		
		for(String s : keywords){
			if(s.contains("power"))
				this.power = Character.getNumericValue(s.charAt(0));
			if(s.contains("toughness"))
				this.toughness = Character.getNumericValue(s.charAt(0));
			if(s.contains("haste"))
				this.sumSick = false;
		}
	}
	

	

	public int getPower() {
		return power;
	}




	public void setPower(int power) {
		this.power = power;
	}




	public int getToughness() {
		return toughness;
	}




	public void setToughness(int toughness) {
		this.toughness = toughness;
	}




	public String getSubtypes() {
		return subtypes;
	}




	public void setSubtypes(String subtypes) {
		this.subtypes = subtypes;
	}




	public boolean isSumSick() {
		return sumSick;
	}




	public void setSumSick(boolean sumSick) {
		this.sumSick = sumSick;
	}




	public boolean isTapped() {
		return tapped;
	}




	public void setTapped(boolean tapped) {
		this.tapped = tapped;
	}

	public boolean canAtk(){
		return (!this.tapped && !this.sumSick);
	}


	public String toString(){
		return this.getCardName() + " " + this.power + " " + this.toughness;
	}
}
