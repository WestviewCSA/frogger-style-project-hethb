import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
	private Image forward;
	private AffineTransform tx;
	
	int dir = 0; 
	int width, height;

	int x, y;
	int vx, vy;
	double scaleWidth = .5;
	double scaleHeight = .7;
	public Background() {
		forward = getImage("/imgs/"+"sr2b11df1ba4eaws3.gif"); //load the image for Tree
		width = 600;
		height = 900;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0,0);
		
		init(x,y);
	}
	
	
	
	
	public Background(int x, int y) {
		this();
		this.x = x;
		this.y = y;
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
