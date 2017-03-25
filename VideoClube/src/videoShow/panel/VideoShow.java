package videoShow.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class VideoShow extends BasePanel{
	MofosPanel mP;
	public VideoShow(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
		mP = new MofosPanel(frame, "Mofos", 0, 0, 800, 600, Color.GRAY);
	}
	
	protected void createComponents(){
		createButton("MOFOS", 100, 100);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "MOFOS"){
			setVisible(false);
			mP.setVisible(true);
		}
	}
}