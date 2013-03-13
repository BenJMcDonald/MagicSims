package magicGame;

import java.util.ArrayList;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// We're going to start with just a two-player game, but potentially
		// this could ask for user input and make a correspondingly sized game.
		GameState currentState = new GameState(2);
		ArrayList<String[]> decks = new ArrayList<String[]>();
		for (int i = 0; i < currentState.getNumPlayers(); i++) {
			decks.add(Game.makeDeck());
		}
		currentState.makePlayers(decks);
		currentState.initializeGame();
		currentState.playGame();
		int finalTurn = currentState.getTurnNumber();
		System.out.println("The game has ended on turn " + finalTurn + "!");

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
