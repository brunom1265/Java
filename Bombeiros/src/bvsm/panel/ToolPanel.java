package bvsm.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class ToolPanel extends BasePanel{

	public ToolPanel(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
	}

	protected void createComponents(){
		createButton("ola", 10, 10);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}