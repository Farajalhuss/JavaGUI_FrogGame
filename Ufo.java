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

public class Ufo extends Bugs{	// it isn't a bug but it has the same properties so extending is better here
	//variable
	int x;
	int y;
	int speed;		
	private BufferedImage spriteImg;
	private BufferedImage right[];
	private BufferedImage bugImg=null;
	static boolean specialTime = false;
	private int cnt = 0;
	int count = 0;
	Color myColour = new Color(255, 255, 255, 0);
	public Ufo(int x, int y) { // constructor
		super(x, y);
		this.x = x;
		this.y = y;
		speed = 6;


		try{
			spriteImg = ImageIO.read(new File("Ufo.png")); 
		}catch(Exception e){
			System.out.print("Error" + e);
		}  		

		right= new BufferedImage[6];

		right[0]= spriteImg.getSubimage(1,1,63,45);
		right[1]= spriteImg.getSubimage(1,1,63,45);
		right[2]= spriteImg.getSubimage(63,1,64,44);
		right[3]= spriteImg.getSubimage(63,1,64,44);
		right[4]= spriteImg.getSubimage(126,1,64,44);
		right[5]= spriteImg.getSubimage(126,1,64,44);
	}
	@Override
	public Rectangle bounds() {
		return (new Rectangle(x,y,25,25));
	}
	@Override
	public void move() {				
		x+=speed;		
	}
	@Override
	public void moveDown() { 
		y +=10;	
		specialTime = true; // used in the Game class to add more time
	}			
	@Override
	public int getScore() {
		return 10;
	}
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public int getY() {
		return y;
	}
	@Override
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public void setX(int x) {
		this.x = x;
	}
	@Override
	public BufferedImage getNextRight(){
		cnt=(cnt+1)%right.length;
		return right[cnt];
	}	
	@Override
	public void draw(Graphics g) {
		g.setColor(myColour);
		g.fillRect(x, y, 45, 25);
		bugImg = this.getNextRight();
		g.drawImage(bugImg,x-10,y,null);
	}
}
