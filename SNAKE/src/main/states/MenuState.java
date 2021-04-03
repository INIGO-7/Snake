package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.input.KeyManager;
import main.input.MouseManager;

public class MenuState extends State{

	private Rectangle startGame;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	private GameState gameState;
	
	public MenuState(KeyManager keyManager, MouseManager mouseManager) {
		
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		
	}
	
	public void tick() {
		
		startGame = new Rectangle(250, 175, 100, 50);
		
		if(mouseManager.getLeftClick() && startGame.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) {
			this.gameState = new GameState(keyManager, mouseManager);
			State.setState(gameState);
		}

		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.RED);
		g.fillRect(250, 175, 100, 50);
		
		g.drawString("SNAKE", 275, 100);
		
		g.setColor(Color.WHITE);
		g.drawString("BEGIN", 275, 200);
		
	}
	
	public GameState getGameState() {
		return gameState;
	}

}
