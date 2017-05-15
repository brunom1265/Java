package bvsm.panel.privilege;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import bvsm.panel.BasePanel;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.Questions;

public class Admin extends BasePanel {

	DefaultTableModel model;
	DefaultTableModel questionModel;

	Questions questions;

	String p = "";

	public Admin(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {

		String[][] topic = { { "Inc�ndio", "Sa�de", "Comunica��es" } };

		String[][] subTopic = { { "Florestal", "Urbano" }, { "TS", "TAT", "TAS" }, { "Radios" } };

		String[][][] subsubTopic = { { { "Extintores", "Bombas" }, { "EPI", "Hidrantes" }, { "EPI", "Hidrantes" } },
				{ { "SBV", "PCR" }, { "1", "2" } }, { { "Tipos" } } };

		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);

		createButton("Utilizadores", 100, 100);
		createButton("Perguntas", 230, 100);

		createButton("Adicionar Utilizador", 100, 550, 145, 35);
		createButton("Gravar", 250, 550, 135, 35, false);
		createButton("Adicionar Pergunta", 100, 550, 145, 35, false);

		createButton("Voltar", "voltarDefinicoes", 100, 600);
		createLabel("Utilizador: ", "labelUtilizador", 100, 175);
		createJTextArea("textUtilizador", "", 200, 175, 300, 35);
		createButton("Procurar", 550, 175);

		createComboBox(topic, "topic", 100, 180, 150, 30, false);
		createComboBox(subTopic, "subTopic", 280, 180, 150, 30, false);
		createComboBox(subsubTopic, "subsubTopic", 460, 180, 150, 30, false);

		createPanel("mainPanel", 100, 250, 600, 270);

		getPanel("mainPanel").add(createTable("mainTable", 0, 6, 600, 270, true));
		getPanel("mainPanel").add(createTable("questionTable", 0, 6, 600, 270, true));

		questions = new Questions(cbm, db);

		model = (DefaultTableModel) getPanelTable("mainTable").getModel();
		questionModel = (DefaultTableModel) getPanelTable("questionTable").getModel();

		getAllUsers();

	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Perguntas") {

			cleanTable(questionModel);
			
			JComboBox<String> cb = getComboBox("topic");
			cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			try {
				questions.getQuestions(questionModel);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			getComboBox("topic").setVisible(true);
			getComboBox("subTopic").setVisible(true);
			getComboBox("subsubTopic").setVisible(true);
			getLabel("labelUtilizador").setVisible(false);
			getTextArea("textUtilizador").setVisible(false);
			getButton("Adicionar Pergunta").setVisible(true);
			getButton("Procurar").setVisible(false);
			getButton("Adicionar Utilizador").setVisible(false);
			getButton("Gravar").setVisible(true);

			getPanelTable("mainTable").setVisible(false);
			getPanelTable("questionTable").setVisible(true);

		}

		if (e.getActionCommand() == "Utilizadores") {

			getComboBox("topic").setVisible(false);
			getComboBox("subTopic").setVisible(false);
			getComboBox("subsubTopic").setVisible(false);
			getLabel("labelUtilizador").setVisible(true);
			getTextArea("textUtilizador").setVisible(true);
			getButton("Adicionar Pergunta").setVisible(false);
			getButton("Gravar").setVisible(false);
			getButton("Procurar").setVisible(true);
			getButton("Adicionar Utilizador").setVisible(true);

			getPanelTable("mainTable").setVisible(true);
			getPanelTable("questionTable").setVisible(false);

			getAllUsers();
		}

		if (e.getActionCommand() == "Voltar")

		{
			setVisible(false);
			previous.setVisible(true);
		}

		if (e.getActionCommand() == "Adicionar Utilizador") {
		}
		
		if (e.getActionCommand() == "Adicionar Pergunta") {
			questions.addQuestion(questionModel);
			JComboBox<String> cb = getComboBox("topic");
			cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
		}

		if (e.getActionCommand() == "Gravar") {
			questions.saveQuestions();
			
		}

		if (e.getActionCommand() == "comboBoxChanged") {
			JComboBox<String> cb = (JComboBox<String>) e.getSource();
			if (cb.isPopupVisible()) {
				cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			}
			
		}
	}

	private void getAllUsers() {

		cleanTable(model);

		try {
			ResultSet res = db.getUsers();
			while (res.next()) {
				model.addRow(new Object[] { res.getString(5), 
											res.getString(6), 
											res.getString(7), 
											res.getString(2),
											res.getString(3), 
											verifyNumber(res.getInt(4))
			});

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private String verifyNumber(int number) {
		if (number == 1) return "Administrador";
		if (number == 2) return "Manager";
		if (number == 3) return "Usu�rio";
		return null;
	}

	private void cleanTable(DefaultTableModel model) {
		int RowsSize = model.getRowCount();
		for (int i = RowsSize - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
