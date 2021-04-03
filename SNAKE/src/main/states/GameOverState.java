package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.input.KeyManager;
import main.input.MouseManager;

public class GameOverState extends State{

	private Rectangle restartGame;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	public GameOverState(KeyManager keyManager, MouseManager mouseManager) {
		
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		
		restartGame = new Rectangle(250, 175, 100, 50);
		
	}
	
	public void tick() {
		
		if(mouseManager.getLeftClick() && restartGame.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) State.setState(new GameState(keyManager, mouseManager));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.RED);
		g.fillRect(250, 175, 100, 50);
		
	}
	
}
