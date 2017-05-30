package bvsm.questions;

import java.sql.ResultSet;
import java.sql.SQLException;

import bvsm.database.Database;
import bvsm.panel.tools.ComboBoxManager;

public class QuestionsEngine extends Questions {
	

	public QuestionsEngine(ComboBoxManager cbm, Database db) {
		super(cbm, db);
	}

	private static String[][][] questionsArray;
	private static String[][] choosenArray;
	private static boolean[][]][] takenArray;
	
	private int questionsSize;
	int topicSize;

	public void start(int questionsSize) {
		this.questionsSize = questionsSize;
		initialise();

	}

	private void initialise() {
		try {

			int temp = 0;
			if (cbm.type == "V�rias") {
				topicSize = cbm.subsubTopic[cbm.indexPress][cbm.indexLast].length;
				questionsArray = new String[topicSize][200][4];

				ResultSet res = getQuestions(getTheme());
				for (int i = 0; i < topicSize; i++) {
					while (res.next()) {

						questionsArray[i][temp][0] = res.getString(2);
						questionsArray[i][temp][1] = res.getString(3);
						questionsArray[i][temp][2] = res.getString(4);
						questionsArray[i][temp][3] = res.getString(5);
						temp++;
					}
				}
			} else {
				questionsArray = new String[1][200][4];
				ResultSet res = getQuestions(getTheme());
				while (res.next()) {

					questionsArray[0][temp][0] = res.getString(2);
					questionsArray[0][temp][1] = res.getString(3);
					questionsArray[0][temp][2] = res.getString(4);
					questionsArray[0][temp][3] = res.getString(5);
					temp++;
				}
			}
			temp = 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void chooseQuestions(){
		choosenArray = new String[questionsSize][4];
		takenArray = new String[][][];
		int qpt = questionsSize / topicSize;
		
		if(cbm.type == "V�rias"){
			for(int i = 0; i < topicSize; i++){
				
			}
		}
	}
}
