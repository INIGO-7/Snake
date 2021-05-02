package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import main.input.KeyManager;
import main.input.MouseManager;
import main.states.GameOverState;
import main.states.State;

public class Snake {

	private LinkedList<Rectangle> snakeBody;
	private long current, last;
	private double diff, rate = 0.1;
	private String lastDirection = "up";	//the snake game will begin with the snake going upwards.
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public Snake(KeyManager keyManager, MouseManager mouseManager, int startLength) {			//constructor with default snake speed.
		
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
				
		snakeBody = new LinkedList<Rectangle>();
		
		int xs = 1, ys = 201;
		
		for(int i = 0; i < startLength; i++) {	//the length of the snake in the beginning is specified by the range of values that "i" accepts (now depends on the startLength parameter).
												//this is just to fill the snake body right when the play button is pressed.
			
			snakeBody.add(new Rectangle(301, ys, 24, 24));	//301 is the starting x of the snake. 24 24 are the width and height of the rectangles that compose the snake's body.
															//ys is at first 301 (starting y coordinate). It will be increased to create new rectangles (fill the body) under the snake.
			ys += 25;
		}
		
		last = System.nanoTime();
		
	}
	
	public Snake(KeyManager keyManager, MouseManager mouseManager, int startLength, float rate) {	//constructor that can specify the snake's speed.
		
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
		
		this.rate = rate;								//initialise the speed rate of the snake.
		
		snakeBody = new LinkedList<Rectangle>();
		
		int xs = 1, ys = 301;
		
		for(int i = 0; i < startLength; i++) {	
			
			snakeBody.add(new Rectangle(301, ys, 24, 24));
			
			ys += 25;
		}
		
		last = System.nanoTime();
		
	}
	
	public void tick() {
		
		updateKeys();
		
		checkSnakeCollisions();
		
		current = System.nanoTime();
		diff += current - last;
		
		if(diff/1000000000 >= rate) {						//sometimes, if keys that indicate contrary directions are pressed quickly the snake will collide with itself. TO FIX.
			
			Rectangle lastRect = snakeBody.getLast();
			
			if(lastDirection == "up") {
				lastRect.x = snakeBody.getFirst().x;
				lastRect.y = snakeBody.getFirst().y - 25;
			}else if(lastDirection == "down") {
				lastRect.x = snakeBody.getFirst().x;
				lastRect.y = snakeBody.getFirst().y + 25;
			}else if(lastDirection == "right") {
				lastRect.x = snakeBody.getFirst().x + 25;
				lastRect.y = snakeBody.getFirst().y;
			}else {
				lastRect.x = snakeBody.getFirst().x - 25;
				lastRect.y = snakeBody.getFirst().y;
			}
	
			snakeBody.removeLast();
			snakeBody.addFirst(lastRect);
			
			diff = 0;
		}
		
		last = current;	
		
		Rectangle snakeHead = getSnakeHead();
		
		if(snakeHead.x < 1 || snakeHead.x > 399 || snakeHead.y < 1 || snakeHead.y > 299) State.setState(new GameOverState(keyManager, mouseManager));
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		for(Rectangle rect : snakeBody) g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
	}
	
	public void incrementSize() {
		
		Rectangle toAdd = new Rectangle(snakeBody.getLast().x, snakeBody.getLast().y, 24, 24);
		snakeBody.add(toAdd);
	}
	
	public Rectangle getSnakeHead() {
		return snakeBody.getFirst();
	}
	
	public LinkedList<Rectangle> getSnakeBody() {
		return snakeBody;
	}
	
	public int getSnakeSize() {
		return snakeBody.size();
	}
	
	public void checkSnakeCollisions() {
		    	
    	Rectangle head = getSnakeHead();
    	
    	//List<Rectangle> snakeBodyCopy = List.copyOf(snakeBody);			//shallow copy, we dont want this.
    	
    	snakeBody.remove(head);		//snake's head is temporarily removed, thus remaining the body of the snake. Now we can check if the head is colliding with the body.
    	
    	for(Rectangle rect : snakeBody) if(head.equals(rect)) State.setState(new GameOverState(keyManager, mouseManager));	//loops through the snake's body and ends the game if the head collides with it.
    	
    	snakeBody.addFirst(head);
		
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