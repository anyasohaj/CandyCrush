import javax.swing.JButton;

public class GameButton {

	private JButton actualButton;
	private int coordI;
	private int coordJ;
	private Candy actualCandy;
	
	public GameButton(JButton button, int i, int j, Candy candy){
		this.actualButton = button;
		this.coordI = i;
		this.coordJ = j;
		this.actualCandy = candy;
	}
	
	public JButton getActualButton(){
		return actualButton;
	}
	
	public int getCoordI(){
		return coordI;
	}
	
	public int getCoordJ(){
		return coordJ;
	}
}
