package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import main.input.KeyManager;

public class Snake {

	private LinkedList<Rectangle> snakeBody;
	private long current, last;
	private double diff, rate = 0.2;
	private String lastDirection = "up";
	private KeyManager keyManager;
	
	public Snake(KeyManager keyManager) {
		
		this.keyManager = keyManager;
		
		snakeBody = new LinkedList<Rectangle>();
		snakeBody.add(new Rectangle(301, 301, 24, 24));
		snakeBody.add(new Rectangle(301, 326, 24, 24));
		snakeBody.add(new Rectangle(301, 351, 24, 24));
		
		last = System.nanoTime();
	}
	
	public void tick() {
		
		updateKeys();
		
		current = System.nanoTime();
		diff += current - last;
		
		if(diff/1000000000 >= rate) {
			
			Rectangle lastRect = snakeBody.getLast();
			
			if(lastDirection == "up") {
				lastRect.y = snakeBody.getFirst().y - 25;
			}else if(lastDirection == "down") {
				lastRect.y = snakeBody.getFirst().y + 25;
			}else if(lastDirection == "right") {
				lastRect.x = snakeBody.getFirst().x + 25;
			}else {
				lastRect.x = snakeBody.getFirst().x - 25;
			}
	
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
	
	public void updateKeys() {
		
		if(keyManager.getUp()) {
			if(lastDirection == "down") return;
			else lastDirection = "up";
		}else if(keyManager.getDown()) {
			if(lastDirection == "up") return;
			else lastDirection = "down";
		}else if(keyManager.getRight()) {
			if(lastDirection == "left") return;
			else lastDirection = "right";
		}else if(keyManager.getLeft()) {
			if(lastDirection == "right") return;
			else lastDirection = "left";
		}
	}
	
}
