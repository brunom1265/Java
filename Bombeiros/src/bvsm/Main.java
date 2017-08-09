package bvsm;

import javax.swing.JFrame;

import bvsm.panel.LoginPanel;
import bvsm.panel.MainPanel;
import bvsm.panel.ToolPanel;

public class Main {
	
	private String mFrameTitle = "Bombeiros Voluntários de Salvaterra de Magos";
	private static final int width = 1104;
	private static final int height = 720;
	
	protected JFrame mainFrame;
	protected JFrame toolFrame;
	
	LoginPanel loginPanel;
	MainPanel mainPanel;
	ToolPanel toolPanel;
		
	public Main(){
		
		
		mainFrame = new JFrame(mFrameTitle);
		//toolFrame = new JFrame("Tools");
		loginPanel = new LoginPanel(null, mainFrame, mFrameTitle, 0, 0, width, height);
		loginPanel.setVisible(true);
		//toolPanel = new ToolPanel(null, toolFrame, "tools", 0, 0, 220, 440);
		//toolPanel.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		setFrame(main.mainFrame, width, height, null, true);
		//int x = main.mainFrame.getWidth() + main.mainFrame.getX();
		//int y = main.mainFrame.getY();
		//setFrame(main.toolFrame, 220, 440, x, y, true);
	} 
	
	public static void setFrame(JFrame frame, int width, int height, JFrame location, boolean visible){
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(location);
		frame.setLayout(null);
		frame.setVisible(visible);
	}
	public static void setFrame(JFrame frame, int width, int height, int x, int y, boolean visible){
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(x, y);
		frame.setLayout(null);
		frame.setVisible(visible);
	}
}
