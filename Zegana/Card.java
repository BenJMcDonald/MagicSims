package Zegana;
import java.util.*;


//TODO: Scratch all this. This essentially needs to be a library of
//static methods that tell you things about cards by name. 
//the point being, the mutator can look at what cards do and, for example,
//try including more cards that do the same thing. Or, it can look at what
//colors the cards it has are and include appropriate lands.
/*public class Card{
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

    public Card(String name){
	this(name, null, "");
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
}*/
