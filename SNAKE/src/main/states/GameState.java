package main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import main.entities.Food;
import main.entities.Snake;
import main.input.KeyManager;

public class GameState extends State{
	
	private Food food;
	private Snake snake;
	private KeyManager keyManager;
	
	public GameState(KeyManager keyManager) {
		
		this.keyManager = keyManager;
		
		snake = new Snake(keyManager);
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

	@Override
	public void render(Graphics g) {

		//world drawn:
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 400);
		
		//drawing the lines:
		
		g.setColor(Color.WHITE);
		
		// X axis

		g.drawLine(25, 0, 25, 400);
		g.drawLine(50, 0, 50, 400);
		g.drawLine(75, 0, 75, 400);
		g.drawLine(100, 0, 100, 400);
		g.drawLine(125, 0, 125, 400);
		g.drawLine(150, 0, 150, 400);
		g.drawLine(175, 0, 175, 400);
		g.drawLine(200, 0, 200, 400);
		g.drawLine(225, 0, 225, 400);
		g.drawLine(250, 0, 250, 400);
		g.drawLine(275, 0, 275, 400);
		g.drawLine(300, 0, 300, 400);
		g.drawLine(325, 0, 325, 400);
		g.drawLine(350, 0, 350, 400);
		g.drawLine(375, 0, 375, 400);
		g.drawLine(400, 0, 400, 400);
		g.drawLine(425, 0, 425, 400);
		g.drawLine(450, 0, 450, 400);
		g.drawLine(475, 0, 475, 400);
		g.drawLine(500, 0, 500, 400);
		g.drawLine(525, 0, 525, 400);
		g.drawLine(550, 0, 550, 400);
		g.drawLine(575, 0, 575, 400);
		
		
		// Y axis
		
		g.drawLine(0, 25, 600, 25);	
		g.drawLine(0, 50, 600, 50);
		g.drawLine(0, 75, 600, 75);
		g.drawLine(0, 100, 600, 100);
		g.drawLine(0, 125, 600, 125);
		g.drawLine(0, 150, 600, 150);
		g.drawLine(0, 175, 600, 175);
		g.drawLine(0, 200, 600, 200);
		g.drawLine(0, 225, 600, 225);
		g.drawLine(0, 250, 600, 250);
		g.drawLine(0, 275, 600, 275);
		g.drawLine(0, 300, 600, 300);
		g.drawLine(0, 325, 600, 325);
		g.drawLine(0, 350, 600, 350);
		g.drawLine(0, 375, 600, 375);
		
		//boundaries
		
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, 600, 0);
		g.drawLine(0, 399, 600, 399);
		g.drawLine(0, 0, 0, 600);
		g.drawLine(599, 0, 599, 399);
		
		////////////////////////////////////////
		
		food.render(g);
		snake.render(g);
		
	}

}

