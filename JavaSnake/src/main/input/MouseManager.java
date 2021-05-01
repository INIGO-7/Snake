package main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{

	private int mouseX, mouseY;
	private boolean leftClick, rightClick;

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) leftClick = true;
		if(e.getButton() == MouseEvent.BUTTON3) rightClick = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) leftClick = false;
		if(e.getButton() == MouseEvent.BUTTON3) rightClick = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public float getMouseX() {
		return mouseX;
	}
	
	public float getMouseY() {
		return mouseY;
	}
	
	public boolean getLeftClick() {
		return leftClick;
	}
	
	public boolean getRightClick() {
		return rightClick;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

}