package com.mecathronic.walker.graphics;

public class Screen {
	private int width, height;
	int pixels[];
			
	public Screen(int width, int height, int pixels[]){
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public void setBackground(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				pixels[x + y * width] = 0xd3d3d3;
			}
		}
	}
	
	public void setPixel(int x, int y){
		pixels[x + y * width] = 0x000000;
	}
}
