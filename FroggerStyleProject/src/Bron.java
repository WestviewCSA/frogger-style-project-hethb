import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Bron {
	private Image forward;
	private AffineTransform tx;
	
	int dir = 0; 
	int width, height;

	int x, y;
	int vx, vy;
	double scaleWidth = 1.2;
	double scaleHeight = 1.2;
	public Bron() {
		forward = getImage("/imgs/"+"bronnn.png"); //load the image for Tree
		width = 60;
		height = 60;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	/*
	 * collision detection with main character class
	 */
	
	public boolean collided(Camera character) {
		//represent each object as a rect 
		//check if they are intersecting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight()
				);
		Rectangle thisObject = new Rectangle(x, y, width, height);
		
		
		//user built in method to check intersection (collision)
		return main.intersects(thisObject);
				
			
				

	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public Bron(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public void move(int dir) {
		switch(dir) {
		
		case 0: //hop up
			
			y -= height; // move up a body length
			break;
		case 1: //hop down
			y+=height; //move down
			break;
		case 2: //hop left
			x -= width;
			break;
		case 3: //hop right
			x += width;
			break;
		
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		x+=vx;
		y+=vy;
		
		init(x,y);
		g2.drawImage(forward, tx, null);
			
		if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(x,  y,  width,  height);
			}
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
 
}
