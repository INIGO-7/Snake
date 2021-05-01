package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.input.KeyManager;
import main.input.MouseManager;

public class MenuState extends State{

	private Rectangle startGame1, startGame2;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	public MenuState(KeyManager keyManager, MouseManager mouseManager) {
		
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
		
	}
	
	public void tick() {
		
		startGame1 = new Rectangle(250, 175, 100, 50);
		startGame2 = new Rectangle(250, 250, 100, 50);
		
		if(mouseManager.getLeftClick() && startGame1.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) State.setState(new GameState(keyManager, mouseManager, 1));
		else if(mouseManager.getLeftClick() && startGame2.contains((int) (mouseManager.getMouseX()), (int) (mouseManager.getMouseY()))) State.setState(new GameState(keyManager, mouseManager, 2));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.RED);
		g.fillRect(250, 175, 100, 50);
		
		g.setColor(Color.RED);
		g.fillRect(250, 250, 100, 50);
		
		g.drawString("SNAKE", 275, 100);
		
		g.setColor(Color.WHITE);
		g.drawString("BEGIN GAMEMODE 1", 240, 200);
		
		g.setColor(Color.WHITE);
		g.drawString("BEGIN GAMEMODE 2", 240, 275);
		
	}

}
