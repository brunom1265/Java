package com.mecathronic;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
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
	
}
