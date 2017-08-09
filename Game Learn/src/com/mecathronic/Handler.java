package com.mecathronic;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean up = false, down = false, right = false, left = false;
	
	public void update(){
		for(GameObject tempObject : object){
			
			tempObject.update();

		}			
	}

	public void render(Graphics g){
		for(GameObject tempObject : object){
			
			tempObject.render(g);

		}	
	}
	
	public void addObject(GameObject tempObject){
		object.add(tempObject);
		
	}
	
	public void removeObject(GameObject tempObject){
		object.remove(tempObject);
	}
	

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	
}
