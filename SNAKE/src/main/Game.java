package main;

import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import main.entities.Food;
import main.entities.Snake;
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
	
	private Graphics g;
	private BufferStrategy bs;
	
	private Window window;
	private Food food;
	private Snake snake;
	
	public Game(String title, int width, int height){
		
		this.title = title;
		this.width = width;
		this.height = height;
		
		window = new Window(title, width, height);
		food = new Food();
		snake = new Snake();
		
		State.setState(new GameState(food, snake));
		
	}
	
	public void tick() {
		
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
				System.out.println(counter);
				counter = 0;
				counterDiff = 0;
			}
			
			past = current;
			
		}
		
		stop();
		
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

