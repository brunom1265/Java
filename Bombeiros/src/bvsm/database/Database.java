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
			ResultSet res = state.executeQuery("SELECT * FROM inFlorestal");
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
							 									   + "name varchar(60),"
							 									   + "surname varchar (60),"
							 									   + "age integer(2),"
							 									   + "primary key(id));");
					 Statement state3 = con.createStatement();
					 state3.execute("CREATE TABLE inFlorestal(id integer,"
							 									   + "q1 varchar(120),"
							 									   + "a1 varchar(120)," 
							 									   + "a2 varchar(120),"
							 									   + "a3 varchar(120),"
							 									   + "theme integer(20),"
							 									   + "type integer(20),"
							 									   + "primary key(id));");
					 
					 Statement state4 = con.createStatement();
					 state4.execute("CREATE TABLE inUrbanos(id integer,"
																   + "q1 varchar(120),"
																   + "a1 varchar(120)," 
																   + "a2 varchar(120),"
																   + "a3 varchar(120),"
							 									   + "theme varchar(60),"
							 									   + "type integer(20),"
							 									   + "primary key(id));");
					 
					 addUser("ICENine", "650031772", 1, "Bruno", "Garcia", 23);
					 addUser("assim", "asdf", 2, "Joana", "Madeira", 23);
					 insertQuestion("1", "2", "3", "4", "inFlorestal", 1);
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

		}
	}
	
	public void addUser(String username, String password, int type, String name, String surname, int age){
		connect();
		
		PreparedStatement prep;
		try {
			prep = con.prepareStatement("INSERT INTO users values(?, ?, ?, ?, ?, ?, ?)");
			prep.setString(2, username);
			prep.setString(3, password);
			prep.setInt(4, type);
			prep.setString(5, name);
			prep.setString(6, surname);
			prep.setInt(7, age);
			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getUser(String username, String password) throws SQLException{
			connect();

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");
		
		if(res.next()){
			return res;
		}
		return null;
	}
	
	public ResultSet getUsers() throws SQLException{
			connect();
		
		Statement state = con.createStatement();
		
		ResultSet res = state.executeQuery("SELECT * FROM users");
		
		return res;
	}
	
	public void insertQuestion(String q1, String a1, String a2, String a3, String theme, int type){
		
		connect();
		
		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO " + theme + " values(?, ?, ?, ?, ?, ?, ?)");
			prep.setString(2, q1);
			prep.setString(3, a1);
			prep.setString(4, a2);
			prep.setString(5, a3);
			prep.setString(6, theme);
			prep.setInt(7, type);

			prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getQuestions(String tableName, String theme) throws SQLException{
		
		connect();
		
		if(tableName != "Variadas"){
			
			connect();
			
			Statement state = con.createStatement();
			
			ResultSet res = state.executeQuery("SELECT " + tableName + "FROM users WHERE theme='" + theme + "'");
			
			return res;
			}
		return null;
	}
}