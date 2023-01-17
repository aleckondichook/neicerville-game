public class Simple_RPG {
	
	static Game_Logic gl = new Game_Logic();

	public static void main(String[] args) {
		
		Game_Objects.initializeNPCArray();
		Game_Objects.initializeItemArray();
		Monster_Thread mt = new Monster_Thread(gl);
		mt.startMonsterThread();
		
		while(true) {
			game_loop();
		}
	}

	public static void game_loop() {
		gl.waitforCommand();
	}
}