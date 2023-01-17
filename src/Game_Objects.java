import java.util.ArrayList;
import java.util.List;

public class Game_Objects {

	static RNG rng = new RNG();
	static Combat combat = new Combat();
	static PC pc = new PC();
	static boolean alreadyExecuted = false;
	static boolean bondoAlreadyExecuted = false;
	static boolean laTapAlreadyExecuted = false;
	static boolean helpAlreadyExecuted = false;
	static boolean tampicoAlreadyExecuted = false;
	static boolean pondoAlreadyExecuted = false;
	static boolean pondoAlreadyExecuted2 = false;
	static boolean bossBeastDead = false;
	static boolean bilboChanceAE = false;
	static boolean posiChanceAE = false;
	static boolean posiSpeakAE = false;
	static boolean eliseSpawnAE = false;
	static boolean bigFrankAE = false;
	static List<Room> room = new ArrayList<Room>();
	static ArrayList<Object> NPCDataBase = new ArrayList<Object>();
	static ArrayList<Object> ItemDataBase = new ArrayList<Object>();	
	
	public static void initializeNPCArray() {
		NPCDataBase.add(new NPC());
		NPCDataBase.add(new Posi());
		NPCDataBase.add(new Beam());
		NPCDataBase.add(new Elisewell());
		NPCDataBase.add(new Phildu_and_Big_Rich());
		NPCDataBase.add(new Blueface());
		NPCDataBase.add(new Big_Frank());
		NPCDataBase.add(new Rodent());
		NPCDataBase.add(new Prince_Charles());
		NPCDataBase.add(new Beast());
		NPCDataBase.add(new Boss_Beast());
		NPCDataBase.add(new Old_Man());
		NPCDataBase.add(new Disciple());
		NPCDataBase.add(new Lord_Chris());
	}
	public static void initializeItemArray() {
		ItemDataBase.add(new Item());
		ItemDataBase.add(new Shadow_Jutsu());
		ItemDataBase.add(new Angry_Cheelo());
		ItemDataBase.add(new Meat_Stick());
		ItemDataBase.add(new Doming_Capabilities());
		ItemDataBase.add(new Salsa_Verde_Jug());
		ItemDataBase.add(new Bilbo_Infused_Latte());
		ItemDataBase.add(new Soup_Ladel());
		ItemDataBase.add(new Candy_Sack());
		ItemDataBase.add(new Chicken_Taco());
		ItemDataBase.add(new Beef_Taco());
		ItemDataBase.add(new Mango_Juice());
		ItemDataBase.add(new Ancient_Texts());
	}
}