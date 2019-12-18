import java.util.concurrent.atomic.AtomicInteger;

public class Candy {
	public static final int COLOR = 0;
	public static final int TYPE = 1;
	
	public static final int SIMPLE_CANDY = 0;
	public static final int HORIZONTAL_CANDY_EATER = 1;
	public static final int VERTICAL_CANDY_EATER = 2;
	public static final int EXTRA_CANDY_EATER = 3;
	

	public static final int BLUE = 0;
	public static final int GREEN = 1;
	public static final int RED = 2;
	
	public static final int NUMBER_OF_COLORS = 3;
	public static final int NUMBER_OF_TYPES = 4;
	
	// probabilities
	
	public static final double COLOR_PROBABILITY = 0.33;
	public static final double BLUE_PROBABILITY = 0.33;
	public static final double GREEN_PROBABILITY = 0.66;
	public static final double SIMPLE_CANDY_PROBABILITY = 0.85;
	public static final double HORIZONTAL_CANDY_PROBABILITY = 0.90;
	public static final double VERTICAL_CANDY_PROBABILITY = 0.95;
	//public static final double EXTRA_CANDY_PROBABILITY = 0.10;
	
	//Points
	public static final int SIMPLE_CANDY_POINT = 10;
	public static final int VERTICAL_CANDY_POINT = 50;
	public static final int HORIZONTAL_CANDY_POINT = 50;
	public static final int EXTRA_CANDY_POINT = 60;

	static AtomicInteger nextId = new AtomicInteger();
	int candyID;

	private int candyType;
	private int candyColor;
	private int point;
	public String image;
	
	public Candy(int candyColor, int candyType ){
		this.candyType = candyType;
		this.candyColor = candyColor;
		this.candyID = nextId.incrementAndGet();
		
		
		switch (candyType){
		case Candy.SIMPLE_CANDY: this.image = "simple";
								 this.point = SIMPLE_CANDY_POINT;
			break;
		case Candy.VERTICAL_CANDY_EATER: this.image = "vertical";
										 this.point = VERTICAL_CANDY_POINT;
			break;
		case Candy.HORIZONTAL_CANDY_EATER: this.image = "horizontal";
										   this.point = HORIZONTAL_CANDY_POINT;
			break;
		case Candy.EXTRA_CANDY_EATER: this.image = "nearby";
									   this.point = EXTRA_CANDY_POINT;
		}
		
		switch (candyColor){
		case Candy.BLUE: this.image+="Blue";
			break;
		case Candy.GREEN: this.image+="Green";
			break;
		case Candy.RED: this.image+="Red";
		}
		
	}
	
	public int getCandyType(){
		return candyType;
	}
	
	public int getCandyColor(){
		return candyColor;
	}
	
	public int getCandyPoint(){
		return this.point;
	}
	
	
	@Override
	public String toString() {
		String candyString = null;
		
		switch (candyColor){
		case BLUE: candyString = "KÉK";
				   break;
		case GREEN: candyString = "ZÖLD";
				   break;
		case RED: candyString = "PIROS";
		}
		
		switch (candyType){
		case SIMPLE_CANDY: candyString +=" EGYSZERÛ CUKOR";
						   break;
		case HORIZONTAL_CANDY_EATER: candyString +=  " VÍZSZINTES CUKORFALÓ";
						   break;
		case VERTICAL_CANDY_EATER: candyString += " FÜGGÕLEGES CUKORFALÓ";
						   break;
		case EXTRA_CANDY_EATER: candyString += " SZOMSZÉDOS CUKORFALÓ";
		
		}
	
		return candyString;
	}
	
	public String toTable(){
String candyString = null;
		
		switch (candyColor){
		case BLUE: candyString = "K";
				   break;
		case GREEN: candyString = "Z";
				   break;
		case RED: candyString = "P";
		}
		
		switch (candyType){
		case SIMPLE_CANDY: candyString +="S";
						   break;
		case HORIZONTAL_CANDY_EATER: candyString +=  "H";
						   break;
		case VERTICAL_CANDY_EATER: candyString += "V";
						   break;
		case EXTRA_CANDY_EATER: candyString += "E";
		
		}
	
		return candyString;
	}
	
}
 