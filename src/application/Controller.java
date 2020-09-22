package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataAccessLayer;

public class Controller {
	DataAccessLayer database;
	int amountOfStudents = 0;
	
	public Controller() {
		try 
		{
		database = new DataAccessLayer();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getStudent(String studentID) throws SQLException {
		return database.getStudent(studentID);
	}
	
	public void registerStudent(String studentID, String name, String ssn, String address, String email) throws SQLException {
		database.registerStudent(studentID,  name,  ssn,  address, email);
	}

	public ResultSet getCourse(String courseID) throws SQLException {
		return database.getCourse(courseID);
	}
	public void registerCourse(String courseID, String name, int credits) throws SQLException {
		database.registerCourse(courseID,  name,  credits);
	}
	
	public ResultSet getStudies(String courseID, String studentID) throws SQLException {
		return database.getStudies(courseID, studentID);
	}
	
	public void registerStudies(String courseID, String studentID) throws SQLException {
		database.registerStudies(courseID, studentID);
	}
	
	public ResultSet getHasStudied(String courseID, String studentID) throws SQLException {
		return database.getHasStudied(courseID, studentID);
	}
	public void registerHasStudied(String grade, String courseID, String studentID) throws SQLException {
		database.registerHasStudied(grade, courseID, studentID);
	}
	
	
}
