package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

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
		
		speed = 30;
		
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
		
		if(x < 0 || x > GamePanel.width - 30 || y < -10 || y > GamePanel.height - 55){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		AffineTransform backup = g.getTransform();
		AffineTransform trans = new AffineTransform();
		
		double tempAngle = angle;
		
		if(angle > -90 && angle < 0){
			tempAngle -= 90;
		}
		
		double transX = Math.sin(Math.toRadians(tempAngle))*18.5;
		double transY = Math.cos(Math.toRadians(tempAngle))*12;
		
		System.out.println(angle);
		
		if(angle > -90 && angle < 0){
			transX = -Math.sin(Math.toRadians(tempAngle))*20;
			transY = -Math.cos(Math.toRadians(tempAngle))*30;
		}
		
		trans.translate(transX, transY);
		trans.rotate(Math.toRadians(angle), x, y); 
		g.setTransform(trans);
		g.drawImage(images[currentImage], x, y, 43, 14, null);
		
		g.setTransform(backup);
	}
}
