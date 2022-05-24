/*
 Faraj Al-Hussaini
 Ms.Strelkovska
 ICS4U1
 2021-06-22
 assignment - Our Game
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;	
	// variables
	JFrame s;
	JFrame over;
	Game game;
	Gameover gameOver;
	int cnt = 0;
	private Timer myT;	
	int time=60;

	ImageIcon titleIcon;
	ImageIcon frogIcon;
	JLabel frog;
	JLabel title;
	JLabel timeLabel;
	JPanel panel;
	JButton newGame;
	JButton instructions;
	JButton exit;

	public Menu(){ // constructor
		newGame = new JButton("Start Game!");
		instructions = new JButton("Instructions");		
		exit = new JButton("Quit");

		newGame.setFocusable(false);
		newGame.addActionListener(this);
		instructions.setFocusable(false);
		instructions.addActionListener(this);		
		exit.setFocusable(false);
		exit.addActionListener(this);



		panel = new JPanel(new GridBagLayout()); // I used grid bag layout because it looks the cleanest to me and is easy to use
		GridBagConstraints c = new GridBagConstraints();

		titleIcon = new ImageIcon("title.png");
		title = new JLabel(titleIcon);
		frogIcon = new ImageIcon("frogcatching.png");
		frog = new JLabel(frogIcon);		

		c.insets = new Insets(10,10,10,10); // sets spacing between components

		c.gridx=0;
		c.gridy=1; // sets at the top of the grid
		panel.add(title,c);

		newGame.setFont(new Font("Serif", Font.BOLD, 20));
		c.gridx=0;
		c.gridy=2;
		panel.add(newGame,c);

		instructions.setFont(new Font("Serif", Font.BOLD, 20));
		c.gridx=0;
		c.gridy=3;
		panel.add(instructions,c);

		exit.setFont(new Font("Serif", Font.BOLD, 20));
		c.gridx=0;
		c.gridy=4;
		panel.add(exit,c);

		c.gridx=0;
		c.gridy=5; // sets at the bottom of the grid
		panel.add(frog,c);

		panel.setBackground(Color.black);						

		this.add(panel, BorderLayout.CENTER);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  		
		this.setVisible(true);    
		this.setSize(600, 600); 		
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		myT = new Timer(1,this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {		

		if(e.getSource()==newGame) { // when the new game button is clicked
			myT.start(); // starts timer
			this.dispose();	// disposes of JFrame	
			s = new JFrame(); // creates new JFrame
			game = new Game();			 									
			s.add(game);	// adds game instance to JFrame			
			s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			s.setVisible(true);    
			s.setSize(600, 600); 		
			s.setLocationRelativeTo(null);
			s.setResizable(false);
		}
		else if(e.getSource()==instructions) {	// when instructions button is clicked it displays what the player needs to know before playing		
			JOptionPane.showMessageDialog(null, "CONTROLS\n"
					+"Use left and right arrow keys to move frog!\n"
					+"Use the up arrow to shoot your tongue out!\n"
					+"RULES\n"
					+"Shoot your tongue out to catch as many bugs as possible within time limit!\n"
					+"The firefly is the most common bug, try your best to catch them all!\n"
					+"BEWARE! Catching the bees reduces your score!\n"
					+"Try to catch the butterflies if you can! They give the most score!\n"
					+"If you catch the UFO your time increases!\n"
					+"HAVE FUN!!!\n", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource()==exit) { // when exit button is clicked game closes
			System.exit(0);			
		}		
		if(game != null && game.getTime() == 0) { // when the game time is 0
			s.dispose();	// closes game frame		
			new Gameover();	// opens instance of Gameover class	    		   	    
			myT.stop();		    
		}
		if(game != null && game.isMenu() == true) { // when the back button is clicked in the Game class it disposes the game JFrame
			s.dispose();
			myT.stop();	
		}




	}
}
