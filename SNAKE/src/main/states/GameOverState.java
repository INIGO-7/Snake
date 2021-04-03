package main.states;

import java.awt.Color;
import java.awt.Graphics;

public class GameOverState extends State{

	public GameOverState() {
		
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
	}
	
}
