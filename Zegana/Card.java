package Zegana;
import java.util.*;

public class Card{
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
