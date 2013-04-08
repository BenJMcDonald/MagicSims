package magicGame;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

public class Tests extends TestCase {

	String[] deckOfAllLands;
	String[] passiveCreatures;
	String[] removalHeavy;
	String[] activeCreatures;
	ArrayList<String[]> decks;
	GameState game;

	protected void setUp() throws Exception {
		super.setUp();
		deckOfAllLands = new String[60];
		passiveCreatures = new String[60];
		removalHeavy = new String[60];
		activeCreatures = new String[60];
		game = new GameState(2);
		decks = new ArrayList<String[]>();

		for (int i = 0; i < 60; i++) {
			deckOfAllLands[i] = "Forest";
			if (i < 30) {
				passiveCreatures[i] = "Forest";
				removalHeavy[i] = "Swamp";
				activeCreatures[i] = "Forest";
			} else {
				passiveCreatures[i] = "Axebane Guardian";
				removalHeavy[i] = "Murder";
				activeCreatures[i] = "Brushstrider";
			}
		}

	}

	protected void tearDown() throws Exception {
		super.tearDown();
		deckOfAllLands = null;
		passiveCreatures = null;
		removalHeavy = null;
		decks = null;
		game = null;
	}

	@Test
	public void testTruth() {
		assertTrue(true);
	}

	@Test
	public void testPlayLand() {
		decks.add(deckOfAllLands);
		decks.add(deckOfAllLands);
		game.makePlayers(decks);
		game.playGame("Com");
		assertTrue(game.getPermanents().get(0).getName().equals("Forest"));
	}

	@Test
	public void testPlayCreature() {
		decks.add(deckOfAllLands);
		decks.add(passiveCreatures);
		game.makePlayers(decks);
		game.playGame("Com");
		ArrayList<Card> permanents = game.getPermanents();
		String permTypes = "";
		for (Card c : permanents) {
			permTypes += c.getTypes();
		}

		assertTrue(permTypes.contains("Creature"));
	}

	@Test
	public void testLosingForNoLife() {
		decks.add(deckOfAllLands);
		decks.add(deckOfAllLands);
		game.makePlayers(decks);
		game.getPlayers().get(0).setLife(0);
		ArrayList<Object> results = game.playGame("Com");
		assertTrue((int) results.get(0) == 1);
	}

	@Test
	public void testTargetedRemoval() {
		decks.add(removalHeavy);
		decks.add(passiveCreatures);
		game.makePlayers(decks);
		game.playGame("Com");
		assertTrue(game.getPermanents().size() == 60);

	}

	@Test
	public void testLosingForDrawingWithEmptyDeck() {
		decks.add(deckOfAllLands);
		decks.add(deckOfAllLands);
		game.makePlayers(decks);
		game.getPlayers().get(0).draw(60);
		ArrayList<Object> results = game.playGame("Com");
		// The player who goes first doesn't draw, so the game will end on turn
		// 3 when the first player attempts to draw and has no deck.
		assertTrue((int) results.get(0) == 3);
	}

	@Test
	public void testCombatDestroyingCreatures() {
		decks.add(activeCreatures);
		decks.add(activeCreatures);
		game.makePlayers(decks);
		// By setting the players' life to something obscenely high, I ensure
		// that there will always eventually be an instance of one creature
		// blocking another creature, so I know that combat damage will be
		// assigned to creatures, and since it happens to be a number larger
		// than their toughness, they should be destroyed at end of combat.
		for (Player p : game.getPlayers()) {
			p.setLife(2000);
			p.draw(40);
		}
		ArrayList<Card> winnerGraveyard = game.getPlayers().get(0)
				.getGraveyard();
		String graveyard = "";
		for (Card c : winnerGraveyard) {
			graveyard += c.getName();
		}

		assertTrue(graveyard.contains("Brushstrider"));

	}

}
