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


public class Frog{	
	//variables
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	int frogX;
	int frogY;
	int tongueWidth;
	int tongueHeight;	
	int yTongue;
	int xTongue;
	private BufferedImage spriteImg, basicImg;
	Color myColour = new Color(255, 255, 255, 0); // to make the hitbox invisible

	public Frog(int x,int y,int height, int width) { //constructor creating the frog

		this.xTongue = x;
		this.yTongue = y;
		this.tongueHeight = height;
		this.tongueWidth = width;
		frogX = 275;
		frogY = 510;


		try{
			spriteImg = ImageIO.read(new File("frog.png")); 
		}catch(Exception e){
			System.out.print("Error" + e);
		}  		

		basicImg=spriteImg.getSubimage(163,279,116,96);
	}

	public Rectangle bounds() { // creates bounds for the tip of the tongue used in collisions method in Game class
		return (new Rectangle(xTongue,yTongue,40,40));		
	}		
	// getters and setters
	public int getYTongue() {
		return this.yTongue;
	}
	public int getXTongue() {
		return this.xTongue;
	}
	public void setXTongue(int x) {
		this.xTongue = x;
	}
	public void setFrogX(int x) {
		this.frogX = x;
	}
	public int getFrogX() {
		return this.frogX;
	}		
	public void moveTongueUp() { // moves tongue up
		tongueHeight +=10;
		yTongue-=10;
	}
	public void moveTongueDown() { // moves tongue down
		tongueHeight -=10;
		yTongue+=10;
	}

	public void setBounds(int x,int y,int height, int width) { // sets new Bounds for the tongue
		this.xTongue = x;
		this.yTongue = y;
		this.tongueHeight = height;
		this.tongueWidth = width;
	}	
	public BufferedImage getBasicImg(){
		return basicImg;
	}

	public void draw(Graphics g) {
		g.setColor(myColour);
		g.fillRect(frogX, frogY, WIDTH, HEIGHT);		
		g.setColor(Color.red);
		g.fillRect(xTongue, yTongue+30, tongueWidth, tongueHeight);
		g.drawImage(basicImg,frogX-34,frogY-20,null);

	}		
}
