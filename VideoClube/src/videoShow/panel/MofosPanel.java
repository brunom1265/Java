package videoShow.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import videoShow.database.Database;

public class MofosPanel extends BasePanel{

	Database d;
	private int videoIndex = 130;

	public MofosPanel(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
		
		try {
			d = new Database("E:/Windows.old/Users/bruno/Videos");
		} catch (IOException e) {
			e.printStackTrace();
		}
		showText();

	}
	
	protected void createComponents(){

		createLabel("Nome Video: ", 100, 100);
		createTextField("videoName", "", 180, 105, 300, 25).setEnabled(false);
		createLabel("Nome Atriz: ", 100, 150);
		createTextField("atrizName", "",180, 150, 300, 25).setEnabled(false);
		createButton("Next", 600, 500);
		createButton("Previous", 100, 500);

	}
	
	public void actionPerformed(ActionEvent e) {

		try {
			if(e.getActionCommand() == "Next" && d.getVideoName("POP", videoIndex) != ""){
					videoIndex += 1;
					showText();
				}
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getActionCommand() == "Previous" && videoIndex > 1){
			videoIndex -= 1;
			showText();
		}
	}
	
	private void showText(){
		try {

			getTextField("videoName").setText(d.getVideoName("POP", videoIndex));
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
