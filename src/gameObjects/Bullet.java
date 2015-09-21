package gameObjects;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import gameWindow.GamePanel;

public class Bullet {
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private double angle;
	
	private int speed;
	
	public Bullet(int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		
		speed = 20;
		
		double rad = Math.toRadians(angle);
		dx = (int) (Math.cos(rad)*speed);
		dy = (int) (Math.sin(rad)*speed);
	}
	
	public boolean update(){
		x += dx;
		y += dy;
		
		if(x < 0 || x > GamePanel.width - 30 || y < -10 || y > GamePanel.height - 55){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		
		trans.rotate(Math.toRadians(angle), x + 20, y + 20); 
		g.setTransform(trans);
		g.drawImage(new ImageIcon("images/bullet.png").getImage(), x, y, 40, 40, null);
		
		g.setTransform(backup);
	}
}
