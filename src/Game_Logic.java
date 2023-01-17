import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game_Logic {
	
	public Game_Logic() {
		Game_Objects.room.add(new Room(0));
		List<String> roomInfo = new ArrayList<>();
		try {
			roomInfo = readLines("/TextFiles/RoomDescriptions.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < roomInfo.size(); i++) {
			String[] firstWord = roomInfo.get(i).split(" ");
			String[] everythingElse = roomInfo.get(i).split(": ");
			if (firstWord[0].equals("RoomName:")) {
				int currentRoomSize = Game_Objects.room.size();
				Game_Objects.room.add(new Room(currentRoomSize));
				Game_Objects.room.get(Game_Objects.room.size() - 1).name = everythingElse[1];
				Game_Objects.room.get(Game_Objects.room.size() - 1).number = (currentRoomSize);				
				int roomcount = 0;
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) {
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if (nextFirstWord[0].equals("Desc:")) {
							String[] nextEverythingElse = roomInfo.get(z).split(": ");
							Game_Objects.room.get(Game_Objects.room.size() - 1).desc.add(nextEverythingElse[1]);
						}
					}
				}
				roomcount = 0;
				for (int z = 0; z < roomInfo.size(); z++) {
					String[] nextFirstWord = roomInfo.get(z).split(" ");
					if (nextFirstWord[0].equals("RoomName:")) {
						roomcount++;
					}
					if (roomcount == currentRoomSize) {
						if (nextFirstWord[0].equals("Exit:")) {
							String[] nextEverythingElse = roomInfo.get(z).split(": ");
							String[] exitWithoutNumber = roomInfo.get(z).split(" ");
							Game_Objects.room.get(Game_Objects.room.size() - 1).exits.add(nextEverythingElse[1]);
							Game_Objects.room.get(Game_Objects.room.size() - 1).exitsname.add(exitWithoutNumber[1]);
						}
					}
				}
			}
		}
	}
	
	public List<String> readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines;
	}

	public void waitforCommand() {
		if (Game_Objects.pc.inRoom == 0) {
			createCharacter();
		}
		System.out.println();
		System.out.println(Game_Objects.pc.name +", what is your next move ?");
		System.out.println();
		Scanner sc = new Scanner(System.in);
		String response = sc.nextLine();
		String[] words = response.split(" ");
		processCommand(words);
	}
	
	public void createCharacter() {
		System.out.println();
		System.out.println("|WELCOME TO NEICERVILLE: THE GAME| (type help for a list of commands)");
		System.out.println();
		System.out.println("You wake up in a bush outside of a small café with an empty fifth of admiral in your hands.");
		System.out.println("You head inside and see this café is Drift Kitchen. The manager walks over to greet you.");
		System.out.println();
		System.out.println("Elisewell: Hello, I am the manager of this establishment. What is your name?");
		System.out.println();
		Scanner scc = new Scanner(System.in);
		Game_Objects.pc.name = scc.next();
		Game_Objects.pc.hp = 100;
		Game_Objects.pc.accuracy = 1.2;
		Game_Objects.pc.inRoom = 1;		
		Game_Objects.pc.lives = 3;
		String[] dkSetUp2 = new String[3];
		dkSetUp2[0] = "create";
		dkSetUp2[1] = "soup";
		dkSetUp2[2] = "ladel";
		dev_create(dkSetUp2);
		System.out.println();
		System.out.println(Game_Objects.pc.name + ": Where am I?");
		System.out.println();
		System.out.println("Elisewell: Your name is " + Game_Objects.pc.name + "? The ancient texts scribed by Lord Chris mentioned someone of that name would");
		System.out.println("arrive in town with no idea of where they are. You are in the town of Neicerville. A location that is nowhere but");
		System.out.println("everywhere at the same time. Unfortunately, I do not have much time to talk. There has been an evil force");
		System.out.println("brewing at the Staybridge Suites and all of Neicerville is feeling its wrath. Lately because of the evil forces,");
		System.out.println("Drift Kitchen has been overrun by rodents and they have been getting into the Bilbo Infused Lattes. If you can");
		System.out.println("help me out by attacking rodents until one of them drops a Bilbo Infused Latte, I would have some time to give");
		System.out.println("you more information on these matters. Come speak to me when you find one.");
	}

	public void processCommand(String[] x) {
		if(x[0].equalsIgnoreCase("dev_create")) {                     
			dev_create(x);
		}
		if (x[0].equalsIgnoreCase("dev_summon")) {                 
			dev_summon(x);
		}
		if(x[0].equalsIgnoreCase("peep")) {
			peep(x);
		}
		if(x[0].equalsIgnoreCase("grab")) {
			grab(x);
		}
		if(x[0].equalsIgnoreCase("help")) {
			help();
		}
		if(x[0].equalsIgnoreCase("move")) {
			move(x);	
		}
		if(x[0].equalsIgnoreCase("hint")) {
			hint();	
		}
		if(x[0].equalsIgnoreCase("speak")) {
			speak(x);	
		}
		if(x[0].equalsIgnoreCase("menu")) {
			menu();	
		}
		if(x[0].equalsIgnoreCase("buy")) {
			buy(x);	
		}
		if(x[0].equalsIgnoreCase("eat")) {
			eat(x);	
		}
		if(x[0].equalsIgnoreCase("equip")) {
			Game_Objects.pc.equip(x);
		}
		if(x[0].equalsIgnoreCase("remove")) {
			Game_Objects.pc.remove(x);
		}
		if(x[0].equalsIgnoreCase("dripcheck")) {
			Game_Objects.pc.dripcheck();
		}
		if(x[0].equalsIgnoreCase("attack")) {
			Game_Objects.combat.attack(x);
		}
		if(x[0].equalsIgnoreCase("killshit")) {
			System.exit(0);
		}
		if(x[0].equalsIgnoreCase("bow")) {
			bow(x);	
		}
	}

	public void dev_create(String[] x) {                       
		if (x.length > 1) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
				Item itemSquared = (Item) Game_Objects.ItemDataBase.get(i);
				if (x[1].equalsIgnoreCase(itemSquared.namefr)) {
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {	
							Game_Objects.room.get(y).item.add((Item) itemSquared);										
						}
					}
				}
			}
		}
	}
	
	public void dev_summon(String[] x) {                           
		if (x.length > 1) {
			for (int i = 0; i < Game_Objects.NPCDataBase.size(); i++) {
				NPC npcItem = (NPC) Game_Objects.NPCDataBase.get(i);
				if (x[1].equalsIgnoreCase(npcItem.idfr)) {
					for (int y = 0; y < Game_Objects.room.size(); y++) {
						if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
							Game_Objects.room.get(y).npc.add((NPC) npcItem);
						}	
					}
				}
			}
		}	
	}
	
	public void peep(String[] x) {
		if (x.length == 1) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					System.out.println();
					System.out.println("| " + Game_Objects.room.get(i).name + " |");
					System.out.println();
					for (int y = 0; y < Game_Objects.room.get(i).desc.size(); y++) {
						System.out.println(Game_Objects.room.get(i).desc.get(y));
					}
					System.out.println();
					System.out.println("Exits:");
					for (int y = 0; y < Game_Objects.room.get(i).exitsname.size(); y++) {
						System.out.println(Game_Objects.room.get(i).exitsname.get(y));
					}
					if(Game_Objects.room.get(i).npc.size() > 0 || Game_Objects.room.get(i).item.size() > 0) {
						System.out.println();
					}
					for (int y = 0; y < Game_Objects.room.get(i).npc.size(); y++ ) {
						System.out.println(Game_Objects.room.get(i).npc.get(y).desc);
					}
					if(Game_Objects.room.get(i).npc.size() > 0 && Game_Objects.room.get(i).item.size() > 0) {
						System.out.println();
					}
					for (int y = 0; y < Game_Objects.room.get(i).item.size(); y++) {
						System.out.println(Game_Objects.room.get(i).item.get(y).desc);
					}
				}
			}
		}
		
		if (x.length > 1) {
			for (int y = 0; y < Game_Objects.room.size(); y++) {
				if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
					for (int i = 0; i < Game_Objects.room.get(y).npc.size(); i++) {
						if(x[1].equalsIgnoreCase(Game_Objects.room.get(y).npc.get(i).idfr)) {
							Game_Objects.room.get(y).npc.get(i).peep();
						}
					}
					for (int i = 0; i < Game_Objects.room.get(y).item.size(); i++) {
						if(x[1].equalsIgnoreCase(Game_Objects.room.get(y).item.get(i).namefr)) {
							Game_Objects.room.get(y).item.get(i).peep();
						}
					}
					for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
						if (x[1].equalsIgnoreCase(Game_Objects.pc.item.get(i).namefr)) {
							Game_Objects.pc.item.get(i).peep();
						}
					}
					for (int i = 0; i < Game_Objects.pc.wornItems.size(); i++) {
						if (x[1].equalsIgnoreCase(Game_Objects.pc.wornItems.get(i).namefr)) {
							Game_Objects.pc.wornItems.get(i).peep();
						}
					}
				}
			}
		}
	}
	
	public void grab(String[] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("What are you grabbing?");
		}
		if (x.length > 1) {
			for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++) {
				for (int y = 0; y < Game_Objects.room.size(); y++) {
					if (Game_Objects.room.get(y).number == Game_Objects.pc.inRoom) {
						for (int z = 0; z < Game_Objects.room.get(y).item.size(); z++) {
							if (x[1].equalsIgnoreCase(Game_Objects.room.get(y).item.get(z).namefr)) {
								if(x[1].equalsIgnoreCase("candy")) {
									System.out.println();
									System.out.println("Blueface: Yo fahm did you just touch my candy?");
									System.out.println("Prince Charles: Don't ever come up in the bondo and just start touching shit. ");
									System.out.println("Blueface: Wait you must be the chosen one here to defeat the evil at the Suites and save all of Neicerville.");
									System.out.println();
									System.out.println(Game_Objects.pc.name + ": yuh thats me");
									System.out.println();
									System.out.println("Blueface: In that case fahm, Prince Charles and I have been lowkey cooking up in the lab and have made a fire");
									System.out.println("weapon for you to use in your quest. Also, if you head to La Tapatia my old roommates have a dope restaurant with some");
									System.out.println("food that will help you during battle. I would give you this weapon right now but we don't know your capabilities");
									System.out.println("yet and if you can handle it or not.");
									System.out.println("Prince Charles: Yeah you are gonna have to dome us both if you want the elusive weapon.");
									Game_Objects.pc.item.add(Game_Objects.room.get(y).item.get(z));
									Game_Objects.room.get(y).item.remove(z);
									String[] bondoSetUp = new String[2];
									bondoSetUp[0] = "summon";
									bondoSetUp[1] = "blueface";
									dev_summon(bondoSetUp);
									String[] bondoSetUp2 = new String[3];
									bondoSetUp2[0] = "summon";
									bondoSetUp2[1] = "prince";
									bondoSetUp2[2] = "charles";
									dev_summon(bondoSetUp2);
									String[] badBlue = new String[2];
									String[] badDave = new String[3];
									badDave[0] = ("attack");
									badDave[1] = ("prince");
									badDave[2] = ("charles");
									badBlue[0] = ("attack");
									badBlue[1] = ("blueface");
									Game_Objects.combat.attack(badBlue);
									Game_Objects.combat.attack(badDave);
									break;
								} 
								else if (x[1].equalsIgnoreCase("ancient")) {
									Game_Objects.pc.item.add(Game_Objects.room.get(y).item.get(z));
									Game_Objects.room.get(y).item.remove(z);
									Game_Objects.room.get(y).npc.remove(Game_Objects.NPCDataBase.get(11));
									Game_Objects.room.get(y).npc.add((NPC) Game_Objects.NPCDataBase.get(2));
									Game_Objects.pc.item.add((Item) Game_Objects.ItemDataBase.get(1));
									System.out.println();
									System.out.println("You pick up the Ancient Texts. The book itself seems to be breathing. You open and read it. The text appears");
									System.out.println("to be a different language but for some reason you are able to understand it. As you read page after page you fall");
									System.out.println("into a trance and lose consciousness.");
									System.out.println();
									System.out.println("Old Man: You did it, you are truly the hero of legend. You have acquired the Shadow Jutsu that is needed to defeat");
									System.out.println("the evil. Please, head to the Staybridge Suites and fulfill your destiny.");
									System.out.println();
									System.out.println(Game_Objects.pc.name + ": I will go now and save Neicerville at last. Thank you for all your help. Who are you anyway?");
									System.out.println();
									System.out.println();
									System.out.println();
									System.out.println();
									System.out.println("Beam: You can call me Beam Soma.");
									break;
								}
								else {
									Game_Objects.pc.item.add(Game_Objects.room.get(y).item.get(z));
									System.out.println();
									System.out.println("You grabbed a " + Game_Objects.room.get(y).item.get(z).name);
									Game_Objects.room.get(y).item.remove(z);
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void help() {
		System.out.println();
		System.out.println("|NEICERVILLE HELP MENU|");
		System.out.println();
		System.out.println("help: brings up this help menu");
		System.out.println("hint: gives you a hint on how to proceed in the game");
		System.out.println("peep: see information about the room you are currently in");
		System.out.println("dripcheck: check your health, accuracy, wallet, and inventory");
		System.out.println("peep <NPC>: see information about a specific NPC");
		System.out.println("grab <ITEM>: pick up an object that is present in a room");
		System.out.println("equip <ITEM>: equip a weapon you have grabbed to use it in battle");
		System.out.println("remove <ITEM>: unequip an item and place back in inventory");
		System.out.println("attack <NPC>: attack an enemy with the weapon you have equipped");
		System.out.println("speak <NPC>: speak with a NPC");
		System.out.println("move <DIRECTION>: move between rooms by typing move and then a direction");
		if(Game_Objects.helpAlreadyExecuted) {
			System.out.println("menu: brings up the menu for La Tapatia");
			System.out.println("buy <ITEM>: buy an item from La Tapatia");
			System.out.println("eat <ITEM>: eat an item");
		}
		System.out.println("There are also some secret commands :)");
	}
	
	public void move(String[] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("Where do you wish to go?");
		}
		if (x.length == 2) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					for (int y = 0; y < Game_Objects.room.get(i).exits.size(); y++) {
						String exitRequested = Game_Objects.room.get(i).exits.get(y);
						String[] exitArray = exitRequested.split(" ");
						if (x[1].equalsIgnoreCase(exitArray[0])) {       
							if (Game_Objects.room.get(i).number == 1 && x[1].equalsIgnoreCase("north")) {                                   											 //staybridge
								if(Game_Objects.pc.wornItems.contains(Game_Objects.ItemDataBase.get(1))) {
									System.out.println();
									System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
									Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
									String[] peepTime = new String[1];
									peepTime[0] = "peeptime";
									peep(peepTime);
									String[] suitesSetUp = new String[3];
									suitesSetUp[0] = "summon";
									suitesSetUp[1] = "big";
									suitesSetUp[2] = "frank";
									dev_summon(suitesSetUp);
									System.out.println();
									System.out.println("You walk into the Staybridge Suites and can feel the evil forces surrounding you.");
								}
								else if (Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(1))) {
									System.out.println();
									System.out.println("You must equip the shadow jutsu in order to enter the suites.");
								}
								else if (!Game_Objects.pc.wornItems.contains(Game_Objects.ItemDataBase.get(1)) || !Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(1))) {
									System.out.println();
									System.out.println("The evil is too strong for you to enter the Staybridge Suites right now.");
								}
							}	
							else if(Game_Objects.room.get(i).number == 1 && x[1].equalsIgnoreCase("south")) {                              												  //bondo
								if(Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(5)) || Game_Objects.pc.wornItems.contains(Game_Objects.ItemDataBase.get(5))) {
									if(!Game_Objects.alreadyExecuted) {
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
										String[] bondoSetUp3 = new String[3];
										bondoSetUp3[0] = "create";
										bondoSetUp3[1] = "candy";
										bondoSetUp3[2] = "sack";
										dev_create(bondoSetUp3);
										Game_Objects.alreadyExecuted = true;
										System.out.println();
										System.out.println("You arrive at the bondo and smell candy in the air but the place seems empty.");
									}
									else {
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
									}
								}
								else {
									System.out.println();
									System.out.println("Can't go here yet");
								}
							}
							else if(Game_Objects.room.get(i).number == 2 && x[1].equalsIgnoreCase("west")) {                              													//la tap
								if(Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(4)) || Game_Objects.pc.wornItems.contains(Game_Objects.ItemDataBase.get(4))) {
									if (!Game_Objects.laTapAlreadyExecuted) {
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.laTapAlreadyExecuted = true;
										Game_Objects.helpAlreadyExecuted = true;
										Game_Objects.room.get(3).laTapShop.add((Item) Game_Objects.ItemDataBase.get(9));
										Game_Objects.room.get(3).laTapShop.add((Item) Game_Objects.ItemDataBase.get(10));
										Game_Objects.room.get(3).laTapShop.add((Item) Game_Objects.ItemDataBase.get(11));
										Game_Objects.room.get(3).npc.add((NPC) Game_Objects.NPCDataBase.get(4));
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
										System.out.println();
										System.out.println("Phildu and Big Rich: Welcome to La Tapatia. We are co-owners of this restaurant, Phildu runs the front of house");
										System.out.println("operations and Big Rich handles business out back. Blueface and Prince Charles said you were coming. If you are");
										System.out.println("trying to save Neicerville, you should head to the Land of Tampico next. I heard it was in really rough shape since");
										System.out.println("the Protector of the Lands went missing. Move south from here to get there.");
										System.out.println();
										System.out.println("(type help to see commands related to the La Tapatia restaurant)");
									}	
									else {
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
									}
								}
								else {
									System.out.println();
									System.out.println("Can't go here yet");
								}
							}
							else if (Game_Objects.room.get(i).number == 3 && x[1].equalsIgnoreCase("south")) {																			 //land of tampico		
								if (!Game_Objects.tampicoAlreadyExecuted) {
									Game_Objects.tampicoAlreadyExecuted = true;
									System.out.println();
									System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
									Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
									String[] peepTime = new String[1];
									peepTime[0] = "peeptime";
									peep(peepTime);
									System.out.println();
									System.out.println("You have arrived at the Land of Tampico. The area is desolate and barren with very little plants or wildlife. There");
									System.out.println("is a sign next to you and you read it.");
									System.out.println();
									System.out.println("Sign: PROCEED WITH CAUTION. Ever since the explosion at the Staybridge Suites, there have been beasts inhabiting these lands.");
									System.out.println("The Protector of the Land of Tampico, Posi, has been missing and these wild and dangerous beasts have made this their home");
									System.out.println("since. The beasts could have something to do with Posi's disappearance.");
								}
								else {
									System.out.println();
									System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
									Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
									String[] peepTime = new String[1];
									peepTime[0] = "peeptime";
									peep(peepTime);
								}
							}
							else if (Game_Objects.room.get(i).number == 4 && x[1].equalsIgnoreCase("west")) {																				//pondo ruins
								if (!Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(3)) && !Game_Objects.pc.wornItems.contains(Game_Objects.ItemDataBase.get(3))) {
									System.out.println();
									System.out.println("Find and speak to Posi first");
								} 
								else {
									if (!Game_Objects.pondoAlreadyExecuted) {
										Game_Objects.pondoAlreadyExecuted = true;
										String[] summonBossBeast = new String[3];
										summonBossBeast[0] = "summon";
										summonBossBeast[1] = "boss";
										summonBossBeast[2] = "beast";
										dev_summon(summonBossBeast);
										System.out.println();
										System.out.println("You attempt to travel to the Pondo Ruins but there is something blocking your path.");
										System.out.println();
										System.out.println("Boss Beast: Silly human. You think you can murder my children and then just leave like nothing happened. I will kill you");
										System.out.println("where you stand as revenge for my offspring. Your quest ends here.");
										String[] attackBossBeast = new String[3];
										attackBossBeast[0] = "attack";
										attackBossBeast[1] = "boss";
										attackBossBeast[2] = "beast";
										Game_Objects.combat.attack(attackBossBeast);
									}
									else if (!Game_Objects.bossBeastDead) {
										System.out.println();
										System.out.println("The Boss Beast is in your path. Defeat him to make your way to the Pondo Ruins.");
									}
									else if (!Game_Objects.pondoAlreadyExecuted2) {
										Game_Objects.pondoAlreadyExecuted2 = true;
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
										Game_Objects.room.get(5).npc.add((NPC) Game_Objects.NPCDataBase.get(11));
										Game_Objects.room.get(5).npc.add((NPC) Game_Objects.NPCDataBase.get(12));
										System.out.println();
										System.out.println("You walk into the Pondo Ruins and it is obvious this place has been abandoned for a long time. The buildings are all dilapidated");
										System.out.println("and the grass and weeds are overgrown. There is a rusted over statue of nine men with a dusty plaque. You wipe some of the dust");
										System.out.println("off to reveal the following text.");
										System.out.println();
										System.out.println("Dusty Plaque: The Original 9 Members of Neicerville will forever be remembered here by this glorious statue.");
										System.out.println();
										System.out.println("Old Man: You know, I knew all of them. Back in the day, we used to plow dabs right here in this very spot.");
										System.out.println();
										System.out.println(Game_Objects.pc.name + ": Where are they now?");
										System.out.println();
										System.out.println("Old Man: All grazing on the different pastures of life. I now remain here in the Ruins of the Pondo by myself watching after this");
										System.out.println("holy site. I know who you are and why you are here. And yes, all the people you have met along your path insisting you are the");
										System.out.println("chosen one are correct. You are the only one who can put a stop to all of this.");
										System.out.println();
										System.out.println(Game_Objects.pc.name + ": I don't even know who I am or how I can save Neicerville.");
										System.out.println();
										System.out.println("Old Man: That does not matter, what matters is you must read the Ancient Texts. Within it, Lord Chris describes your prophecy");
										System.out.println("and the jutsu required to defeat the evil at the Staybridge Suites.");
										System.out.println();
										System.out.println(Game_Objects.pc.name + ": Where are these texts?");
										System.out.println();
										System.out.println("Old Man: They are here in the Pondo Ruins right now. To ensure you are truly the chosen one, a disciple of Lord Chris was sent");
										System.out.println("here to challenge you. Defeat him, and you will be granted access to the ancient texts.");
									}
									else {
										System.out.println();
										System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
										Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
										String[] peepTime = new String[1];
										peepTime[0] = "peeptime";
										peep(peepTime);
									}
								}
							}
							else {                                                                                                        													 //regular move
								System.out.println();
								System.out.println("You left " + Game_Objects.room.get(Game_Objects.pc.inRoom).name);
								Game_Objects.pc.inRoom = Integer.parseInt(exitArray[1]);
								String[] peepTime = new String[1];
								peepTime[0] = "peeptime";
								peep(peepTime);
							}
						}
					}
				}
			}
		}
	}

	public void hint() {
		if(Game_Objects.pc.inRoom == 1) {
			if (Game_Objects.room.get(0).item.contains(Game_Objects.ItemDataBase.get(6))) {
				System.out.println();
				System.out.println("use the speak command on elisewell");
			}
			else {
			System.out.println();
			System.out.println("use the peep, grab, equip, and attack commands to kill the rodents");
			}
		}
		if(Game_Objects.pc.inRoom == 2) {
			System.out.println();
			System.out.println("use the peep and grab commands to get the attention of the Bondo's inhabitants");
		}
		if(Game_Objects.pc.inRoom == 3) {
			System.out.println();
			System.out.println("type help to see commands for La Tapatia Restaurant");
		}
		if(Game_Objects.pc.inRoom == 4) {
			System.out.println();
			System.out.println("attack the beasts to find Posi");
		}
		if(Game_Objects.pc.inRoom == 5) {
			if (Game_Objects.room.get(5).item.contains(Game_Objects.ItemDataBase.get(12))) {
				System.out.println();
				System.out.println("grab the ancient texts to read them");
			}
			else {
				System.out.println();
				System.out.println("attack the disciple to get the ancient texts");
			}
		}
		if(Game_Objects.pc.inRoom == 6) {
			System.out.println();
			System.out.println("use the peep and speak commands");
		}
	}

	public void speak(String [] x) {
		if(x.length == 1) {
			System.out.println();
			System.out.println("Who do you want to speak to ?");
		}
		if(x.length > 1) {
			if (Game_Objects.pc.inRoom == 1) {
				if(x[1].equalsIgnoreCase("elisewell") || x[2].equalsIgnoreCase("elisewell")) {
					if (Game_Objects.pc.item.contains(Game_Objects.ItemDataBase.get(6))) {
						if (!Game_Objects.eliseSpawnAE) {
							Game_Objects.eliseSpawnAE = true;
							String[] elisewellSetUp = new String[2];
							elisewellSetUp[0] = "summon";
							elisewellSetUp[1] = "elisewell";
							dev_summon(elisewellSetUp);
						}
						System.out.println();
						System.out.println("Elisewell: Thank you so much for finding me a Bilbo Infused Latte, I thought the rats drank all of them.");
						System.out.println();
						System.out.println(Game_Objects.pc.name + ": No problem, so tell me about these brewing evil forces.");
						System.out.println();
						System.out.println("Elisewell: A few years ago, there was a loud explosion at the Staybridge Suites and ever since");
						System.out.println("then, horrible things have been happening all around Neicerville. I heard that Blueface");
						System.out.println("and Prince Charles who live at the Bondo have been working on an elite weapon that could be");
						System.out.println("used to defeat whatever evil is there. You know, you were so finding one of the lattes,");
						System.out.println("you could be the chosen one. You have to work here. You can start right now. We");
						System.out.println("can co-manage Drift Kitchen forever.");
						System.out.println();
						System.out.println(Game_Objects.pc.name + ": Nah fam, I got to go to the Bondo to get the weapon to stop the evil at the Suites and save Neicerville.");
						System.out.println();
						System.out.println("Elisewell: If I can't have you, no one can. You shall die here in Drift Kitchen then!!!!");
						String[] elisewellSetUp2= new String[2];
						elisewellSetUp2[0] = "attack";
						elisewellSetUp2[1] = "elisewell";
						Game_Objects.combat.attack(elisewellSetUp2);
					}
					else {
						System.out.println();
						System.out.println("find and grab the latte first");
					}
				}
			}
			if (Game_Objects.pc.inRoom == 4) {
				if (Game_Objects.room.get(4).npc.contains(Game_Objects.NPCDataBase.get(1))) {
					if(x[1].equalsIgnoreCase("posi") || x[2].equalsIgnoreCase("posi")) {
						if(!Game_Objects.posiSpeakAE) {
							Game_Objects.posiSpeakAE = true;
							Game_Objects.pc.item.add((Item) Game_Objects.ItemDataBase.get(3));
							System.out.println();
							System.out.println("Posi: Thank you for saving me! I had been trapped inside of there for years. Since the explosion, there");
							System.out.println("have been too many damn beasts for me to protect the lands and they overpowered me. Who are you?");
							System.out.println();
							System.out.println(Game_Objects.pc.name + ": " + Game_Objects.pc.name);
							System.out.println();
							System.out.println("Posi: " + Game_Objects.pc.name + "? You may be able to solve all of this. The ancient texts described a chosen one");
							System.out.println("that would arrive when Neicerville needed them the most and you seem to fit the description. If you're able to");
							System.out.println("find the ancient texts it could give you more information on how to stop these evil forces. I believe the last");
							System.out.println("copy of the texts is at the Pondo Ruins. There is an old man who still lives at the Ruins of the Pondo even");
							System.out.println("after all these years. Move west from here to get to the Pondo Ruins. Also, take this meat stick. I have used it");
							System.out.println("to protect the Land of Tampico for years but I feel like it is better off in your hands.");
							System.out.println();
							System.out.println("Posi gave you a Meat Stick!");
						}	
					}
				}	
			}
			if (Game_Objects.pc.inRoom == 6) {
				if(x[1].equalsIgnoreCase("big") || x[2].equalsIgnoreCase("big")) {
					Game_Objects.bigFrankAE = true;
					System.out.println();
					System.out.println("Big Frank: I have been expecting you. They told me there would be some nerd coming to ruin my fun. I am Big Frank and I");
					System.out.println("believe you are here to kill me.");
					System.out.println();
					System.out.println(Game_Objects.pc.name + ": Big Frank? You are the one behind all of this??");
					System.out.println();
					System.out.println("Big Frank: Yes, 4 years ago I arrived in Neicerville and decided to post up at the Staybridge Suites. The explosion that");
					System.out.println("disrupted all of Neicerville was a slime explosion that only Big Frank is capable of. Ever since then, I have been here");
					System.out.println("sliming on Staybridge bodies relentlessly. My continuous sliming has been leaking slime all over Neicerville and causing");
					System.out.println("dangerous beasts to appear.");
					System.out.println();
					System.out.println(Game_Objects.pc.name + ": You have to stop sliming! Your slime will cause Neicerville to cease to exist!");
					System.out.println();
					System.out.println("Big Frank: I don't give a shit, I will slime to my fullest until my death. And there is nothing you can do to kill me.");
					System.out.println();
					System.out.println(Game_Objects.pc.name + ": You couldn't be more wrong Lil Frank. My name is " + Game_Objects.pc.name + " and I will use the power of the shadow jutsu to");
					System.out.println("defeat you and end the sliming for good!");
					String[] bigFrankBattle = new String[3];
					bigFrankBattle[0] = "attack";
					bigFrankBattle[1] = "big";
					bigFrankBattle[2] = "frank";
					Game_Objects.combat.attack(bigFrankBattle);
				}
				else if(x[1].equalsIgnoreCase("lord") || x[2].equalsIgnoreCase("lord")) {
					System.out.println();
					System.out.println("Lord Chris: I am His Holiness, Lord Chris, and I wanted to personally thank you for defeating Big Frank and saving Neicerville.");
					System.out.println("You have truly demonstrated that you are the chosen one that I described in the Ancient Texts. You will forever be known as the hero");
					System.out.println("who saved Neicerville.");
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("|YOU HAVE BEAT NEICERVILLE: THE GAME|");
					System.out.println("THANK YOU FOR PLAYING :)");
				}
			}
		}
	}

	public void bow(String [] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("You just hit a fat ass bow on them hoes");
			System.out.println("(you should try using bow on a npc)");
		}
		else {
			Game_Objects.combat.bow(x);
		}
	}

	public void menu() {
		for (int i = 0; i < Game_Objects.room.size(); i++) {
			if (Game_Objects.pc.inRoom == 3) {
				if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
					System.out.println();
					System.out.println("Wallet: $" + Game_Objects.pc.wallet);
					for (int y = 0; y < Game_Objects.room.get(i).laTapShop.size(); y++) {
						System.out.println();
						System.out.println("| " + Game_Objects.room.get(i).laTapShop.get(y).name + " | $" + Game_Objects.room.get(i).laTapShop.get(y).price + " |");
						if (!Game_Objects.room.get(i).laTapShop.get(y).namefr.equalsIgnoreCase("mango")) {
							System.out.println("Restores " + Game_Objects.room.get(i).laTapShop.get(y).hearts + " hp points");						
						}
						if (Game_Objects.room.get(i).laTapShop.get(y).namefr.equalsIgnoreCase("mango")) {
							System.out.println("Raises your accuracy by 0.2");						
						}
					}
				}	
			}	
		}
	}

	public void buy(String [] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("What do you want to buy ?");
		}
		if (x.length > 1) {
			for (int i = 0; i < Game_Objects.room.size(); i++) {
				if (Game_Objects.pc.inRoom == 3) {
					if (Game_Objects.room.get(i).number == Game_Objects.pc.inRoom) {
						for (int y = 0; y < Game_Objects.room.get(i).laTapShop.size(); y++) {
							if (x[1].equalsIgnoreCase(Game_Objects.room.get(i).laTapShop.get(y).namefr)) {
								if (Game_Objects.pc.wallet > Game_Objects.room.get(i).laTapShop.get(y).price) {
									Game_Objects.pc.wallet = Game_Objects.pc.wallet - Game_Objects.room.get(i).laTapShop.get(y).price;
									Game_Objects.pc.item.add(Game_Objects.room.get(i).laTapShop.get(y));
									System.out.println();
									System.out.println("You bought a " + Game_Objects.room.get(i).laTapShop.get(y).name + " for " + Game_Objects.room.get(i).laTapShop.get(y).price + " bucks");
									System.out.println("Wallet: $" + Game_Objects.pc.wallet);
								}
								else {
									System.out.println();
									System.out.println("You do not have enough money to buy this. sowwy");
								}
							}
						}
					}	
				}	
			}		
		}
	}

	public void eat(String [] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("What do you want to eat?");
		}
		if (x.length > 1) {
			for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
				if (x[1].equalsIgnoreCase(Game_Objects.pc.item.get(i).namefr)) {
					if (Game_Objects.pc.item.get(i).isEdible) {
						if (x[1].equalsIgnoreCase("mango")) {
							Game_Objects.pc.accuracy = Game_Objects.pc.accuracy + .2;
							Game_Objects.pc.item.remove(i);
							System.out.println();
							System.out.println("You raised your accuracy by 0.2 by drinking a Mango Juice");
							break;
						}
						else {
							Game_Objects.pc.hp = Game_Objects.pc.hp + Game_Objects.pc.item.get(i).hearts;
							if (Game_Objects.pc.hp < 100) {
								System.out.println();
								System.out.println("You healed " + Game_Objects.pc.item.get(i).hearts + " hp by eating a " + Game_Objects.pc.item.get(i).name);
								System.out.println("HP: " + Game_Objects.pc.hp);
								Game_Objects.pc.item.remove(i);
								break;
							}
							else {
								Game_Objects.pc.hp = 100;
								System.out.println();
								System.out.println("You healed " + Game_Objects.pc.item.get(i).hearts + " hp by eating a " + Game_Objects.pc.item.get(i).name);
								System.out.println("HP: " + Game_Objects.pc.hp);
								Game_Objects.pc.item.remove(i);
								break;
							}
						}
					}	
					else {
						System.out.println();
						System.out.println("You cannot eat that shit you animal");
						break;
					}	
				}			
			}
		}
	}	
}	