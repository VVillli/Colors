package TitleMap;

import gameWindow.GamePanel;



import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;

public class Background {

	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background (String s, double ms){
		/*try{
			image = ImageIO.read(
			getClass().getResourceAsStream(s)		
					);
			moveScale = ms;
		}
		catch(Exception e){
			e.printStackTrace();
			
		}*/

	}
	
	public void setPosition(double x, double y){
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
		
	}
	
	public void setVector(double dx	, double dy){
		this.dx = dx;
		this.dy = dy;
		
	}
	
	public void update(){
		x += dx;
		y += dy;
		
		if(x < -800){
			x = 0;
		}
	}
	
	public void draw(Graphics2D g){
		g.drawImage(new ImageIcon("images/background.png").getImage(), (int)x, (int)y, 800,800, null);
		g.drawImage(new ImageIcon("images/background.png").getImage(), (int)x + 800, (int)y, 800,800, null);
	}
	
	
	
}
