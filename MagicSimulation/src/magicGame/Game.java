package magicGame;

import java.util.ArrayList;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String[]> decks = new ArrayList<String[]>();
		for (int i = 0; i < 2; i++) {
			decks.add(Game.makeDeck());
		}
		String[] bestDeck = {};
		int turnFinishedCount = 0;
		// This integer is a testing parameter. It can be changed at will.
		int gamesToTest = 10000;

		for (int i = 1; i < gamesToTest; i++) {
			GameState currentState;
			while (true) {
				currentState = new GameState(2);
				currentState.makePlayers(decks);
				currentState.initializeGame();
				ArrayList<Object> results = currentState.playGame();
				if (null != results.get(1)) {
					break;
				}
			}
			bestDeck = currentState.getPlayers().get(0).getDeckList();
			decks = new ArrayList<String[]>();
			decks.add(bestDeck);
			decks.add(Game.makeDeck());
			turnFinishedCount += currentState.getTurnNumber();

		}

		double averageTurnFinished = (double) turnFinishedCount
				/ (double) gamesToTest;
		System.out.println("Results of paired contest:");
		System.out.println("After " + gamesToTest
				+ " games, with an average length of " + averageTurnFinished
				+ " turns, the surviving deck is:");
		Game.printDeck(bestDeck);
		System.out.println();
		System.out
				.println("Results of fish-bowl trials to optimize for fastest win time:  ");

		// Makes a dummy deck to test against.
		String[] deckOfAllLands = new String[60];
		for (int i = 0; i < 60; i++) {
			deckOfAllLands[i] = "Forest";
		}

		// This will be a random deck which is tested against the dummy deck.
		String[] deckToTest;
		int testCount = 200;
		int decksToTest = 10000;
		int endTurnCount = 0;
		double bestAverageTurnWin = Integer.MAX_VALUE;

		ArrayList<Object> results;
		for (int i = 0; i < decksToTest; i++) {
			deckToTest = Game.makeDeck();
			decks = new ArrayList<String[]>();
			decks.add(deckToTest);
			decks.add(deckOfAllLands);
			for (int j = 0; j < testCount; j++) {
				GameState currentState = new GameState(2);
				while (true) {
					currentState = new GameState(2);
					currentState.makePlayers(decks);
					currentState.initializeGame();
					results = currentState.playGame();
					if (null != results.get(1)) {
						break;
					}
				}
				endTurnCount += (int) results.get(0);
			}
			double averageTurnWin = (double) endTurnCount / (double) testCount;
			if (averageTurnWin < bestAverageTurnWin) {
				bestAverageTurnWin = averageTurnWin;
				bestDeck = deckToTest;
			}

		}

		System.out.println("The fastest average turn win is "
				+ bestAverageTurnWin + " turns, and the winning deck is:  ");
		Game.printDeck(bestDeck);

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
		for (String card : cards) {
			if (!card.equals("Forest")) {
				int number = (int) (5 * Math.random());
				for (int i = 0; i < number; i++) {
					newDeck.add(card);
				}
			}
		}
		int size = newDeck.size();
		for (int i = 0; i < (60 - size); i++) {
			newDeck.add("Forest");

		}
		String[] deck = new String[60];
		for (int i = 0; i < 60; i++) {
			deck[i] = newDeck.get(i);
		}

		return deck;
	}

}
