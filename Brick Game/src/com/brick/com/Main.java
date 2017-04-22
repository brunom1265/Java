package com.brick.com;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Brick Game");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(gamePlay);
	}
}
