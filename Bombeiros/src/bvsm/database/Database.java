package bvsm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {

	String host = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11189329";
	String user = "sql11189329";
	String password = "Y1FGHSNK7h";

	private static Connection con;
	private static boolean hasData = false;

	public Database() {
		getQuestions("inFlorestal");
	}

	private void connect() {
		if (con == null) {
			getConnection();
		}
	}

	private void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host, user, password);
			//initialise();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void initialise() {
		if (!hasData) {
			hasData = true;
			try {
				Statement state = con.createStatement();
				//ResultSet res = state
						//.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");

				//if (!res.next()) {
					System.out.println("Building the User table");

					Statement state2 = con.createStatement();
					state2.execute("CREATE TABLE users(id integer(255)," + "username varchar(60),"
							+ "password varchar(60)," + "type int(3)," + "name varchar(60)," + "surname varchar (60),"
							+ "age integer(2));");

					addUser(1, "ICENine", "650031772", 1, "Bruno", "Garcia", 23);

					addUser(2, "assim", "asdf", 2, "Joana", "Madeira", 23);
				//}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void addUser(int dbSize, String username, String password, int type, String name, String surname, int age) {
		connect();

		PreparedStatement prep;
		try {
			System.out.println(dbSize);
			prep = con.prepareStatement("INSERT INTO users values(?, ?, ?, ?, ?, ?, ?)");
			prep.setInt(1, dbSize);
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

	public ResultSet getUser(String username, String password) throws SQLException {
		connect();

		Statement state = con.createStatement();

		ResultSet res = state
				.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");

		if (res.next()) {
			return res;
		}
		return null;
	}

	public ResultSet getUsers() throws SQLException {
		connect();

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM users");

		return res;
	}

	public void deleteUser(int id) {
		connect();

		try {
			Statement state = con.createStatement();

			state.execute("DELETE FROM users WHERE id='" + id + "'");

			for (int i = id; i <= getTableSize("users"); i++) {
				int temp = ++i;

				Statement state2 = con.createStatement();
				state2.execute("UPDATE users SET id = " + i + " WHERE id = " + temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertQuestion(int dbSize, String q1, String a1, String a2, String a3, String a4, String theme,
			int type) {

		connect();

		try {
			PreparedStatement prep = con.prepareStatement("INSERT INTO " + theme + " values(?, ?, ?, ?, ?, ?, ?)");
			prep.setInt(1, dbSize);
			prep.setString(2, q1);
			prep.setString(3, a1);
			prep.setString(4, a2);
			prep.setString(5, a3);
			prep.setString(6, a4);
			prep.setInt(7, type);
			prep.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getQuestions(String theme) {
		connect();
		try {
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT * FROM " + theme);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet getQuestions(String theme, int type) throws SQLException {

		connect();

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM " + theme + " WHERE type='" + type + "'");

		return res;
	}

	public void deleteQuestion(String theme, int id) throws SQLException {
		connect();

		Statement state = con.createStatement();

		state.execute("DELETE FROM " + theme + " WHERE id='" + id + "'");

		for (int i = id; i <= getTableSize(theme); i++) {
			int temp = ++i;

			Statement state2 = con.createStatement();
			state2.execute("UPDATE " + theme + " SET id = " + i + " WHERE id = " + temp);

		}
	}

	public void createTable(String tableName) {
		try {
			Statement state = con.createStatement();
			state.execute("CREATE TABLE " + tableName + "(id integer(255)," + "q1 varchar(255)," + "a1 varchar(255),"
					+ "a2 varchar(255)," + "a3 varchar(255)," + "a4 varchar(255)," + "type integer(20));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getTableSize(String name, int type) throws SQLException {
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT COUNT(*) FROM " + name + " WHERE type='" + type + "'");
		res.next();
		return res.getInt(1);

	}

	public int getTableSize(String name) throws SQLException {
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery("SELECT COUNT(*) FROM " + name);
		res.next();
		return res.getInt(1);

	}

}