package bvsm.panel;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import bvsm.panel.privilege.Admin;
import bvsm.panel.privilege.QuestionManager;

public class DefinicoesPanel extends BasePanel{

	BasePanel aP;
	BasePanel qmP;
	
	public DefinicoesPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
		aP = new Admin(this, frame, "adminPanel", 0, 0, width, height);
		qmP = new QuestionManager(this, frame, "QuestionManager", 0, 0, width, height);
	}
	
	protected void createComponents(){
		
		createLabel("Utilizador: ", "utilizador", 100, 100);
		createLabel("Password: ", "password", 100, 150);
		createJTextArea("utilizadorLogin", "", 200, 100, 110, 30);
		createJTextArea("passwordLogin", "", 200, 150, 110, 30);
		createButton("Entrar", "entrarLogin", 200, 200);
		createButton("Voltar", "voltarDefinicoes", 100, 600);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Entrar"){
			String username = getTextArea("utilizadorLogin").getText();
			String password = getTextArea("passwordLogin").getText();
			
			try {
				ResultSet res = db.getUser(username, password);
				String user = res.getString(2);
				
				if(user.equals(username)){
					int type = res.getInt(4);
					if(type == 1){
						setVisible(false);
						aP.setVisible(true);
					}
					else if(type == 2){
						setVisible(false);
						qmP.setVisible(true);
					}
					else{
						
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand() == "Voltar"){
			setVisible(false);
			previous.setVisible(true);
		}
	}
}
