package bvsm.panel.privilege;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import bvsm.panel.BasePanel;
import bvsm.panel.tools.ComboBoxManager;

public class QuestionManager extends BasePanel{

	DefaultTableModel model;
	
	ComboBoxManager cbm;

	protected String[] topics = {"Inc�ndio", "Sa�de", "Comunica��es"};
	protected String[] subTopic = {"Florestal", "Urbano"};
	protected String[] subsubTopic = {"Extintores", "Bombas"};
	
	public QuestionManager(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {
		
		cbm = new ComboBoxManager(topics, subTopic, subsubTopic);

		createComboBox(cbm.topic, "topic", 100, 100, 150, 30, true);
		createComboBox(cbm.subTopic, "subTopic", 300, 100, 150, 30, true);
		createComboBox(cbm.subsubTopic, "subsubTopic", 500, 100, 150, 30, true);
		
		String[] header = {"Pergunta 1", "Pergunta 2", "Pergunta 3", "Resposta"};
		
		createTable("mainTable", 0, 4, 100, 250, 600, 270);
		
		model = (DefaultTableModel) getTable("mainTable").getModel();
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//ComboboxManager
		
		String[] incendio = {"Florestal", "Urbano"};
		String[] subFlorestal = {"Mangueiras", "Bombas"};
		String[] subUrbano = {"Extintores"};
		String[] saude = {"TS", "TAT", "TAS"};
		String[] subSaude = {"SBV"};

        String topic = "";
        
        if(e.getActionCommand() == "comboBoxChanged"){
        	@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
            topic = (String)cb.getSelectedItem();
        }
	}

}
