import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class river2 {
	private Image forward;
	private AffineTransform tx;
	
	int dir = 0; 
	int width, height;

	int x, y;
	int vx, vy;
	double scaleWidth = 1;
	double scaleHeight = 1;
	public river2() {
		forward = getImage("/imgs/"+"river.gif"); //load the image for Tree
		width = 700;
		height = 60;
		x = -width;
		y = 320;  
		vx = 5;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	public int getVX() {
		return vx;
	}
	

	
	
	
	
	public river2(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public boolean collided(Bron character) {
		//represent each object as a rect 
		//check if they are intersecting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight()
				);
		Rectangle thisObject = new Rectangle(x,  y+15,  width+30,  height+10);
		
		
		//user built in method to check intersection (collision)
		return main.intersects(thisObject);
				
			
				

	}
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		x+=vx;
		y+=vy;
		
		//for infinite scrolling - teleport to the other side
		//once it leaves the other side
		if(x > 600) {
			x = -150;
		}
		
		init(x,y);
		g2.drawImage(forward, tx, null);
			
		if(Frame.debugging) {
				g.setColor(Color.green);
				g.drawRect(x,  y+15,  width+30,  height+10);
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
