package GameState;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import gameWindow.GamePanel;

public class GameStateManager {

	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int MAINGAME = 1;
	
	public static GamePanel p;
	
	public GameStateManager(GamePanel p)
	{
		gameStates = new ArrayList<GameState>();
		
		currentState= MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new MainGame(this));
		
		this.p = p;
	}
	
	public void SetState(int state){
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g){
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k){
		gameStates.get(currentState).keyPressed(k);	
	}
	
	public void keyReleased(int k){
		gameStates.get(currentState).keyReleased(k);
	}
	
	public void mousePressed(MouseEvent e) {
		gameStates.get(currentState).mousePressed(e);
	}
	public void mouseReleased(MouseEvent e) {
		gameStates.get(currentState).mouseReleased(e);
	}
	public void mouseDragged(MouseEvent e) {
		gameStates.get(currentState).mouseDragged(e);
	}
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
	}
}
