package bvsm.panel.privilege;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import bvsm.panel.BasePanel;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.Questions;
import bvsm.users.User;
import bvsm.users.UsersManager;

public class QuestionManager extends BasePanel{

	DefaultTableModel questionModel;
	Questions questions;
	UsersManager users;
	User user;
	boolean state = false;

	public QuestionManager(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	@SuppressWarnings("unchecked")
	protected void createComponents() {
		
		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);
		user = new User();
		
		createButton("Utilizador", 100, 100);
		createButton("Perguntas", 220, 100);
		createButton("Estatísticas", 340, 100);

		
		//Utilizador
		createButton("Editar", "editar", 100, 400);
		createLabel("Nome: ", "nome", 100, 200);
		createLabel("Apelido: ", "apelido", 350, 200);
		createLabel("Idade: ", "idade", 100, 300);
		createLabel("Privilégio: ", "privilegio", 250, 300);
		createLabel(800, 0, 300,300, images.getGif("bombeiros"));

		createJTextArea("textNome", "", 170, 200, 150, 30).setEditable(false);
		createJTextArea("textApelido", "", 440, 200, 150, 30).setEditable(false);
		createJTextArea("textIdade", "", 170, 300, 50, 30).setEditable(false);
		createJTextArea("textPrivilegio", "", 360, 300, 150, 30).setEditable(false);


		//Perguntas
		createButton("Adicionar Pergunta", 100, 550, 145, 35, false);
		createButton("Eliminar Pergunta", 250, 550, 145, 35, false);
		createButton("Gravar", 400, 550, 135, 35, false);
		createButton("Voltar", "voltarDefinicoes", 100, 600, true);
		
		createComboBox(topic, "topic", 100, 180, 150, 30, false);
		createComboBox(subTopic, "subTopic", 280, 180, 150, 30, false);
		createComboBox(subsubTopic, "subsubTopic", 460, 180, 150, 30, false);

		String[] questionsHeader = { "Pergunta", "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4" };

		createTable("questionTable", 100, 250, 900, 270, false, questionsHeader, this.jpanel);

		questions = new Questions(cbm, db);
		users = new UsersManager(db);
				
		questionModel = (DefaultTableModel) getTable("questionTable").getModel();
		cleanTable(questionModel);

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
		
		String type = e.getActionCommand();
		
		if (type == "Perguntas") {
			state = true;
			cleanTable(questionModel);

			JComboBox<String> cb = getComboBox("topic");
			cbm.updateCombo(cb, getComboBox("topic"), getComboBox("subTopic"), getComboBox("subsubTopic"));
			try {
				questions.getQuestions(questionModel, cbm.b3);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			getComboBox("topic").setVisible(true);
			getComboBox("subTopic").setVisible(true);
			getComboBox("subsubTopic").setVisible(true);
			getButton("Adicionar Pergunta").setVisible(true);
			getButton("Eliminar Pergunta").setVisible(true);
			setTableVisible("questionTable", true);
			
			getJTextArea("textNome").setVisible(false);
			getJTextArea("textApelido").setVisible(false);
			getJTextArea("textIdade").setVisible(false);
			
			getLabel("nome").setVisible(false);
			getLabel("apelido").setVisible(false);
			getLabel("idade").setVisible(false);
			getLabel("privilegio").setVisible(false);
			getButton("editar").setVisible(false);


		}
		
		if(type == "Utilizador") {
			getJTextArea("textNome").setVisible(true);
			getJTextArea("textApelido").setVisible(true);
			getJTextArea("textIdade").setVisible(true);
			
			getLabel("nome").setVisible(true);
			getLabel("apelido").setVisible(true);
			getLabel("idade").setVisible(true);
			getLabel("privilegio").setVisible(true);
			
			getButton("editar").setVisible(true);
			
			getComboBox("topic").setVisible(false);
			getComboBox("subTopic").setVisible(false);
			getComboBox("subsubTopic").setVisible(false);
			getButton("Adicionar Pergunta").setVisible(false);
			getButton("Eliminar Pergunta").setVisible(false);
			setTableVisible("questionTable", false);

		}
		
		if (type == "Voltar") {
			setVisible(false);
			previous.setVisible(true);
		}
		
		if (type == "Adicionar Pergunta") {
			questions.addQuestion(questionModel);
		}
		if (type == "Eliminar Pergunta") {
			try {
				questions.deleteQuestion(getTable("questionTable").getSelectedRow() + 1);
				cleanTable(questionModel);
				questions.getQuestions(questionModel, cbm.b3);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if (type == "Gravar") {
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
		
		if(type == "comboBoxChanged"){
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
