package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import gameWindow.GamePanel;

public class Bullet {
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private double angle;
	
	private int speed;
	
	private Image[] images;
	private int currentImage;
	
	private long timer;
	private long switchImage;
	
	public Bullet(int x, int y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		
		speed = 20;
		
		double rad = Math.toRadians(angle);
		dx = (int) (Math.cos(rad)*speed);
		dy = (int) (Math.sin(rad)*speed);
		
		images = new Image[2];
		
		for(int i = 1; i <= images.length; i++){
			images[i - 1] = new ImageIcon("images/bullet" + i + ".png").getImage();
		}
		
		currentImage = 0;
		
		timer = System.nanoTime();
		switchImage = 10;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public boolean update(){
		x += dx;
		y += dy;
		
		long elapsed = (System.nanoTime() - timer)/1000000;
		
		if(elapsed >= switchImage){
			timer = System.nanoTime();
			currentImage++;
			if(currentImage >= images.length){
				currentImage = 0;
			}
		}
		
		if(x < 0 || x > GamePanel.width || y < 0 || y > GamePanel.height){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		/*AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate(Math.toRadians(angle), x + 24, y + 14); 
		g.setTransform(trans);
		g.drawImage(images[currentImage], x, y, 43, 14, null);
		
		g.setTransform(backup);*/
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 10, 10);
	}
}
