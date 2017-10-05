package bvsm.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bvsm.users.User;

public class LoginPanel extends BasePanel{
	
	BasePanel mP;
	
	User users;
	
	String password = "";
	
	public LoginPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
		users = new User();
		
		
		mP = new MainPanel(this, frame, "mainPanel", 0, 0, width, height);
	}
	
	protected void createComponents(){
		
		createLabel("Utilizador: ", "utilizador", 100, 100, 150, 35);
		createLabel("Password: ", "password", 104, 150, 150, 35);
		createJTextArea("utilizadorLogin", "", 220, 100, 110, 30);
		createJTextArea("passwordLogin", "", 220, 150, 110, 30);

		createButton("Entrar", "entrarLogin", 200, 200);
		createLabel(800, 0, 300,300, images.getGif("bombeiros"));
	}
	
	public void actionPerformed(ActionEvent e) {
		String temp = e.getActionCommand();
		
		if(temp == "Entrar"){
			String username = getTextArea("utilizadorLogin").getText();
			
			try {
				ResultSet res = db.getUser(username, password);
				if(res == null){
					JOptionPane.showMessageDialog(null, "O username ou a password está errada");
				}else{
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
				}
			} catch (SQLException x) {
			}
		}
	}

	public void keyPressed(KeyEvent e) {

		
		int keyCode = e.getKeyCode();

		if(keyCode == KeyEvent.VK_ENTER){
			getButton("entrarLogin").doClick();
		}

		if(e.getComponent().getName() == "passwordLogin") {

			int length = getJTextArea("passwordLogin").getText().length();
			String word = "";
			
			for(int i = 0; i < length; i++) {
				word += "*";
			}
			getJTextArea("passwordLogin").setText(word);
			if(password.length() <= length && keyCode != 20 && keyCode != 8) password += e.getKeyChar();
			else if(keyCode != 20 && length > 0){
				password = password.substring(0, password.length() - 1);
			}
			System.out.println(password);
		}
				
		if(e.getComponent().getName() == "utilizadorLogin" || e.getComponent().getName() == "passwordLogin"){
			if((keyCode >= 65 && keyCode <= 90) || (keyCode >= 48 && keyCode <= 57) ||  keyCode == 8 || keyCode == 127){
			} else if(keyCode == 9){
				getJTextArea("passwordLogin").grabFocus();
				e.consume();
			} else{
				e.consume();
			}
		}
	}
}
