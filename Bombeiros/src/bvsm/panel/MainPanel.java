package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import bvsm.panel.privilege.Admin;
import bvsm.panel.privilege.QuestionManager;
import bvsm.users.User;

public class MainPanel extends BasePanel{
	
	BasePanel materiaPanel;
	BasePanel questionsPanel;
	BasePanel adminPanel;
	BasePanel managerPanel;
	
	JFrame frame;
	User user;
	
	public MainPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);

		materiaPanel = new MateriaPanel(this, frame, "Materia", 0, 0, width, height);
		questionsPanel = new PerguntasPanel(this, frame, "Perguntas", 0, 0, width, height);
		adminPanel = new Admin(this, frame, "Definicoes", 0, 0, width, height);
		managerPanel = new QuestionManager(this, frame, "Definicoes", 0, 0, width, height);

		user = new User();
		this.frame = frame;
	}
	
	protected void createComponents(){
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		createLabel("", "Name", 50, 50, 220, 35);
		createLabel("Versão Alpha 0.01 ", "versão", 700, 600, 220, 35);
		createButton("Matéria", 100, 100);
		createButton("Perguntas", 230, 100);
		createButton("Definições", 360, 100);
		createButton("Sair", 490, 100);
				
	}
		
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Perguntas"){
			setVisible(false);
			questionsPanel.setVisible(true);
		}
		
		if(e.getActionCommand() == "Matéria"){
			setVisible(false);
			materiaPanel.setVisible(true);
		}
		
		if(e.getActionCommand() == "Definições"){
			setVisible(false);

			if(user.getType() == 1) adminPanel.setVisible(true);
			if(user.getType() == 2) managerPanel.setVisible(true);
		}
		
		if(e.getActionCommand() == "Sair"){
			frame.dispose();
		}
		
		
		
	}
}
