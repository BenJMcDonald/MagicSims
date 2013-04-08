package magicGame;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

public class Tests extends TestCase {

	String[] deckOfAllLands;
	String[] passiveCreatures;
	String[] removalHeavy;
	ArrayList<String[]> decks;
	GameState game;

	protected void setUp() throws Exception {
		super.setUp();
		deckOfAllLands = new String[60];
		passiveCreatures = new String[60];
		removalHeavy = new String[60];
		game = new GameState(2);
		decks = new ArrayList<String[]>();

		for (int i = 0; i < 60; i++) {
			deckOfAllLands[i] = "Forest";
			if (i < 30) {
				passiveCreatures[i] = "Forest";
				removalHeavy[i] = "Swamp";
			} else {
				passiveCreatures[i] = "Axebane Guardian";
				removalHeavy[i] = "Murder";
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
	public void testPlayLand(){
		decks.add(deckOfAllLands);
		decks.add(deckOfAllLands);
		game.makePlayers(decks);		
		game.playGame("Com");
		assertTrue(game.getPermanents().get(0).getName().equals("Forest"));
	}
	
	@Test
	public void testPlayCreature(){
		decks.add(deckOfAllLands);
		decks.add(passiveCreatures);
		assertTrue(true);
	}
	
	
	
	
	
	
}
