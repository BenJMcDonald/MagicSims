package Zegana;
import java.util.*;

//This represents a cache of cards. When the mutator needs to know
//what a card does, it asks this class.
class CardsInfo{
    private static TreeMap<String, Card> Cards = new TreeMap<String, Card>();
    private static void populate(String s){
	if(Cards.containsKey(s))
	    return;
	else
	    CardsInfo.Cards.put(s, new Card(s));
    }
    
    //if a named card has a named keyword
    public static boolean has(String card, String kwd){
	CardsInfo.populate(card);
	return CardsInfo.Cards.get(card).has(kwd);
    }
}

class Card{
    public String name;
    public Mana cost;
    public String rulesText;
    private TreeSet<String> Keywords;
    private TreeMap<String, Integer> KwdAmounts;
    private TreeMap<String, Mana> KwdCosts;

    public Card(String name, Mana manaCost, String rulesText){
	this.name = name;
	this.cost = manaCost;
	this.rulesText = rulesText;
	this.Keywords =  new TreeSet<String>();
	this.KwdAmounts = new TreeMap<String, Integer>();
	this.KwdCosts = new TreeMap<String, Mana>();
    }

    //This basically needs to be a giant database
    public Card(String name){
	this(name, null, "");
	if(name.compareTo("fred")==0)
	    this.add("trample");
    }
    
    //Adds a keyword
    public void add(String s){
	Keywords.add(s);
    }

    public void add(String s, int a){
	Keywords.add(s);
	KwdAmounts.put(s, a);
    }

    public void add(String s, Mana c){
	Keywords.add(s);
	KwdCosts.put(s, c);
    }
    
    public boolean has(String s){
	return Keywords.contains(s);
    }
}
