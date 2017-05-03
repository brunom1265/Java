package bvsm.panel.privilege;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import bvsm.panel.BasePanel;

public class QuestionManager extends BasePanel{

	DefaultTableModel model;
	
	public QuestionManager(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {
		
		String[] topics = {"Inc�ndio", "Sa�de", "Comunica��es", "Sorteado"};
		String[] subTopic = {"Florestal", "Urbano"};
		String[] subsubTopic = {"Extintores", "Bombas"};
		
		createComboBox(topics, "topic", 100, 100, 150, 30, true);
		createComboBox(subTopic, "subTopic", 300, 100, 150, 30, true);
		createComboBox(subsubTopic, "subsubTopic", 500, 100, 150, 30, true);
		
		createTable("mainTable", 0, 4, 100, 250, 600, 270);
		
		model = (DefaultTableModel) getTable("mainTable").getModel();
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
