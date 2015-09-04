package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	private Color c;
	
	private int health;
	private int attack;
	
	private int speed;
	private int xLoc;
	private int yLoc;
	
	private int r;
	
	public Player(Color c){
		this.c = c;
		
		xLoc = 390;
		yLoc = 390;
		
		r = 10;
		
		speed = 10;
	}
	
	public void update(int xMouse, int yMouse){
		double yDiff = yMouse - yLoc;
		double xDiff = xMouse - xLoc;
		
		double rad;
		
		if(xMouse < xLoc){
			rad = Math.atan(yDiff/xDiff) - Math.PI;
		} 
		else{
			rad = Math.atan(yDiff/xDiff);
		}
		
		int dx = (int) (Math.cos(rad)*speed);
		int dy = (int) (Math.sin(rad)*speed);
		
		xLoc += dx;
		yLoc += dy;
	}
	
	public void draw(Graphics2D g){
		g.setColor(c);
		g.fillOval(xLoc, yLoc, r*2, r*2);
	}
}
