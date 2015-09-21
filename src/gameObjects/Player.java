package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

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
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 0;
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
				GamePanel.b.add(new Bullet(x, y, angle));
			}
		}
	}
	
	public void draw(Graphics2D g, int playerType){
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		
		trans.rotate(Math.toRadians(angle), x + 12, y + 12); 
		g.setTransform(trans);
		
		if (playerType == 1)
		{
			g.drawImage(new ImageIcon("images/greenDark.png").getImage(), x, y, 24, 24, null);
		}
		else
		{
			g.drawImage(new ImageIcon("images/redot.png").getImage(), x, y, 24, 24, null);
		}
		
		
		g.setTransform(backup);
	}
}
