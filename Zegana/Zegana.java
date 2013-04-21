package Zegana;
import java.util.*;
import java.io.*;
import java.math.*;
import magicGame.*;

public class Zegana{
    public static int genSize = 100;
    public static int trials = 100;
    public static char sa = 'r'; //Preformance evaluation algorithm- r: raw, s: standard deviation
    public static char xa = 't'; //Mutation/representatoin algorithm- t: twiddle, i: crossover/inversion
    public static char env = 't';//Environment- t: tournament style
    public static int end = -1;
    public static int deckCount;
    public static boolean verbose = false;
    public static int genCount = 0;
    public static boolean quit = false;
    private static ArrayList<String> LegalCards = new ArrayList<String>();
    private static Deck[] currentGen = null;
    private static float[] performance = null;

    public static void main(String [] args){
		
	Zegana.initalize(args);

	if(quit)
	    return;
	
	while(genCount != end){
	    genCount++;

	    simulate();
	    printBest();
	    populate();
	    }
	}
    

    public static void printBest(){
	Deck best = Zegana.currentGen[0];
	float bestP = Zegana.performance[0];
	for(int i=1; i<Zegana.performance.length; i++){
	    if(Zegana.performance[i]>bestP){
		bestP = Zegana.performance[i];
		best = Zegana.currentGen[i];
	    }
	}
	System.out.println("New deck:");
	System.out.println(best);
	System.out.println("Performance: \n"+bestP);
    }

    public static void initalize(String[] args){
	if(args.length == 0){
	    printUsage();
	    Zegana.quit = true;
	    return;
	}
	Zegana.parseArguements(args);
    
	if(Zegana.verbose)
	    System.out.println("Reading card list");
	Zegana.readCards("Zegana/standard.txt");
	
	Zegana.currentGen = new Deck[genSize];

	if(Zegana.verbose)
	    System.out.println("Generating new random decks");
	
	if(Zegana.xa == 't'){
	    for(int i=0; i<Zegana.genSize; i++){
		Zegana.currentGen[i] = new Deck(LegalCards, true);
		Zegana.useLands(currentGen[i], 0.3);
	    }
	}
	else if(Zegana.xa == 'i'){
	    for(int i=0; i<Zegana.genSize; i++){
		Zegana.currentGen[i] = new Deck(LegalCards, false);
	    }
	}

	Zegana.performance = new float[genSize];
    }
	
    public static void evaluate(){
	//Using raw values for now
	return;	
    }

    public static void populate(){
	Zegana.currentGen = Zegana.select(Zegana.currentGen, Zegana.performance);

	for(int i=0; i<currentGen.length; i++){
	    if(Math.random()>0.2)
		Zegana.twiddle(currentGen[i]);
	}
    }


    //Generates floats representing the performance of each deck.
    //Guarentees only that higher values are better.
    public static void simulate(){
	for(int i = 0; i<Zegana.currentGen.length; i++){
	    Zegana.performance[i] = 0;
	    for(int j = 0; j<trials; j++){
		int target = (int) (Math.random() * Zegana.currentGen.length);
		int result = Connect.simulate(Zegana.currentGen[i], Zegana.currentGen[target]);
		if(result == 0)
		    result = 1;
		else
		    result = 0;
		Zegana.performance[i] += result;
	    }
	}
	if(Zegana.verbose){
	    System.out.println("Performance array:");
	    for(int i=0; i<Zegana.performance.length; i++){
		System.out.println(Zegana.performance[i]);
	    }
	}
	if(Zegana.verbose)
	    System.out.println("Done simulating generation "+Zegana.genCount);
    }
	/*
	Deck a = new Deck(LegalCards);
	Zegana.useLands(a, 0.31);
	Deck b = new Deck(LegalCards);
	Zegana.useLands(b, 0.31);
	for(int i = 0; i< trials; i++)	
	    Connect.simulate(a, b);
	    */

    
    
    /*
    public static void crossover(Deck a, Deck b){
	int start = 0;
	int end = Math.max(a.cards.size(), b.cards.size());
	if(math.Random()>0.5)
	    start = (int) (Math.random()*end);
	if(math.Random()>0.5)
	    end = end - ((int) Math.random()*(end-start));
    */
    
    public static void twiddle(Deck in){
	if(!in.repeated){
	    System.out.println("Can't twiddle a non-repeated deck");
	    return;
	}
	for(int i=0; i< in.cards.size(); i++){
	    while(Math.random()>0.7){
		int mod = ((int) (Math.random()*3)-1);
		in.quantity.set(i, in.quantity.get(i)+mod);
	    }
	    if(in.quantity.get(i) < 0)
		in.quantity.set(i, 0);
	    if(in.quantity.get(i) > 4){
		if(! CardsInfo.has(in.cards.get(i), "Basic")){
		    in.quantity.set(i, 4);
		}
	    }
	    if(in.quantity.get(i) == 0){
		in.cards.remove(i);
		in.quantity.remove(i);
	    }
	}
	while(Math.random()>0.7){
	    int tries = 10;
	    while(tries > 0){
		String newCard = LegalCards.get((int) (Math.random()*LegalCards.size()));
		if(in.cards.contains(newCard)){
		    tries--;
		}else{
		    in.cards.add(newCard);
		    in.quantity.add(2);
		    break;
		}
	    }
	}
	minSize(in);
    }

    public static Deck[] select(Deck[] old, float[] perf){
	float total = arraySum(perf);
	Deck[] result = new Deck[old.length];
	
	for(int i=0; i<old.length; i++){
	    result[i] = selectOne(old, perf, total);
	}
	return result;
    }

    public static float arraySum(float[] ls){
	float total = 0;
	for(int i=0; i<ls.length; i++){
	    total+=ls[i];
	}
	return total;
    }

    public static Deck selectOne(Deck[] old, float[] perf, float total){
	
	float target = ((float) Math.random())*total;
	float running = 0;
	for(int j=0; j<perf.length; j++){
	    running += perf[j];
	    if((running > target) || (j == perf.length)){
		return new Deck(old[j]);
	    }
	}
	return new Deck(old[old.length]);
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
	System.out.println("  -e       (number of generations");
	System.out.println("  -sa (n)  (selection algorithm");
	System.out.println("           Valid algorithms: r (raw), s (standard deviation)");
	System.out.println("  -xa (n)  (mutation algorithm)");
	System.out.println("           t (twiddle), i (crossover/inversion)");
	System.out.println("  -env (n) (environment)");
	System.out.println("           t (tournament style) f (no opponent)");
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
	    
	    else if(args[position].equals("-env")){
		Zegana.env = args[position+1].charAt(0);
		position++;
	    }

	    else if(args[position].equals("-e")){
		Zegana.end = Integer.parseInt(args[position+1]);
		position++;
	    }
	}
    }

    //Modifies the given deck such that it has 60 cards by adding basic lands
    //equal amounts of each type
    public static void minSize(Deck d){
	int cards = 0;
	int uniqueBasics = 0;
	for(int i=0; i<d.cards.size(); i++){
	    cards += d.quantity.get(i);
	    String s = d.cards.get(i);
	    if(CardsInfo.has(s, "Basic")){
		uniqueBasics++;
	    }
	}
	if(cards<60){
	    int add =60 - cards;
	    if(uniqueBasics != 0){
		int addPer = add/uniqueBasics;
		for(int i = 0; i<d.cards.size(); i++){
		    if(CardsInfo.has(d.cards.get(i), "Basic")){
			if(uniqueBasics==1){
			    d.quantity.set(i, d.quantity.get(i)+add);
			}else{
			    d.quantity.set(i, d.quantity.get(i)+addPer);
			    add-=addPer;
			    uniqueBasics--;
			}
		    }
		}
	    }else{
		d.cards.add("Forest");
		d.quantity.add(add);
	    }
	}
    }
    
    //Modifies the given deck such that the portion of basic
    //lands is equal to i
    public static void useLands(Deck d, double r){
	if(!d.repeated){
	    System.out.println("Uselands does not support decks without repetition");
	    return;
	}
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
    public boolean repeated;

    public Deck(ArrayList<String> LegalCards){
	this(LegalCards, true);
    }

    public Deck(ArrayList<String> LegalCards, boolean rep){
	if(Zegana.verbose)
	    System.out.println("Generating new deck");
	cards = new ArrayList<String>();
	this.repeated = rep;
	if(rep)
	    quantity = new ArrayList<Integer>();
	for(int i=0; i<LegalCards.size(); i++){
	    int r = (int) (Math.random()*5);
	    if(rep){
		if(r>0){
		    cards.add(LegalCards.get(i));
		    quantity.add(r);
		}
	    }else{
		while(r>0){
		    cards.add(LegalCards.get(i));
		    r--;
		}
	    }
	}
	name = ""+Zegana.deckCount;
	if(Zegana.verbose)
	    System.out.println("Generated deck "+Zegana.deckCount);
	Zegana.deckCount++;
    }

    //copy constructor
    public Deck(Deck in){
	name = in.name;
	cards = new ArrayList<String>(in.cards);
	if(in.repeated)
	    quantity = new ArrayList<Integer>(in.quantity);
	repeated = in.repeated;
    }

    @Override
    public String toString(){
	String o = "Deck "+name+'\n';
	int count = 0;
	if(this.repeated){
	    for(int i=0; i<cards.size(); i++){
		o = o + quantity.get(i) + ' ' + cards.get(i) + '\n';
		count += quantity.get(i);
	    }
	}else{
	    for(int i=0; i<cards.size(); i++){
		o = o + cards.get(i) + '\n';
	    }
	}
	if(!this.repeated)
	    count = cards.size();
	o = o +"Cards: "+count+'\n';
	return o;
    }
}
