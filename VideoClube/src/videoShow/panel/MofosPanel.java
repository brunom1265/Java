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
			if(e.getActionCommand() == "Next" && d.getVideoName("POP", videoIndex) != null){
					if(d.getVideoName("POP", videoIndex + 1) != null)
					videoIndex++;
					showText();

				}
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(e.getActionCommand() == "Previous" && videoIndex > 1){
			--videoIndex;
			showText();
		}
	}
	
	private void showText(){
		try {
			String tempFirst = "";
			String tempLast = "";

			String video = d.getVideoName("POP", videoIndex);
			String[] ss=video.split("_");
			for(int i=0;i<ss.length;i++)
			{
				if(i == 1) tempFirst = ss[i];
				if(i == 2) tempLast = ss[i];
			}
			String name = tempFirst.substring(0, 1).toUpperCase() + tempFirst.substring(1);
			String surname = tempLast.substring(0, 1).toUpperCase() + tempLast.substring(1);

			getTextField("videoName").setText(d.getVideoName("POP", videoIndex));
			getTextField("atrizName").setText(name + " " + surname);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
