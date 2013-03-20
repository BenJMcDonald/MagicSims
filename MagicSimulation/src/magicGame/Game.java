package magicGame;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int poolSize = 200;
		int trials = 300;
		ArrayList<String[]> generation = Game.makeGeneration(poolSize);
		System.out.println("New pool of " + poolSize
				+ " decks generated. \nThis is the first generation.");
		int endingGeneration = 10000;
		// Makes a dummy deck to test against.
		String[] deckOfAllLands = new String[60];
		for (int i = 0; i < 60; i++) {
			deckOfAllLands[i] = "Forest";
		}
		ArrayList<Object> results;
		ArrayList<String[]> decks;
		GameState currentState;
		double bestAverageWinTime = Integer.MAX_VALUE;
		// A silly initialization, but later stuff wants it initialized.
		String[] bestDeck = deckOfAllLands;
		double[] records = new double[poolSize];
		double threshold;
		for (int gen = 0; gen < endingGeneration; gen++) {
			System.out.println("Begin generation " + (gen + 1) + " testing.");

			for (int i = 0; i < poolSize; i++) {
				int turnCount = 0;
				for (int j = 0; j < trials; j++) {

					currentState = new GameState(2);
					decks = new ArrayList<String[]>();
					decks.add(generation.get(i));
					decks.add(deckOfAllLands);
					currentState.makePlayers(decks);
					currentState.initializeGame();
					results = currentState.playGame();
					turnCount += (int) results.get(0);
				}
				double averageWinTime = (double) turnCount
						/ (2 * (double) trials);
				records[i] = averageWinTime;
				if (averageWinTime < bestAverageWinTime) {
					bestDeck = generation.get(i);
					bestAverageWinTime = averageWinTime;
					System.out.println("New best deck is deck number " + gen
							+ "-" + i + " , with an average win time of "
							+ averageWinTime + " turns.");
					System.out.println(bestDeck.length + " cards");
					Game.printDeck(bestDeck);
					System.out.println();
				}
			}

			System.out
					.println("Finished testing generation "
							+ (gen + 1)
							+ ".  Now building next generation from most successful members of this generation.");
			threshold = 0;
			for (int j = 0; j < records.length; j++) {
				threshold += records[j];
			}
			threshold = threshold / records.length;

			ArrayList<String[]> nextGen = new ArrayList<String[]>();
			for (int deckIndex = 0; deckIndex < poolSize; deckIndex++) {
				if (records[deckIndex] > threshold) {
					nextGen.add(Game.makeDeck(generation.get(deckIndex)));

				} else {
					nextGen.add(Game.makeDeck(bestDeck));
				}

			}

			generation = nextGen;

		}

		System.out.println("After " + endingGeneration
				+ " generations, the most successful deck is: ");
		Game.printDeck(bestDeck);
		System.out.println();
		System.out.println(bestDeck.length + " cards, average winning turn of "
				+ bestAverageWinTime + ".");

		// TODO: make a makeGeneration function that takes the list of decks as
		// an argument

		// ArrayList<String[]> decks = new ArrayList<String[]>();
		// for (int i = 0; i < 2; i++) {
		// decks.add(Game.makeDeck());
		// }
		// String[] bestDeck = {};
		// int turnFinishedCount = 0;
		// int failedPairCount = 0;
		// // This integer is a testing parameter. It can be changed at will.
		// int gamesToTest = 50000;
		// ArrayList<Object> results;
		// for (int i = 1; i < gamesToTest; i++) {
		// GameState currentState;
		// currentState = new GameState(2);
		// currentState.makePlayers(decks);
		// currentState.initializeGame();
		// results = currentState.playGame();
		// decks = new ArrayList<String[]>();
		// if (null != results.get(1)) {
		//
		// bestDeck = currentState.getPlayers().get(0).getDeckList();
		// decks.add(bestDeck);
		// decks.add(Game.makeDeck());
		// turnFinishedCount += currentState.getTurnNumber();
		// } else {
		// failedPairCount++;
		// decks.add(Game.makeDeck());
		// decks.add(Game.makeDeck());
		//
		// System.out.println("Failure! " + i);
		// }
		//
		// }
		//
		// double averageTurnFinished = (double) turnFinishedCount
		// / (double) (gamesToTest - 2 * failedPairCount);
		// System.out.println("Results of paired contest:");
		// System.out.println("After " + gamesToTest + " games and "
		// + failedPairCount * 2
		// + " failed decks, with an average length of "
		// + averageTurnFinished
		// + " turns, the surviving deck, with a size of "
		// + bestDeck.length + " is:");
		// Game.printDeck(bestDeck);
		// System.out.println();

		// This will be a random deck which is tested against the dummy deck.
		// String[] deckToTest;
		// int testCount = 200;
		// int decksToTest = 10000;
		// int endTurnCount = 0;
		// double bestAverageTurnWin = Integer.MAX_VALUE;
		// System.out
		// .println("Results of fish-bowl trials to optimize for fastest win time ("
		// + (testCount * decksToTest) + " trials):  ");
		// ArrayList<Object> results;
		// for (int i = 0; i < decksToTest; i++) {
		// deckToTest = Game.makeDeck();
		// decks = new ArrayList<String[]>();
		// decks.add(deckToTest);
		// decks.add(deckOfAllLands);
		// for (int j = 0; j < testCount; j++) {
		// GameState currentState = new GameState(2);
		// while (true) {
		// currentState = new GameState(2);
		// currentState.makePlayers(decks);
		// currentState.initializeGame();
		// results = currentState.playGame();
		// if (null != results.get(1)) {
		// break;
		// }
		// }
		// endTurnCount += (int) results.get(0);
		// }
		// double averageTurnWin = (double) endTurnCount / (double) testCount;
		// if (averageTurnWin < bestAverageTurnWin) {
		// bestAverageTurnWin = averageTurnWin;
		// bestDeck = deckToTest;
		// }
		//
		// }
		//
		// System.out.println("The fastest average turn win is "
		// + bestAverageTurnWin + " turns, and the winning deck is:  ");
		// Game.printDeck(bestDeck);

		// int finalTurn = currentState.getTurnNumber();
		// System.out.println("The game has ended on turn " + finalTurn + "!");

		// //Test code here.
		// String[] dummyCardList = { "Forest", "Forest", "Forest", "Forest",
		// "Forest", "Forest", "Forest", "Forest", "Forest", "Forest", "Forest",
		// "Forest", "Forest", "Forest", "Forest", "Forest"}; String[] deck1 =
		// dummyCardList; String[] deck2 = dummyCardList;
		//
		//
		//
		// Player player1 = new Player(deck1); Player player2 = new
		// Player(deck2);
		//
		// ArrayList<Card> constructedDeck1 = player1.getDeck(); ArrayList<Card>
		// constructedDeck2 = player2.getDeck();
		//
		// System.out.println("This is a test of my deck constructing code");
		// for(int i = 0; i < constructedDeck1.size(); i++){
		// System.out.println(constructedDeck1.get(i).name);
		//
		// }
		//
		// String[] deck3 = Game.makeDeck();
		//
		// Player player3 = new Player(deck3, currentState);
		//
		// ArrayList<Card> constructedDeck3 = player3.getDeck();
		// System.out.println("This is a test of my deck constructing code");
		// for (int i = 0; i < constructedDeck3.size(); i++) { System.out
		// .print(constructedDeck3.get(i).getName() + " " + i + ", "); }
		// System.out.println(); System.out.print(constructedDeck3.size());
		//

	}

	private static ArrayList<String[]> makeGeneration(int poolSize) {
		ArrayList<String[]> decks = new ArrayList<String[]>();
		for (int i = 0; i < poolSize; i++) {
			decks.add(Game.makeDeck());
		}

		return decks;

	}

	private static void printDeck(String[] bestDeck) {
		for (String s : bestDeck) {
			System.out.print(s + ", ");
		}

	}

	// TODO: make another deck constructor makeDeckFromSeed which uses
	// Math.random() to permutate a deck by taking the random double and
	// subtracting 0.5, then multiplying that by an interval size and changing
	// the deck on a card by card basis this way.
	private static String[] makeDeck() {
		String[] cards = CardDB.getCards();
		ArrayList<String> newDeck = new ArrayList<String>();
		ArrayList<String> basicLands = new ArrayList<String>();
		basicLands.add("Forest");
		basicLands.add("Swamp");
		basicLands.add("Mountain");
		basicLands.add("Plains");
		basicLands.add("Island");
		for (String card : cards) {
			int multiplier = 5;
			if (basicLands.contains(card)) {
				multiplier = 15;
			}

			int number = (int) (multiplier * Math.random());
			for (int i = 0; i < number; i++) {
				newDeck.add(card);
			}
		}
		// Fill with forests if not full
		int size = newDeck.size();
		for (int i = 0; i < (60 - size); i++) {
			newDeck.add("Forest");

		}
		String[] deck = new String[newDeck.size()];
		for (int i = 0; i < newDeck.size(); i++) {
			deck[i] = newDeck.get(i);
		}

		return deck;
	}

	private static String[] makeDeck(String[] parentDeck) {
		// TODO: make it so that the "four of a card" rule is preserved.

		// wip = work in progress. It was too hard to do with just string
		// arrays.
		ArrayList<String> wip = new ArrayList<String>();
		for (String s : parentDeck) {
			wip.add(s);
		}
		// TODO: Figure out how to do permutations better. It may become
		// necessary to
		// change how decks are represented.

		// mutate the deck 70% of the time. This is an arbitrary choice.
		boolean mutate = false;
		if (Math.random() > .3) {
			mutate = true;
		}
		if (mutate) {
			for (String card : parentDeck) {
				// There will be three possibilities for each card already in
				// the deck: Remove, replicate, or do nothing.
				double mutationSelector = Math.random();
				if (mutationSelector < 0.05) {
					wip.remove(card);
				}
			}

			for (String s : CardDB.getCards()) {
				// For each card, there is a 2% chance that one will be randomly
				// added to the deck.
				if (Math.random() > .98) {
					wip.add(s);
				}
			}

			// This block of code makes sure that there aren't more than 4 of a
			// given card in the mutated deck.
			Collections.sort(wip);
			// TODO: make more efficient sort happen, assuming that
			// Collections.sort is not optimally efficient.

			ArrayList<String> basicLands = new ArrayList<String>();
			basicLands.add("Forest");
			basicLands.add("Swamp");
			basicLands.add("Mountain");
			basicLands.add("Plains");
			basicLands.add("Island");
			int thingy = 0;
			String card;
			int cardCount = 0;
			while (thingy < wip.size()) {
				card = wip.get(thingy);
				cardCount = 1;
				int otherThingy = thingy + 1;
				if (otherThingy < wip.size()) {
					while (card.equals(wip.get(otherThingy))) {
						cardCount++;
						otherThingy++;
						if (otherThingy == wip.size()) {
							break;
						}
					}
					if (cardCount > 4) {
						if (!basicLands.contains(card)) {
							for (int i = 3; i < cardCount; i++) {
								wip.remove(card);
							}
							cardCount = 4;
						}
					}

				}

				thingy += cardCount;
			}

			// Trim down to at most 75 cards.
			// while (wip.size() > 75) {
			// wip.remove((int) ((wip.size()) * Math.random()));
			// }

			// Add forests up to 60 card minimum deck size.
			if (wip.size() < 60) {
				int defficit = 60 - wip.size();
				for (int i = 0; i < defficit; i++) {
					wip.add(CardDB.getCards()[(int) Math.random() * 5]);
				}
			}
		}

		String[] childDeck = new String[wip.size()];
		for (int i = 0; i < wip.size(); i++) {
			childDeck[i] = wip.get(i);
		}

		return childDeck;
	}

}
