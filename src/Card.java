import java.util.ArrayList;


public class Card implements Comparable{
	public String cardName;
	public String manaCost;
	public int cmc;
	public ArrayList<COLOR> colors;
	public String keywords;
	public String cardText;
	public String type;
	
	public enum COLOR{
		Red, Blue, Black, Green, White
	}

	public Card(String cardName, String manaCost, int cmc, ArrayList<COLOR> colors,
			String keywords, String cardText, String type) {
		this.cardName = cardName;
		this.manaCost = manaCost;
		this.cmc = cmc;
		this.colors = colors;
		this.keywords = keywords;
		this.cardText = cardText;
		this.type = type;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public int getCmc() {
		return cmc;
	}

	public void setCmc(int cmc) {
		this.cmc = cmc;
	}

	public ArrayList<COLOR> getColors() {
		return colors;
	}

	public void setColors(ArrayList<COLOR> colors) {
		this.colors = colors;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}
	
	public String toString(){
		return this.cardName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean canPlay(String mana){
		if(mana.length() < this.getCmc())
			return false;
		
		ArrayList<Character> costArr = new ArrayList<Character>();
		
		for(int i = 0; i < mana.length(); i++)
			costArr.add(mana.charAt(i));
		
		for(int i = (this.manaCost.length() - 1); i >= 0; i--){
			Character c = this.manaCost.charAt(i);
			if(costArr.contains(c))
				costArr.remove(c);
			
			else if(c >= '0' && c <= '9'){
				if(Character.getNumericValue(c) <= costArr.size())
					return true;
			}
			else if(c == 'X' || c == 'x')
				return true;
			
			else
				return false;
		}
		
		return true;
	}
	
	public String removeMana(String manaPool){
		for(int i = (this.manaCost.length() - 1); i >= 0; i--){
			Character c = this.manaCost.charAt(i);
			
			if(c >= '0' && c <= '9'){
				for(int j = 0; j < Character.getNumericValue(c); j++)
					manaPool = manaPool.substring(1);
			} else{
					manaPool = manaPool.replaceFirst(c.toString(), "");
			}
		}
		
		return manaPool;
	}

	@Override
	public int compareTo(Object card2) throws ClassCastException{
		if (!(card2 instanceof Card))
		      throw new ClassCastException("A Card object expected.");
		
		if(this.cmc == ((Card) card2).getCmc())
			return this.cardName.compareTo(((Card) card2).getCardName());
		
		return ((Card) card2).getCmc() - this.cmc;
	}
}
