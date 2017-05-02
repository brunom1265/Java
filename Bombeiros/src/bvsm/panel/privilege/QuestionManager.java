package bvsm.panel.privilege;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import bvsm.panel.BasePanel;

public class QuestionManager extends BasePanel{

	public QuestionManager(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {
		createPanel("mainPanel", 200, 200, 100, 100).setBackground(Color.black);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
