Card::Card (string nameIn){
    name = nameIn;
    if(name == "Lightning Bolt")
    {
        castingCost = 1;
        CMC = 1;
        type = burn;
        damage = 3;
    }

    else if(name == "Thunderbolt")
    {
        castingCost = 2;
        CMC = 2;
        type = burn;
        damage = 3;
    }

    else if(name == "Skullcrack")
    {
        castingCost = 2;
        CMC = 2;
        type = burn;
        damage = 3;
    }

    else if(name == "Brimstone Volley")
    {
        castingCost = 3;
        CMC = 3;
        type = burn;
        damage = 3;
    }
	

    else if(name == "Thunderous Wrath")
    {
        castingCost = 6;
        CMC = 6;
        type = burn;
        damage = 5;
        //TODO miracle
    }
	
    else if(name == "Pillar of Flame")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 2;
    }

    else if(name == "Flames of the Firebrand")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
	
    else if(name == "Searing Spear")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 3;
    }
	
    else if(name == "Chandra's Fury")
    {
	castingCost = 5;
	CMC = 5;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Geistflame")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 1;
	//TODO flashback
    }
	
    else if(name == "Annihilating Fire")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
	
    else if(name == "Explosive Impact")
    {
	castingCost = 6;
	CMC = 6;
	type = burn;
	damage = 5;
    }
    
    else if(name == "Fires of Undeath")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 2;
	//TODO flashback (it's black)
    }
    
    else if(name == "Structural Collapse")
    {
	castingCost = 6;
	CMC = 6;
	type = burn;
	damage = 2;
    }

    else if(name == "Mountain"){
	castingCost = 0;
	CMC = 0;
	type = land;
	damage = 0;
    }
    
    else if(name == "Fireblast")
    {
	castingCost = 6;
	CMC = 6;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Chain Lightning")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Magma Jet")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Lava Spike")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Galvanic Blast")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Incinerate")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Rift Bolt")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
   
    else if(name == "Sudden Shock")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 2;
    }
   
    else if(name == "Pulse of the Forge")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 4;
    }
   
    else if(name == "Flames of the Blood Hand")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Violent Eruption")
    {
	castingCost = 4;
	CMC = 4;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Burst Lightning")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Flame Wave")
    {
	castingCost = 7;
	CMC = 7;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Flame Javelin")
    {
	castingCost = 3;
	CMC = 6;
	type = burn;
	damage = 4;
    }
    
    else if(name == "Firey Temper")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Forked Bolt")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Firebolt")
    {
	castingCost = 1;
	CMC = 1;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Glacial Ray")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Unstable Footing")
    {
	castingCost = 5;
	CMC = 1;
	type = burn;
	damage = 5;
    }
    
    else if(name == "Punishing Fire")
    {
	castingCost = 2;
	CMC = 2;
	type = burn;
	damage = 2;
    }
    
    else if(name == "Urza's Rage")
    {
	castingCost = 12;
	CMC = 3;
	type = burn;
	damage = 10;
    }
    
    else if(name == "Volt Charge")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Hammer of Bogardan")
    {
	castingCost = 3;
	CMC = 3;
	type = burn;
	damage = 3;
    }
    
    else if(name == "Searing Wind")
    {
	castingCost = 9;
	CMC = 9;
	type = burn;
	damage = 10;
    }
};





