
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Gui {
	 static JFrame frame;
	//private ArrayList<ArrayList<Button>> gameButtons = new ArrayList<>();
	private GameTable table;
	private Game game;

	
	
	public Gui( Game game, ArrayList<ArrayList<Button>> currentStateOfTable){
		this.frame  = new JFrame("Candy Crush");
		this.table = game.gameTable;
		this.game = game;
		
		
		frame.setLayout(new GridLayout(table.getTableSize(), table.getTableSize()));
		frame.setResizable(false);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(1000,1000);
		frame.setLocationRelativeTo(null);
		
		handout(currentStateOfTable);
        frame.setVisible(true);
   
	}
	
	
	public  void actionHandler(Button pressedButton){
		System.out.println("\nActionHandler starts. PressedButton: " + pressedButton.getButtonCoord().toString());
		
		if (game.firstPressed == null){
			System.out.println("FirstPressed is null");
			game.firstPressed = pressedButton;
			game.firstPressed.getModel().setPressed(true);
					
		}else if (!game.firstPressed.equals(pressedButton)) {
			if (game.firstPressed.isNextTo(pressedButton)){
				System.out.println("FirstPressed isNextTo pressedButton");
				game.firstPressed.getModel().setPressed(true);
				game.secondPressed = pressedButton;
				System.out.println("FirstPressed: " + game.firstPressed.toString() + "SecondPressed: " + game.secondPressed.toString());
				game.secondPressed.getModel().setPressed(true);
			}else{
				game.firstPressed.getModel().setPressed(false);
				game.firstPressed = pressedButton;
			}
		}
		if(game.secondPressed != null){
			Main.controller(game);
		}
				
	
	}
	


	public void handout(ArrayList<ArrayList<Button>> newHandout){
		int buttonCounter = 0;
		Button button;
		
		for (int i = 0; i < table.getTableSize(); i++){
			
			for(int j = 0; j < table.getTableSize(); j++){
				button = table.getCurrentStateOfTable().get(i).get(j);
				
				try {
				    Image img = ImageIO.read(getClass().getResource(button.getButtonCandy().image + ".png"));
				    button.setIcon(new ImageIcon(img));
				    
				  } catch (Exception ex) {
				    System.out.println("Nem találom a képet!");
				  }
				button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					Button myButton = (Button)e.getSource();
					actionHandler(myButton);
					
				}
		    	
				});
				button.setName(String.valueOf(buttonCounter));
				buttonCounter++;
				frame.add(button);		
			}
		
		}
	}
	
	
	
	
}


