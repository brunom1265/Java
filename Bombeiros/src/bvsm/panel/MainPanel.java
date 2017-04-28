package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class MainPanel extends BasePanel{
	
	BasePanel mPanel;
	BasePanel pPanel;
	BasePanel dPanel;
	
	JFrame frame;
	
	public MainPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
		mPanel = new MateriaPanel(this, frame, "Materia", 0, 0, width, height);
		pPanel = new PerguntasPanel(this, frame, "Perguntas", 0, 0, width, height);
		dPanel = new DefinicoesPanel(this, frame, "Definicoes", 0, 0, width, height);
		this.frame = frame;
	}
	
	protected void createComponents(){
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		createLabel("Vers�o Alpha 0.01 ", "vers�o", 700, 600);
		createButton("Mat�ria", 100, 100);
		createButton("Perguntas", 230, 100);
		createButton("Defini��es", 360, 100);
		createButton("Sair", 490, 100);
		
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
		
		if(e.getActionCommand() == "Sair"){
			frame.dispose();
		}
	}
}
