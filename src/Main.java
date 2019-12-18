import java.awt.event.WindowEvent;
import java.util.Scanner;

public class Main {
	static int tableSize;
	static int level;

	public static void main(String[] args) {
		
	/*	Scanner scanner = new Scanner(System.in);
		System.out.println("Mekkora legyen a tábla?");
		tableSize = scanner.nextInt();
		System.out.println("Milyen szintû legyen?");
		level = scanner.nextInt(); */
		tableSize = 10;
		level = 1;
		
		GameTable currentTable = new GameTable(tableSize);
		
		
		// This method is removable when Gui works
		createTable(currentTable); 
		
		Game game = new Game(currentTable, level);
		Gui gui = new Gui(game, currentTable.getCurrentStateOfTable());
		
		
	}
	
	public static void controller(Game game){
		game.start();
	}
	
	//This is an output method, it will be replaced by GUI
	public static void createTable(GameTable table){
		int currentTableSize = table.getTableSize();
		for (int i= 0; i< currentTableSize; i++){
			System.out.println("");
			for (int j=0; j<currentTableSize; j++){
				System.out.print(table.getCurrentStateOfTable().get(i).get(j).toString() + " ");
			}
		}
	}
	

}
