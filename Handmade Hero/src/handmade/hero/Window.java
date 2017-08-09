package handmade.hero;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Graphics g;
	
	public Window(String title){
		setTitle(title);
		setSize(new Dimension(800, 600));
		setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		setVisible(true);
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
		}
		
		
	}
	
	private void createWindow(){
		
	}
	
	public void initiateGraphics(){
		
	}
	
}
