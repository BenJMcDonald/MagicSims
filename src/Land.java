import java.util.ArrayList;


public class Land extends Card {
	public boolean tapped;
	public String manaTypes;
	
	public Land(String cardName, String manaCost, int cmc,
			ArrayList<COLOR> colors, String keywords, String cardText) {
		super(cardName, manaCost, cmc, colors, keywords, cardText, "land");

		this.setManaTypes("");
		
		this.tapped = false;
	}
	
	public Land(Card c){
		super(c.getCardName(), "", 0, c.getColors(), c.keywords, c.cardText, "land");
		
		this.setManaTypes("");
		
		this.tapped = false;
	}

	public boolean isTapped() {
		return this.tapped;
	}

	public void setTapped(boolean tapped) {
		this.tapped = tapped;
	}

	public String getManaTypes() {
		return manaTypes;
	}

	public void setManaTypes(String manaTypes) {
		if (manaTypes.equals("")) {
			this.manaTypes = "";
			if (this.cardText.contains("{R}"))
				this.manaTypes += "R";

			if (this.cardText.contains("{U}"))
				this.manaTypes += "U";

			if (this.cardText.contains("{B}"))
				this.manaTypes += "B";

			if (this.cardText.contains("{W}"))
				this.manaTypes += "W";

			if (this.cardText.contains("{G}"))
				this.manaTypes += "G";

			if (this.cardText.contains("{1}"))
				this.manaTypes += "1";
		} else
			this.manaTypes = manaTypes;
	}

	public boolean typeIsAvalible(String s){
		return this.manaTypes.contains(s);
	}

}
