package Zegana;
import java.util.*;
import java.io.*;
import java.math.*;

public class Zegana{
    public static int deckCount = 1;
    public static boolean verbose = true;
    private static ArrayList<String> LegalCards = new ArrayList<String>();

    public static void main(String [] args){
	if(args.length == 0){
	    printUsage();
	    return;
	}
	readCards("Zegana/standard.txt");
	System.out.println(new Deck(LegalCards));
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
	System.out.println("usage: Zegana.Zegana [command] [-arguements] [input file] [output file]");
	System.out.println("Valid commands:");
	System.out.println("  new      (make new decks)");
	System.out.println("  evolve   (make new decks from a given generation file)");
	System.out.println("Valid arguements:");
	System.out.println("  -s        (generation size)");
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
