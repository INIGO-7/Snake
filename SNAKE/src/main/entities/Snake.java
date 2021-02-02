package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class Snake {

	private LinkedList<Rectangle> snakeBody;
	private long current, last;
	private double diff, rate = 0.2;
	
	public Snake() {
		snakeBody = new LinkedList<Rectangle>();
		snakeBody.add(new Rectangle(301, 301, 24, 24));
		snakeBody.add(new Rectangle(301, 326, 24, 24));
		snakeBody.add(new Rectangle(301, 351, 24, 24));
		
		last = System.nanoTime();
	}
	
	public void tick() {
		
		current = System.nanoTime();
		diff += current - last;
		
		if(diff/1000000000 >= rate) {
			
			Rectangle lastRect = snakeBody.getLast();
			lastRect.y = snakeBody.getFirst().y - 25;
			snakeBody.removeLast();
			snakeBody.addFirst(lastRect);
			
			diff = 0;
		}
		
		last = current;
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		for(Rectangle rect : snakeBody) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}
	
}
