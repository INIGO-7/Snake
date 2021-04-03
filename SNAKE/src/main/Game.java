package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import main.entities.Food;
import main.entities.Snake;
import main.input.KeyManager;
import main.input.MouseManager;
import main.states.GameState;
import main.states.State;

public class Game implements Runnable{

	private String title;
	private int width, height;
	
	private Boolean running = false;
	private Thread t;
	private long current, counterDiff, past;
	private double updateDiff, rate;
	private int counter;
	private String fpsCounter = "0";
	
	private Graphics g;
	private BufferStrategy bs;
	
	private Window window;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private GameState gameState;
	
	public Game(String title, int width, int height){
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		window = new Window(title, width, height);
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		gameState = new GameState(keyManager, mouseManager);
		
		window.getJFrame().addKeyListener(keyManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);
		
		State.setState(gameState);
		
	}
	
	public void tick() {
		
		keyManager.tick();
		State.getState().tick();
		
	}
	
	public void render() {
		
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		//here we can start drawing:
		
		State.getState().render(g);
		
		g.setColor(getFpsCounterColor(gameState.getSnakeBody(), 782, 15));
		
		g.drawString(fpsCounter, 782, 15);
		
		//here we end drawing
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		
		past = System.nanoTime();
		rate = 1000000000/60;						//rate at which we want to update stuff.
		
		while(running) {

			current = System.nanoTime();
			updateDiff += (current-past)/rate;
			counterDiff += current-past;
			
			if(updateDiff >= 1) {				//if more than a 1/60 of a sec. has passed, then do the following...
				render();
				tick();
				counter++;
				updateDiff = 0;
			}
			
			if(counterDiff >= 1000000000) {
				setFpsCounter(counter);
				counter = 0;
				counterDiff = 0;
			}
			
			past = current;
			
		}
		
		stop();
		
	}
	
	public Color getFpsCounterColor(LinkedList<Rectangle> snakeBody, int fpsX, int fpsY) {
		
		for(Rectangle rect : snakeBody) if(rect.contains(fpsX, fpsY)) return Color.BLUE;
		return Color.GREEN;
	}
	
	public void setFpsCounter(int counter) {
		this.fpsCounter = String.valueOf(counter);
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public synchronized void start() {
		
		if(!running) running = true;
		else return;
		
		t = new Thread(this);
		t.start();
	}
	
	public synchronized void stop() {
		
		if(running) running = false;
		else return;
		
		try {t.join();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}

}

