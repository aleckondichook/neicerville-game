import java.text.DecimalFormat;
import java.util.ArrayList;

public class PC {
	
	String name;
	int lives;
	int hp;
	double accuracy;
	int inRoom = 0;
	int wallet = 0;
	ArrayList<Item> item = new ArrayList<Item>();
	ArrayList<Item> wornItems = new ArrayList<Item>();
	
	public void dripcheck() {
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println();
		System.out.println("| " + Game_Objects.pc.name + " |");
		System.out.println();
		System.out.println("HP: " + hp);
		System.out.println("Accuracy: " + df.format(accuracy));
		System.out.println("Wallet: $" + Game_Objects.pc.wallet);
		System.out.println();
		System.out.println("You are carrying: ");
		for (int i = 0; i < Game_Objects.pc.item.size(); i++) {
			System.out.println(Game_Objects.pc.item.get(i).name);
		}
		System.out.println();
		System.out.println("Equipped: ");
		for (int i = 0; i < wornItems.size(); i++) {
			System.out.println(wornItems.get(i).name);
		}
	}
	
	public void remove(String[] x) {
		for (int i = 0; i < wornItems.size(); i++) {
			if (wornItems.size() == 0) {
				System.out.println();
				System.out.println("You have no weapon equipped");
			}
			if (wornItems.get(i).namefr.equalsIgnoreCase(x[1])) {
				System.out.println();
				System.out.println("You removed " + wornItems.get(i).name);
				Game_Objects.pc.accuracy = 1.2;
				item.add(wornItems.get(i));
				wornItems.remove(i);
			}
		}
	}

	public void equip(String[] x) {
		if (x.length == 1) {
			System.out.println();
			System.out.println("What do you want to equip?");
		}
		else {
			for(int i = 0; i < item.size(); i++) {
				if (x[1].equalsIgnoreCase(item.get(i).namefr)) {
					if(item.get(i).isNotWearable) {
						System.out.println();
						System.out.println("You cannot equip this item. sowwy");
					}
					else {
						if (wornItems.size() == 0) {
							Game_Objects.pc.accuracy = item.get(i).accuracy;
							wornItems.add(item.get(i));
							System.out.println();
							System.out.println("You equipped " + item.get(i).name);
							item.remove(i);
							break;
						}
						else {
							System.out.println();
							System.out.println("You switched " + wornItems.get(0).name + " for " + item.get(i).name);
							wornItems.add(item.get(i));
							item.add(wornItems.get(0));
							wornItems.remove(0);
							item.remove(i);
							Game_Objects.pc.accuracy = wornItems.get(0).accuracy;
							break;
						}
					}	
				}	
			}
		}	
	}	
}