package bvsm.questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import bvsm.database.Database;
import bvsm.panel.tools.ComboBoxManager;

public class Questions{

	int size = 0;
	int rowSize = 0, columnSize = 0;
	boolean save = false;
	
	DefaultTableModel model;
	Database db;
	ComboBoxManager cbm;
	
	ArrayList<String> q1 = new ArrayList<String>();
	ArrayList<String> a1 = new ArrayList<String>();
	ArrayList<String> a2 = new ArrayList<String>();
	ArrayList<String> a3 = new ArrayList<String>();
	
	public Questions(ComboBoxManager cbm, Database db){
		this.cbm = cbm;
		this.db = db;
	}
	
	public void addQuestion(DefaultTableModel model){
		this.model = model;
		if(!save){
			rowSize = model.getRowCount();
			columnSize = model.getColumnCount();
			save = true;
		}
		model.addRow(new Object[]{
				"",
				"",
				"",
				"",
				"",
				""
			});
		q1.add("");
		a1.add("");
		a2.add("");
		a3.add("");
		size++;
 
	}
	
	public void saveQuestions(){
		for(int x = rowSize; x < (rowSize + size); x++){
			db.insertQuestion((String)model.getValueAt(x , 0), 
								  (String)model.getValueAt(x , 1), 
								  (String)model.getValueAt(x, 2), 
								  (String) model.getValueAt(x, 3),
								  getTheme(),
								  cbm.b3);

		}
		size = 0;
		save = false;
	}
	
	public String getTheme() {

		String theme = cbm.b1.substring(0, 2).toLowerCase();
		String normalize = Normalizer.normalize(cbm.b2, Normalizer.Form.NFD).replaceAll("^p\\{ASCII}]", "");
		theme += normalize;
		return theme;
	}

	public void getQuestions(DefaultTableModel model, String theme, int type) throws SQLException{
		this.model = model;
		
		ResultSet res = db.getQuestions(theme, type);
		
		while(res.next()){
			model.addRow(new Object[] { res.getString(2),
										res.getString(3),
										res.getString(4),
										res.getString(5),
			});
		}
	}
	
	public ResultSet getQuestions(String theme) throws SQLException{
		
		ResultSet res = db.getQuestions(theme);
		return res;

	}

}
