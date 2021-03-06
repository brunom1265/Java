package bvsm.users;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import bvsm.database.Database;

public class UsersManager {
	
	public int size = 0;
	int rowSize = 0, columnSize = 0;
	boolean save = false;
	
	Database db;
	DefaultTableModel model;
	
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<String> surname = new ArrayList<String>();
	ArrayList<String> age = new ArrayList<String>();
	ArrayList<String> username = new ArrayList<String>();
	ArrayList<String> password = new ArrayList<String>();
	ArrayList<String> type = new ArrayList<String>();

	
	public UsersManager(Database db) {
		this.db = db;
	}
	
	public void addUser(DefaultTableModel model){
		this.model = model;
		if(!save){
			rowSize = model.getRowCount();

			columnSize = model.getColumnCount();

			save = true;
		}
		
		model.addRow(new Object[]{"", "", "", "", "", ""});
		name.add("");
		surname.add("");
		age.add("");
		username.add("");
		password.add("");
		type.add("");
		
		size++;

		save = false;
	}
	
	public void saveUsers(){
		for(int x = rowSize; x < (rowSize + size); x++){
			db.addUser(x + 1, (String)model.getValueAt(x,  3), (String)model.getValueAt(x,  4), verifyName((String)model.getValueAt(x,  5)), (String)model.getValueAt(x,  0), (String)model.getValueAt(x,  1), Integer.parseInt((String) model.getValueAt(x,  2)));
		}
		size = 0;
		save = false;
	}
	
	private int verifyName(String name) {
		if (name == "Administrador") return 1;
		if (name == "Moderador") return 2;
		if (name == "Utilizador") return 3;
		return 3;
	}
	
	public void deleteUsers(int id){
		db.deleteUser(id);
	}	
}
