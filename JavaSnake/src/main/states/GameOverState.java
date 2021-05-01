package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.input.KeyManager;
import main.input.MouseManager;

public class GameOverState extends State{

	private Rectangle restartGame1, restartGame2;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	public GameOverState(KeyManager keyManager, MouseManager mouseManager) {
		
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		
		restartGame1 = new Rectangle(250, 175, 100, 50);
		restartGame2 = new Rectangle(250, 250, 100, 50);
		
	}
	
	public void tick() {
		
		if(mouseManager.getLeftClick() && restartGame1.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) State.setState(new GameState(keyManager, mouseManager, 1));
		else if(mouseManager.getLeftClick() && restartGame2.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) State.setState(new GameState(keyManager, mouseManager, 2));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.RED);
		g.fillRect(250, 175, 100, 50);
		
		g.setColor(Color.GREEN);
		g.fillRect(250, 250, 100, 50);
		
	}
	
}