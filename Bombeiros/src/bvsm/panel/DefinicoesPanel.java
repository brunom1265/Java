package bvsm.panel;

import javax.swing.JFrame;

public class DefinicoesPanel extends BasePanel{

	public DefinicoesPanel(JFrame frame, String name, int x, int y, int width, int height) {
		super(frame, name, x, y, width, height);
	}
	
	protected void createComponents(){
		createLabel("Utilizador: ", 100, 100);
		createLabel("Password: ", 100, 150);
		createJTextArea("utilizadorLogin", "", 200, 100, 110, 30);
		createJTextArea("utilizadorLogin", "", 200, 100, 110, 30);
		createButton("Entrar", "entrarLogin", 200, 200);
	}
}