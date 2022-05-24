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

public class Butterfly extends Bugs{	
	// variables
	int x;
	int y;
	int speed;	
	private BufferedImage spriteImg;
	private BufferedImage right[];
	private BufferedImage bugImg=null;
	boolean bool = false;
	private int cnt = 0;
	int count = 0;
	Color myColour = new Color(255, 255, 255, 0);
	public Butterfly(int x, int y) { // constructor
		super(x, y);
		this.x = x;
		this.y = y;
		speed = 5;


		try{
			spriteImg = ImageIO.read(new File("bugs.png")); 
		}catch(Exception e){
			System.out.print("Error" + e);
		}  		

		right= new BufferedImage[6];

		right[0]= spriteImg.getSubimage(9,110,66,53);
		right[1]= spriteImg.getSubimage(9,110,66,53);
		right[2]= spriteImg.getSubimage(98,110,62,58);
		right[3]= spriteImg.getSubimage(98,110,62,58);
		right[4]= spriteImg.getSubimage(179,110,61,61);
		right[5]= spriteImg.getSubimage(179,110,61,61);
	}
	@Override
	public Rectangle bounds() {
		return (new Rectangle(x,y,45,45));

	}
	@Override
	public void move() {	// makes it bounce up and down across the screen			
		x+=speed;
		if(bool == false) {
			y+=speed-1;
		}
		else {
			y-=speed-1;
		}
		if(y >= 300){
			bool = true;
		}
		if(y <= 100) {
			bool = false;
		}
	}
	@Override
	public void moveDown() {
		y +=10;		
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
		g.fillRect(x, y, 45, 45);
		bugImg = this.getNextRight();
		g.drawImage(bugImg,x-5,y-10,null);
	}
}
