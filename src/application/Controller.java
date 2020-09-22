package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DataAccessLayer;

public class Controller {
	DataAccessLayer database;
	
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
}
