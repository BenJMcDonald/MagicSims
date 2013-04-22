package magicGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DebicccdGA {
	
	ArrayList<String> cardList;
	String[][] prevPopulation;
	String[][] population;
	Double[] fitness;
	Integer trialSize = 3;
	Double mutationChance = .02;
	Double crossoverChance = .6;
	
	public DebicccdGA(String args){
		cardList = new ArrayList<String>(Arrays.asList(CardDB.getCards()));
	}
	
	public void generateInitialPopulation(int size){
		population = new String[size][];
		prevPopulation = new String[size][];
		fitness = new Double[size];
		
		for(int i = 0; i < size; i++){
			population[i] = this.generateDeck("random");
			fitness[i] = 0.0;
		}
	}
	
	public void generateNextPopulation(String args){
		Random rng = new Random();
		
		prevPopulation = population.clone();
		
		if(args.compareToIgnoreCase("roulette") == 0){
			for(int i = 1; i < fitness.length; i++)
				fitness[i] += fitness[i-1];
			
			for(int i = 0; i < this.population.length; i += 2){
				String[][] next = this.nextTwo(args);
				if(rng.nextDouble() <= this.crossoverChance)
					next = crossover(next[0], next[1], "chunk");
				
				for(int j = 0; j < 2; j++)
					for(int k = 0; k < next[j].length; k++)
						if(rng.nextDouble() <= this.mutationChance){
							//System.out.println(next[j][k]);
							next[j][k] = mutateCard(next[j][k], "land");
							//System.out.println(next[j][k]);
						}
				
				population[i] = next[0];
				if(!(i + 1 >= this.population.length))
					population[i + 1] = next[1];
			}
		}
	}
	
	public void simulatePopulation(String args){
		ArrayList<String[]> players = new ArrayList<String[]>();
		
		players.add(null);
		players.add(null);

		for (int i = 0; i < this.population.length; i += 2) {
			players.set(0, this.population[i]);
			players.set(1, this.population[i + 1]);

			for (int j = 0; j < this.trialSize; j++) {
				GameState gs = new GameState(2);
				gs.makePlayers(players);
				gs.initializeGame();

				ArrayList result = gs.playGame("Com");
				
				Integer playerNum = null;
				
				if(((Player) result.get(1)) != null)
					playerNum = ((Player) result.get(1)).getPlayerNumber();

				if (playerNum != null && playerNum == 0) {
					this.fitness[i] += (1 / (Double.valueOf((int) result.get(0)) / 50));
				} else if (playerNum != null && playerNum == 1) {
					this.fitness[i + 1] += (1 / (Double.valueOf((int) result.get(0)) / 50));
				}
			}
			
			this.fitness[i] /= this.trialSize;
			this.fitness[i + 1] /= this.trialSize;
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

			for (int j = 0; j < this.fitness.length; j++) {
				if (num1 <= this.fitness[j] && num1 >= 0) {
					rt[0] = this.prevPopulation[j].clone();
					num1 = -1.0;
				}

				if (num2 <= this.fitness[j] && num2 >= 0) {
					rt[1] = this.prevPopulation[j].clone();
					num2 = -1.0;
				}
			}
		}
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
		
		if (args.compareToIgnoreCase("any") == 0)
			return this.cardList.get(r.nextInt(this.cardList.size()));
		
		if (args.compareToIgnoreCase("land") == 0) {
			if(isLand(prevCard)){
				int num = r.nextInt(5);
					switch(num){
						case 0:
							return "Swamp";
						case 1:
							return "Island";
						case 2:
							return "Mountain";
						case 3:
							return "Forest";
						case 4:
							return "Plains";
						default:
							return String.valueOf(num);
					}
			}
				
			return this.cardList.get(r.nextInt(this.cardList.size()));
		}
		
		return mutateList.get(r.nextInt(mutateList.size()));
	}

	public String[][] crossover(String[] d1, String[] d2, String args){
		String[][] crossedDecks = new String[2][];
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
			
			crossedDecks[0] = d1;
			crossedDecks[1] = d2;
		}
		
		return crossedDecks;
	}
	
	public String toString(){
		String result = "";
		int i = 0;
		for(String[] s1 : this.population){
			for(String s2 : s1){
				result += s2 + ", ";
			}
			
			result += "Fitness = " + String.valueOf(this.fitness[i]);
			result += "\n";
			
			i++;
		}
		
		return result;
	}
	
	public void printPopulation(){
		for(String[] d : this.population){
			for(String s : d){
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
	
	public void printPrevPopulation(){
		for(String[] d : this.prevPopulation){
			for(String s : d){
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
	
	public boolean isLand(String cardName){
		if(cardName.equalsIgnoreCase("mountain"))
			return true;
		
		if(cardName.equalsIgnoreCase("island"))
			return true;
		
		if(cardName.equalsIgnoreCase("forest"))
			return true;
		
		if(cardName.equalsIgnoreCase("plains"))
			return true;
		
		if(cardName.equalsIgnoreCase("swamp"))
			return true;
		
		return false;
	}
}
