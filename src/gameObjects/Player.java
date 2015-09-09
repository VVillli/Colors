package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	private Color c;
	
	private int health;
	private int attack;
	
	private int speed;
	private int x;
	private int y;
	
	private int r;
	
	public Player(Color c){
		this.c = c;
		
		x = 390;
		y = 390;
		
		r = 10;
		
		speed = 10	;
	}
	
	public void update(int mouseX, int mouseY){
		double yDiff = mouseY - y;
		double xDiff = mouseX - x;
		
		double rad;
		
		if(mouseX < x){
			rad = Math.atan(yDiff/xDiff) - Math.PI;
		} 
		else{
			rad = Math.atan(yDiff/xDiff);
		}
		
		int dx = (int) (Math.cos(rad)*speed);
		int dy = (int) (Math.sin(rad)*speed);
		
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g){
		g.setColor(c);
		g.fillOval(x, y, r*2, r*2);
	}
}
