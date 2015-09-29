package gameWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import GameState.GameStateManager;
import gameObjects.Bullet;
import gameObjects.Player;
import gameObjects.Target;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	public static int width = 800;
	public static int height = 800;
	public static int SCALE = 2;
	
	private Thread thread;
	private boolean running;
	
	public int count;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private final int FPS = 60;
	private int averageFPS;
	
	private Player p;
	
	//game state manager
	private GameStateManager gsm;
	
	
	
	public GamePanel(){
		super();
		
		running = false;
		gsm = new GameStateManager(this);
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);
	}
	
	public void run(){
		running = true;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)image.getGraphics();		
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		long startTime;
		long URDTimeMilli;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 60;
		
		long targetTime = 1000/FPS;
		
		while(running){
			startTime = System.nanoTime();
			
			gameUpdate();
			gameRender();
			gameDraw();
			
			URDTimeMilli = (System.nanoTime() - startTime)/1000000;
			
			waitTime = targetTime - URDTimeMilli;
			if (waitTime <0) waitTime = 5;
			
			
			try{
				Thread.sleep(waitTime);
			}catch(Exception e){}
			
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			
			if(frameCount == maxFrameCount){
				averageFPS = (int)(1000.0/((totalTime/frameCount)/1000000));
				frameCount = 0;
				totalTime = 0;
			}
		}
	}
	
	public void gameUpdate(){
		gsm.update();
	}
	
	public void gameRender(){
		g.setColor(new Color(30,30,30));
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + averageFPS, 10, 20);
		g.drawString("Score: " + p.getScore(), 10, 32);
		g.drawString("Opponent Score: " + p.getUser().getEScore(), 10, 44);
		
		
		gsm.draw(g);
	}
	
	public void gameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE  , null);
		g2.dispose();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
	}
}
