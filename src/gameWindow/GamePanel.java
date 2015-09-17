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

import gameObjects.Bullet;
import gameObjects.Player;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	public static int width = 800;
	public static int height = 800;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private final int FPS = 60;
	private int averageFPS;
	
	private Player p;
	
	private int mouseX;
	private int mouseY;
	
	public static List<Bullet> b;
	
	public GamePanel(){
		super();
		
		running = false;
		
		p = new Player(Color.BLUE);
		
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
		
		b = new ArrayList<Bullet>();
		
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
		p.update(mouseX, mouseY);
		
		for(int i = 0; i < b.size(); i++){
			boolean remove = b.get(i).update();
			if(remove){
				b.remove(i);
				i--;
			}
		}
	}
	
	public void gameRender(){
		g.setColor(new Color(30,30,30));
		g.fillRect(0, 0, width, height);
		
		g.setColor(Color.WHITE);
		g.drawString("FPS: " + averageFPS, 10, 20);
		
		for(int i = 0; i < b.size(); i++){
			b.get(i).draw(g);
		}
		
		p.draw(g);
	}
	
	public void gameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
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

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
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
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
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
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		p.setFiring(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		p.setFiring(false);
	}
}
