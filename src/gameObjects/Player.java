package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import GameState.GameStateManager;
import GameState.MainGame;
import ServerSide.User;
import gameWindow.GamePanel;

public class Player {
	
	private Color c;
	private int playerType;
	private int health;
	private int attack;
	
	private int speed;
	private int x;
	private int y;
	private int dx;
	private int dy;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private int r;
	
	private double angle;
	
	private boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	private GamePanel parent;
	
	//Server
	private User user;
	private PrintWriter print;
	
	private int score;
	public Player(Color c){
		this.c = c;
		
		x = 390;
		y = 390;
		
		dx = 0;
		dy = 0;
		
		r = 10;
		
		up = false;
		down = false;
		left = false;
		right = false;
		
		speed = 5;
		
		angle = 0;
		
		parent = GameStateManager.p;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 500;
		
			try {
				user = new User(InetAddress.getLocalHost().getHostName(), 9005, this);
				user.start();
			} catch (UnknownHostException e) {} 
		}
	
	public void setUp(boolean b){up = b;}
	public void setDown(boolean b){down = b;}
	public void setLeft(boolean b){left = b;}
	public void setRight(boolean b){right = b;}
	public void setFiring(boolean b){firing = b;}
	
	public void update(int mouseX, int mouseY){
		if(up){dy = -speed;}
		if(down){dy = speed;}
		if(left){dx = -speed;}
		if(right){dx = speed;}
		
		x += dx;
		y += dy;
		
		if(x < 0){x = 0;}
		if(x > GamePanel.width - 30){x = GamePanel.width - 30;}
		if(y < 0){y = 0;}
		if(y > GamePanel.height - 55){y = GamePanel.height - 55;}
		
		dx = 0;
		dy = 0;
		
		double distX = mouseX - x;
		double distY = mouseY - y;
		if(mouseX < x){
			angle = Math.toDegrees(Math.atan(distY/distX)) - 180;
		} 
		else{
			angle = Math.toDegrees(Math.atan(distY/distX));
		}
		
		if(firing){
			long elapsed = (System.nanoTime() - firingTimer)/1000000;
			if(elapsed > firingDelay){
				firingTimer = System.nanoTime();
				
				/*int transX;
				int transY;
				
				if(angle <= -90){
					transX = x + (int)(Math.cos(Math.toRadians(angle))*18);
				}
				else{
					transX = x - (int)(Math.cos(Math.toRadians(angle))*18);
				}
				
				if(angle > -180 && angle < 0){
					transY = y + (int)(Math.sin(Math.toRadians(angle))*6);
				}
				else{
					transY = y - (int)(Math.sin(Math.toRadians(angle))*6);
				}
				
				MainGame.b.add(new Bullet(transX, transY, angle));*/
				
				MainGame.b.add(new Bullet(x + 5, y + 5, angle));
			}
		}
	}
	
	public void draw(Graphics2D g, int playerType){
		/*AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		
		trans.rotate(Math.toRadians(angle), x + 6, y + 12); 
		g.setTransform(trans);
		
		if (playerType == 1){
			//URL url = parent.getClass().getResource("images/greenDark.png"); 
			//g.drawImage(ImageIO.read(getClass().getResource("images/greenDark.png")), x, y, 24, 24, null);
		}
		else{
			g.drawImage(new ImageIcon("src/images/redot.png").getImage(), x, y, 24, 24, null);
		}
		
		
		g.setTransform(backup);*/
		g.setColor(new Color(68, 223, 60, 70));
		g.fillOval(x, y, 25, 25);
		g.setColor(new Color(78, 246, 69, 100));
		g.fillOval(x + 3, y + 3, 19, 19);
	}
	
	public int getScore(){
		return score;
	}
	
	public void increaseScore(){
		score++;
		System.out.println("New Score " +  score);
	}
}
