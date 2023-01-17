public class Combat {

	public void attack(String[] x) {
		for (int i = 0; i < Game_Objects.room.size(); i++) {
			if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
				for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {
					if (Game_Objects.room.get(i).npc.get(y).idfr.equalsIgnoreCase(x[1]) || x[1].equalsIgnoreCase("rodents") || x[1].equalsIgnoreCase("beasts")) {  
						if (x[1].equalsIgnoreCase("posi")) {
							System.out.println();
							System.out.println("Why would you want to attack Posi? :(");
						} 
						else if (x[1].equalsIgnoreCase("phildu")) {
							System.out.println();
							System.out.println("Phildu and Big Rich are humble restaurant operators and mean you no harm.");
						}
						else if (x[1].equalsIgnoreCase("old")) {
							System.out.println();
							System.out.println("The Old Man has too much wisdowm for you to attack him.");
						}
						else if (x[1].equalsIgnoreCase("beam")) {
							System.out.println();
							System.out.println("Beam would sizzle you, don't even try.");
						}
						else if (x[1].equalsIgnoreCase("lord")) {
							System.out.println();
							System.out.println("You could not possibly do damage to His Holiness.");
						}
						else if (x[1].equalsIgnoreCase("big") && !Game_Objects.bigFrankAE) {
							System.out.println();
							System.out.println("You must speak to Big Frank first.");
						}
						else if (Game_Objects.pc.wornItems.isEmpty()) {
							System.out.println();
							System.out.println("You have no weapon equipped to attack with");
						} else {
						double pc_hit = Game_Objects.rng.returnRandom(100)*Game_Objects.pc.accuracy;
					    if (pc_hit < 15) {
					    	System.out.println();
					    	System.out.println("You missed");
					    } else if(pc_hit > 85) {
					    	int pc_damage = Game_Objects.pc.wornItems.get(0).damage*2;
					    	Game_Objects.room.get(i).npc.get(y).hp = Game_Objects.room.get(i).npc.get(y).hp - pc_damage;
					    	System.out.println();
					    	System.out.println("You hit a CRITICAL HIT for " + pc_damage);
					    } else {
					    	int pc_damage = Game_Objects.pc.wornItems.get(0).damage;
					    	Game_Objects.room.get(i).npc.get(y).hp = Game_Objects.room.get(i).npc.get(y).hp - pc_damage;
					    	System.out.println();
					    	System.out.println("You hit for " + pc_damage);
					    }	
							double npc_hit = Game_Objects.rng.returnRandom(100)*Game_Objects.room.get(i).npc.get(y).accuracy;
							if (npc_hit < 15) {
								System.out.println(Game_Objects.room.get(i).npc.get(y).name + " missed");
								System.out.println();
								System.out.println("Your HP: " + Game_Objects.pc.hp);
								if (Game_Objects.room.get(i).npc.get(y).hp > 0) {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: " + Game_Objects.room.get(i).npc.get(y).hp);
								}
								else {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: 0");
								}
							} else if(npc_hit > 85){
								int npc_damage = Game_Objects.room.get(i).npc.get(y).damage*2;
								Game_Objects.pc.hp = Game_Objects.pc.hp - npc_damage;
								System.out.println(Game_Objects.room.get(i).npc.get(y).name + " hit a CRITICAL HIT on you for " + npc_damage);
								System.out.println();
								if(Game_Objects.pc.hp > 0) {
									System.out.println("Your HP: " + Game_Objects.pc.hp);
								}
								else {
									System.out.println("Your HP: 0");
								}
								if (Game_Objects.room.get(i).npc.get(y).hp > 0) {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: " + Game_Objects.room.get(i).npc.get(y).hp);
								}
								else {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: 0");
								}
							} else {
								int npc_damage = Game_Objects.room.get(i).npc.get(y).damage;
								Game_Objects.pc.hp = Game_Objects.pc.hp - npc_damage;
								System.out.println(Game_Objects.room.get(i).npc.get(y).name + " hit you for " + npc_damage);
								System.out.println();
								if(Game_Objects.pc.hp > 0) {
									System.out.println("Your HP: " + Game_Objects.pc.hp);
								}
								else {
									System.out.println("Your HP: 0");
								}
								if (Game_Objects.room.get(i).npc.get(y).hp > 0) {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: " + Game_Objects.room.get(i).npc.get(y).hp);
								}
								else {
									System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: 0");
								}
							}
						    if (Game_Objects.room.get(i).npc.get(y).hp <= 0) {
								npc_death(i,y);
							  }	
						    
						    if (Game_Objects.pc.hp <= 0) {
						    	pc_death(i,y);		  
							}
						}
					}
				}
			}
		}
	}	
	
	public void bow(String[] x) {
		for (int i = 0; i < Game_Objects.room.size(); i++) {
			if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
				for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++) {
					if (Game_Objects.room.get(i).npc.get(y).idfr.equalsIgnoreCase(x[1])) {
						Game_Objects.room.get(i).npc.get(y).hp = 0;
						System.out.println();
						System.out.println("YOU HIT A BOW ON " + Game_Objects.room.get(i).npc.get(y).name);
						System.out.println();
						System.out.println("Your HP: " + Game_Objects.pc.hp);
						System.out.println(Game_Objects.room.get(i).npc.get(y).name + " HP: 0");
						npc_death(i,y);	
					}
				}
			}
		}
	}

	public void npc_death(int i, int y) {
		if(Game_Objects.room.get(i).npc.get(y).name == "Elisewell") {     																			//dead elisewell
			Game_Objects.pc.item.add((Item) Game_Objects.ItemDataBase.get(5));
			Game_Objects.pc.wallet = Game_Objects.pc.wallet + 50;
			System.out.println();
			System.out.println("Elisewell has died");
			System.out.println("She dropped 50 bucks!");
			System.out.println();
			System.out.println("You have defeated Elisewell! She dropped a salsa verde jug to attack enemies with and you");
			System.out.println("picked it up. Now move south to the bondo to link up with Blueface and Prince Charles.");			
		}
		if(Game_Objects.room.get(i).npc.get(y).name == "Rodent") {																					//rodent dropping bilbo
			double bilboChance = Game_Objects.rng.returnRandom(3);
			if(bilboChance == 1 && !Game_Objects.bilboChanceAE) {
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 10;
				Game_Objects.room.get(1).item.add((Item) Game_Objects.ItemDataBase.get(6));
				Game_Objects.bilboChanceAE = true;
				System.out.println();
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 10 + " bucks!");
				System.out.println("This rodent had a Bilbo Infused Latte! Grab it and go speak to Elisewell.");
			}
			else {
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 5;
				System.out.println();
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 5 + " bucks.");
			}
		}
		if(Game_Objects.room.get(i).npc.get(y).name == "Blueface" || Game_Objects.room.get(i).npc.get(y).name == "Prince Charles") {			//dead blueface and prince charles
			if (!Game_Objects.bondoAlreadyExecuted) {
				Game_Objects.bondoAlreadyExecuted = true;
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 50;
				System.out.println();
				System.out.println(Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println(Game_Objects.room.get(i).npc.get(y).name + " dropped " + 50 + " bucks!");
			}
			else {
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 50;
				Game_Objects.pc.item.add((Item) Game_Objects.ItemDataBase.get(4));
				System.out.println();
				System.out.println(Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println(Game_Objects.room.get(i).npc.get(y).name + " dropped " + 50 + " bucks!");
				System.out.println();
				System.out.println("You have defeated Blueface and Prince Charles! They have given you their elusive weapon, Doming Capabilities.");
				System.out.println("Equip the doming capabilities and use them to dome enemies that are in your way. Move west to La Tapatia to check");
				System.out.println("out the menu.");
			}	
		}
		if (Game_Objects.room.get(i).npc.get(y).name == "Beast") {      																		//beast dropping posi	
			double posiChance = Game_Objects.rng.returnRandom(5);
			if(posiChance == 1 && !Game_Objects.posiChanceAE) {
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 15;
				Game_Objects.room.get(4).npc.add((NPC) Game_Objects.NPCDataBase.get(1));
				Game_Objects.posiChanceAE = true;
				System.out.println();
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 15 + " bucks!");
				System.out.println("You have found Posi! This beast had swallowed him and just spit him out upon death. Speak to Posi to wake him up.");
			}
			else {
				Game_Objects.pc.wallet = Game_Objects.pc.wallet + 5;
				System.out.println();
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
				System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 5 + " bucks.");
			}
		}
		if (Game_Objects.room.get(i).npc.get(y).name == "Boss Beast") {																			//dead boss beast
			Game_Objects.pc.wallet = Game_Objects.pc.wallet + 50;
			Game_Objects.bossBeastDead = true;
			System.out.println();
			System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
			System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 50 + " bucks!");
			System.out.println();
			System.out.println("You have defeated the Boss Beast! You can travel to the Pondo Ruins.");
		}
		if (Game_Objects.room.get(i).npc.get(y).name == "Disciple") {																			//dead disciple
			Game_Objects.pc.wallet = Game_Objects.pc.wallet + 50;
			Game_Objects.room.get(5).item.add((Item) Game_Objects.ItemDataBase.get(12));
			System.out.println();
			System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " has died");
			System.out.println("The " + Game_Objects.room.get(i).npc.get(y).name + " dropped " + 50 + " bucks!");
			System.out.println();
			System.out.println("You have defeated the Disciple of Lord Chris and in his death he drops a large bound leather book on the ground.");
		}
		if (Game_Objects.room.get(i).npc.get(y).name == "Big Frank") {																			//dead big frank
			Game_Objects.room.get(6).npc.add((NPC) Game_Objects.NPCDataBase.get(13));
			System.out.println();
			System.out.println("You have defeated Big Frank and saved Neicerville by ending the sliming!");
			System.out.println("A spectral being floats down from above and hovers in front of you. Use the peep and speak commands to see who it is and speak to them.");
		}
		Game_Objects.room.get(i).npc.remove(y);
	}
		
	public void pc_death(int i, int y) {
		Game_Objects.pc.hp = 100;
		Game_Objects.pc.lives = Game_Objects.pc.lives - 1;
		System.out.println();
		System.out.println("You dumb stinky idiot, you died!! Who does that, you suck.");
		if (Game_Objects.pc.lives == 0) {
			System.out.println("You have lost all your lives! You silly goose. Here, have some more and try again.");
			Game_Objects.pc.lives = 3;
		}
		System.out.println("You now have " + Game_Objects.pc.lives + " lives left.");
	}
}