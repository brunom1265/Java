package bvsm.questions;

import java.sql.SQLException;

import bvsm.database.Database;
import bvsm.panel.tools.ComboBoxManager;

public class QuestionsEngine  extends Questions{
	
	public QuestionsEngine(ComboBoxManager cbm, Database db) {
		super(cbm, db);
	}

	private static int qNumbers = 0;
	private static String question, a1, a2, a3;
	private static String theme;
	private static String[][] testArray;
	
	public void start(){
		try {
			System.out.println(getQuestions(getTheme()).next());
			System.out.println(getQuestions(getTheme()).getString(2));


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
