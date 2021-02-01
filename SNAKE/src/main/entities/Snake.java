package main.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {

	public Snake() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(301, 301, 24, 24);
		g.fillRect(301, 326, 24, 24);
		g.fillRect(301, 351, 24, 24);
		g.fillRect(301, 376, 24, 24);
		g.fillRect(301, 401, 24, 24);
	}
	
}
