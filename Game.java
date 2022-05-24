/*
 Faraj Al-Hussaini
 Ms.Strelkovska
 ICS4U1
 2021-06-22
 assignment - Our Game
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.event.*;


public class Game extends JPanel implements ActionListener, KeyListener{


	private static final long serialVersionUID = 1L;
	//variables
	ArrayList<Bugs> allBugs= new ArrayList<Bugs>();
	JFrame s;
	private Timer myT;	
	JPanel p1;
	Frog frog;
	Bugs bugs;
	Bee bee;
	Ufo ufo;
	Butterflyfast fastButterfly;
	Butterfly Butterfly;	
	ImageIcon forest;
	Image forest2;
	JLabel scoreLabel;
	JLabel timeLabel;
	JLabel background;
	JButton start;
	JButton back;

	int timeCnt;
	int score = 0;
	int time;	

	int cnt = 0;
	int tongueCnt = 0;	
	int tongueUpCnt = 0;
	int spawnCnt = 0;
	int butterflyCnt = 0;
	int ufoCnt = 0;
	int fastButterflyCnt = 0;

	boolean menu;
	boolean tongueUp = false;
	boolean up = false;
	boolean down = false;	
	Game () {	// constructor
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());

		time = 60;
		timeCnt = 0;
		menu = false;		

		for(int i = 0; i < 3; i++) {	// spawns the initial amount of bugs		
			int randY = (int) (Math.random()*250)+100;
			int randX = (int)(Math.random()*640)-650;
			bugs = new Bugs(randX, randY);
			allBugs.add(bugs);
			randY = (int) (Math.random()*250)+100;
			randX = (int)(Math.random()*640)-650;
			bee = new Bee(randX, randY);
			allBugs.add(bee);		
		}
		Collections.shuffle(allBugs);

		scoreLabel = new JLabel("Score "+ score);
		timeLabel = new JLabel("Time Left: "+ time);
		start = new JButton("Start");
		back = new JButton("Main Menu");

		forest = new ImageIcon("forest.gif");
		forest2 = forest.getImage();

		p1.add(start);
		p1.add(back);
		p1.add(timeLabel);
		p1.add(scoreLabel);

		start.addActionListener(this);
		back.addActionListener(this);
		start.addKeyListener(this);
		back.addKeyListener(this);

		this.add(p1, BorderLayout.NORTH);		
		frog = new Frog(280,440,40,40);		

		setFocusable(true);		
		addKeyListener(this);

		myT= new Timer(15,this);
	}
	public int getTime() {
		return time;
	}	
	public boolean isMenu() {
		return menu;
	}

	public void paintComponent(Graphics g){ // draws the background and all the bugs
		super.paintComponent(g);			
		g.drawImage(forest2, 0, 0, this);		
		frog.draw(g);		
		for(int i=0; i<allBugs.size(); i++) {
			allBugs.get(i).draw(g);
		}	
	}
	public void checkCollision() { // checks the collision between the tip of the tongue and the bugs
		Rectangle rect = frog.bounds();
		for(int i=0; i<allBugs.size(); i++) {
			Rectangle rect2 = allBugs.get(i).bounds();			
			if(rect.intersects(rect2)) { // if the tongue intersects with any bug
				allBugs.get(i).setSpeed(0); // sets the bugs speed to 0
				allBugs.get(i).moveDown(); // moves the bug down
			}
		}				
	}
	public void addBug() { //method to add new bugs

		int randY = (int)(Math.random()*250)+100; // randomized height for bugs
		int randX = (int)(Math.random()*160)-180; // randomized distance the bugs spawn at on the left of the screen

		if(spawnCnt == 1 || spawnCnt == 2) { // spawns two fireflys for every 1 bee
			bugs = new Bugs(randX, randY);
			allBugs.add(bugs);
		}
		if(spawnCnt == 3) {
			bee = new Bee(randX, randY);
			allBugs.add(bee);
			spawnCnt = 0;
		}
		if(fastButterflyCnt >= 10) { // evey 10 seconds a fast butterfly flys across the screen
			fastButterfly = new Butterflyfast(randX, randY);
			allBugs.add(fastButterfly);
			fastButterflyCnt = 0;
		}
		if(butterflyCnt >= 15) { // every 15 seconds a butterfly that bounces around the screen spawns
			Butterfly = new Butterfly(randX, randY);
			allBugs.add(Butterfly);
			butterflyCnt = 0;
		}
		if(ufoCnt >= 25) { // every 25 seconds a UFO spawns in and only flys through the top of the screen
			ufo = new Ufo(-50, 50);
			allBugs.add(ufo);
			ufoCnt = 0;
		}

	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_UP && tongueUpCnt >= 2) {	// if up is clicked the tongue goes up 				
			tongueUp = true; // negates movement for other keys
			tongueCnt = 1;	// sets to 1 so the tongue moving if statement below starts
			tongueUpCnt = 0; // sets to 0 so person can not keep clicking up key forever
		}	
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) { // moves frog left when left button clicked
			if(tongueUp == false && frog.getFrogX()-5 > 0) {
				frog.setFrogX(frog.getFrogX()-10);
				frog.setXTongue(frog.getXTongue()-10);
				repaint();
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) { // moves frog right when right button clicked
			if(tongueUp == false && frog.getFrogX() < 525) { 
				frog.setFrogX(frog.getFrogX()+10);
				frog.setXTongue(frog.getXTongue()+10);
				repaint();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) { // when the start button is clicked
			time = 60; // sets the time to 60
			timeLabel.setText("Time Left: "+ time);
			myT.start(); // starts timer for game
			start.setEnabled(false); // makes start button unclickable
			tongueUpCnt = 5; // sets the tongueUpCnt to something higher than 2
			score = 0; // resets the score to 0
			scoreLabel.setText("Score "+ score);
			spawnCnt = 0; // used for the bees and fireflys in addBug method
		}
		else {
			checkCollision(); // keeps checking if the tongue touches any bug
			for (int i = 0; i < allBugs.size(); i++) {		//for loop for the bug mechanics			
				allBugs.get(i).move();	// moves all the bugs
				if(allBugs.get(i).getY() >= 400) { //if the bugs reach around the bottom of the screen
					score += allBugs.get(i).getScore(); // get the score of the bug
					scoreLabel.setText("Score "+ score);	
					if(Ufo.specialTime == true) { // to add time whenever a UFO goes down
						time += 10; 
						Ufo.specialTime = false;
					}
					allBugs.remove(i); // remove that bug
					this.addBug(); // and add a new one
					spawnCnt++;
				}								  			
			}
			for (int i = 0; i < allBugs.size(); i++) {					
				if(allBugs.get(i).getX() >= 600) { // if the bugs reach the very right of the screen 						
					allBugs.remove(i); // the bug gets removed
					this.addBug();		// a new bug gets added				
					spawnCnt++;					
				}																				
			}								  									
			if(tongueCnt > 0) { // if statement for the tongue moving up only works when the up arrow is clicked
				tongueCnt++;
				if(tongueCnt<50) { // tongue goes up until tongueCnt reaches 50
					frog.moveTongueUp();		 									
				}
				else if(tongueCnt<100) { // tongue goes down until tongueCnt reaches 100
					frog.moveTongueDown();						
				}
				if(frog.getYTongue() >= 440) { // if the y value for the tongue reaches below y 440
					tongueCnt = 0; // resets the tongueCnt so tongue stops moving
					tongueUp = false; // sets to false so frog can move left and right
				}

			}

			timeCnt++;
			if (timeCnt >= 67) { // made timer run 15ms so timeCnt 67 would be one second, I found this to be the smoothest the game runs at
				time--;		// reduces time every second	
				timeLabel.setText("Time Left: "+ time);								
				tongueUpCnt++;
				butterflyCnt++;
				ufoCnt++;
				fastButterflyCnt++;
				timeCnt=0; 
			}	
			if(time == 0) { // when the timer reaches 0
				myT.stop(); // stop this game timer as it can cause problems if not
				Gameover.s = score; // set the score for the gameover screen
			}
			if(e.getSource() == back) { // if main menu button clicked
				myT.stop(); // stop this game timer as it can cause problems if not
				score = 0; // reset the score
				new Menu();	// create new menu instance
				menu = true;	// boolean used in Menu class			
			}	
			repaint();
		}
	}
}

