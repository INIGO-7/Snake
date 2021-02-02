package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {

	private int x, y, width = 24, height = 24, nextX, nextY;
	private Random xVal = new Random(), yVal = new Random();
	
	public Food() {
		generateNewPosition();
	}
	
	public void generateNewPosition() {
		nextX = xVal.nextInt(776) + 1;
		nextY = yVal.nextInt(576) + 1;
		
		while((nextX - 1) % 50 != 0 || (nextX - 1) % 25 != 0) nextX--;
		while((nextY - 1) % 50 != 0 || (nextY - 1) % 25 != 0) nextY--;
		
		//////////////////////////////////////////////////////////////////////////
		///////	check that the food doesn't appear in the middle of snake: ///////
		//////////////////////////////////////////////////////////////////////////
		
		x = nextX;
		y = nextY;
	
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
