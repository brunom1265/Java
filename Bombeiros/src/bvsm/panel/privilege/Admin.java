package bvsm.panel.privilege;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import bvsm.panel.BasePanel;

public class Admin extends BasePanel{

	public Admin(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {
		createButton("Utilizadores", 100, 100);
		createButton("Perguntas", 230, 100);
		
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
