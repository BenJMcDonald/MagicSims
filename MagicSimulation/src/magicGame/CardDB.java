package magicGame;

public class CardDB {

	private static String[] cards = { "Forest", "Axebane Stag", "Acidic Slime",
			"Adaptive Snapjaw", "Ambush Viper", "Arbor Elf", "Archweaver",
			"Avacyn's Pilgrim", "Axebane Guardian", "Bond Beetle",
			"Borderland Ranger", "Briarpack Alpha", "Brushstrider", "Centaur Courser" };

	public static Card getCard(String cardName, Player owner) {
		switch (cardName) {
		case "Forest":
			return new Card("Forest", "", "Land", owner);
		case "Axebane Stag":
			return new Card("Axebane Stag", "111111G", "Creature", owner, 6, 7);
		case "Acidic Slime":
			return new Card("Acidic Slime", "111GG", "Creature", owner, 2, 2);
		case "Adaptive Snapjaw":
			return new Card("Adaptive Snapjaw", "1111G", "Creature", owner, 6, 2);
		case "Ambush Viper":
			return new Card("Ambush Viper", "1G", "Creature", owner, 2, 1);
		case "Arbor Elf":
			return new Card("Arbor Elf", "G", "Creature", owner, 1, 1);
		case "Archweaver":
			return new Card("Archweaver", "11111GG", "Creature", owner, 5, 5);
		case "Avacyn's Pilgrim":
			return new Card("Avacyn's Pilgrim", "G", "Creature", owner, 1, 1);
		case "Axebane Guardian":
			return new Card("Axebane Guardian", "11G", "Creature", owner, 0, 3);
		case "Bond Beetle":
			return new Card("Bond Beetle", "G", "Creature", owner, 0, 1);
		case "Borderland Ranger":
			return new Card("Borderland Ranger", "11G", "Creature", owner, 2, 2);
		case "Briarpack Alpha":
			return new Card("Briarpack Alpha", "111G", "Creature", owner, 3, 3);
		case "Brushstrider":
			return new Card("Brushstrider", "1G", "Creature", owner, 3, 1);
		case "Centaur Courser":
			return new Card("Centaur Courser", "11G", "Creature", owner, 3, 3);
		default:
			return null;

		}
	}

	public static String[] getCards() {
		return cards;
	}

}
