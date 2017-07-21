package com.mecathronic.walker;

import java.util.Random;

public class Walker {
	public int x, y;

	Random random;
	
	public Walker(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		random = new Random();
		
		int dir = random.nextInt(100);
		
		if(dir >= 1 && dir <= 25){
			x++;
		} else if(dir > 25 && dir <= 50){
			x--;
		} else if(dir > 50 && dir <= 75){
			y++;
		} else{
			y--;
		} 
	}
}
