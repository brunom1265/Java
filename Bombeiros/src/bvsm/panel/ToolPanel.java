package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class ToolPanel extends BasePanel{

	public ToolPanel(JFrame frame, String name, int x, int y, int width, int height) {
		super(frame, name, x, y, width, height);
	}

	protected void createComponents(){
		createButton("ola", 10, 10);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
