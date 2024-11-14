import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class resetWin {
	private Image forward;
	private AffineTransform tx;
	
	int dir = 0; 
	int width, height;

	int x, y;
	int vx, vy;
	double scaleWidth = 0;
	double scaleHeight = 0;
	public resetWin() {
		forward = getImage("/imgs/"+"camera.png"); //load the image for Tree
		width = 60;
		height = 60;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	public boolean collided(Camera character) {
		//represent each object as a rect 
		//check if they are intersecting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight()
				);
		Rectangle thisObject = new Rectangle(0,  0,  600,  50);
		
		
		//user built in method to check intersection (collision)
		return main.intersects(thisObject);
	
	}
	
	
	
	public resetWin(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	
	
	//getters!!
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		x+=vx;
		y+=vy;
		
		init(x,y);
		g2.drawImage(forward, tx, null);
			
		if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(0,  0,  600,  50);
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