package Zegana;
import java.util.*;

public class Zegana{
    
    public static void main(String [] args){
	if(args.length == 0){
	    printUsage();
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

