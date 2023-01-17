public class Item {
	
	String name;
	String namefr;
	String id = "Item";
	String desc;
	boolean isWearable = false;
	boolean isNotWearable = false;
	boolean isEdible = false;
	int damage;
	int hearts;
	int price;
	double accuracy;

	public void peep() {
		System.out.println();
		System.out.println("| " + name + " |");
		System.out.println(desc);
		if (isWearable) {
			System.out.println("Damage: " + damage);
			System.out.println("Accuracy: " + accuracy);
		}
	}
}