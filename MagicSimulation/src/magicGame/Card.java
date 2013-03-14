package magicGame;

//This is going to be the meat of the magic game.

public class Card {

	private String name;
	private String cost;
	private int cmc;
	// There are better ways to handle types, but I haven't figured out how to
	// implement them yet.
	private String types;
	private int power = -2;
	private int toughness = -2;
	private Player controller;
	private Player owner;
	private boolean tapped;
	private boolean attacking = false;
	private Card blocking = null;
	private boolean blocked = false;
	private boolean summoningSickness = true;
	private int damageMarked = 0;
	private Player defendingPlayer = null;
	private Card blockedBy = null;

	public Card(String cardName, String cardCost, String cardTypes,
			Player player) {

		this.name = cardName;
		this.cost = cardCost;
		this.cmc = cardCost.length();
		this.types = cardTypes;
		this.controller = player;
		this.owner = player;
		this.tapped = false;

	}

	public Card(String cardName, String cardCost, String cardTypes,
			Player player, int newPower, int newToughness) {

		this.name = cardName;
		this.cost = cardCost;
		this.cmc = cardCost.length();
		this.types = cardTypes;
		this.power = newPower;
		this.toughness = newToughness;
		this.controller = player;
		this.owner = player;
		this.tapped = false;
	}

	/**
	 * The getters and setters live down here.
	 */

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCmc() {
		return cmc;
	}

	public void setCmc(int cmc) {
		this.cmc = cmc;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
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

	public Player getController() {
		return controller;
	}

	public void setController(Player controller) {
		this.controller = controller;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isTapped() {
		return tapped;
	}

	public void setTapped(boolean tapped) {
		this.tapped = tapped;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public void untap() {
		this.tapped = false;

	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	// These are for when this card blocks, so it knows what it is blocking.
	public Card getBlocking() {
		return blocking;
	}

	public void setBlocking(Card blocking) {
		this.blocking = blocking;
	}

	public boolean hasSummoningSickness() {
		return summoningSickness;
	}

	public void setSummoningSickness(boolean summoningSickness) {
		this.summoningSickness = summoningSickness;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public int getDamageMarked() {
		return damageMarked;
	}

	public void dealDamage(int damageDealt) {
		this.damageMarked += damageDealt;
	}

	public Player getDefendingPlayer() {
		return defendingPlayer;
	}

	public void setDefendingPlayer(Player defendingPlayer) {
		this.defendingPlayer = defendingPlayer;
	}

	// These are for if this card is attacking, so it knows what creature it is
	// blocked by.
	public Card getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(Card blockedBy) {
		this.blockedBy = blockedBy;
	}

}
