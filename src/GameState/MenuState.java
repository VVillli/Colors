package GameState;

import TitleMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = { 
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	
	
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;	
		
		try{
			
			bg = new Background("images/colorsscrolling.jpg", 1);
			bg.setVector(-4, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic",
					Font.PLAIN, 28
					);
			
			font = new Font("Arial", Font.PLAIN, 50);
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	public void init() {}
	public void update() {
		bg.update();
	}
		
	public void draw(Graphics2D g) {
		//draw bg
		bg.draw(g);
		
		
		g.drawImage(new ImageIcon("images/Logo.png").getImage(), 200, 50, 400,400, null);
		g.drawImage(new ImageIcon("images/Logo.png").getImage(), 200, 50, 400,400, null);
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		//g.drawString("Colors", 80, 70);
		
		//draw menu options
		g.setFont(font);
		
		for(int i = 0; i < options.length; i++)
		{
			if (i == currentChoice)
			{
				g.setColor(Color.BLUE);
				
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 350, 450 + i * 60);
			
			
			
		}
	}
	private void select()
	{
		if(currentChoice == 0)
		{
			gsm.SetState(GameStateManager.MAINGAME);
		}
		
		if(currentChoice == 1){
			//help
		}
		
		if(currentChoice == 2){
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		
		
		
		
		if (k == KeyEvent.VK_ENTER){
			select();
		}
		
		if (k == KeyEvent.VK_UP){
			currentChoice--;
			if (currentChoice == -1){	
				currentChoice = options.length-1;
			}
		}
			
		if(k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
			
		}	
		
		
		}
		
		
	
	public void keyReleased(int k) {}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
