package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.QuestionsEngine;

public class PerguntasPanel extends BasePanel{
		
	QuestionsEngine qe;
	
	public PerguntasPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents(){
				

		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Extintores", "Bombas", "V�rias" }, { "EPI", "Hidrantes", "V�rias" }, { "EPI", "Hidrantes", "V�rias" } },
				{ { "SBV", "PCR", "V�rias"}, { "1", "2", "V�rias" } , { "3", "4", "5", "V�rias" } }, { { "Tipos", "V�rias" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);
		qe = new QuestionsEngine(cbm, db);
		
		createComboBox(topic, "topic", 100, 100, 150, 30, true);
		createComboBox(subTopic, "subTopic", 280, 100, 150, 30, true);
		createComboBox(subsubTopic, "subsubTopic", 460, 100, 150, 30, true);
		
		createButton("Iniciar teste", "iniciar", 100, 200);
		createButton("Voltar", "voltarDefinicoes", 100, 600);
		createQuestionArea();
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
        
		if(e.getActionCommand() == "Iniciar teste"){
			getButton("iniciar").setVisible(false);
			createQuestionArea();
			setRadioBoxVisible(true);
			setComboBoxEditable(false);
			qe.start(20);
		}
		
		if(e.getActionCommand() == "Voltar"){
			setVisible(false);
			previous.setVisible(true);
		}
		
		if (e.getActionCommand() == "comboBoxChanged") {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			if (cb.isPopupVisible()) {
				cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			}
		}
	}

	private void createQuestionArea() {
		createButtonGroup();
		createRadioButton("Answer1", 100, 300, 400, 40, false).setSelected(true);
		createRadioButton("Answer2", 100, 350, 400, 40, false);
		createRadioButton("Answer3", 100, 400, 400, 40, false);
		
	}
	
}
