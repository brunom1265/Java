package bvsm.panel;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import bvsm.users.User;

public class LoginPanel extends BasePanel{
	
	BasePanel mP;
	
	User users;
	
	public LoginPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
		users = new User();
		
		
		mP = new MainPanel(this, frame, "mainPanel", 0, 0, width, height);
	}
	
	protected void createComponents(){
		
		createLabel("Utilizador: ", "utilizador", 100, 100, 150, 35);
		createLabel("Password: ", "password", 104, 150, 150, 35);
		createJTextArea("utilizadorLogin", "", 220, 100, 110, 30).setText("ICENine");
		createJTextArea("passwordLogin", "", 220, 150, 110, 30).setText("650031772");
		createButton("Entrar", "entrarLogin", 200, 200);
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
					users.setType(type);
					users.setName(res.getString(5));
					users.setSurname(res.getString(6));
					users.setAge(res.getInt(7));
					mP.getLabel("Name").setText(users.getName());
					setVisible(false);
					mP.setVisible(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
