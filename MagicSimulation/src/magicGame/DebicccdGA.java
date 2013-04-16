package magicGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DebicccdGA {
	
	ArrayList<String> cardList;
	String[][] prevPopulation;
	String[][] population;
	Double[] fitness;
	
	public DebicccdGA(String args){
		cardList = new ArrayList<String>(Arrays.asList(CardDB.getCards()));
	}
	
	public void generateInitialPopulation(int size){
		for(int i = 0; i < size; i++)
			population[i] = this.generateDeck("random");
	}
	
	public void generateNextPopulation(String args){
		Random rng = new Random();
		String[] d1 = null;
		String[] d2 = null;
		
		prevPopulation = population.clone();
		
		if(args.compareToIgnoreCase("roulette") == 0){
			for(int i = 1; i < fitness.length; i++)
				fitness[i] += fitness[i-1];
			
			for(int i = 0; i < this.population.length; i += 2){
				String[][] next = this.nextTwo(args);
				population[i] = next[0];
				if(!(i + 1 >= this.population.length))
					population[i + 1] = next[1];
			}
		}
	}
	
	public String[][] nextTwo(String args) {
		Random rng = new Random();
		String[][] rt = new String[2][0];

		if (args.compareToIgnoreCase("roulette") == 0) {
			Double num1 = rng.nextDouble() * this.fitness[this.fitness.length - 1];
			Double num2 = rng.nextDouble() * this.fitness[this.fitness.length - 1];

			if (num2 < num1) {
				Double temp = num1;
				num1 = num2;
				num2 = temp;
			}

			System.out.println("\t\t" + num1 + " : " + num2);

			for (int j = 0; j < this.fitness.length; j++) {
				if (num1 <= this.fitness[j] && num1 >= 0) {
					rt[0] = this.prevPopulation[j];
					num1 = -1.0;
				}

				if (num2 <= this.fitness[j] && num2 >= 0) {
					rt[1] = this.prevPopulation[j];
					num2 = -1.0;
				}
			}
		}
		System.out.println("\t\t\t" + rt[0][0] + " : " + rt[1][0]);
		return rt;
	}
	
	public String[] generateDeck(String args){
		Random r = new Random();
		int size = r.nextInt(31) + 60;
		String[] deck = new String[size];
		
		if(args.compareToIgnoreCase("random") == 0){
			for(int i = 0; i < size; i++){
				int num = r.nextInt(13);
				if(num % 3 == 0){
					switch(num){
						case 0:
							deck[i] = "Swamp";
							break;
						case 3:
							deck[i] = "Island";
							break;
						case 6:
							deck[i] = "Mountain";
							break;
						case 9:
							deck[i] = "Forest";
							break;
						case 12:
							deck[i] = "Plains";
							break;
						default:
							deck[i] = String.valueOf(num);
					}
				} else{
					deck[i] = this.cardList.get(r.nextInt(this.cardList.size()));
				}
			}
		}
		
		return deck;
	}
	
	public String mutateCard(String prevCard, String args){
		ArrayList<String> mutateList = new ArrayList<String>();
		Card crd = CardDB.getCard(prevCard, Player.blankPlayer());
		Random r = new Random();
		

		if (args.compareToIgnoreCase("color") == 0) {
			for (String s : cardList)
				if (crd.similarColors(CardDB.getCard(s, Player.blankPlayer())))
					mutateList.add(s);
			
			return mutateList.get(r.nextInt(mutateList.size()));
		}
		
		mutateList = this.cardList;
		return mutateList.get(r.nextInt(mutateList.size()));
	}
	
	public ArrayList<String[]> crossover(String[] d1, String[] d2, String args){
		ArrayList<String[]> crossedDecks = new ArrayList<String[]>();
		Random rng = new Random();

		int crossoverStart;
		int crossoverEnd;

		if (args.compareToIgnoreCase("chunk") == 0) {
			if (d1.length < d2.length) {
				crossoverStart = rng.nextInt(d1.length);
				crossoverEnd = rng.nextInt(d1.length - crossoverStart) + crossoverStart;
			} else {
				crossoverStart = rng.nextInt(d2.length);
				crossoverEnd = rng.nextInt(d2.length - crossoverStart) + crossoverStart;
			}
			
			for(int i = crossoverStart; i <= crossoverEnd; i++){
				String temp = d1[i];
				d1[i] = d2[i];
				d2[i] = temp;
			}
			
			crossedDecks.add(d1);
			crossedDecks.add(d2);
		}
		
		return crossedDecks;
	}
}
