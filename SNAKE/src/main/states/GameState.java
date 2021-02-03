package main.states;

import java.awt.Color;
import java.awt.Graphics;

import main.entities.Food;
import main.entities.Snake;
import main.input.KeyManager;

public class GameState extends State{
	
	private Food food;
	private Snake snake;
	private KeyManager keyManager;
	
	public GameState(KeyManager keyManager) {
		
		this.keyManager = keyManager;
		
		food = new Food();
		snake = new Snake(keyManager);
		
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

	@Override
	public void render(Graphics g) {

		//world drawn:
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		//drawing the lines:
		
		g.setColor(Color.WHITE);
		
		// X axis

		g.drawLine(25, 0, 25, 600);
		g.drawLine(50, 0, 50, 600);
		g.drawLine(75, 0, 75, 600);
		g.drawLine(100, 0, 100, 600);
		g.drawLine(125, 0, 125, 600);
		g.drawLine(150, 0, 150, 600);
		g.drawLine(175, 0, 175, 600);
		g.drawLine(200, 0, 200, 600);
		g.drawLine(225, 0, 225, 600);
		g.drawLine(250, 0, 250, 600);
		g.drawLine(275, 0, 275, 600);
		g.drawLine(300, 0, 300, 600);
		g.drawLine(325, 0, 325, 600);
		g.drawLine(350, 0, 350, 600);
		g.drawLine(375, 0, 375, 600);
		g.drawLine(400, 0, 400, 600);
		g.drawLine(425, 0, 425, 600);
		g.drawLine(450, 0, 450, 600);
		g.drawLine(475, 0, 475, 600);
		g.drawLine(500, 0, 500, 600);
		g.drawLine(525, 0, 525, 600);
		g.drawLine(550, 0, 550, 600);
		g.drawLine(575, 0, 575, 600);
		g.drawLine(600, 0, 600, 600);
		g.drawLine(625, 0, 625, 600);
		g.drawLine(650, 0, 650, 600);
		g.drawLine(675, 0, 675, 600);
		g.drawLine(700, 0, 700, 600);
		g.drawLine(725, 0, 725, 600);
		g.drawLine(750, 0, 750, 600);
		g.drawLine(775, 0, 775, 600);
		
		
		// Y axis
		
		g.drawLine(0, 25, 800, 25);	
		g.drawLine(0, 50, 800, 50);
		g.drawLine(0, 75, 800, 75);
		g.drawLine(0, 100, 800, 100);
		g.drawLine(0, 125, 800, 125);
		g.drawLine(0, 150, 800, 150);
		g.drawLine(0, 175, 800, 175);
		g.drawLine(0, 200, 800, 200);
		g.drawLine(0, 225, 800, 225);
		g.drawLine(0, 250, 800, 250);
		g.drawLine(0, 275, 800, 275);
		g.drawLine(0, 300, 800, 300);
		g.drawLine(0, 325, 800, 325);
		g.drawLine(0, 350, 800, 350);
		g.drawLine(0, 375, 800, 375);
		g.drawLine(0, 400, 800, 400);
		g.drawLine(0, 425, 800, 425);
		g.drawLine(0, 450, 800, 450);
		g.drawLine(0, 475, 800, 475);
		g.drawLine(0, 500, 800, 500);
		g.drawLine(0, 525, 800, 525);
		g.drawLine(0, 550, 800, 550);
		g.drawLine(0, 575, 800, 575);
		
		//boundaries
		
		g.setColor(Color.GREEN);
		g.drawLine(0, 0, 800, 0);
		g.drawLine(0, 599, 800, 599);
		g.drawLine(0, 0, 0, 600);
		g.drawLine(799, 0, 799, 600);
		g.drawLine(0, 0, 800, 0);
		
		////////////////////////////////////////
		
		food.render(g);
		snake.render(g);
		
	}

}

