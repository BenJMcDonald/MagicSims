package magicGame;

public class CardDB {

	private static String[] cards = { "Mountain", "Island", "Plains", "Swamp",
			"Forest", "Axebane Stag", "Acidic Slime", "Adaptive Snapjaw",
			"Ambush Viper", "Arbor Elf", "Archweaver", "Avacyn's Pilgrim",
			"Axebane Guardian", "Bond Beetle", "Borderland Ranger",
			"Briarpack Alpha", "Brushstrider", "Centaur Courser",
			"Centaur's Herald", "Champion of Lambholt", "Craterhoof Behemoth",
			"Crocanura", "Crowned Ceratok", "Darkthicket Wolf",
			"Dawntreader Elk", "Daybreak Ranger", "Deadbridge Goliath",
			"Deadly Recluse", "Deranged Outcast", "Diregraf Escort",
			"Disciple of the Old Ways", "Drudge Beetle", "Druid's Familiar",
			"Duskdale Wurm", "Elder of Laurels", "Elvish Archdruid",
			"Elvish Visionary", "Essence of the Wild", "Experiment One",
			"Elderscale Wurm" };

	// TODO: put in full list of types, since String has a .contains(String)
	// function.
	public static Card getCard(String cardName, Player owner) {

		switch (cardName) {
		case "Forest":
			return new Card("Forest", "", "Land", owner);
		case "Mountain":
			return new Card("Mountain", "", "Land", owner);
		case "Island":
			return new Card("Island", "", "Land", owner);
		case "Plains":
			return new Card("Plains", "", "Land", owner);
		case "Swamp":
			return new Card("Swamp", "", "Land", owner);
		case "Axebane Stag":
			return new Card("Axebane Stag", "111111G", "Creature", owner, 6, 7);
		case "Acidic Slime":
			return new Card("Acidic Slime", "111GG", "Creature", owner, 2, 2);
		case "Adaptive Snapjaw":
			return new Card("Adaptive Snapjaw", "1111G", "Creature", owner, 6,
					2);
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
			// TODO: once creatures die for having zero toughness, implement
			// Boneyard Wurm as a 0/0, and then when creatures have abilities,
			// implement his ability.
		case "Borderland Ranger":
			return new Card("Borderland Ranger", "11G", "Creature", owner, 2, 2);
		case "Briarpack Alpha":
			return new Card("Briarpack Alpha", "111G", "Creature", owner, 3, 3);
		case "Brushstrider":
			return new Card("Brushstrider", "1G", "Creature", owner, 3, 1);
		case "Centaur Courser":
			return new Card("Centaur Courser", "11G", "Creature", owner, 3, 3);
		case "Centaur's Herald":
			return new Card("Centaur's Herald", "G", "Creature", owner, 0, 1);
		case "Champion of Lambholt":
			return new Card("Champion of Lambholt", "1GG", "Creature", owner,
					1, 1);
		case "Craterhoof Behemoth":
			return new Card("Craterhoof Behemoth", "11111GGG", "Creature",
					owner, 5, 5);
		case "Crocanura":
			return new Card("Crocanura", "11G", "Creature", owner, 1, 3);
		case "Crowned Ceratok":
			return new Card("Crowned Ceratok", "111G", "Creature", owner, 4, 3);

		case "Darkthicket Wolf":
			return new Card("Darkthicket Wolf", "1G", "Creature", owner, 2, 2);
		case "Dawntreader Elk":
			return new Card("Dawntreader Elk", "1G", "Creature", owner, 2, 2);
		case "Daybreak Ranger":
			return new Card("Daybreak Ranger", "11G", "Creature", owner, 2, 2);
		case "Deadbridge Goliath":
			return new Card("Deadbridge Goliath", "11GG", "Creature", owner, 5,
					5);
		case "Deadly Recluse":
			return new Card("Deadly Recluse", "1G", "Creature", owner, 1, 2);
		case "Deranged Outcast":
			return new Card("Deranged Outcast", "1G", "Creature", owner, 2, 1);
		case "Diregraf Escort":
			return new Card("Diregraf Escort", "G", "Creature", owner, 1, 1);
		case "Disciple of the Old Ways":
			return new Card("Disciple of the Old Ways", "1G", "Creature",
					owner, 2, 2);
		case "Drudge Beetle":
			return new Card("Drudge Beetle", "1G", "Creature", owner, 2, 2);
		case "Druid's Familiar":
			return new Card("Druid's Familiar", "111G", "Creature", owner, 2, 2);
		case "Duskdale Wurm":
			return new Card("Duskdale Wurm", "11111GG", "Creature", owner, 7, 7);

		case "Elder of Laurels":
			return new Card("Elder of Laurels", "11G", "Creature", owner, 2, 3);
		case "Elderscale Wurm":
			return new Card("Elderscale Wurm", "1111GGG", "Creature", owner, 7,
					7);
		case "Elvish Archdruid":
			return new Card("Elvish Archdruid", "1GG", "Creature", owner, 2, 2);
		case "Elvish Visionary":
			return new Card("Elvish Visionary", "1G", "Creature", owner, 1, 1);
		case "Essence of the Wild":
			return new Card("Essence of the Wild", "111GGG", "Creature", owner,
					6, 6);
		case "Experiment One":
			return new Card("Experiment One", "G", "Creature", owner, 1, 1);
			// TODO: Put creatures below this point on the creature list. Then,
			// redo deck building to make reasonably sized decks.

		case "Festerhide Boar":
			return new Card("Festerhide Boar", "111G", "Creature", owner, 3, 3);
		case "Flinthoof Boar":
			return new Card("Flinthoof Boar", "1G", "Creature", owner, 2, 2);
		case "Flowering Lumberknot":
			return new Card("Flowering Lumberknot", "111G", "Creature", owner,
					5, 5);
		case "Garruk's Packleader":
			return new Card("Garruk's Packleader", "1111G", "Creature", owner,
					4, 4);
		case "Gatekeeper Vine":
			return new Card("Gatekeeper Vine", "1G", "Creature", owner, 0, 2);
		case "Gatstaf Shepherd":
			return new Card("Gatstaf Shepherd", "1G", "Creature", owner, 2, 2);
		case "Geist Trappers":
			return new Card("Geist Trappers", "1111G", "Creature", owner, 3, 5);
		case "Ghoultree":
			return new Card("Ghoultree", "1111111G", "Creature", owner, 10, 10);
		case "Giant Adephage":
			return new Card("Giant Adephage", "11111GG", "Creature", owner, 7,
					7);
		case "Gloomwidow":
			return new Card("Gloomwidow", "11G", "Creature", owner, 3, 3);
		case "Gobbling Ooze":
			return new Card("Gobbling Ooze", "1111G", "Creature", owner, 3, 3);
		case "Golgari Decoy":
			return new Card("Golgari Decoy", "111G", "Creature", owner, 2, 2);
		case "Grave Bramble":
			return new Card("Grave Bramble", "1GG", "Creature", owner, 3, 4);
		case "Gravetiller Wurm":
			return new Card("Gravetiller Wurm", "11111G", "Creature", owner, 4,
					4);
		case "Greenside Watcher":
			return new Card("Greenside Watcher", "1G", "Creature", owner, 2, 1);
		case "Grizzled Outcasts":
			return new Card("Grizzled Outcasts", "1111G", "Creature", owner, 4,
					4);
		case "Gyre Sage":
			return new Card("Gyre Sage", "1G", "Creature", owner, 1, 2);
		case "Hamlet Captain":
			return new Card("Hamlet Captain", "1G", "Creature", owner, 2, 2);
		case "Hollowhenge Beast":
			return new Card("Hollowhenge Beast", "111GG", "Creature", owner, 5,
					5);
		case "Hollowhenge Scavenger":
			return new Card("Hollowhenge Scavenger", "111GG", "Creature",
					owner, 4, 5);
		case "Howlgeist":
			return new Card("Howlgeist", "11111G", "Creature", owner, 4, 2);
		case "Ivy Lane Denizen":
			return new Card("Ivy Lane Denizen", "111G", "Creature", owner, 2, 3);

		default:
			return null;

		}
	}

	public static String[] getCards() {
		return cards;
	}

}
