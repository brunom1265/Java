package videoShow.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

public class VideoShow extends BasePanel{
	BasePanel mP;
	BasePanel df;

	public VideoShow(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
		mP = new MofosPanel(frame, "DigitalPlayground", 0, 0, 800, 600, Color.GRAY);
		df = new DefinitionPanel(frame, "Definicoes", 0, 0, 800, 600, Color.GRAY);
	}
	
	protected void createComponents(){
		createButton("DigitalPlayground", "digital", 100, 100, 140, 35);
		createButton("Definições", "definicoes", 300, 100, 140, 35);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "DigitalPlayground"){
			setVisible(false);
			mP.setVisible(true);
		}
		
		if(e.getActionCommand() == "Definições"){
			setVisible(false);
			df.setVisible(true);
		}
	}
}