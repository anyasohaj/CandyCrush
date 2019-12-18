
public class Game {
	static GameTable gameTable;
	private Level level;
	boolean secondCounting = false;
	boolean isEdible = false;
	int deletableElementCounterX ;
	int deletableElementCounterY;
	int pointCounter;
	int stepCounter;
	
	
	public Game (GameTable table, int levelNumber){
		this.gameTable = table;
		this.pointCounter = 0;
		
		if (levelNumber > 0 && levelNumber <= 3){
			this.level = new Level(levelNumber);
		}else{
			this.level = new Level(1);
			System.out.println(" A begépelt szint helytelen, autmatikusan a legkönnyebb szinten fut a játék.");
		}
		this.stepCounter = level.getSteps();
	}
		
	public void start(Step step){
	
		deletableElementCounterX = 1;
		deletableElementCounterY = 1;
		
			replaceWith(step.firstButton, step.secondButton);
			
			if (isEdible(step.firstButton,step.secondButton)){
				deleteCandies();	
			}else{
				System.out.println(" Nem törölhetõ, úgyhogy visszacserélem.");
				replaceWith(step.firstButton, step.secondButton);
				
			}
	
		isEdible = false;
		
		secondCounting = false;
		System.out.println("Itt állt le a start()");
		Main.gui.handout(gameTable.getCurrentStateOfTable());
	}
	
	
	public void replaceWith(Button button1, Button button2){
			System.out.println(" Csere elõtt button1: " + button1.getButtonCoord().toString() + " button2: " + button2.getButtonCoord().toString());
		
			Coordinates coord1 = button1.getButtonCoord();
			Coordinates coord2 = button2.getButtonCoord();
			
			Button storedButton = gameTable.getCurrentStateOfTable().get(button1.getButtonCoord().x).get(button1.getButtonCoord().y);
			
			gameTable.getCurrentStateOfTable().get(button1.getButtonCoord().x).set(button1.getButtonCoord().y, button2);
			gameTable.getCurrentStateOfTable().get(button2.getButtonCoord().x).set(button2.getButtonCoord().y, storedButton);
			
			gameTable.getCurrentStateOfTable().get(coord1.x).get(coord1.y).setButtonCoord(coord1);
			gameTable.getCurrentStateOfTable().get(coord2.x).get(coord2.y).setButtonCoord(coord2);
			System.out.println(" Csere után button1: " + button1.getButtonCoord().toString() + " button2: " + button2.getButtonCoord().toString());
	}
	
	public boolean isEdible(Button firstPressed, Button secondPressed){
		
		System.out.println("isEdible() starts");
		
		int firstCandyColor = firstPressed.getButtonCandyColor();
		int secondCandyColor = secondPressed.getButtonCandyColor();
		int x1 = firstPressed.getButtonCoord().x;
		int y1 = firstPressed.getButtonCoord().y;
		int x2 = secondPressed.getButtonCoord().x;
		int y2 = secondPressed.getButtonCoord().y;
		
		System.out.println("FirstPressed: " + firstPressed.toString());		
		System.out.println("SecondPressed: " + secondPressed.toString());
		
		System.out.println("Az elsõ gomb koordinátái: (" + x1 + " ; " + y1 + " )");
		edibleElementCounter(x1, y1, firstCandyColor);
	
		secondCounting = false;
		deletableElementCounterX = 1;
		deletableElementCounterY = 1;
		System.out.println("A második gomb koordinátái: (" + x2 + " ; " + y2 + " )");
		edibleElementCounter(x2, y2, secondCandyColor);
		
		System.out.println("isEdible() ends");
		return isEdible;
	
	}

	void deleteCandies(){
		System.out.println("deleteCandies() starts");

		for (int i = 0; i <  gameTable.getTableSize(); i++){
			for (int j = gameTable.getTableSize() - 1; j>=0 ; j--){
				if (gameTable.getCurrentStateOfTable().get(i).get(j).getEdible()){
					switch (gameTable.getCurrentStateOfTable().get(i).get(j).getButtonCandyType()){
					case Candy.SIMPLE_CANDY: pointCounter += Candy.SIMPLE_CANDY_POINT;
						break;
					case Candy.VERTICAL_CANDY_EATER: pointCounter += Candy.VERTICAL_CANDY_POINT;
						break;
					case Candy.HORIZONTAL_CANDY_EATER: pointCounter += Candy.HORIZONTAL_CANDY_POINT;
						break;
					case Candy.EXTRA_CANDY_EATER: pointCounter += Candy.EXTRA_CANDY_POINT;
						break;
					default: System.out.println("Something went wrong. Here: deleteCandies()");
					}
					gameTable.getCurrentStateOfTable().get(i).remove(j);
					
					if (stepCounter <= 0 && pointCounter <= 800){
						System.out.println("Vége a játéknak, You've earned " + pointCounter + " points. You've lost.  New game.");
						System.exit(0);
					}else if (pointCounter >=800) {
						System.out.println(" YOU'VE WON THE GAME. You've reached the required points! You have: " + pointCounter + " points.");
						System.exit(0);
					}else{
					gameTable.getCurrentStateOfTable().get(i).add(new Button(new Coordinates( i, gameTable.getTableSize() - 1), gameTable.generateNew()));
		
					}
				}
			}
		}
		gameTable.patchStateOfButtons();
		System.out.println("deleteCandies() starts");
		//Main.controller(this);
		
		System.out.println("You have now " + pointCounter + " points. " + stepCounter + " steps remain.");
		
		
		
	}
	

	// Egy adott pozícióból kiindulva megvizsgálja hány azonos szín van a közelében
	void edibleElementCounter(int x, int y, int color){
		
		System.out.println("0. A vertikális számláló: " + deletableElementCounterX + " A koordináták: (" + x + " ; " + y + " )" );
		System.out.println("SecondCounting: " + secondCounting);

		if (x>0){
			int i = x - 1;  
			
			while  (( i>=0  && color == gameTable.getCurrentStateOfTable().get(i).get(y).getButtonCandyColor())  ){
					
					if (secondCounting ) {
						gameTable.getCurrentStateOfTable().get(i).get(y).setEdible(true);
					}else{
						deletableElementCounterX++;
						System.out.println("1. A vertikális számláló: " + deletableElementCounterX + " A koordináták: (" +i + " ; " + y + " )");
					}
					i--;
			}
			
		}
		//Megszámolom az elsõ gomtól lefele
		if (x < (gameTable.getTableSize() - 1)){
		
			int i = x + 1;
			while ( i < gameTable.getTableSize()  &&  (color == gameTable.getCurrentStateOfTable().get(i).get(y).getButtonCandyColor())){
					if (secondCounting ) {
						gameTable.getCurrentStateOfTable().get(i).get(y).setEdible(true);
						System.out.println("SecondCounting fut.");
					}else{
						deletableElementCounterX++;
						System.out.println("2. A vertikális számláló: " + deletableElementCounterX + " A koordináták: (" + i + " ; " + y + " )");
						
					}
					i++;
			}
			
			
		}
		
		if (y>0){
			int j = y - 1;
			while ( j>=0 &&  (color == gameTable.getCurrentStateOfTable().get(x).get(j).getButtonCandyColor()) ){
					if (secondCounting ) {
						gameTable.getCurrentStateOfTable().get(x).get(j).setEdible(true);
						System.out.println("SecondCounting fut.");
					}else{
						deletableElementCounterY++;
						
					}
					j--;
			}
		}
		
		if (y < (gameTable.getTableSize() - 1)){
			int j = y + 1;
			while (  j < gameTable.getTableSize()  && (color == gameTable.getCurrentStateOfTable().get(x).get(j).getButtonCandyColor())){
				 	if (secondCounting ) {
						gameTable.getCurrentStateOfTable().get(x).get(j).setEdible(true);
						System.out.println("SecondCounting fut.");
					}else{
						deletableElementCounterY++;

					}
				 	j++;
			}
		}
	
		
	if (((deletableElementCounterX >= 3) || (deletableElementCounterY >= 3)) && !secondCounting){
		System.out.println("A vertikális számláló: " + deletableElementCounterX );
		System.out.println("A horizontális számláló: " + deletableElementCounterY );
		gameTable.getCurrentStateOfTable().get(x).get(y).setEdible(true);
		isEdible = true;
		secondCounting = true;
		edibleElementCounter(x, y, color);
	}	
	}

	public static void horizontallyEating (Button button){
		int horizontalCoord = button.getButtonCoord().x;
		for (int i = 0; i < gameTable.getTableSize(); i++){
			if (!gameTable.getCurrentStateOfTable().get(horizontalCoord).get(i).getEdible()){
				gameTable.getCurrentStateOfTable().get(horizontalCoord).get(i).setEdible(true);
			}
		}
	}
	
	public static void verticallyEating (Button button){
		int verticalCoord = button.getButtonCoord().y;
		for (int i = 0; i < gameTable.getTableSize(); i++){
			if (!gameTable.getCurrentStateOfTable().get(i).get(verticalCoord).getEdible()){
				gameTable.getCurrentStateOfTable().get(i).get(verticalCoord).setEdible(true);
			}
		}
	}
	
	public static void nearbyEating(Button button){
		int horizontalCoord = button.getButtonCoord().x;
		int verticalCoord = button.getButtonCoord().y;
		int iStart, jStart, iEnd, jEnd;
		
		
		if (horizontalCoord==0 ){
			iStart = horizontalCoord;
		}else {
			iStart = horizontalCoord -1;
		}
		
		if (horizontalCoord == gameTable.getTableSize() - 1 ){
			iEnd = horizontalCoord;
		}else {
			iEnd = horizontalCoord + 1;
		}
		
		if (verticalCoord == 0){
			jStart = verticalCoord;
		}else {
			jStart = verticalCoord - 1;
		}
		
		if (verticalCoord == gameTable.getTableSize() - 1){
			jEnd = verticalCoord;
		}else {
			jEnd = verticalCoord + 1;
		}
		
		for (int i = iStart; i <= iEnd; i++)
			for (int j = jStart ; j <= jEnd; j++){
				if (gameTable.getCurrentStateOfTable().get(i).get(j) != button && !gameTable.getCurrentStateOfTable().get(i).get(j).getEdible()){
					gameTable.getCurrentStateOfTable().get(i).get(j).setEdible(true);
				}
			}
		
	}
	}
	


