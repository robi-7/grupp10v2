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
	
	public ResultSet getCourse(String courseID) throws SQLException {
		return database.getCourse(courseID);
	}
	
	public ResultSet getStudies(String studentID, String courseID) throws SQLException {
		return database.getStudies(studentID, courseID);
	}
	
	public ResultSet getHasStudied(String studentID, String courseID) throws SQLException {
		return database.getHasStudied(studentID, courseID);
	}
	
	
}
