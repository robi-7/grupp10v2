package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public ResultSet registerStudent(String studentID, String name, String ssn, String address, String email) throws SQLException {
		String query = "INSERT INTO Student VALUES()";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getCourse(String courseID) throws SQLException {
		String query = "SELECT * FROM Course WHERE courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getStudies(String studentID, String courseID) throws SQLException {
		String query = "SELECT * FROM Studies WHERE studentID = '" + studentID + "' AND courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getHasStudied(String studentID, String courseID) throws SQLException {
		String query = "SELECT * FROM HasStudied WHERE studentID = '" + studentID + "' AND courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
}
