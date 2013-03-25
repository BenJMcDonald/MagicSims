package Zegana;
import java.util.*;
import java.io.*;

public class Zegana{
    private static boolean verbose = true;
    private static ArrayList<String> LegalCards = new ArrayList<String>();

    public static void main(String [] args){
	if(args.length == 0){
	    printUsage();
	    return;
	}
	readCards("Zegana/standard.txt");
    }

    public static void readCards(String fn){
	if(verbose)
	    System.out.println("Reading card list from file "+fn);
	try{
	    Scanner in = new Scanner(new File(fn));
	    while(in.hasNext()){
		LegalCards.add(in.next());
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
}
