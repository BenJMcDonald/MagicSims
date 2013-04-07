package Zegana;
import java.util.*;
import java.io.*;
import java.math.*;
import magicGame.*;

public class Zegana{
    public static int genSize = 100;
    public static int trials = 100;
    public static char sa = 't';
    public static char xa = '0';
    public static int deckCount;
    public static boolean verbose = false;
    private static ArrayList<String> LegalCards = new ArrayList<String>();

    public static void main(String [] args){
	if(args.length == 0){
	    printUsage();
	    return;
	}

	Zegana.parseArguements(args);

	readCards("Zegana/standard.txt");
	Deck a = new Deck(LegalCards);
	Zegana.useLands(a, 0.31);
	Deck b = new Deck(LegalCards);
	Zegana.useLands(b, 0.31);
	for(int i = 0; i< 100; i++)	
	    Connect.simulate(a, b);
    }

    public static void readCards(String fn){
	if(verbose)
	    System.out.println("Reading card list from file "+fn);
	try{
	    Scanner in = new Scanner(new File(fn));
	    while(in.hasNext()){
		LegalCards.add(in.nextLine());
	    }
	}catch(Exception e){
	    System.out.println(e);
	}
    }

    public static void printUsage(){
	System.out.println("usage: java Zegana.Zegana [command] [-arguements] [input file] [output file]");
	System.out.println("Valid commands:");
	System.out.println("  new      (make new decks)");
	System.out.println("  evolve   (make new decks from a given generation file) (todo)");
	System.out.println("Valid arguements:");
	System.out.println("  -s       (generation size)");
	System.out.println("  -t       (repetitions of each simulation)");
	System.out.println("  -v       (verbose)");
	System.out.println("  -sa (n)  (selection algorithm) (todo)");
	System.out.println("           Valid algorithms: r (roulette wheel) (todo), t (tournament) (todo)");
	System.out.println("  -xa (n)  (mutation algorithm) (todo)");
	System.out.println("           Valid algorithms: err...");
    }

    public static void parseArguements(String[] args){
	for(int position = 1; position < args.length; position++){
	    if(args[position].equals("-s")){
		Zegana.genSize = Integer.parseInt(args[position+1]);
		position++;
	    }
	    
	    else if(args[position].equals("-t")){
		Zegana.trials = Integer.parseInt(args[position+1]);
		position++;
	    }


	    else if(args[position].equals("-v")){
		Zegana.verbose = true;
	    }

	    else if(args[position].equals("-sa")){
		Zegana.sa = args[position+1].charAt(0);
		position++;
	    }

	    else if(args[position].equals("-xa")){
		Zegana.xa = args[position+1].charAt(0);
		position++;
	    }
	}
    }
    
    //Modifies the given deck such that the portion of basic
    //lands is equal to i
    public static void useLands(Deck d, double r){
	int cards = 0;
	int lands = 0;
	int uniqueBasics = 0;
	for(int i=0; i<d.cards.size(); i++){
	    cards += d.quantity.get(i);
	    String s = d.cards.get(i);
	    if(CardsInfo.has(s, "Basic")){
		uniqueBasics++;
		lands += d.quantity.get(i);
	    }
	}
	if(uniqueBasics != 0){
	    int add = (int) (((cards*r) - lands)/uniqueBasics);
	    for(int i = 0; i<d.cards.size(); i++){
		if(CardsInfo.has(d.cards.get(i), "Basic"))
		    d.quantity.set(i, d.quantity.get(i)+add);
	    }
	}else{
	    int add = (int) ((cards*r) - lands);
	    d.cards.add("Forest");
	    d.quantity.add(add);
	}
    }
}

class Deck{
    public ArrayList<String> cards;
    public ArrayList<Integer> quantity;
    public String name;

    public Deck(ArrayList<String> LegalCards){
	if(Zegana.verbose)
	    System.out.println("Generating new deck");
	cards = new ArrayList<String>();
	quantity = new ArrayList<Integer>();
	for(int i=0; i<LegalCards.size(); i++){
	    int r = (int) (Math.random()*5);
	    if(r>0){
		cards.add(LegalCards.get(i));
		quantity.add(r);
	    }
	}
	name = ""+Zegana.deckCount;
	if(Zegana.verbose)
	    System.out.println("Generated deck "+Zegana.deckCount);
	Zegana.deckCount++;
    }
    
    @Override
    public String toString(){
	String o = "Deck "+name+'\n';
	for(int i=0; i<cards.size(); i++){
	    o = o + quantity.get(i) + ' ' + cards.get(i) + '\n';
	}
	return o;
    }
}
