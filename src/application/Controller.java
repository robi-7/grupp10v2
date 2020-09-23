package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
	public void deleteStudent(String studentID) throws SQLException {
		database.deleteStudent(studentID);
	}

	public ResultSet getCourse(String courseID) throws SQLException {
		return database.getCourse(courseID);
	}
	public void registerCourse(String courseID, String name, int credits) throws SQLException {
		database.registerCourse(courseID,  name,  credits);
	}
	public void deleteCourse(String courseID) throws SQLException {
		database.deleteCourse(courseID);
	}
	
	public ResultSet getStudies(String courseID, String studentID) throws SQLException {
		return database.getStudies(courseID, studentID);
	}
	
	public void registerStudies(String courseID, String studentID) throws SQLException {
		database.registerStudies(courseID, studentID);
	}
	public void deleteStudentFromCourse(String courseID, String studentID) throws SQLException {
		database.deleteStudies(courseID, studentID);
	}
	
	public ResultSet getHasStudied(String courseID, String studentID) throws SQLException {
		return database.getHasStudied(courseID, studentID);
	}
	public void registerHasStudied(String grade, String courseID, String studentID) throws SQLException {
		database.registerHasStudied(grade, courseID, studentID);
	}
	
	
}
