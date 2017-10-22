package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import bvsm.panel.privilege.Admin;
import bvsm.panel.privilege.QuestionManager;
import bvsm.users.User;
import bvsm.users.UserPanel;

public class MainPanel extends BasePanel{
	
	BasePanel materiaPanel;
	BasePanel questionsPanel;
	BasePanel adminPanel;
	BasePanel managerPanel;
	BasePanel userPanel;
	
	JFrame frame;
	User user;
	
	public MainPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);

		materiaPanel = new MateriaPanel(this, frame, "Materia", 0, 0, width, height);
		questionsPanel = new PerguntasPanel(this, frame, "Perguntas", 0, 0, width, height);
		adminPanel = new Admin(this, frame, "Definicoes", 0, 0, width, height);
		managerPanel = new QuestionManager(this, frame, "Definicoes", 0, 0, width, height);
		userPanel = new UserPanel(this, frame, "Definicoes", 0, 0, width, height);
		
		user = new User();
		this.frame = frame;
	}
	
	protected void createComponents(){
		
		createLabel("", "Name", 50, 50, 220, 35);
		createLabel("Versão Alpha 1.00 ", "versão", 700, 600, 220, 35);
		createButton("Matéria", 100, 100);
		createButton("Teste", 230, 100);
		createButton("Definições", 490, 100);
		createButton("Sair", 620, 100);
		createLabel(800, 0, 300,300, images.getGif("bombeiros"));

	}
		
	public void actionPerformed(ActionEvent e) {
		String type = e.getActionCommand();
		if(type == "Teste"){
			setVisible(false);
			questionsPanel.setVisible(true);
		}
		
		if(type == "Matéria"){
			setVisible(false);
			materiaPanel.setVisible(true);
		}
		
		if(type == "Definições"){
			setVisible(false);

			switch(user.getType()) {
			case 1:
				adminPanel.getJTextArea("textNome").setText(user.getName());
				adminPanel.getJTextArea("textApelido").setText(user.getSurname());
				adminPanel.getJTextArea("textIdade").setText(String.valueOf(user.getAge()));
				
				adminPanel.setVisible(true);
				break;
			case 2:
				managerPanel.getJTextArea("textNome").setText(user.getName());
				managerPanel.getJTextArea("textApelido").setText(user.getSurname());
				managerPanel.getJTextArea("textIdade").setText(String.valueOf(user.getAge()));

				managerPanel.setVisible(true);
				break;
			case 3:
				
				userPanel.getJTextArea("textNome").setText(user.getName());
				userPanel.getJTextArea("textApelido").setText(user.getSurname());
				userPanel.getJTextArea("textIdade").setText(String.valueOf(user.getAge()));
				
				userPanel.setVisible(true);
				break;
			}
		}
		
		if(type == "Sair"){
			frame.dispose();
		}
		
	}
}
