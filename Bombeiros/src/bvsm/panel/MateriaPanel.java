package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class MateriaPanel extends BasePanel{

	public MateriaPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}
	
	protected void createComponents(){
		createButton("Voltar", "voltarDefinicoes", 100, 600);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Voltar"){
			setVisible(false);
			previous.setVisible(true);
		}
	}

}
