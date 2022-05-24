/*
 Faraj Al-Hussaini
 Ms.Strelkovska
 ICS4U1
 2021-06-22
 assignment - Our Game
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Bugs{	
	// variables
	int x;
	int y;
	int speed;
	private BufferedImage spriteImg;
	private BufferedImage right[];
	private BufferedImage bugImg=null;
	private int cnt=0;
	Color myColour = new Color(255, 255, 255, 0);
	public Bugs(int x, int y) { // constuctor for default bugs (fireflys), all the other bug classes extend from this one
		this.x = x;
		this.y = y;
		speed = 3;

		try{
			spriteImg = ImageIO.read(new File("bugs.png")); 
		}catch(Exception e){
			System.out.print("Error" + e);
		}  		

		right= new BufferedImage[6];

		right[0]= spriteImg.getSubimage(220,30,33,45);
		right[1]= spriteImg.getSubimage(220,30,33,45);
		right[2]= spriteImg.getSubimage(267,30,32,47);
		right[3]= spriteImg.getSubimage(267,30,32,47);
		right[4]= spriteImg.getSubimage(305,30,36,51);	
		right[5]= spriteImg.getSubimage(305,30,36,51);
	}
	public Rectangle bounds() {
		return (new Rectangle(x,y,30,30));

	}
	public void move() { // moves the bug
		x+=speed;		
	}
	public void moveDown() { // speed at which it moves down
		y +=10;		
	}
	// getters and setters
	public int getScore() { // gets the score for the current bug
		return 5;
	}	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}	
	public BufferedImage getNextRight(){
		cnt=(cnt+1)%right.length;
		return right[cnt];
	}	

	public void draw(Graphics g) { // draws bug
		g.setColor(myColour);
		g.fillRect(x, y, 30, 30);
		bugImg = this.getNextRight();
		g.drawImage(bugImg,x,y,null); 
	}
}
