package bvsm.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class PerguntasPanel extends BasePanel{

	String names[] = {"Fogos Florestais", "Saúde"};
	public PerguntasPanel(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
	}
	
	protected void createComponents(){
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Matéria"){
		}
	}
}
