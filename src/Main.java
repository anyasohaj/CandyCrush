
public class Main {
	static int tableSize;
	static int level;
	static Gui gui;

	public static void main(String[] args) {
	
		tableSize = 10;
		level = 1;
		
		GameTable currentTable = new GameTable(tableSize);
		Game game = new Game(currentTable, level);
		gui = new Gui(game, currentTable.getCurrentStateOfTable());

		
	}
	
}
