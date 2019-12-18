
public class Level {
	
	private int steps;
	int EASY_LEVEL = 40;
	int MEDIUM_LEVEL = 30;
	int HARD_LEVEL = 20;
	
	public Level(int level){
	
		switch (level){
		case 1: this.steps = EASY_LEVEL;
				break;
		case 2: this.steps = MEDIUM_LEVEL;
				break;
		case 3: this.steps = HARD_LEVEL;
				break;
		default: System.out.println("Error with level number");
				
		}
	}
	
	public int getSteps(){
		return steps;
	}
}
