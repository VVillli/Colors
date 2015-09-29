package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import gameObjects.Bullet;
import gameObjects.Player;
import gameObjects.Target;
import gameWindow.GamePanel;

public class MainGame extends GameState{
	
	private GameStateManager gsm;
	
	private Player p;
	
	private int mouseX;
	private int mouseY;
	
	public static List<Bullet> b;
	public static List<Target> t;
	
	private int score;
	
	public MainGame(GameStateManager gsm){
		this.gsm = gsm; 
		
		p = new Player(Color.GREEN);
		
		b = new ArrayList<Bullet>();
		t = new ArrayList<Target>();
		
		t.add(new Target(30, 30));
		
		score = 0;
	}
	
	public void init() {}
	
	public void update(){
		p.update(mouseX, mouseY);
		
		for(int i = 0; i < b.size(); i++){
			boolean remove = b.get(i).update();
			if(remove){
				b.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < t.size(); i++){
			int tx = t.get(i).getX();
			int ty = t.get(i).getY();
			for(int j = 0; j < b.size(); j++){
				int bx = b.get(j).getX();
				int by = b.get(j).getY();
				
				int distX;
				int distY;
				
				if(tx > bx){distX = tx - bx;}
				else{distX = bx - tx;}
				
				if(ty > by){distY = ty - by;}
				else{distY = by - ty;}
				
				if(distX > -10 && distX < 20 && distY > -10 && distY < 20){
					t.remove(i);
					t.add(new Target((int)(Math.random()*GamePanel.width),(int)(Math.random()*GamePanel.height)));
					b.remove(j);
					score++;
					break;
				}
			}
		}
	}
	
	public void draw(Graphics2D g) {
		for(int i = 0; i < b.size(); i++){
			b.get(i).draw(g);
		}
		
		for(int i = 0; i < t.size(); i++){
			t.get(i).draw(g);
		}
		
		p.draw(g, 1);
		
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 32);
		
		//g.drawString("Score: " + p.getScore(), 10, 32);
		g.drawString("Opponent Score: " + p.getUser().getEScore(), 10, 44);
	}
	
	public void keyPressed(int k) {
		switch(k){
			case KeyEvent.VK_W:
				p.setUp(true);
				break;
			case KeyEvent.VK_A:
				p.setLeft(true);
				break;
			case KeyEvent.VK_S:
				p.setDown(true);
				break;
			case KeyEvent.VK_D:
				p.setRight(true);
				break;
			case KeyEvent.VK_ESCAPE:
				gsm.SetState(gsm.MENUSTATE);
			default:
				break;
		}
		
	}
	public void keyReleased(int k) {
		switch(k){
			case KeyEvent.VK_W:
				p.setUp(false);
				break;
			case KeyEvent.VK_A:
				p.setLeft(false);
				break;
			case KeyEvent.VK_S:
				p.setDown(false);
				break;
			case KeyEvent.VK_D:
				p.setRight(false);
				break;
			default:
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		p.setFiring(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		p.setFiring(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
