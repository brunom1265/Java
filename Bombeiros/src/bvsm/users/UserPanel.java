package bvsm.users;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import bvsm.panel.BasePanel;

public class UserPanel extends BasePanel {

	public UserPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	public void createComponents() {
		createLabel("Nome:", 200, 100);
		createLabel("Sobrenome:", "", 500, 100, 150, 35);
		createLabel("Idade:", "", 200, 200, 150, 35);
		createLabel("Tipo:", "", 200, 250, 150, 35);

		createButton("Voltar", "voltarDefinicoes", 100, 600);

		// createJTextArea("nome", "", 280, 100, 100, 35).setText(user.getName());
		// createJTextArea("sobreNome", "", 280, 100, 100,
		// 35).setText(user.getSurname());
		// createJTextArea("idade", "", 280, 100, 100, 35).setText(new
		// Integer(user.getAge()).toString());
		// createJTextArea("tipo", "", 280, 100, 100, 35).setText(user.getType());
	}

	public void actionPerformed(ActionEvent e) {
		
		String type = e.getActionCommand();
		if (type == "Voltar") {
			setVisible(false);
			previous.setVisible(true);
		}
	}
}
