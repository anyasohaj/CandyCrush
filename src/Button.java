import javax.swing.JButton;

public class Button extends JButton {
	
	private Coordinates buttonCoord;
	private Candy buttonCandy;
	private boolean edible;
	
	
	public Button(Coordinates coord, Candy candy){
		this.buttonCoord = coord;
		this.buttonCandy = candy;
		this.edible = false;

	}
	
	public Button(Candy candy, int i, int j){
		this.buttonCandy = candy;
		this.edible = false;
		this.buttonCoord = new Coordinates(i, j);
	}
	public void setButtonCoord(Coordinates newCoord){
		this.buttonCoord = newCoord;
	}

	public Coordinates getButtonCoord(){
		return buttonCoord;
	}
	
	public Candy getButtonCandy() {
		return buttonCandy;
	}
	
	public int getButtonCandyColor(){
		return buttonCandy.getCandyColor();
	}
	
	public int getButtonCandyType(){
		return buttonCandy.getCandyType();
	}
	
	public int getButtonPoint(){
		return buttonCandy.getCandyPoint();
	}

	public void setButtonCandy(Candy buttonCandy) {
		this.buttonCandy = buttonCandy;
	}

	public boolean getEdible() {
		return edible;
	}

	public void setEdible(boolean edible) {
		this.edible = edible;
		switch (buttonCandy.getCandyType()){
			case Candy.HORIZONTAL_CANDY_EATER: Game.horizontallyEating(this);
			break;
			case Candy.VERTICAL_CANDY_EATER: Game.verticallyEating(this);
			break;
			case Candy.EXTRA_CANDY_EATER: Game.nearbyEating(this);
			break;
			default: 
		}
	}
	
	public String toString(){
		return   buttonCandy.toTable() + " " + edible;
		
	}
	
	public boolean isNextTo(Button button){
		
		if (  ((this.buttonCoord.x == button.buttonCoord.x) && ((this.buttonCoord.y == button.buttonCoord.y - 1 ) || ( this.buttonCoord.y == button.buttonCoord.y + 1 )) ) || 
			(   ((this.buttonCoord.y == button.buttonCoord.y) && ((this.buttonCoord.x == button.buttonCoord.x - 1)) || (this.buttonCoord.x == button.buttonCoord.x +1 ))))
		{
			return true;
		}else 
			return false;
	
}
	
	
	
}
