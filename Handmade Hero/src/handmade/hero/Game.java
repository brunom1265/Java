package handmade.hero;

import javax.swing.JFrame;

public class Game {
	
	JFrame frame = new JFrame();
	Window window;
	
	public Game(){
		window = new Window("Handmade Hero");
	}
	
	public static void main(String[] args){
		new Game();
	}
	
	public void win32UpdateWindow(){
		
	}
	
}
