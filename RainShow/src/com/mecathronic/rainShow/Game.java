package com.mecathronic.rainShow;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mecathronic.rainShow.entity.mob.Player;
import com.mecathronic.rainShow.graphics.Screen;
import com.mecathronic.rainShow.input.Keyboard;
import com.mecathronic.rainShow.input.Mouse;
import com.mecathronic.rainShow.level.Level;
import com.mecathronic.rainShow.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	/**
	 * Made by ICENine
	 */

	private static final long serialVersionUID = 1L;

	public static final int width = 300;
	public static final int height = width / 16 * 9;
	public static final int scale = 3;

	private Thread thread;
	private JFrame frame;
	private Level SpawnLevel;
	private Player player;

	private boolean running = false;

	private Screen screen;
	private Keyboard key;
	Mouse mouse;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		
		SpawnLevel = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate(20, 62);
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		player.init(SpawnLevel);
		
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
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
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta = 0;
			}

			render();
			frames++;
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer = System.currentTimeMillis();
				frame.setTitle("frames = " + frames + ", updates = " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void update() {
		key.update();
		player.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // is like a pointer
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		screen.clear();
		SpawnLevel.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); // dispose all the graphics on the graphic
		bs.show(); // swap the buffer
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("RainShow");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
