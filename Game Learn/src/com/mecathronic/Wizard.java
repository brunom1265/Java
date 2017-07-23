package com.mecathronic;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wizard extends GameObject{

	public Wizard(int x, int y, ID id) {
		super(x, y, id);
	}

	public void update() {
		x += velX;
		y += velY;
	}

	public void render(Graphics g) {
		
	}

	public Rectangle getBound() {
		return null;
	}

}
