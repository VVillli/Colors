package gameObjects;

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
	
	public boolean update(int x, int y){
		if((x > this.x && x < this.x+(r*2)) || (y > this.y && y < this.y + (r*2))){
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics2D g){
		g.fillOval(x, y, r*2, r*2);
	}
}
