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
			"Elderscale Wurm", "Festerhide Boar", "Flinthoof Boar",
			"Flowering Lumberknot", "Garruk's Packleader", "Gatecreeper Vine",
			"Gatstaf Shepherd", "Geist Trappers", "Ghoultree",
			"Giant Adephage", "Gloomwidow", "Gobbling Ooze", "Golgari Decoy",
			"Grave Bramble", "Gravetiller Wurm", "Grizzled Outcasts",
			"Gyre Sage", "Hamlet Captain", "Hollowhenge Beast",
			"Hollowhenge Scavenger", "Howlgeist", "Ivy Lane Denizen",
			"Kessig Cagebreakers", "Kessig Recluse", "Korozda Monitor",
			"Kindercatch", "Lambholt Elder", "Lumberknot", "Mayor of Avabruck",
			"Moldgraf Monstrosity", "Mwonvuli Beast Tracker", "Nettle Swine",
			"Nightshade Peddler", "Oak Street Innkeeper", "Orchard Spirit",
			"Pathbreaker Wurm", "Predator Ooze", "Primal Huntbeast",
			"Quirion Dryad", "Roaring Primadox", "Rubbleback Rhino",
			"Rust Scarab", "Scorned Villager", "Scab-Clan Charger",
			"Sentinel Spider", "Silklash Spider", "Skarrg Goliath",
			"Abattoir Ghoul", "Afflicted Deserter", "Abbey Griffin",
			"Ajani's Sunstriker", "Alchemist's Apprentice", "Alms Beast",
			"Angel of Flight Alabaster", "Angel of Glory's Rise",
			"Angel of Jubilation", "Angel of Serenity", "Angelic Overseer",
			"Angelic Skirmisher", "Angelic Wall", "Aquus Steed",
			"Archaeomancer", "Archangel", "Archon of the Triumvirate",
			"Archwing Dragon", "Arctic Aven", "Armada Wurm", "Armored Skaab",
			"Armored Transport", "Armory Guard", "Arms Dealer", "Ash Zealot",
			"Ashmouth Hound", "Assault Griffin", "Attended Knight",
			"Auger of Bolas", "Aurelia, the Warleader",
			"Avacyn, Angel of Hope", "Avacynian Priest", "Aven Squire",
			"Azorius Arrester", "Azorius Justicar", "Balefire Dragon",
			"Balustrade Spy", "Bane Alley Broker", "Basilica Guards",
			"Basilica Screecher", "Batterhorn", "Battleflight Eagle",
			"Battleground Geist", "Bazaar Krovod", "Beguiler of Wills",
			"Bellows Lizard", "Biovisionary", "Bitterheart Witch", "Black Cat",
			"Sanctuary Cat", "Urban Evolution", "Think Twice", "Murder",
			"Supreme Verdict" };

	// TODO: put vexing devil back in once he works right.

	// TODO: put in full list of types, since String has a .contains(String)
	// function.
	public static Card getCard(String cardName, Player owner) {

		switch (cardName) {
		case "Forest":
			return new Card("Forest", "", "Basic Land - Forest", owner);
		case "Mountain":
			return new Card("Mountain", "", "Basic Land - Mountain", owner);
		case "Island":
			return new Card("Island", "", "Basic Land - Island", owner);
		case "Plains":
			return new Card("Plains", "", "Basic Land - Plains", owner);
		case "Swamp":
			return new Card("Swamp", "", "Basic Land - Swamp", owner);
		case "Axebane Stag":
			return new Card("Axebane Stag", "111111G", "Creature - Elk", owner,
					6, 7);
		case "Acidic Slime":
			return new Card("Acidic Slime", "111GG", "Creature - Ooze", owner,
					2, 2);
		case "Adaptive Snapjaw":
			return new Card("Adaptive Snapjaw", "1111G",
					"Creature - Lizard Beast", owner, 6, 2);
		case "Ambush Viper":
			return new Card("Ambush Viper", "1G", "Creature - Snake", owner, 2,
					1);
		case "Arbor Elf":
			return new Card("Arbor Elf", "G", "Creature - Elf Druid", owner, 1,
					1);
		case "Archweaver":
			return new Card("Archweaver", "11111GG", "Creature - Spider",
					owner, 5, 5);
		case "Avacyn's Pilgrim":
			return new Card("Avacyn's Pilgrim", "G", "Creature - Human Monk",
					owner, 1, 1);
		case "Axebane Guardian":
			return new Card("Axebane Guardian", "11G",
					"Creature - Human Druid", owner, 0, 3);
		case "Bond Beetle":
			return new Card("Bond Beetle", "G", "Creature - Insect", owner, 0,
					1);
			// TODO: once creatures die for having zero toughness, implement
			// Boneyard Wurm as a 0/0, and then when creatures have abilities,
			// implement his ability.
		case "Borderland Ranger":
			return new Card("Borderland Ranger", "11G",
					"Creature - Human Scout", owner, 2, 2);
		case "Briarpack Alpha":
			return new Card("Briarpack Alpha", "111G", "Creature - Wolf",
					owner, 3, 3);
		case "Brushstrider":
			return new Card("Brushstrider", "1G", "Creature - Beast", owner, 3,
					1);
		case "Centaur Courser":
			return new Card("Centaur Courser", "11G",
					"Creature - Centaur Warrior", owner, 3, 3);
		case "Centaur's Herald":
			return new Card("Centaur's Herald", "G", "Creature - Elf Scout",
					owner, 0, 1);
		case "Champion of Lambholt":
			return new Card("Champion of Lambholt", "1GG",
					"Creature - Human Warrior", owner, 1, 1);
		case "Craterhoof Behemoth":
			return new Card("Craterhoof Behemoth", "11111GGG",
					"Creature - Beast", owner, 5, 5);
		case "Crocanura":
			return new Card("Crocanura", "11G", "Creature - Crocodile Frog",
					owner, 1, 3);
		case "Crowned Ceratok":
			return new Card("Crowned Ceratok", "111G", "Creature - Rhino",
					owner, 4, 3);

		case "Darkthicket Wolf":
			return new Card("Darkthicket Wolf", "1G", "Creature - Wolf", owner,
					2, 2);
		case "Dawntreader Elk":
			return new Card("Dawntreader Elk", "1G", "Creature - Elk", owner,
					2, 2);
		case "Daybreak Ranger":
			return new Card("Daybreak Ranger", "11G",
					"Creature - Human Archer Werewolf", owner, 2, 2);
		case "Deadbridge Goliath":
			return new Card("Deadbridge Goliath", "11GG", "Creature - Insect",
					owner, 5, 5);
		case "Deadly Recluse":
			return new Card("Deadly Recluse", "1G", "Creature - Spider", owner,
					1, 2);
		case "Deranged Outcast":
			return new Card("Deranged Outcast", "1G", "Creature - Human Rogue",
					owner, 2, 1);
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
		case "Gatecreeper Vine":
			return new Card("Gatecreeper Vine", "1G", "Creature", owner, 0, 2);
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
		case "Vexing Devil":
			return new Card("Vexing Devil", "R", "Creature - Devil", owner, 4,
					3);
		case "Kessig Cagebreakers":
			return new Card("Kessig Cagebreakers", "1111G",
					"Creature - Human Rogue", owner, 3, 4);
		case "Kessig Recluse":
			return new Card("Kessig Recluse", "11GG", "Creature - Spider",
					owner, 2, 3, "Reach, Deathtouch");
		case "Kindercatch":
			return new Card("Kindercatch", "111GGG", "Creature - Spirit",
					owner, 6, 6);
		case "Korozda Monitor":
			return new Card("Korozda Monitor", "11GG", "Creature - Lizard",
					owner, 3, 3, "Trample");
		case "Lambholt Elder":
			return new Card("Lambholt Elder", "11G",
					"Creature - Human Werewolf", owner, 1, 1);
		case "Lumberknot":
			return new Card("Lumberknot", "11GG", "Creature - Treefolk", owner,
					1, 1, "Hexproof");
		case "Mayor of Avabruck":
			return new Card("Mayor of Avabruck", "1G",
					"Creature - Human Advisor Werewolf", owner, 1, 1);
		case "Moldgraf Monstrosity":
			return new Card("Moldgraf Monstrosity", "1111GGG",
					"Creature - Insect", owner, 8, 8, "Trample");
		case "Mwonvuli Beast Tracker":
			return new Card("Mwonvuli Beast Tracker", "1GG",
					"Creature - Human Scout", owner, 2, 1);
		case "Nettle Swine":
			return new Card("Nettle Swine", "111G", "Creature - Boar", owner,
					4, 3);
		case "Nightshade Peddler":
			return new Card("Nightshade Peddler", "1G",
					"Creature - Human Druid", owner, 1, 1);
		case "Oak Street Innkeeper":
			return new Card("Oak Street Innkeeper", "11G", "Creature - Elf",
					owner, 1, 2);
		case "Orchard Spirit":
			return new Card("Orchard Spirit", "11G", "Creature - Spirit",
					owner, 2, 2);
		case "Pathbreaker Wurm":
			return new Card("Pathbreaker Wurm", "1111GG", "Creature - Wurm",
					owner, 6, 4);
		case "Predator Ooze":
			return new Card("Predator Ooze", "GGG", "Creature - Ooze", owner,
					1, 1, "Indestructible");
		case "Primal Huntbeast":
			return new Card("Primal Huntbeast", "111G", "Creature - Beast",
					owner, 3, 3, "Hexproof");
			// TODO: implement Primordial Hydra. It's a non-trivial problem.
		case "Quirion Dryad":
			return new Card("Quirion Dryad", "1G", "Creature - Dryad", owner,
					1, 1);
		case "Roaring Primadox":
			return new Card("Roaring Primadox", "111G", "Creature - Beast",
					owner, 4, 4);
		case "Rubbleback Rhino":
			return new Card("Rubbleback Rhino", "1111G", "Creature - Rhino",
					owner, 3, 4, "Hexproof");
		case "Rust Scarab":
			return new Card("Rust Scarab", "1111G", "Creature - Insect", owner,
					4, 5);
		case "Scab-Clan Charger":
			return new Card("Scab-Clan Charger", "111G",
					"Creature - Centaur Warrior", owner, 2, 4);
		case "Scorned Villager":
			return new Card("Scorned Villager", "1G",
					"Creature - Human Werewolf", owner, 1, 1);
		case "Sentinel Spider":
			return new Card("Sentinel Spider", "111GG", "Creature - Spider",
					owner, 4, 4, "Vigilance, Reach");
		case "Silklash Spider":
			return new Card("Silklash Spider", "111GG", "Creature - Spider",
					owner, 2, 7, "Reach");
		case "Skarrg Goliath":
			return new Card("Skarrg Goliath", "111111GG", "Creature - Beast",
					owner, 9, 9, "Trample");
			// TODO: add the rest of the green creatures. I'm gonna move on to
			// other colors now.
		case "Abattoir Ghoul":
			return new Card("Abattoir Ghoul", "111B", "Creature - Zombie",
					owner, 3, 2, "First strike");
		case "Abbey Griffin":
			return new Card("Abbey Griffin", "111W", "Creature - Griffin",
					owner, 2, 2, "Flying, Vigilance");
		case "Afflicted Deserter":
			return new Card("Afflicted Deserter", "111R",
					"Creature - Human Werewolf", owner, 3, 2);
		case "Ajani's Sunstriker":
			return new Card("Ajani's Sunstriker", "WW",
					"Creature - Cat Cleric", owner, 2, 2, "Lifelink");
		case "Alchemist's Apprentice":
			return new Card("Alchemist's Apprentice", "1U",
					"Creature - Human Wizard", owner, 1, 1);
		case "Alms Beast":
			return new Card("Alms Beast", "11WB", "Creature - Beast", owner, 6,
					6);
		case "Angel of Flight Alabaster":
			return new Card("Angel of Flight Alabaster", "1111W",
					"Creature - Angel", owner, 4, 4, "Flying");
		case "Angel of Glory's Rise":
			return new Card("Angel of Glory's Rise", "11111WW",
					"Creature - Angel", owner, 4, 6, "Flying");
		case "Angel of Jubilation":
			return new Card("Angel of Jubilation", "1WWW", "Creature - Angel",
					owner, 3, 3, "Flying");
		case "Angel of Serenity":
			return new Card("Angel of Serenity", "1111WWW", "Creature - Angel",
					owner, 5, 6, "Flying");
		case "Angelic Overseer":
			return new Card("Angelic Overseer", "111WW", "Creature - Angel",
					owner, 5, 3, "Flying");
		case "Angelic Skirmisher":
			return new Card("Angelic Skirmisher", "1111WW", "Creature - Angel",
					owner, 4, 4, "Flying");
		case "Angelic Wall":
			return new Card("Angelic Wall", "1W", "Creature - Wall", owner, 0,
					4, "Defender, Flying");
		case "Aquus Steed":
			return new Card("Aquus Steed", "111U", "Creature - Beast", owner,
					1, 3);
		case "Archaeomancer":
			return new Card("Archaeomancer", "11UU", "Creature - Human Wizard",
					owner, 1, 2);
		case "Archangel":
			return new Card("Archangel", "11111WW", "Creature - Angel", owner,
					5, 5, "Flying, Vigilance");
		case "Archon of the Triumvirate":
			return new Card("Archon of the Triumvirate", "11111WU",
					"Creature - Archon", owner, 4, 5, "Flying");
		case "Archwing Dragon":
			return new Card("Archwing Dragon", "11RR", "Creature - Dragon",
					owner, 4, 4, "Flying, Haste");
		case "Arctic Aven":
			return new Card("Arctic Aven", "11U", "Creature - Bird Wizard",
					owner, 2, 1, "Flying");
		case "Armada Wurm":
			return new Card("Armada Wurm", "11GGWW", "Creature - Wurm", owner,
					5, 5, "Trample");
		case "Armored Skaab":
			return new Card("Armored Skaab", "11U",
					"Creature - Zombie Warrior", owner, 1, 4);
		case "Armored Transport":
			return new Card("Armored Transport", "111",
					"Artifact Creature - Construct", owner, 2, 1);
		case "Armory Guard":
			return new Card("Armory Guard", "111W", "Creature - Giant Soldier",
					owner, 2, 5);
		case "Arms Dealer":
			return new Card("Arms Dealer", "11R", "Creature - Goblin Rogue",
					owner, 1, 1);
		case "Ash Zealot":
			return new Card("Ash Zealot", "RR", "Creature - Human Warrior",
					owner, 2, 2, "First strike, Haste");
		case "Ashmouth Hound":
			return new Card("Ashmouth Hound", "1R",
					"Creature - Elemental Hound", owner, 2, 1);
		case "Assault Griffin":
			return new Card("Assault Griffin", "111W", "Creature - Griffin",
					owner, 3, 2, "Flying");
		case "Attended Knight":
			return new Card("Attended Knight", "11W",
					"Creature - Human Knight", owner, 2, 2, "First strike");
		case "Auger of Bolas":
			return new Card("Auger of Bolas", "1U",
					"Creature - Merfolk Wizard", owner, 1, 3);
		case "Aurelia, the Warleader":
			return new Card("Aurelia, the Warleader", "11RRWW",
					"Legendary Creature - Angel", owner, 3, 4,
					"Flying, Vigilance, Haste");
		case "Avacyn, Angel of Hope":
			return new Card("Avacyn, Angel of Hope", "11111WWW",
					"Legendary Creature - Angel", owner, 8, 8,
					"Flying, Vigilance, Indestructible");
		case "Avacynian Priest":
			return new Card("Avacynian Priest", "1W",
					"Creature - Human Cleric", owner, 1, 2);
		case "Aven Squire":
			return new Card("Aven Squire", "1W", "Creature - Bird Soldier",
					owner, 1, 1, "Flying");
		case "Azorius Arrester":
			return new Card("Azorius Arrester", "1W",
					"Creature - Human Soldier", owner, 2, 1);
		case "Azorius Justicar":
			return new Card("Azorius Justicar", "11WW",
					"Creature - Human Wizard", owner, 2, 2);
			// TODO: implement Azor's Elocutors. This requires hybrid mana
			// costs.
		case "Balefire Dragon":
			return new Card("Balefire Dragon", "11111RR", "Creature - Dragon",
					owner, 6, 6, "Flying");
		case "Balustrade Spy":
			return new Card("Balustrade Spy", "111B",
					"Creature - Vampire Rogue", owner, 2, 3, "Flying");
		case "Bane Alley Broker":
			return new Card("Bane Alley Broker", "1UB",
					"Creature - Human Rogue", owner, 0, 3);
		case "Basilica Guards":
			return new Card("Basilica Guards", "11W",
					"Creature - Human Soldier", owner, 1, 4, "Defender");
		case "Basilica Screecher":
			return new Card("Basilica Screecher", "1B", "Creature - Bat",
					owner, 1, 2, "Flying");
		case "Batterhorn":
			return new Card("Batterhorn", "1111R", "Creature - Beast", owner,
					4, 3);
		case "Battleflight Eagle":
			return new Card("Battleflight Eagle", "1111W", "Creature - Bird",
					owner, 2, 2, "Flying");
		case "Battleground Geist":
			return new Card("Battleground Geist", "1111U", "Creature - Spirit",
					owner, 3, 3, "Flying");
		case "Bazaar Krovod":
			return new Card("Bazaar Krovod", "1111W", "Creature - Beast",
					owner, 2, 5);
		case "Beguiler of Wills":
			return new Card("Beguiler of Wills", "111UU",
					"Creature - Human Wizard", owner, 1, 1);
		case "Bellows Lizard":
			return new Card("Bellows Lizard", "R", "Creature - Lizard", owner,
					1, 1);
		case "Biovisionary":
			return new Card("Biovisionary", "1GU", "Creature - Human Wizard",
					owner, 2, 3);
		case "Bitterheart Witch":
			return new Card("Bitterheart Witch", "1111B",
					"Creature - Human Shaman", owner, 1, 2, "Deathtouch");
		case "Black Cat":
			return new Card("Black Cat", "1B", "Creature - Zombie Cat", owner,
					1, 1);

		case "Urban Evolution":
			return new Card("Urban Evolution", "111UG", "Sorcery", owner,
					"Draw 3, Extra Land");
		case "Sanctuary Cat":
			return new Card("Sanctuary Cat", "W", "Creature - Cat", owner, 1, 2);

			// TODO: implement flashback so that Think Twice isn't just a 2-cost
			// draw.
		case "Think Twice":
			return new Card("Think Twice", "1U", "Instant", owner, "Draw 1");
		case "Murder":
			return new Card("Murder", "1BB", "Instant", owner,
					"Destroy Target Creature");
		case "Supreme Verdict":
			return new Card("Supreme Verdict", "1WWU", "Sorcery", owner,
					"Destroy All Creatures");

		default:
			return null;

		}
	}

	public static String[] getCards() {
		return cards;
	}

}
