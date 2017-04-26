package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class MainPanel extends BasePanel{
	
	MateriaPanel mPanel;
	PerguntasPanel pPanel;
	DefinicoesPanel dPanel;
	
	public MainPanel(JFrame frame, String name, int x, int y, int width, int height) {
		super(frame, name, x, y, width, height);
		mPanel = new MateriaPanel(frame, "Materia", 0, 0, width, height);
		pPanel = new PerguntasPanel(frame, "Perguntas", 0, 0, width, height);
		dPanel = new DefinicoesPanel(frame, "Definicoes", 0, 0, width, height);
	}
	
	protected void createComponents(){
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		createButton("Mat�ria", 100, 100);
		createButton("Perguntas", 230, 100);
		createButton("Defini��es", 360, 100);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Perguntas"){
			setVisible(false);
			pPanel.setVisible(true);
		}
		
		if(e.getActionCommand() == "Mat�ria"){
			setVisible(false);
			mPanel.setVisible(true);
		}
		
		if(e.getActionCommand() == "Defini��es"){
			setVisible(false);
			dPanel.setVisible(true);
		}
	}
}
