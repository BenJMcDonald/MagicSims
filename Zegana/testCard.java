package Zegana;

class testCard{
    public static void main(String[] args){
	Card c = new Card("Cardy", new Mana(1, 1, 1, 1, 2, 3), "do things");
	c.add("Trample");
	c.add("Devour", 2);
	c.add("Miracle", new Mana(0, 0, 0, 0, 0, 1));

	System.out.println(c.has("Miracle"));
	System.out.println(c.has("Haste"));
	System.out.println(c.cost.cmc());
    }
}
