public class NPC {
	
	String name;
	String id = "NPC";
	String idfr = "npc";
	String desc;
	String descfr;
	int hp;
	double accuracy;
	int accuracyfr;
	int inRoom;
	int damage;
	
	public void peep() {
		System.out.println();
		System.out.println("| " + name + " |");
		System.out.println(descfr);
		if (hp > 0) {
			System.out.println("HP: " + hp);
			System.out.println("Accuracy: " + accuracy);
		}	
	}
}