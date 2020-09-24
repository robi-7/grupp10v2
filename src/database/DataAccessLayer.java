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
	
	public ResultSet viewStudents() throws SQLException {
		String query = "SELECT * FROM Student";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getStudent(String studentID) throws SQLException {
		String query = "SELECT * FROM Student WHERE studentID = '" + studentID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public void registerStudent(String name, String ssn, String address, String email) throws SQLException {
		String checkStudentIDquery = "SELECT studentID FROM Student ORDER BY studentID DESC";
		String studentID = "";
		String query = "INSERT INTO Student VALUES('"+ studentID +"', '" + name + "', '" + ssn + "', '"+ address +"', '"+ email +"')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	public void deleteStudent(String studentID) throws SQLException {
		String queryDeleteHasStudied = "DELETE FROM HasStudied WHERE studentID = '"+ studentID +"'";
		Statement statementHasStudied = con.createStatement();
		statementHasStudied.executeUpdate(queryDeleteHasStudied);
		String queryDeleteStudies = "DELETE FROM Studies WHERE studentID = '"+ studentID +"'";
		Statement statementStudies = con.createStatement();
		statementStudies.executeUpdate(queryDeleteStudies);
		String queryDeleteStudent = "DELETE FROM Student WHERE studentID = '"+ studentID +"'";
		Statement statementStudent = con.createStatement();
		statementStudent.executeUpdate(queryDeleteStudent);
	}
	
	public ResultSet getStudentCourses(String studentID) throws SQLException {
		String query = "SELECT c.courseID, c.name, c.credits, h.grade FROM Course c, HasStudied h WHERE h.studentID = '" + studentID + "' AND h.courseID = c.courseID UNION SELECT c.courseID, c.name, c.credits, 'Not graded' FROM Course c, Studies s WHERE s.studentID = '" + studentID + "' AND s.courseID = c.courseID";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet viewCourses() throws SQLException {
		String query = "SELECT * FROM Course";
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
	
	public void registerCourse(String courseID, String name, int credits) throws SQLException {
		String query = "INSERT INTO Course VALUES('"+ courseID +"', '" + name + "', " + credits + ")";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	public void deleteCourse(String courseID) throws SQLException {
		String queryDeleteHasStudied = "DELETE FROM HasStudied WHERE courseID = '"+ courseID +"'";
		Statement statementHasStudied = con.createStatement();
		statementHasStudied.executeUpdate(queryDeleteHasStudied);
		String queryDeleteStudies = "DELETE FROM Studies WHERE courseID = '"+ courseID +"'";
		Statement statementStudies = con.createStatement();
		statementStudies.executeUpdate(queryDeleteStudies);
		String queryDeleteCourse = "DELETE FROM Course WHERE courseID = '"+ courseID +"'";
		Statement statementCourse = con.createStatement();
		statementCourse.executeUpdate(queryDeleteCourse);
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
	public void deleteStudies(String courseID, String studentID) throws SQLException {
		String query = "DELETE FROM Studies WHERE studentID = '"+ studentID +"' AND courseID = '"+ courseID +"'";
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
