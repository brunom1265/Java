package bvsm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Connection con;
	private static boolean hasData = false;
	
	public Database(){
		getQuestions();
	}

	public ResultSet getQuestions(){
		connect();
		try {
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT q1, q2, q3 FROM inFlorestais");
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	private void connect(){
		if(con == null){
			getConnection();
		}
	}
	
	private void getConnection(){
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:./src/bvsm/database/BVSM.db");
			initialise();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialise() {
		
		if(!hasData){
			 hasData = true;
			try {
				 Statement state = con.createStatement();
				 ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");

				 if(!res.next()){
					 System.out.println("Building the User table");
					 
					 
					 Statement state2 = con.createStatement();
					 state2.execute("CREATE TABLE users(id integer,"
							 									   + "username varchar(60)," 
							 									   + "password varchar(60),"
							 									   + "type int(3),"
							 									   + "primary key(id));");
					 Statement state3 = con.createStatement();
					 state3.execute("CREATE TABLE inFlorestais(id integer,"
							 									   + "q1 varchar(120)," 
							 									   + "q2 varchar(120),"
							 									   + "q3 varchar(120),"
							 									   + "rq integer(3),"
							 									   + "theme integer(20),"
							 									   + "primary key(id));");
					 
					 Statement state4 = con.createStatement();
					 state4.execute("CREATE TABLE inUrbanos(id integer,"
							 									   + "q1 varchar(120)," 
							 									   + "q2 varchar(120),"
							 									   + "q3 varchar(120),"
							 									   + "rq integer(3),"
							 									   + "theme integer(20),"
							 									   + "primary key(id));");
					 
					 addUser("ICENine", "650031772", 1);
					 addUser("assim", "asdf", 2);
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		}
	}
	
	public void addUser(String username, String password, int type){
		if(con == null){
			connect();
		}
		
		PreparedStatement prep;
		try {
			prep = con.prepareStatement("INSERT INTO users values(?, ?, ?, ?)");
			prep.setString(2, username);
			prep.setString(3, password);
			prep.setInt(4, type);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getUser(String username, String password) throws SQLException{
		if(con == null){
			connect();
		}

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");
		
		if(res.next()){
			return res;
		}
		return null;
	}
	
	public ResultSet getUsers() throws SQLException{
		if(con == null){
			connect();
		}
		
		Statement state = con.createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM users");
		
		return res;
	}
	
	public void insertQuestion(String theme, String q1, String q2, String q3, int rQ){
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO " + theme + " values(?, ?, ?, ?, ?)");
			prep.setString(2, q1);
			prep.setString(3, q2);
			prep.setString(4, q3);
			prep.setInt(5, rQ);
			prep.setString(6, theme);
			prep.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}