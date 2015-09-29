package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Target {
	private int x;
	private int y;
	private int r;
	
	public Target(int x, int y){
		this.x = x;
		this.y = y;
		
		r = 10;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public void draw(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, r*2, r*2);
	}
}
