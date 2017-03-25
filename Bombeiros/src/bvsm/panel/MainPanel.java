package bvsm.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class MainPanel extends BasePanel{
	
	PerguntasPanel pPanel;
	
	public MainPanel(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
		pPanel = new PerguntasPanel(frame, "Perguntas", 0, 0, width, height, Color.RED);
	}
	
	protected void createComponents(){
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		createButton("Matéria", 100, 100);
		createButton("Perguntas", 230, 100);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Perguntas"){
			setVisible(false);
			pPanel.setVisible(true);
		}
	}
}
