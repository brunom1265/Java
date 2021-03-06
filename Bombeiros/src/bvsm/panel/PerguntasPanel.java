package bvsm.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.QuestionsEngine;

public class PerguntasPanel extends BasePanel{
		
	QuestionsEngine qe;
	String[] questions;
	
	boolean end = false;

	
	public PerguntasPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents(){

		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Geral", "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Fenomenologia da Combust�o"}, { "Extintores", "Bombas" }, { "EPI", "Hidrantes" } },
				{ { "SBV", "PCR" }, { "Abordagem da V�tima", "2" }, { "123", "456" } }, { { "Tipos" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);
		qe = new QuestionsEngine(cbm, db);
		
		int comboWidth = 150;
		int comboHeight = 30;
		int comboY = 40;
		
		createComboBox(topic, "topic", 100, comboY, comboWidth, comboHeight, true);
		createComboBox(subTopic, "subTopic", 280, comboY, comboWidth, comboHeight, true);
		createComboBox(subsubTopic, "subsubTopic", 460, comboY, comboWidth, comboHeight, true);
		
		String[] questionsSize = {"10", "20", "30", "40"};
		
		createComboBox(questionsSize, "questionsSize", 640, comboY, comboWidth, comboHeight, true);
		
		createLabel("", "question", 100, 90, 800, 130);
		createLabel("", "note", 850, 550, 200, 50);
		createLabel(0, 0, this.jpanel.getWidth(), this.jpanel.getHeight(), images.getImage("Capa"));

		createButton("Iniciar teste", "iniciar", 100, 120);
		createButton("Anterior", "anterior", 100, 500, false);
		createButton("Proxima", "proxima", 600, 500, false);
		createButton("Voltar", "voltar", 100, 600);
		createButton("Parar", "parar", 100, 600, false);
		createButton("Novo Teste", "novoTeste", 100, 600, false);
		
		createQuestionArea();

	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		
		String type = e.getActionCommand();
        
		if(type == "Iniciar teste"){
			end = false;
			getButton("iniciar").setVisible(false);
			
			createQuestionArea();
			setRadioButton(true);
			setRadioBoxVisible(true);
			setComboBoxEditable(false);
			
			qe.start(Integer.parseInt((String) getComboBox("questionsSize").getSelectedItem()));
			
			getButton("proxima").setVisible(true);
			getButton("voltar").setVisible(false);
			getButton("parar").setVisible(true);
			
			questions = qe.getQuestions(true);
			
			getLabel("question").setText("<html>" + questions[0] + "</html>");
			for(int i = 0; i < 4; i++){
				getRadioButton().get(i).setText(questions[i + 1]);
				getRadioButton().get(i).setOpaque(false);;

			}

			getLabel("question").setVisible(true);

		}
		
		if(type == "Answer1"){
			qe.setSelected(1);
		}else if(type == "Answer2"){
			qe.setSelected(2);
			
		} else if(type == "Answer3"){
			qe.setSelected(3);
			
		} else if(type == "Answer4"){
			qe.setSelected(4);
		}
		
		if(type == "Voltar"){
			setVisible(false);
			previous.setVisible(true);

		}
		
		if(type == "Proxima"){
			
			questions = qe.getQuestions(true);

			getQuestion();
			if(qe.atualQuestion == qe.questionsSize - 1){
				getButton("proxima").setVisible(false);;
			}
			getButton("anterior").setVisible(true);

			getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);
			
			cleanRadio();


		}
		
		if(type == "Anterior"){
			questions = qe.getQuestions(false);

			getQuestion();
			if(qe.atualQuestion == 1){
				getButton("anterior").setVisible(false);
			}
			getButton("proxima").setVisible(true);
			getRadioButton().get(qe.selectedQuestion[qe.atualQuestion]).setSelected(true);
			
			cleanRadio();

		}
		
		if(type == "Parar") {
			end = true;
			qe.finalize();
			getButton("parar").setVisible(false);
			getLabel("note").setText("Nota: " + Double.toString(qe.note));
			setRadioButton(false);
			getButton("novoTeste").setVisible(true);;
			getLabel("note").setVisible(true);
			cleanRadio();
						
		}
		
		if(type == "Novo Teste"){
			setRadioBoxVisible(false);
			setComboBoxEditable(true);
			getButton("iniciar").setVisible(true);
			getLabel("question").setVisible(false);
			getButton("proxima").setVisible(false);
			getButton("anterior").setVisible(false);
			getButton("voltar").setVisible(true);
			getLabel("note").setVisible(false);
			getButton("novoTeste").setVisible(false);
			
			qe.atualQuestion = 0;

		}
		
		if (type == "comboBoxChanged") {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			if (cb.isPopupVisible()) {
				cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			}
		}
	}

	private void createQuestionArea() {
		createButtonGroup();
		int width = 900;
		int radioWidth = 100;
		
		createRadioButton("Answer1", radioWidth, 250, width, 40, false).setSelected(true);
		createRadioButton("Answer2", radioWidth, 300, width, 40, false);
		createRadioButton("Answer3", radioWidth, 350, width, 40, false);
		createRadioButton("Answer4", radioWidth, 400, width, 40, false);
		
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
	
	private void cleanRadio(){
		if(end){
			for(int i = 0; i < 4; i++){
				getRadioButton().get(i).setOpaque(false);
			}
			
			changeColor((qe.selectedQuestion[qe.atualQuestion]));
		}

	}
	
	private void changeColor(int i){
		
		int aq = qe.atualQuestion;
		int sq = qe.selectedQuestion[aq];
		int rq = qe.rightQuestion[aq] - 1;
		
		if(rq == sq){
			getRadioButton().get(sq).setOpaque(true);

			getRadioButton().get(sq).setBackground(Color.GREEN);
		}
		else{
			getRadioButton().get(rq).setOpaque(true);
			getRadioButton().get(sq).setOpaque(true);

			getRadioButton().get(rq).setBackground(Color.GREEN);
			getRadioButton().get(sq).setBackground(Color.YELLOW);

		}
	}
}
