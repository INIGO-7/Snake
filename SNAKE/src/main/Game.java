package main;

public class Game implements Runnable{

	private Boolean running = false;
	private Thread t;
	private long current, counterDiff, past;
	private double updateDiff, rate;
	
	private int counter;
	
	public Game(){
		
		
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
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

