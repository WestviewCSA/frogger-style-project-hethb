import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	public static boolean debugging = true;
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	int score = 0;
	String Score = "0";
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	int loseCount = 0;
	int winCount = 0;
	String winner = "YOU WIN!";
	
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", true);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
//	
	Camera camera = new Camera();
	Camera camera2 = new Camera();
	Sprite sp = new Sprite();
	Background bg = new Background();
	Bron player = new Bron();
	GameOver end = new GameOver();
	lose loser = new lose();
	
	//row of camera objects
	CameraScrolling[] row1 = new CameraScrolling[10];
	CameraScrolling2[] row2 = new CameraScrolling2[10];
	CameraScrolling3[] row3 = new CameraScrolling3[10];
	ArrayList<CameraScrolling3> row3List = new ArrayList<CameraScrolling3>();
	CameraScrolling4[] row4 = new CameraScrolling4[10];
	river[] River = new river[20];
	ArrayList<LifeChange> lives = new ArrayList<LifeChange>();
	
	//frame width/height
	static int width = 600;
	static int height = 800;	
	//Sprite sp1 = new Sprite();

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		boolean riding = false;
	
		
		//paint the other objects on the screen
		bg.paint(g);
		//camera.paint(g);
		//camera2.paint(g);
	
		
		if(score < 0) {
			end.paint(g);
		}
		
		
		//have the row1 objects paint on the screen
		for(CameraScrolling obj : row1) {
			obj.paint(g);
		}
		
		for(CameraScrolling2 obj : row2) {
			obj.paint(g);
		}
		
		
		
		for(CameraScrolling3 obj : row3) {
			obj.paint(g);
		}
		
		for(CameraScrolling3 obj : row3List) {
			obj.paint(g);
		}
		
		for(river obj : River) {
			obj.paint(g);
		}
		
		for(LifeChange obj : lives) {
			obj.paint(g);
		}
		
		
		for(CameraScrolling4 obj : row4) {
			obj.paint(g);
		}
		
		
		player.paint(g);

		g.drawString(score+"", 10, 10);
		
		g.drawString(Score, 0, 0);
	 
		//sp.paint(g);
		
		//collision detection
		for( CameraScrolling obj : row1) {
			if(obj.collided(player)) {
				System.out.println("HITS!");
				score -= 10;
				player.setX(Frame.width/2);
				player.setY(player.getY() + 5);
				loseCount++;
				
			}
		}
		
		
		
		for( CameraScrolling2 obj : row2) {
			if(obj.collided(player)) {
				System.out.println("HITS!");
				score -= 10;
				player.setX(Frame.width/2);
				player.setY(player.getY() + 5);
				loseCount++
			}
		}
		
		
		//winning
		if(player.getY() <= 60) {
			System.out.println("WIN!");
			player.setX(Frame.width/2);
			player.setY(900);
			score += 50;
			winCount++;
			
		}
		
		//losing
		if(player.getX() <= 0 || player.getX() >= 600) {
			System.out.println("Lose");
			player.setX(Frame.width/2);
			player.setY(900);
			score -= 50;
			loseCount++;
			
		}
		
		if(player.getY() >= 901) {
			System.out.println("Lose");
			player.setX(Frame.width/2);
			player.setY(900);
			score -= 50;
			loseCount++;
		}
		
		
		for( CameraScrolling3 obj : row3) {
			if(obj.collided(player)) {
				System.out.println("HITS!");
				player.setX(Frame.width/2);
				player.setY(player.getY() + 5);				
				score -= 10;
				loseCount++;
			}
		}
		
		for( CameraScrolling4 obj : row4) {
			if(obj.collided(player)) {
				System.out.println("RIDE");
				if(obj.collided(player)) {
					//int v = row4.getVX();
					player.setVX(5);
					riding = true;
				}
			}
		}
		
		for(CameraScrolling3 obj : row3List) {
			if(obj.collided(player)) {
				System.out.println("HITS!");
				player.setX(Frame.width/2);
				player.setY(player.getY() + 5);				
				score -= 10;
				loseCount++;
			}
		}
		
		
		
		if(riding != true) {
			player.setVX(0);
		}
		
		if(player.getX() > 90 && player.getX() < 150) {
			for( river obj : River) {
				if(obj.collided(player)) {
					System.out.println("DROWN");
					player.setX(Frame.width/2);
					player.setY(player.getY() + 5);					
					score -= 10;
					loseCount++
				}
			}
		}

		if(loseCount > 5){
			loser.paint(g);
		}

		if(winCount > 3){
			g.drawString(winner+"", width/2, height/2);
		}
		
		
	

	}

	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height+ 200));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();
		
		/*
		 * set up any 1d arrays here!!!
		 * create the objects that go in them 
		 */
		
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new CameraScrolling(i*250 ,250);
			
		}
		
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new CameraScrolling2(i*250, 500);
		}
		
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new CameraScrolling3(i*250, 350);
		}
		
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new CameraScrolling4(i*200, 100);
		}
		
//		//Jiten added code to initialize the elements of River array
		for(int i = 0; i < River.length; i++) {
			River[i] = new river(i*100, 100);
		}	
		
		for(int i = 0; i < 10; i++) {
			//run the body 10x
			this.row3List.add(new CameraScrolling3(i*180, 700));
		}
			
		for(int i = 0; i < 6; i++) {
			//this.lives.add(new LifeChange(i*40, 10));
		}
		
		if(lives.size() > 0) {
			lives.remove(lives.size() - 1);
		}
	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		//w is 87
		if(arg0.getKeyCode() == 38) {
			//move main character up
			player.move(0);
			//System.out.println(player.getY());
			score += 10;
//			System.out.println(score);
//			System.out.println(height);
//			System.out.println(width);
			
		}
		
		if(arg0.getKeyCode() == 40) {
			//move main character down
			player.move(1);
		}
		
		if(arg0.getKeyCode() == 37) {
			//move main character down
			player.move(2);
		}
		
		if(arg0.getKeyCode() == 39) {
			//move main character down
			player.move(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
