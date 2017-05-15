package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import bvsm.panel.tools.ComboBoxManager;

public class PerguntasPanel extends BasePanel{
		
	public PerguntasPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents(){
				

		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Extintores", "Bombas" }, { "EPI", "Hidrantes" }, { "EPI", "Hidrantes" } },
				{ { "SBV", "PCR" }, { "1", "2" } }, { { "Tipos" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);
		
		createComboBox(topic, "topic", 100, 100, 150, 30, true);
		createComboBox(subTopic, "subTopic", 280, 100, 150, 30, true);
		createComboBox(subsubTopic, "subsubTopic", 460, 100, 150, 30, true);
		
		createButton("Iniciar teste", "iniciar", 100, 200);
		createButton("Voltar", "voltarDefinicoes", 100, 600);
		createQuestionArea();
	}
	
	public void actionPerformed(ActionEvent e) {
				
		String[] incendio = {"Florestal", "Urbano"};
		String[] subFlorestal = {"Mangueiras", "Bombas"};
		String[] subUrbano = {"Extintores"};
		String[] saude = {"TS", "TAT", "TAS"};
		String[] subSaude = {"SBV"};

        String topic = "";
        
		if(e.getActionCommand() == "Iniciar teste"){
			getButton("iniciar").setVisible(false);
			createQuestionArea();
			setRadioBoxVisible(true);
			setComboBoxEditable(false);
		}
		
		if(e.getActionCommand() == "Voltar"){
			setVisible(false);
			previous.setVisible(true);
		}
	}

	private void createQuestionArea() {
		createButtonGroup();
		createRadioButton("Answer1", 100, 300, 400, 40, false).setSelected(true);
		createRadioButton("Answer2", 100, 350, 400, 40, false);
		createRadioButton("Answer3", 100, 400, 400, 40, false);
		
	}
	
}
