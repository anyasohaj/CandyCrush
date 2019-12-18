
public class Step {
	Button firstButton;
	Button secondButton;
	Game game;
	
	public Step(Game game){
		this.game = game;
	}
	
	public void read(Button pressedButton){
		if (firstButton == null){
			firstButton = pressedButton;
			firstButton.getModel().setPressed(true);
					
		}else if (!firstButton.equals(pressedButton)) {
			if (firstButton.isNextTo(pressedButton)){
				System.out.println("FirstPressed isNextTo pressedButton");
				firstButton.getModel().setPressed(true);
				secondButton = pressedButton;
				System.out.println("FirstPressed: " + firstButton.toString() + "SecondPressed: " + secondButton.toString());
				secondButton.getModel().setPressed(true);
			}else{
				firstButton.getModel().setPressed(false);
				firstButton = pressedButton;
			}
		}
		
	if (secondButton != null){
		game.start(this);
	}
	}
}
