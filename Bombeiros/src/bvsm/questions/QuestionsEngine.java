package bvsm.questions;

import bvsm.panel.tools.ComboBoxManager;

public class QuestionsEngine {
	
	private static int qNumbers = 0;
	private static String question, a1, a2, a3;
	private static boolean running = false;
	private static String theme;
	private static Questions q;
	
	private static String[][] testArray;
	
	public QuestionsEngine(Questions q, int aNumbers, int type, ComboBoxManager cbm){
		this.q = q;
	}
	
	public void startTest(){
		this.theme = q.getTheme();

		running = true;
		
		while(running){
			
		}
		stopTest();
	}
	
	public void stopTest(){
		running = false;
	}
	
}
