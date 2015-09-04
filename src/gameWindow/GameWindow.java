package gameWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	public GameWindow(){
		super();
		
		this.setTitle("Colors");
		this.setBackground(Color.black);
		this.setContentPane(new GamePanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.pack();
		
		this.setSize(new Dimension(800,800));
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
	    
	    this.setResizable(false);
		this.setVisible(true);
	}
}
