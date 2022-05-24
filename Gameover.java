/*
 Faraj Al-Hussaini
 Ms.Strelkovska
 ICS4U1
 2021-06-22
 assignment - Our Game
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gameover extends JFrame implements ActionListener{	
	private static final long serialVersionUID = 1L;
	//variables
	static int s;	
	JPanel panel; 
	ImageIcon frogIcon;
	JLabel frog;
	JLabel scoreLabel;
	JLabel oldScoreLabel;
	JButton menu;	
	JButton exit;
	public Gameover(){ //constructor
		panel = new JPanel(new GridBagLayout()); 
		scoreLabel = new JLabel("Score "+ s);
		menu = new JButton("Main Menu");	
		exit = new JButton("Quit");
		frogIcon = new ImageIcon("frogthumb.png");
		frog = new JLabel(frogIcon);

		menu.setFocusable(false);
		menu.addActionListener(this);		
		exit.setFocusable(false);
		exit.addActionListener(this);

		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(10,10,10,10);		

		scoreLabel.setFont(new Font("Serif", Font.BOLD, 30));
		scoreLabel.setForeground(Color.green);
		c.gridx=0;
		c.gridy=1;
		panel.add(scoreLabel,c);

		menu.setFont(new Font("Serif", Font.BOLD, 20));
		c.gridx=0;
		c.gridy=2;
		panel.add(menu,c);

		exit.setFont(new Font("Serif", Font.BOLD, 20));
		c.gridx=0;
		c.gridy=3;
		panel.add(exit,c);

		c.gridx=0;
		c.gridy=4;
		panel.add(frog,c);

		panel.setBackground(Color.black);	






		this.add(panel, BorderLayout.CENTER);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setVisible(true);    
		this.setSize(600, 600); 		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu) { // new instance of Menu class created when main menu button clicked
			this.dispose();
			new Menu();
		}

		else if(e.getSource()==exit) {
			System.exit(0);

		}

	}
}
