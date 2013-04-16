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
		game.initializeGame();
		// By setting the players' life to something obscenely high, I ensure
		// that there will always eventually be an instance of one creature
		// blocking another creature, so I know that combat damage will be
		// assigned to creatures, and since it happens to be a number larger
		// than their toughness, they should be destroyed at end of combat.
		for (Player p : game.getPlayers()) {
			p.setLife(20000);
			p.draw(10);
		}
		game.playGame("Com");
		ArrayList<Card> winnerGraveyard = game.getPlayers().get(0)
				.getGraveyard();
		String graveyard = "";
		for (Card c : winnerGraveyard) {
			graveyard += c.getName();
		}

		assertTrue(graveyard.contains("Brushstrider"));

	}
	
	//todo: swap the values of the assertEquals statements so they make sense
	public void testAttackBlock(){
		ArrayList<Card> creatureList1 = new ArrayList<Card>();
		ArrayList<Card> creatureList2 = new ArrayList<Card>();

		Player p1 = new Player(activeCreatures, game);
		Player p2 = new Player(activeCreatures, game);
		
		decks.add(activeCreatures);
		decks.add(activeCreatures);
		
		game.makePlayers(decks);
		
		Card elk1 = new Card("Dawntreader Elk", "1G", "Creature - Elk", p1, 2, 2);
		
		Card elk2 = new Card("Dawntreader Elk", "1G", "Creature - Elk", p2, 2, 2);
		
		Card elk3 = new Card("Buff Dawntreader Elk", "1G", "Creature - Elk", p2, 4, 4);
		
		elk1.setSummoningSickness(true);
		elk2.setSummoningSickness(false);
		elk3.setSummoningSickness(false);
		
		creatureList1.add(elk1);
		creatureList2.add(elk2);
		creatureList2.add(elk3);
		
		creatureList1 = p1.chooseAttackers(creatureList1);
		creatureList2 = p2.chooseAttackers(creatureList2);
		
		assertEquals(creatureList1.size(), 0);
		assertEquals(creatureList2.size(), 2);
		
		creatureList1.clear();
		creatureList2.clear();
		
		creatureList1.add(elk1);
		creatureList2.add(elk2);
		
		p1.chooseBlockers(creatureList1, creatureList2);
		
		assertEquals(creatureList2.size(), 0);
		
		creatureList1.clear();
		creatureList2.clear();
		
		creatureList1.add(elk1);
		creatureList2.add(elk2);
		creatureList2.add(elk2);
		
		p1.chooseBlockers(creatureList1, creatureList2);
		
		assertEquals(creatureList2.size(), 1);
		assertEquals(creatureList2.get(0).getName(), "Dawntreader Elk");
		
		creatureList1.clear();
		creatureList2.clear();
		
		creatureList1.add(elk1);
		creatureList1.add(elk1);
		creatureList2.add(elk2);
		creatureList2.add(elk3);
		
		p1.chooseBlockers(creatureList1, creatureList2);
		
		assertEquals(0, creatureList2.size());
		
		creatureList1.add(elk1);
		creatureList1.add(elk1);
		creatureList2.add(elk3);
		creatureList2.add(elk2);
		creatureList2.add(elk3);
		
		
		p1.chooseBlockers(creatureList1, creatureList2);
		
		assertEquals(1, creatureList2.size());
		assertEquals("Dawntreader Elk", creatureList2.get(0).getName());
	}
	
//	public void testMutate(){
//		DebicccdGA g = new DebicccdGA("");
//		
//		String curds = g.mutateCard("Supreme Verdict", "color");
//		
//		System.out.print(curds);
//	}
//	
//	public void testCrossover(){
//		DebicccdGA g = new DebicccdGA("");
//		
//		String[] d1 = {"1","2","3","4"};
//		String[] d2 = {"5","6","7","8","9","0"};
//		
//		ArrayList<String[]> cross = g.crossover(d1, d2, "simple");
//		
//		for(String[] d : cross){
//			for(String s : d)
//				System.out.print(s);
//			System.out.println();
//		}
//	}
//	
//	public void testDeckGen(){
//		DebicccdGA g = new DebicccdGA("");
//		
//		String[] deck = g.generateDeck("random");
//		
//		for(String s : deck)
//			System.out.println(s);
//	}
//	
	public void testNextPopulation(){
		DebicccdGA g = new DebicccdGA("");
		
		g.fitness = new Double[15];
		g.population = new String[15][];
		
		for(int i = 0; i < 15; i++){
			String[] temp = {String.valueOf(i),String.valueOf(i)};
			g.fitness[i] = 1.0;
			g.population[i] = temp;
		}
		
		g.generateNextPopulation("roulette");
		
		for(String[] s : g.population)
			System.out.print(s[0] + ":");
	}
	

}
