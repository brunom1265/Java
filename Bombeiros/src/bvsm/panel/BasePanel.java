package bvsm.panel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bvsm.database.Database;
import bvsm.images.GetImages;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.panel.tools.Tools;
import bvsm.users.User;

public abstract class BasePanel extends Tools{
	
	public JPanel jpanel;
	protected GetImages images;
	public Database db = new Database(this);
	public BasePanel previous;
	protected ComboBoxManager cbm;
	public User user;
	
	public static String[][] topic = { { "Incêndio", "Saúde", "Comunicações" } };

	public static String[][] subTopic = { { "Geral", "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

	public static String[][][] subsubTopic = { { { "Fenomenologia da Combustão" }, { "Extintores", "Bombas" }, { "EPI", "Hidrantes" } },
			{ { "SBV", "PCR" }, { "Abordagem da Vítima", "2" }, { "123", "456" } }, { { "Tipos" } } };


	public BasePanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height){
      
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		images = new GetImages();
		jpanel = new JPanel();
		jpanel.setName(name);
		jpanel.setBounds(x, y, width, height);
		jpanel.setBackground(Color.RED);
		this.previous = previous;
		jpanel.setLayout(null);
		jpanel.setVisible(false);
		createComponents();
		tasks();
		addComponents(jpanel);
		frame.add(jpanel);
		
	}
	
	protected void createComponents(){
		
	}
	
	protected void tasks() {
		
	}
	
	public void setVisible(boolean visible){
		jpanel.setVisible(visible);
	}
	
	public void print(String temp){
		System.out.println(temp);
	}
	
	public void print(int temp){
		System.out.println(temp);
	}
	
	protected void setUser(User user){
		this.user = user;
	}
	
}
