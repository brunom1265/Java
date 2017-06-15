package bvsm.panel;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.QuestionsEngine;

public class PerguntasPanel extends BasePanel{
		
	QuestionsEngine qe;
	String[] questions;
	
	public PerguntasPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents(){
				

		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Extintores", "Bombas", "V�rias" }, { "EPI", "Hidrantes", "V�rias" }, { "EPI", "Hidrantes", "V�rias" } },
				{ { "SBV", "PCR", "V�rias"}, { "Abordagem da V�tima", "2", "V�rias" } , { "3", "4", "5", "V�rias" } }, { { "Tipos", "V�rias" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);
		qe = new QuestionsEngine(cbm, db);
				
		createComboBox(topic, "topic", 100, 100, 150, 30, true);
		createComboBox(subTopic, "subTopic", 280, 100, 150, 30, true);
		createComboBox(subsubTopic, "subsubTopic", 460, 100, 150, 30, true);
		
		createLabel("", "question", 100, 200, 500, 100);
		createLabel("", "note", 600, 250, 200, 50);
		
		createButton("Iniciar teste", "iniciar", 100, 200);
		createButton("Anterior", "anterior", 100, 500, false);
		createButton("Proxima", "proxima", 600, 500, false);
		createButton("Voltar", "voltar", 100, 600);
		createButton("Parar", "parar", 100, 600, false);
		createButton("Novo Teste", "novoTeste", 100, 600, false);
		createQuestionArea();
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
        
		if(e.getActionCommand() == "Iniciar teste"){
			getButton("iniciar").setVisible(false);
			createQuestionArea();
			setRadioButton(true);
			setRadioBoxVisible(true);
			setComboBoxEditable(false);
			qe.start(10);
			getButton("proxima").setVisible(true);
			getButton("voltar").setVisible(false);
			getButton("parar").setVisible(true);
			
			questions = qe.getQuestions(true);
			
			getLabel("question").setText("<html>" + questions[0] + "</html>");
			getRadioButton().get(0).setText(questions[1]);
			getRadioButton().get(1).setText(questions[2]);
			getRadioButton().get(2).setText(questions[3]);
			getRadioButton().get(3).setText(questions[4]);
			getLabel("question").setVisible(true);

		}
		
		if(e.getActionCommand() == "Answer1"){
			qe.setSelected(1);
		}else if(e.getActionCommand() == "Answer2"){
			qe.setSelected(2);
			
		} else if(e.getActionCommand() == "Answer3"){
			qe.setSelected(3);
			
		} else if(e.getActionCommand() == "Answer4"){
			qe.setSelected(4);
		}
		
		if(e.getActionCommand() == "Voltar"){
			setVisible(false);
			previous.setVisible(true);

		}
		
		if(e.getActionCommand() == "Proxima"){
			questions = qe.getQuestions(true);

			getQuestion();
			if(qe.atualQuestion == qe.questionsSize - 1){
				getButton("proxima").setVisible(false);;
				getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);
			} else{
				getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);
			}
				getButton("anterior").setVisible(true);;

		}
		
		if(e.getActionCommand() == "Anterior"){
			questions = qe.getQuestions(false);

			getQuestion();
			if(qe.atualQuestion == 1){
				getButton("anterior").setVisible(false);
				getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);

			} else{
				getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);
			}
			getButton("proxima").setVisible(true);;

		}
		
		if(e.getActionCommand() == "Parar") {
			qe.finalize();
			getButton("parar").setVisible(false);
			getLabel("note").setText("Nota: " + Double.toString(qe.note));
			setRadioButton(false);
			getButton("novoTeste").setVisible(true);;
			getLabel("note").setVisible(true);

		}
		
		if(e.getActionCommand() == "Novo Teste"){
			setRadioBoxVisible(false);
			setComboBoxEditable(true);
			getButton("iniciar").setVisible(true);
			getLabel("question").setVisible(false);
			getButton("proxima").setVisible(false);
			getButton("anterior").setVisible(false);
			getButton("voltar").setVisible(true);
			getLabel("note").setVisible(false);
			getButton("novoTeste").setVisible(false);

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
		createRadioButton("Answer4", 100, 450, 400, 40, false);
		
	}
	
	private void getQuestion(){
		if(questions != null){
			getLabel("question").setText("<html>" + questions[0] + "</html>");
			getRadioButton().get(0).setText(questions[1]);
			getRadioButton().get(1).setText(questions[2]);
			getRadioButton().get(2).setText(questions[3]);
			getRadioButton().get(3).setText(questions[4]);
		}
	}
	private void setRadioButton(boolean enabled){
		getRadioButton().get(0).setEnabled(enabled);
		getRadioButton().get(1).setEnabled(enabled);
		getRadioButton().get(2).setEnabled(enabled);
		getRadioButton().get(3).setEnabled(enabled);
	}
}
