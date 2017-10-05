package bvsm.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import bvsm.panel.BasePanel;

public class Database {

	String OnlineHost = "jdbc:mysql://mysql5.gear.host/bvsm";
	String OfflineHost = "";
	
	String user = "bvsm";
	String password = "Zw4L67v!Y1!K";

	private static Connection con;
	private static boolean hasData = false;
	private static boolean verification = false;
	
	public Database(BasePanel base) {
		//getQuestions("inFlorestal");
		
	}

	private void connectOnline() {
		if(!verification) {
			if (con == null) {
				getConnectionOn(OnlineHost);
				verification = true;
			}
		} else {
			initialise();

		}
	}
	
	private void connectOffline() {
		if(!verification) {
			if (con == null) {
				getConnectionOn(OfflineHost);
				verification = true;
			}
		} else {
			initialise();

		}
	}

	private void getConnectionOn(String host) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host, user, password);

		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível verificar as atualizações, o programa "
												+ "	irá continuar offline");
		}
	}

	private void initialise() {
		if (!hasData) {
			hasData = true;
			try {
				Statement state = con.createStatement();
				ResultSet res = state
						.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='inGeral'");

			} catch (SQLException e) {
				//e.printStackTrace();
			}

		}
	}

	public void addUser(int dbSize, String username, String password, int type, String name, String surname, int age) {
		connectOnline();

		PreparedStatement prep;
		try {
			System.out.println("User added");
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
		//connectOnline();

		Statement state = con.createStatement();

		ResultSet res = state
				.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");

		if (res.next()) {
			return res;
		}
		return null;
	}

	public ResultSet getUsers() throws SQLException {
		connectOnline();

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM users");

		return res;
	}

	public void deleteUser(int id) {
		connectOnline();

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

		connectOnline();

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
		connectOnline();
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

		connectOnline();

		Statement state = con.createStatement();

		ResultSet res = state.executeQuery("SELECT * FROM " + theme + " WHERE type='" + type + "'");

		return res;
	}

	public void deleteQuestion(String theme, int id) throws SQLException {
		connectOnline();

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