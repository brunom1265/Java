package bvsm.database;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

	private static Connection con;
	private static boolean hasData = false;

	private Path dir;
	private DirectoryStream<Path> stream;
	public int lastOne;
	ArrayList<String> filesNames = new ArrayList<String>();

	public Database(String dir) throws IOException {
		setDir(dir);
	}

	public ResultSet displayVideosDBM(String table, int id) throws ClassNotFoundException, SQLException, IOException {

		if (con == null) {
			getConnection();
		}

		Statement state;
		state = con.createStatement();
		ResultSet res = state.executeQuery(("SELECT id, vName FROM " + table + " WHERE id = " + id));
		if(!state.isClosed()){
			return res;
		}
		return null;
	}

	private void getConnection() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:src/videoShow/database/DB_videoclube.db");
		initialise();
	}

	private void initialise() throws SQLException, IOException {

		if (!hasData) {
			hasData = true;
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='DBM'");

			if (!res.next()) {
				System.out.println("Building the User table");
				updateTables();
				System.out.println("Building the Rows");
				updateRows();
				System.out.println("Finish the Rows");
			}
		}

	}

	private void updateTables() throws SQLException {
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE users(id integer," + "username varchar(60),"
						+ "password varchar(30)," + "primary key(id));");
	}

	private void updateRows() throws SQLException, IOException {
		
					//PreparedStatement prep = con.prepareStatement("INSERT INTO " + filesNames.get(i) + " values(?,?,?);");
					//prep.execute();
	}

	private void setDir(String path) throws IOException {
		dir = Paths.get(path);
		stream = Files.newDirectoryStream(dir);
	}
	
	public String getVideoName(String table, int id) throws SQLException, ClassNotFoundException, IOException{
		
		ResultSet rs;
		rs = displayVideosDBM(table, id);

		if(rs.next()){
			return rs.getString("vName");
		}
		return null;
	}

}