import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("unused")
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Card> cardsAvalible = addCards();
		ArrayList<Card> randDeck;

		double trials = 500;
		
		double avgTurns;
		double bestAvgTurns = 20;

		
		while(true){
			Deck d1 = new Deck();
			d1.randomDeck((ArrayList<Card>) cardsAvalible.clone());
			
			randDeck = d1.getCardList();
			
			avgTurns = simulateDeck(randDeck, trials);
			
			if(avgTurns < bestAvgTurns){
				System.out.println("\nNew Best Deck Found! Average Win " + avgTurns + " Turns!");
				bestAvgTurns = avgTurns;
				
				Collections.sort(randDeck);
				
				for(Card c : randDeck)
					System.out.print(c.toString() + ", ");
			}
		}
	}

	private static double simulateDeck(ArrayList<Card> randDeck, double trials) {
		Deck d1 = new Deck();
		int turns = 1;
		double turnsGlobal = 0;
		
		for (int i = 0; i < trials; i++) {
			d1.setCardList((ArrayList<Card>) randDeck.clone());
			
			Player p = new Player(d1);
			
			Game g = new Game(p);
			
			g.init();
			
			turns = 1;
			
			while (p.getLife() > 0) {
				g.untap();
				g.upkeep();
				g.draw();
				g.mainPhase();
				g.combat();
				turns++;
			}
			turnsGlobal += turns;
		}
		
		return turnsGlobal/trials;
	}

	private static ArrayList<Card> addCards() {
		Card exIm = new Card("Explosive Impact", "5R", 6,
				new ArrayList<Card.COLOR>(), "5 damage",
				"Explosive Impact deals 5 damage to target creature or player",
				"Sorcery");
		Card divination = new Card("Divination", "2U", 3,
				new ArrayList<Card.COLOR>(), "2 draw", "Draw two cards",
				"Sorcery");
		Card inspiration = new Card("Inspiration", "3U", 4,
				new ArrayList<Card.COLOR>(), "2 draw",
				"Target player draws two cards ", "Instant");
		Card signBlood = new Card("Sign in Blood", "BB", 2,
				new ArrayList<Card.COLOR>(), "2 draw",
				"Target player draws two cards and loses 2 life ", "Sorcery");
		Card scour = new Card(
				"Though Scour",
				"U",
				1,
				new ArrayList<Card.COLOR>(),
				"1 draw",
				"Target player puts the top two cards of his or her library into his or her graveyard.  Draw a card.",
				"Instant");
		Card urbEv = new Card(
				"Urban Evolution",
				"3GU",
				5,
				new ArrayList<Card.COLOR>(),
				"3 draw",
				"Draw three cards. You may play an additional land this turn. ",
				"Sorcery");
		Card elvVis = new Card("Elvish Visionary", "1G", 2,
				new ArrayList<Card.COLOR>(), "1 draw, 1 power, 1 toughness",
				"When Elvish Visionary enters the battlefield, draw a card. ",
				"Creature");
		Card anFir = new Card(
				"Annihilating Fire",
				"1RR",
				3,
				new ArrayList<Card.COLOR>(),
				"3 damage",
				"Annihilating Fire deals 3 damage to target creature or player. If a creature dealt damage this way would die this turn, exile it instead. ",
				"Instant");
		Card borCrm = new Card(
				"Boros Charm",
				"RW",
				2,
				new ArrayList<Card.COLOR>(),
				"4 damage",
				"Choose one - Boros Charm deals 4 damage to target player; or permanents you control are indestructible this turn; or target creature gains double strike until end of turn. ",
				"Instant");
		Card brmVol = new Card(
				"Brimstone Volley",
				"2R",
				3,
				new ArrayList<Card.COLOR>(),
				"3 damage",
				"Brimstone Volley deals 3 damage to target creature or player. Morbid - Brimstone Volley deals 5 damage to that creature or player instead if a creature died this turn.",
				"Instant");
		Card pilFlm = new Card(
				"Pillar of Flame",
				"RW",
				1,
				new ArrayList<Card.COLOR>(),
				"2 damage",
				"Pillar of Flame deals 2 damage to target creature or player. If a creature dealt damage this way would die this turn, exile it instead. ",
				"Instant");
		Card searSpr = new Card("Searing Spear", "1R", 2,
				new ArrayList<Card.COLOR>(), "3 damage",
				"Searing Spear deals 3 damage to target creature or player. ",
				"Instant");
		Card skulCrk = new Card(
				"Skull Crack",
				"1R",
				2,
				new ArrayList<Card.COLOR>(),
				"3 damage",
				"Players can't gain life this turn. Damage can't be prevented this turn. Skullcrack deals 3 damage to target player. ",
				"Instant");
		Card vexDev = new Card(
				"Vexing Devil",
				"R",
				1,
				new ArrayList<Card.COLOR>(),
				"4 damage",
				"When Vexing Devil enters the battlefield, any opponent may have it deal 4 damage to him or her. If a player does, sacrifice Vexing Devil. ",
				"Sorcery");
		Card ashZel = new Card("Ash Zealot", "RR", 2,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness, haste", "",
				"Creature");
		Card bondBet = new Card("Bond Beetle", "G", 1,
				new ArrayList<Card.COLOR>(), "1 power, 2 toughness", "",
				"Creature");
		Card BorEl = new Card("Boros Elite", "W", 1,
				new ArrayList<Card.COLOR>(), "1 power, 1 toughness", "",
				"Creature");
		Card brushStr = new Card("Brushstrider", "1G", 2,
				new ArrayList<Card.COLOR>(), "3 power, 1 toughness", "",
				"Creature");
		Card centCour = new Card("Centaur Courser", "2G", 3,
				new ArrayList<Card.COLOR>(), "3 power, 3 toughness", "",
				"Creature");
		Card champPar = new Card("Champion of the Parish", "W", 1,
				new ArrayList<Card.COLOR>(), "1 power, 1 toughness", "",
				"Creature");
		Card cobBru = new Card("Cobblebrute", "3R", 4,
				new ArrayList<Card.COLOR>(), "5 power, 2 toughness", "",
				"Creature");
		Card crosVmp = new Card("Crossway Vampire", "1RR", 3,
				new ArrayList<Card.COLOR>(), "3 power, 2 toughness", "",
				"Creature");
		Card crypCrp = new Card("Crypt Creeper", "1B", 2,
				new ArrayList<Card.COLOR>(), "2 power, 1 toughness", "",
				"Creature");
		Card darSjk = new Card("Daring Skyjek", "1W", 2,
				new ArrayList<Card.COLOR>(), "3 power, 1 toughness", "",
				"Creature");
		Card darkWlf = new Card("Darkthicket Wolf", "1G", 2,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness", "",
				"Creature");
		Card dwnElk = new Card("Dawntreader Elk", "1G", 2,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness", "",
				"Creature");
		Card dedGol = new Card("Deadbridge Goliath", "2GG", 4,
				new ArrayList<Card.COLOR>(), "5 power, 5 toughness", "",
				"Creature");
		Card direGhl = new Card("Diregraf Ghoul", "B", 1,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness", "",
				"Creature");
		Card doomTrv = new Card("Doomed Traveler", "W", 1,
				new ArrayList<Card.COLOR>(), "1 power, 1 toughness", "",
				"Creature");
		Card drkKrs = new Card("Drakewing Krasis", "1GU", 3,
				new ArrayList<Card.COLOR>(), "3 power, 1 toughness", "",
				"Creature");
		Card drgMng = new Card("Dreg Mangler", "1GB", 3,
				new ArrayList<Card.COLOR>(), "3 power, 3 toughness, haste", "",
				"Creature");
		Card drydMil = new Card("Dryad Militant", "G", 1,
				new ArrayList<Card.COLOR>(), "2 power, 1 toughness", "",
				"Creature");
		Card flkArst = new Card("Falkenrath Aristocrat", "2BR", 4,
				new ArrayList<Card.COLOR>(), "4 power, 1 toughness, haste", "",
				"Creature");
		Card geistSnt = new Card("Geist of Saint Traft", "1UW", 3,
				new ArrayList<Card.COLOR>(), "6 power, 6 toughness", "",
				"Creature");
		Card gerfMes = new Card("Geralf's Messenger", "BBB", 3,
				new ArrayList<Card.COLOR>(), "2 damage, 3 power, 2 toughness",
				"", "Creature");
		Card grvCrwl = new Card("Gravecrawler", "B", 1,
				new ArrayList<Card.COLOR>(), "2 power, 1 toughness", "",
				"Creature");
		Card kesMal = new Card("Kessig Malcontents", "2R", 3,
				new ArrayList<Card.COLOR>(), "1 damage, 3 power, 1 toughness",
				"", "Creature");
		Card loxSmt = new Card("Loxodon Smiter", "1GW", 3,
				new ArrayList<Card.COLOR>(), "4 power, 4 toughness", "",
				"Creature");
		Card rkdsCk = new Card("Rakdos Cackler", "R", 1,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness", "",
				"Creature");
		Card scrchwlk = new Card("Scorchwalker", "3R", 4,
				new ArrayList<Card.COLOR>(), "5 power, 1 toughness", "",
				"Creature");
		Card skyLeg = new Card("Skyknight Legionnaire", "1RW", 3,
				new ArrayList<Card.COLOR>(), "2 power, 2 toughness, haste", "",
				"Creature");
		Card strGst = new Card("Strangleroot Geist", "GG", 2,
				new ArrayList<Card.COLOR>(), "2 power, 1 toughness, haste", "",
				"Creature");
		Card volRig = new Card("Volatile Rig", "4", 4,
				new ArrayList<Card.COLOR>(), "4 power, 4 toughness", "",
				"Creature");
		Card woHal = new Card("Wojek Halberdiers", "WR", 2,
				new ArrayList<Card.COLOR>(), "3 power, 2 toughness", "",
				"Creature");
		
		ArrayList<Card> cardsAvalible = new ArrayList<Card>();
		
		cardsAvalible.add(exIm);
		cardsAvalible.add(divination);
		cardsAvalible.add(inspiration);
		cardsAvalible.add(signBlood);
		cardsAvalible.add(scour);
		cardsAvalible.add(urbEv);
		cardsAvalible.add(elvVis);
		cardsAvalible.add(anFir);
		cardsAvalible.add(borCrm);
		cardsAvalible.add(brmVol);
		cardsAvalible.add(pilFlm);
		cardsAvalible.add(searSpr);
		cardsAvalible.add(skulCrk);
		cardsAvalible.add(vexDev);
		cardsAvalible.add(ashZel);
		cardsAvalible.add(bondBet);
		cardsAvalible.add(BorEl);
		cardsAvalible.add(brushStr);
		cardsAvalible.add(centCour);
		cardsAvalible.add(champPar);
		cardsAvalible.add(cobBru);
		cardsAvalible.add(crosVmp);
		cardsAvalible.add(crypCrp);
		cardsAvalible.add(darSjk);
		cardsAvalible.add(darkWlf);
		cardsAvalible.add(dwnElk);
		cardsAvalible.add(dedGol);
		cardsAvalible.add(direGhl);
		cardsAvalible.add(doomTrv);
		cardsAvalible.add(drkKrs);
		cardsAvalible.add(drgMng);
		cardsAvalible.add(drydMil);
		cardsAvalible.add(flkArst);
		cardsAvalible.add(geistSnt);
		cardsAvalible.add(gerfMes);
		cardsAvalible.add(grvCrwl);
		cardsAvalible.add(kesMal);
		cardsAvalible.add(loxSmt);
		cardsAvalible.add(rkdsCk);
		cardsAvalible.add(scrchwlk);
		cardsAvalible.add(skyLeg);
		cardsAvalible.add(strGst);
		cardsAvalible.add(volRig);

		return cardsAvalible;
	}
}
