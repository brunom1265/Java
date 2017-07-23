package bvsm.panel.privilege;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import bvsm.panel.BasePanel;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.Questions;
import bvsm.users.UsersManager;

public class QuestionManager extends BasePanel{

	DefaultTableModel questionModel;
	Questions questions;
	UsersManager users;

	boolean state = false;

	public QuestionManager(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	@SuppressWarnings("unchecked")
	protected void createComponents() {
		
		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Geral", "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Fenomenologia da Combust�o" }, { "Extintores", "Bombas" }, { "EPI", "Hidrantes" } },
				{ { "SBV", "PCR" }, { "Abordagem da V�tima", "2" }, { "123", "456" } }, { { "Tipos" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);

		createButton("Perguntas", 100, 100);
		
		createButton("Adicionar Pergunta", 100, 550, 145, 35, true);
		createButton("Eliminar Pergunta", 250, 550, 145, 35, true);
		createButton("Gravar", 400, 550, 135, 35, true);
		createButton("Voltar", "voltarDefinicoes", 100, 600);

		createComboBox(topic, "topic", 100, 180, 200, 30, true);
		createComboBox(subTopic, "subTopic", 330, 180, 200, 30, true);
		createComboBox(subsubTopic, "subsubTopic", 510, 180, 200, 30, true);

		String[] questionsHeader = { "Pergunta", "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4" };

		createTable("questionTable", 100, 250, 900, 270, false, questionsHeader, this.jpanel);

		questions = new Questions(cbm, db);
		users = new UsersManager(db);

		questionModel = (DefaultTableModel) getTable("questionTable").getModel();
		
		JComboBox<String> cb = getComboBox("topic");
		cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
		try {
			questions.getQuestions(questionModel, cbm.b3);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Perguntas") {
			state = true;
			cleanTable(questionModel);

			JComboBox<String> cb = getComboBox("topic");
			cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			try {
				questions.getQuestions(questionModel, cbm.b3);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand() == "Voltar") {
			setVisible(false);
			previous.setVisible(true);
		}
		
		if (e.getActionCommand() == "Adicionar Pergunta") {
			questions.addQuestion(questionModel);
		}
		if (e.getActionCommand() == "Eliminar Pergunta") {
			try {
				questions.deleteQuestion(getTable("questionTable").getSelectedRow() + 1);
				cleanTable(questionModel);
				questions.getQuestions(questionModel, cbm.b3);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand() == "Gravar") {
			if (state) {
				try {
					questions.saveQuestions(db.getTableSize(questions.getTheme(), cbm.b3));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				users.saveUsers();
			}
		}
		
		if(e.getActionCommand() == "comboBoxChanged"){
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			if (cb.isPopupVisible()) {
				cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
				cleanTable(questionModel);
				try {
					questions.getQuestions(questionModel, cbm.b3);
				} catch (SQLException e1) {
					try {
						db.createTable(questions.getTheme());
						questions.getQuestions(questionModel, cbm.b3);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}

				}
				questions.size = 0;
			}
		}
	}

	private void cleanTable(DefaultTableModel model) {
		int RowsSize = model.getRowCount();
		for (int i = RowsSize - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
	
}
