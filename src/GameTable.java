
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameTable{
	private int tableSize;
	ArrayList<ArrayList<Candy>> field = new ArrayList<>(tableSize);
	private ArrayList<ArrayList<Button>> currentStateOfTable;
	
	
	public GameTable(int tableSize) {
		this.tableSize = tableSize;
		this.currentStateOfTable = createNewTable(this.tableSize);
		
		
		}
	
	public int getTableSize(){
		return tableSize;
	}
	
	public ArrayList<ArrayList<Button>> getCurrentStateOfTable(){
		return currentStateOfTable;
	}
	
	public void setCurrentStateOfTable(ArrayList<ArrayList<Button>> changedCurrentStateOfTable){
		this.currentStateOfTable = changedCurrentStateOfTable;
	}
	
	public ArrayList<ArrayList<Candy>> replaceButton( int button1, int button2){
		
		ArrayList<ArrayList<Candy>> tempField = field;
		int xButton1 = button1/tempField.size();
		int yButton1 = button1%tempField.size();
		int xButton2 = button2/tempField.size();
		int yButton2 = button2%tempField.size();
		
		
		// Replace buttons
		Candy storeCandy = tempField.get(xButton1).get(yButton1);
		System.out.println("Az aktu�lis Candy-nk: " + storeCandy.toString());
		// the buttons will be replaced ONLY virtually in @tempield
		tempField.get(xButton1).set(yButton1, tempField.get(xButton2).get(yButton2));
		tempField.get(xButton2).set(yButton2, storeCandy);
		
		return tempField;
	}
	
	// A val�sz�n�s�g alapj�n �j cukorka gener�l�sa
	public Candy generateNew(){
		int color;
		double randomColor = Math.random();
		double randomType = Math.random();

			if (randomColor < Candy.BLUE_PROBABILITY){
				color = Candy.BLUE;
			}else if (randomColor < Candy.GREEN_PROBABILITY){
				color = Candy.GREEN;
			}else {
				color = Candy.RED;
			}
			
		if (randomType < Candy.SIMPLE_CANDY_PROBABILITY){
			switch (color){
			case Candy.BLUE: return new Candy(Candy.BLUE, Candy.SIMPLE_CANDY);
			case Candy.GREEN: return new Candy(Candy.GREEN, Candy.SIMPLE_CANDY);
			case Candy.RED: return new Candy(Candy.RED, Candy.SIMPLE_CANDY);
			}
		}else if (randomType < Candy.HORIZONTAL_CANDY_PROBABILITY){
			switch (color){
			case Candy.BLUE: return new Candy(Candy.BLUE, Candy.HORIZONTAL_CANDY_EATER);
			case Candy.GREEN: return new Candy(Candy.GREEN, Candy.HORIZONTAL_CANDY_EATER);
			case Candy.RED: return new Candy(Candy.RED, Candy.HORIZONTAL_CANDY_EATER);
			}
		}else if (randomType < Candy.VERTICAL_CANDY_PROBABILITY){
			switch (color){
			case Candy.BLUE: return new Candy(Candy.BLUE, Candy.VERTICAL_CANDY_EATER);
			case Candy.GREEN: return new Candy(Candy.GREEN, Candy.VERTICAL_CANDY_EATER);
			case Candy.RED: return new Candy(Candy.RED, Candy.VERTICAL_CANDY_EATER);
			}
		}else{
			switch (color){
			case Candy.BLUE: return new Candy(Candy.BLUE, Candy.EXTRA_CANDY_EATER);
			case Candy.GREEN: return new Candy(Candy.GREEN, Candy.EXTRA_CANDY_EATER);
			case Candy.RED: return new Candy(Candy.RED, Candy.EXTRA_CANDY_EATER);
			}
		}
		return null;
	}
	
	// Ellen�rzi a felette �s a t�le 'balra' l�v� cukrokat, hogy ne legyen h�rom egym�s mellett. Ha el�fordul, �jat gener�l, m�g megfelel� nem lesz.
	public Button insertNewButton(ArrayList<ArrayList<Button>> currentTable, ArrayList<Button> helperRow, Coordinates coord){
		Candy newCandy = generateNew();
		
		int i = coord.x;
		int j = coord.y;
		 	if (i <= 1 && j <= 1){
		 		return new Button(coord, newCandy);
		 	}else
		 		if (j > 1 && i <= 1 ){
				while (helperRow.get(j-1).getButtonCandyColor() == newCandy.getCandyColor() && helperRow.get(j-2).getButtonCandyColor() == newCandy.getCandyColor()){
					newCandy = generateNew();
				}
				return new Button(coord, newCandy);
			} else
				if (i > 1 && j <= 1){
					
				while (currentTable.get(i-1).get(j).getButtonCandyColor() == newCandy.getCandyColor() && currentTable.get(i-2).get(j).getButtonCandyColor() == newCandy.getCandyColor() ){
					newCandy = generateNew();
				}
				return new Button(coord, newCandy);
			
				}else  {
					while ((helperRow.get(j-1).getButtonCandyColor() == newCandy.getCandyColor() && helperRow.get(j-2).getButtonCandyColor() == newCandy.getCandyColor()) || ((currentTable.get(i-1).get(j).getButtonCandyColor() == newCandy.getCandyColor()) && (currentTable.get(i-2).get(j).getButtonCandyColor() == newCandy.getCandyColor() ))){
						newCandy = generateNew();
					}
					return new Button(coord, newCandy);
				}
	}
	// Elk�sz�ti (felt�lti) a t�bl�t, (az oszt�ly @currentStateOfTable v�ltoz�j�t) Buttonokkal
	public ArrayList<ArrayList<Button>>  createNewTable(int tableSize){
		ArrayList<ArrayList<Button>> tableUnderConstruction = new ArrayList<>();
		ArrayList<Button> helperRow ;
		
		
		for (int i = 0; i < tableSize; i++){
			helperRow = new ArrayList<>();
			for (int j = 0; j < tableSize ; j++){
				helperRow.add( insertNewButton(tableUnderConstruction, helperRow, new Coordinates(i, j)));
			}
			tableUnderConstruction.add(helperRow);	
		}
		
		return tableUnderConstruction;
	}

	// Minden Buttonnak be�ll�tja az adott helyen l�v�t a gomb saj�t koordin�t�j�nak.
	void patchStateOfButtons(){
		for (int i= 0; i < tableSize; i++){
			for (int j = 0; j < tableSize; j++){
				currentStateOfTable.get(i).get(j).setButtonCoord(new Coordinates(i, j)); 
			}
		}
	}



}
