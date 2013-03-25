package magicGame;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {

	private int numPlayers = 0;
	private ArrayList<Player> players;
	private ArrayList<Card> permanents;
	private Player activePlayer;
	private int turnNumber;
	private LinkedList<Card> stack;

	public GameState(int numberPlayers) {
		this.numPlayers = numberPlayers;
		this.players = new ArrayList<Player>();
		this.permanents = new ArrayList<Card>();
		this.turnNumber = 0;
	}

	public int getNumPlayers() {
		return this.numPlayers;
	}

	public void makePlayers(ArrayList<String[]> decks) {
		int playerIndex = 0;
		Player nextPlayer;
		for (String[] deck : decks) {
			nextPlayer = new Player(deck, this);
			nextPlayer.setPlayerNumber(playerIndex);
			players.add(nextPlayer);
			playerIndex++;
		}
	}

	public void initializeGame() {
		for (Player player : this.players) {
			player.shuffle();
			player.draw(7);
		}

	}

	/**
	 * Here is where a lot of the magic takes place. In this method, the players
	 * actually play a game of Magic. It returns the finishing turn in Integer
	 * form, and the winning player, stored in an ArrayList<Object>.
	 * 
	 * @return winning player
	 */
	public ArrayList<Object> playGame() {
		int activePlayerIndex = 0;
		while (this.players.size() > 1) {
			this.turnNumber++;

			this.activePlayer = this.players.get(activePlayerIndex);
			this.activePlayer.setPlayedLand(false);
			for (Card perm : this.permanents) {
				if (perm.getController().equals(this.activePlayer)) {
					perm.setSummoningSickness(false);
				}
			}
			this.untap();
			this.upkeep();
			this.draw();
			this.mainPhase(1);
			this.combat();
			this.mainPhase(2);
			// TODO: implement cleanup on end step.
			this.endStep();
			if (activePlayerIndex < this.players.size() - 1) {
				activePlayerIndex++;
			} else {
				activePlayerIndex = 0;
			}
		}

		Integer turnFinished = new Integer(this.turnNumber);
		ArrayList<Object> results = new ArrayList<Object>();
		results.add(turnFinished);
		if (this.players.size() > 0) {
			results.add(this.players.get(0));
		} else {
			results.add(null);
		}
		return results;
	}

	private void draw() {
		this.activePlayer.draw(1);

	}

	// For the moment, I'll just check if players have lost in the end step.
	// I'll fix this when I implement state-based actions.
	private void endStep() {
		ArrayList<Player> losingPlayers = new ArrayList<Player>();
		for (Player p : this.players) {
			if (p.checkHasLost()) {
				// System.out.println("Player " + p.getPlayerNumber()
				// + " has lost the game.");
				losingPlayers.add(p);
			}
		}
		for (Player p : losingPlayers) {
			this.players.remove(p);
		}

	}

	private void combat() {
		ArrayList<Card> activePlayerCreatures = new ArrayList<Card>();
		for (Card perm : this.permanents) {
			if (perm.getTypes().contains("Creature")
					&& perm.getController().equals(this.activePlayer)) {
				activePlayerCreatures.add(perm);
			}
		}
		ArrayList<Card> attackingCreatures = this.activePlayer
				.chooseAttackers(activePlayerCreatures);

		// For now, I'm only doing 2-player games, so creatures don't know which
		// player they're attacking. This will be something that creatures know
		// once multiplayer is implemented.
		for (Player player : this.players) {
			ArrayList<Card> defendingPlayerCreatures = new ArrayList<Card>();
			if (!(player.equals(this.activePlayer))) {
				for (Card perm : this.permanents) {
					if (perm.getTypes().contains("Creature")
							&& perm.getController().equals(player)) {
						defendingPlayerCreatures.add(perm);
					}
				}

				player.chooseBlockers(defendingPlayerCreatures,
						activePlayerCreatures);
			}
		}
		Card blocker = null;
		for (Card creature : attackingCreatures) {
			if (creature.isBlocked()) {
				// Figure out blocker and have the creatures exchange damage
				blocker = creature.getBlockedBy();
				blocker.dealDamage(creature.getPower());
				creature.dealDamage(blocker.getPower());

				// Destroy creatures with damage equal to their toughness makred
				// on them. In the final version, this should be handled in
				// state-based actions. Also, I currently have the owner
				// destroying the creatures. This is fine and good, but bear in
				// mind that the destroy function will have to trigger on-death
				// effects for the controller, not itself.
				if (creature.getDamageMarked() >= creature.getToughness()) {
					creature.getOwner().destroy(creature);
					this.permanents.remove(creature);
				}
				if (blocker.getDamageMarked() >= blocker.getToughness()) {
					blocker.getOwner().destroy(blocker);
					this.permanents.remove(blocker);
				}

			}
			// If the creature isn't blocked, have the defending player lose
			// life equal to its power. "Damage to player" effects will trigger
			// here, as will any replacement effects for damage. Once these are
			// implemented, I'll check for them.
			else {

				creature.getDefendingPlayer().changeLife(
						-1 * creature.getPower());
			}
		}

		for (Player p : this.players) {
			if (p.getLife() < 1) {
				p.setHasLost(true);
			}
		}
	}

	private void mainPhase(int phase) {
		// As of right now we don't actually care which main phase it is.
		// However, we will once things are in the game that require stuff like
		// opponent taking damage, so it'll go ahead and follow normal patterns.
		this.activePlayer.playMainPhase();

	}

	private void upkeep() {
		// There are no upkeep triggers in the game at this point, so nothing
		// happens here yet.

	}

	private void untap() {
		for (Card permanent : this.permanents) {
			if (permanent.getOwner().equals(activePlayer)) {
				permanent.untap();
			}
		}
	}

	public void addPermanent(Card newPermanent) {
		this.permanents.add(newPermanent);

	}

	public String openMana(Player player) {
		// Currently only considers basic lands.
		String mana = "";
		for (Card perm : this.permanents) {
			if (perm.getTypes().contains("Land")
					&& perm.getController().equals(player) && !perm.isTapped()) {
				String cardName = perm.getName();
				switch (cardName) {
				case "Forest":
					mana += "G";
					break;
				case "Mountain":
					mana += "R";
					break;
				case "Plains":
					mana += "W";
					break;
				case "Swamp":
					mana += "B";
					break;
				case "Island":
					mana += "U";
					break;
				default:
					break;
				}
			}
		}

		return mana;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public Player getActivePlayer() {
		return this.activePlayer;
	}

	public Card getNextUntappedPerm(String permName, Player controller) {
		for (Card perm : this.permanents) {
			if (perm.getName().equals(permName)
					&& perm.getController().equals(controller)
					&& !perm.isTapped()) {
				return perm;
			}
		}

		return null;
	}

	public void addToStack(Card c) {
		this.stack.add(c);
	}

	public void resolveStack() {
		// TODO: Make this Charlie Foxtrot work.

	}

	public LinkedList<Card> getStack() {
		return this.stack;
	}

}
