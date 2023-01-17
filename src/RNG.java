import java.util.Random;

public class RNG {

	public int returnRandom(int x) {
		Random rand = new Random();
		int y = rand.nextInt(x);
		return y;
	}
	
}