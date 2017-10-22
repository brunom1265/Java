package bvsm.panel.privilege;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bvsm.panel.BasePanel;
import bvsm.panel.tools.ComboBoxManager;
import bvsm.questions.Questions;
import bvsm.users.UsersManager;

public class Admin extends BasePanel {

	DefaultTableModel model;
	DefaultTableModel questionModel;

	Questions questions;
	UsersManager users;
	TableColumn sportColumn;
	String p = "";

	boolean state = false;

	public Admin(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents() {
		
		cbm = new ComboBoxManager(this, subsubTopic, topic, subTopic);

		// Main Buttons
		createButton("Utilizador", 100, 100);
		createButton("Utilizadores", 230, 100);
		createButton("Perguntas", 360, 100);
		createButton("Temas", 490, 100);
		createButton("Estatísticas", 620, 100);

		// Users Buttons
		createButton("Adicionar Utilizador", 100, 550, 145, 35, false);
		createButton("Eliminar Utilizador", 250, 550, 145, 35, false);
		createButton("Gravar", 400, 550, 145, 35, false);

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
		
		// Questions Buttons
		createButton("Adicionar Pergunta", 100, 550, 145, 35, false);
		createButton("Eliminar Pergunta", 250, 550, 145, 35, false);
		createButton("Gravar", 400, 550, 135, 35, false);

		// Info Area
		createButton("Voltar", "voltarDefinicoes", 100, 600);
		createLabel("Utilizador: ", "labelUtilizador", 100, 175, false);
		createJTextArea("textUtilizador", "", 200, 175, 300, 35, false);
		createButton("Procurar", 550, 175, false);

		// Combobox for choose
		createComboBox(topic, "topic", 100, 180, 150, 30, false);
		createComboBox(subTopic, "subTopic", 280, 180, 150, 30, false);
		createComboBox(subsubTopic, "subsubTopic", 460, 180, 150, 30, false);

		String[] mainHeader = { "Nome", "Sobrenome", "Idade", "Username", "Password", "Tipo" };
		String[] questionsHeader = { "Pergunta", "Resposta 1", "Resposta 2", "Resposta 3", "Resposta 4" };

		createTable("mainTable", 100, 250, 900, 270, false, mainHeader, this.jpanel);
		createTable("questionTable", 100, 250, 900, 270, false, questionsHeader, this.jpanel);

		String[] type = { "Administrador", "Moderador", "Utilizador" };

		sportColumn = getTable("mainTable").getColumnModel().getColumn(5);
		createComboBox(type, "type");

		sportColumn.setCellEditor(new DefaultCellEditor(getComboBox("type")));

		questions = new Questions(cbm, db);
		users = new UsersManager(db);

		model = (DefaultTableModel) getTable("mainTable").getModel();
		questionModel = (DefaultTableModel) getTable("questionTable").getModel();

		getAllUsers();

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
			getLabel("labelUtilizador").setVisible(false);
			getTextArea("textUtilizador").setVisible(false);
			
			getJTextArea("textNome").setVisible(false);
			getJTextArea("textApelido").setVisible(false);
			getJTextArea("textIdade").setVisible(false);
			
			getButton("Adicionar Pergunta").setVisible(true);
			getButton("Eliminar Pergunta").setVisible(true);

			getButton("Procurar").setVisible(false);
			getButton("Adicionar Utilizador").setVisible(false);
			getButton("Eliminar Utilizador").setVisible(false);

			
			getLabel("nome").setVisible(false);
			getLabel("apelido").setVisible(false);
			getLabel("idade").setVisible(false);
			getLabel("privilegio").setVisible(false);
			getButton("editar").setVisible(false);
			
			setTableVisible("mainTable", false);
			setTableVisible("questionTable", true);

		}
		
		if(type == "Utilizador") {
			getJTextArea("textNome").setVisible(true);
			getJTextArea("textApelido").setVisible(true);
			getJTextArea("textIdade").setVisible(true);
			
			getLabel("nome").setVisible(true);
			getLabel("apelido").setVisible(true);
			getLabel("idade").setVisible(true);
			getLabel("privilegio").setVisible(true);
			getLabel("labelUtilizador").setVisible(false);
			getTextArea("textUtilizador").setVisible(false);

			getButton("editar").setVisible(true);
			
			getComboBox("topic").setVisible(false);
			getComboBox("subTopic").setVisible(false);
			getComboBox("subsubTopic").setVisible(false);
			getButton("Adicionar Pergunta").setVisible(false);
			getButton("Eliminar Pergunta").setVisible(false);
			getButton("Procurar").setVisible(false);

			setTableVisible("questionTable", false);
			
			setTableVisible("mainTable", false);
			setTableVisible("questionTable", false);

		}

		if (type == "Utilizadores") {
			state = false;

			getComboBox("topic").setVisible(false);
			getComboBox("subTopic").setVisible(false);
			getComboBox("subsubTopic").setVisible(false);
			getLabel("labelUtilizador").setVisible(true);
			getTextArea("textUtilizador").setVisible(true);
			getButton("Adicionar Pergunta").setVisible(false);
			getButton("Eliminar Pergunta").setVisible(false);

			getButton("Procurar").setVisible(true);
			getButton("Adicionar Utilizador").setVisible(true);
			getButton("Eliminar Utilizador").setVisible(true);

			getJTextArea("textNome").setVisible(false);
			getJTextArea("textApelido").setVisible(false);
			getJTextArea("textIdade").setVisible(false);
			
			getLabel("nome").setVisible(false);
			getLabel("apelido").setVisible(false);
			getLabel("idade").setVisible(false);
			getLabel("privilegio").setVisible(false);
			getButton("editar").setVisible(false);
			
			getComboBox("topic").setVisible(false);
			getComboBox("subTopic").setVisible(false);
			getComboBox("subsubTopic").setVisible(false);
			getButton("Adicionar Pergunta").setVisible(false);
			getButton("Eliminar Pergunta").setVisible(false);
			
			setTableVisible("mainTable", true);
			setTableVisible("questionTable", false);

			getAllUsers();
		}

		if (type == "Voltar") {
			setVisible(false);
			previous.setVisible(true);
		}

		if (type == "Adicionar Utilizador") {
			users.addUser(model);
		}

		if (type == "Adicionar Pergunta") {
			questions.addQuestion(questionModel);
		}
		if (type == "Eliminar Pergunta") {
			try {
				questions.deleteQuestion(getTable("questionTable").getSelectedRow() + 1);
				cleanTable(questionModel);
				questions.getQuestions(questionModel, cbm.b3);
				JOptionPane.showMessageDialog(null, "Pergunta eliminada com sucesso");

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erro ao eliminar pergunta");

				e1.printStackTrace();
			}
		}
		
		if (type == "Eliminar Utilizador") {
			users.deleteUsers(getTable("mainTable").getSelectedRow() + 1);
			cleanTable(model);
			getAllUsers();
		}

		if (type == "Gravar") {
			if (state) {
				try {
					questions.saveQuestions(db.getTableSize(questions.getTheme(), cbm.b3));
					JOptionPane.showMessageDialog(null, "Perguntas Gravadas");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Falha ao Gravar");

					e1.printStackTrace();
				}
			} else {
				users.saveUsers();
			}
		}

		if (type == "comboBoxChanged") {
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

	private void getAllUsers() {

		cleanTable(model);

		try {
			ResultSet res = db.getUsers();
			while (res.next()) {
				model.addRow(new Object[] { res.getString(5), res.getString(6), res.getString(7), res.getString(2),
						res.getString(3) });

				model.setValueAt(verifyNumber(res.getInt(4)), res.getInt(1) - 1, 5);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private String verifyNumber(int number) {
		if (number == 1) return "Administrador";
		if (number == 2) return "Moderador";
		if (number == 3) return "Utilizador";
		return null;
	}

	private void cleanTable(DefaultTableModel model) {
		int RowsSize = model.getRowCount();
		for (int i = RowsSize - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
