
import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import videoShow.panel.VideoShow;

public class Main{
	
	JFrame frame;
	JPanel panel;
	JButton eButton;
	
	VideoShow vS;
	
	public Main() throws ClassNotFoundException, SQLException, IOException{
		frame = new JFrame();
		panel = new JPanel();
		vS = new VideoShow(frame, "VideoClube", 0, 0, 800, 600, Color.black);
		vS.setVisible(true);

	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		
		Main main = new Main();
		setFrame(main.frame, 800, 600, null, true);
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
