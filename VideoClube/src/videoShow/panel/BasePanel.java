package videoShow.panel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import videoShow.images.GetImages;
import videoShow.panel.tools.Tools;

public abstract class BasePanel extends Tools{
	
	public JPanel panel;
	protected GetImages images;
	
	public BasePanel(JFrame frame, String name, int x, int y, int width, int height, Color color){

		images = new GetImages();
		panel = new JPanel();
		createComponents();
		panel.setName(name);
		panel.setBounds(x, y, width, height);
		panel.setBackground(color);
		addComponents(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		frame.add(panel);
		
	}
	
	protected void createComponents(){
		
	}
	public void setVisible(boolean visible){
		panel.setVisible(visible);
	}
}
