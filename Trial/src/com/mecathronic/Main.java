package com.mecathronic;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mecathronic.walker.Walker;
import com.mecathronic.walker.graphics.Screen;
import com.mecathronic.walker.graphics.Shapes;

public class Main extends Canvas implements Runnable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private static int width = 1024;
	private static int height = 720;
	private static boolean running = false;

	
	Walker walk;
	JFrame frame;
	Thread thread;
	Shapes shape;
	private Screen screen;
	private boolean pass = false;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Main(){
		screen = new Screen(width, height, pixels);
		frame = new JFrame();
		shape = new Shapes(screen);
	}
	
	public synchronized void start() {
		walk = new Walker(400, 400);

		running = true;
		thread = new Thread(this, "Trial");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// long lastTime = System.nanoTime();
		// final double ns = 1000000000.0 / 60;

		while (running) {
			update();
			render();
		}
		stop();
	}

	int temp = 0;

	public void update() {
		temp++;
		if (temp >= 2) {
			walk.update();
			temp = 0;
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		if(!pass)screen.setBackground();
		pass = true;
		shape.DrawCircle(100, 400, 600);

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Main main = new Main();

		main.frame.setResizable(false);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setSize(width, height);
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);
		
		main.start();

	}

}