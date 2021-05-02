package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import main.entities.Food;
import main.entities.Snake;
import main.input.KeyManager;
import main.input.MouseManager;

public class GameState extends State{
	
	private Food food;
	private Snake snake;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public GameState(KeyManager keyManager, MouseManager mouseManager, int gameMode) {
		
		this.keyManager = keyManager;
		this.mouseManager = mouseManager;
		
		if(gameMode == 1) snake = new Snake(keyManager, mouseManager, 5);
		else snake = new Snake(keyManager, mouseManager, 5, 0.05f);
		
		food = new Food(snake.getSnakeBody());
		
	}
	
	@Override
	public void tick() {
		food.tick();
		snake.tick();
		
		checkIfEaten();
		
	}
	
	public void checkIfEaten() {
		if(snake.getSnakeHead().contains(food.getX(), food.getY())) {
			snake.incrementSize();
			food.generateNewPosition(snake.getSnakeBody());
		}
	}
	
	public LinkedList<Rectangle> getSnakeBody() {
		return snake.getSnakeBody();
	}
	
	public int getScore() {
		return snake.getSnakeSize();
	}

	@Override
	public void render(Graphics g) {

		//world drawn:
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 300);
		
		//drawing the lines:
		
		g.setColor(Color.WHITE);
		
		// X axis

		g.drawLine(25, 0, 25, 300);
		g.drawLine(50, 0, 50, 4300);
		g.drawLine(75, 0, 75, 300);
		g.drawLine(100, 0, 100, 300);
		g.drawLine(125, 0, 125, 300);
		g.drawLine(150, 0, 150, 300);
		g.drawLine(175, 0, 175, 300);
		g.drawLine(200, 0, 200, 300);
		g.drawLine(225, 0, 225, 300);
		g.drawLine(250, 0, 250, 300);
		g.drawLine(275, 0, 275, 300);
		g.drawLine(300, 0, 300, 300);
		g.drawLine(325, 0, 325, 300);
		g.drawLine(350, 0, 350, 300);
		g.drawLine(375, 0, 375, 300);
		
		
		// Y axis
		
		g.drawLine(0, 25, 400, 25);	
		g.drawLine(0, 50, 400, 50);
		g.drawLine(0, 75, 400, 75);
		g.drawLine(0, 100, 400, 100);
		g.drawLine(0, 125, 400, 125);
		g.drawLine(0, 150, 400, 150);
		g.drawLine(0, 175, 400, 175);
		g.drawLine(0, 200, 400, 200);
		g.drawLine(0, 225, 400, 225);
		g.drawLine(0, 250, 400, 250);
		g.drawLine(0, 275, 400, 275);
		
		//boundaries
		
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, 400, 0);
		g.drawLine(0, 299, 400, 299);
		g.drawLine(0, 0, 0, 400);
		g.drawLine(399, 0, 399, 299);
		
		////////////////////////////////////////
		
		food.render(g);
		snake.render(g);
		
	}

}