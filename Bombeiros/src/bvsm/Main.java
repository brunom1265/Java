package bvsm;

import javax.swing.JFrame;

import bvsm.panel.BasePanel;
import bvsm.panel.LoadingPanel;
import bvsm.panel.LoginPanel;

public class Main {
	
	private String mFrameTitle = "Bombeiros Voluntários de Salvaterra de Magos";
	private static final int width = 1104;
	private static final int height = 720;
	
	protected JFrame mainFrame;
	protected JFrame loadingFrame;
	
	BasePanel loginPanel;
	BasePanel mainPanel;
	BasePanel loadingPanel;
		
	public Main(){
		
		loadingFrame = new JFrame("Loading");
		setFrame(loadingFrame, 350, 150, null, false, true);

		loadingPanel = new LoadingPanel(null, loadingFrame, "Loading", 0, 0, 350, 150);
		loadingFrame.toFront();
		loadingPanel.setVisible(true);
		loadingFrame.setVisible(true);

		mainFrame = new JFrame(mFrameTitle);
		loginPanel = new LoginPanel(null, mainFrame, mFrameTitle, 0, 0, width, height);
		loginPanel.setVisible(true);

	}
	
	public static void main(String[] args) {
		Main main = new Main();
		setFrame(main.mainFrame, width, height, null, true);
		main.loadingFrame.dispose();
		main.mainFrame.setVisible(true);
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
	
	public static void setFrame(JFrame frame, int width, int height, JFrame location, boolean visible, boolean border){
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(location);
		frame.setLayout(null);
		frame.setUndecorated(border);
		frame.setVisible(visible);

	}
	
}
