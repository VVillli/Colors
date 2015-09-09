package gameObjects;

import java.awt.Graphics2D;

import gameWindow.GamePanel;

public class Camera {
	
	private Player p;
	private GameMap m;
	
	private int width;
	private int height;
	
	public Camera(Player p, GameMap m){
		this.p = p;
		this.m = m;
		
		width = GamePanel.width;
		height = GamePanel.height;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		
	}
}
