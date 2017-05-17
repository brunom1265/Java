package bvsm.questions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import bvsm.database.Database;
import bvsm.panel.tools.ComboBoxManager;

public class Questions{
	
	Database db;
	ComboBoxManager cbm;
	DefaultTableModel model;
	QuestionsEngine qe;
	
	int size = 0;
	int rowSize = 0, columnSize = 0;
	boolean save = false;
	
	ArrayList<String> q1 = new ArrayList<String>();
	ArrayList<String> a1 = new ArrayList<String>();
	ArrayList<String> a2 = new ArrayList<String>();
	ArrayList<String> a3 = new ArrayList<String>();
	
	public Questions(ComboBoxManager cbm, Database db){
			this.db = db;
			this.cbm = cbm;
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
			db.insertQuestion((String)model.getValueAt(x , 1), 
								  (String)model.getValueAt(x , 2), 
								  (String)model.getValueAt(x, 3), 
								  (String) model.getValueAt(x, 4),
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
		System.out.println(theme);
		return theme;
	}

	public void getQuestions(DefaultTableModel model) throws SQLException{
		this.model = model;
		
		ResultSet res = db.getQuestions();
		
		while(res.next()){
			model.addRow(new Object[] { res.getString(2),
										res.getString(3),
										res.getString(4),
										res.getString(5),
										res.getString(6),
										res.getInt(7),
			});
		}
	}

}
