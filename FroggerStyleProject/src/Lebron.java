import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Lebron {
	private Image forward, backward, left, right;
	private AffineTransform tx;
	
	int dir = 0; 
	int width, height;
	int x, y;
	int vx, vy;
	double scaleWidth = 15.0;
	double scaleHeight = 15.0;
	public Lebron() {
		forward = getImage("/imgs" + "");
		width = 60;
		height = 60;
		x = 600/2 - width/2;
		y = 600;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	public Lebron(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
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
		tx.setToTranslation(a,b);
		tx.scale(scaleWidth, scaleHeight);
	}
	
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Lebron.class.getResource(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
