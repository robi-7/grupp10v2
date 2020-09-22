package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessLayer {
	
	String url = "jdbc:sqlserver://localhost:1433;database=Assignment1;";
	String loginName = "sa";
	String password = "AlexandraAmarOgnjenRobert1!";
	Connection con;
	
	public DataAccessLayer() throws SQLException {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		con = DriverManager.getConnection(url,loginName,password);
	}
	
	public ResultSet getStudent(String studentID) throws SQLException {
		String query = "SELECT * FROM Student WHERE studentID = '" + studentID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public void registerStudent(String studentID, String name, String ssn, String address, String email) throws SQLException {
		String query = "INSERT INTO Student VALUES('"+ studentID +"', '" + name + "', '" + ssn + "', '"+ address +"', '"+ email +"')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	
	public ResultSet getCourse(String courseID) throws SQLException {
		String query = "SELECT * FROM Course WHERE courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public void registerCourse(String courseID, String name, int credits) throws SQLException {
		String query = "INSERT INTO Course VALUES('"+ courseID +"', '" + name + "', " + credits + ")";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	
	
	public ResultSet getStudies(String courseID, String studentID) throws SQLException {
		String query = "SELECT * FROM Studies WHERE studentID = '" + studentID + "' AND courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public void registerStudies(String courseID, String studentID) throws SQLException {
		String query = "INSERT INTO Studies VALUES('"+ courseID +"', '" + studentID + "')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	
	public ResultSet getHasStudied(String courseID, String studentID) throws SQLException {
		String query = "SELECT * FROM HasStudied WHERE studentID = '" + studentID + "' AND courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public void registerHasStudied(String grade, String courseID, String studentID) throws SQLException {
		String query = "INSERT INTO HasStudied VALUES('"+ grade +"', '" + courseID + "', '" + studentID + "')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
}
