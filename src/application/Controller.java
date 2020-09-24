package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tableModels.Course;
import tableModels.Student;
import tableModels.StudentCourses;
import database.DataAccessLayer;

public class Controller implements Initializable {
	//VARIABLES
	private DataAccessLayer database;
	
	
	//TAB: STUDENT
    @FXML
    private Tab tabStudent;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtStudentName;
    @FXML
    private TextField txtStudentSsn;
    @FXML
    private TextField txtStudentAddress;
    @FXML
    private TextField txtStudentEmail;
    @FXML
    private Button btnStudentRegister;
    @FXML
    private Button btnStudentDelete;
    @FXML
    private Button btnStudentFind;
    @FXML
    private Label lblStudentStatus;
    //TABLE: STUDENTS
    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private TableColumn<Student, String> tblColStudentStudentID;
    @FXML
    private TableColumn<Student, String> tblColStudentName;
    @FXML
    private TableColumn<Student, String> tblColStudentSsn;
    @FXML
    private TableColumn<Student, String> tblColStudentAddress;
    @FXML
    private TableColumn<Student, String> tblColStudentEmail;
    //TABLE: COURSES
    @FXML
    private TableView<StudentCourses> tblStudentCourses;
    @FXML
    private TableColumn<StudentCourses, String> tblColStudentCourseCourseID;
    @FXML
    private TableColumn<StudentCourses, String> tblColStudentCourseName;
    @FXML
    private TableColumn<StudentCourses, String> tblColStudentCourseCredits;
    @FXML
    private TableColumn<StudentCourses, String> tblColStudentCourseGrade;
    
    //TAB: COURSE
    @FXML    
    private Tab tabCourse;
    @FXML
    private TextField txtCourseID;
    @FXML
    private TextField txtCourseName;
    @FXML
    private TextField txtCourseCredits;
    @FXML
    private Button btnCourseRegister;
    @FXML
    private Button btnCourseDelete;
    @FXML
    private Button btnCourseFind;
    @FXML
    private Label lblCourseStatus;
    @FXML
    private TableView<?> tblCourses;
    @FXML
    private TableView<?> tblCourseStudents;
    
    //TAB: REGISTER
    @FXML    
    private Tab tabRegister;
    @FXML
    private Label lblRegisterStatus;
    @FXML
    private TableView<?> tblRegisterCourses;
    @FXML
    private TableView<?> tblRegisterStudents;
    @FXML
    private Button btnRegisterStudies;
    @FXML
    private Button btnRegisterHasStudied;
    @FXML
    private ComboBox<?> cbxGrade;
	
    //INITIALIZATOR
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Tab: Student, Table: Student
    	tblColStudentStudentID.setCellValueFactory(new PropertyValueFactory<Student,String>("studentID"));
    	tblColStudentName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
    	tblColStudentSsn.setCellValueFactory(new PropertyValueFactory<Student,String>("ssn"));
    	tblColStudentAddress.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
    	tblColStudentEmail.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
    	
    	//Tab: Student, Table: Course
    	tblColStudentCourseCourseID.setCellValueFactory(new PropertyValueFactory<StudentCourses,String>("courseID"));
    	tblColStudentCourseName.setCellValueFactory(new PropertyValueFactory<StudentCourses,String>("name"));
    	tblColStudentCourseCredits.setCellValueFactory(new PropertyValueFactory<StudentCourses,String>("credits"));
    	tblColStudentCourseGrade.setCellValueFactory(new PropertyValueFactory<StudentCourses,String>("grade"));
    	
    	try {
			this.viewStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    //CONSTRUCTOR
	public Controller() {
		try 
		{
		database = new DataAccessLayer();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//METHODS
	public void viewStudents() throws SQLException {
		ResultSet rs = database.viewStudents();
		
		ObservableList<Student> data = FXCollections.observableArrayList();
		while(rs.next()) {
			String studentID = rs.getString(1);
			String name = rs.getString(2);
			String ssn = rs.getString(3);
			String address = rs.getString(4);
			String email = rs.getString(5);
			data.add(new Student(studentID,name,ssn,address,email));
		}
		tblStudents.setItems(data);
	}
	
	public ResultSet getStudent(String studentID) throws SQLException {
		return database.getStudent(studentID);
	}
	@FXML
	public void registerStudent() throws SQLException {
		String name = txtStudentName.getText();
		String ssn = txtStudentSsn.getText();
		String address = txtStudentAddress.getText();
		String email = txtStudentEmail.getText();
		
		database.registerStudent(name,  ssn,  address, email);
	}
	
	public void deleteStudent(String studentID) throws SQLException {
		database.deleteStudent(studentID);
	}
	@FXML
	public void tblStudentSelected() throws SQLException {
		if(!tblStudents.getSelectionModel().isEmpty()) {
			int index = tblStudents.getSelectionModel().getSelectedIndex();
			String studentID = tblColStudentStudentID.getCellData(index);
			
			ResultSet course = database.getStudentCourses(studentID);
			ObservableList<StudentCourses> data = FXCollections.observableArrayList();
			while(course.next()) {
				String courseID = course.getString(1);
				String name = course.getString(2);
				String credits = course.getString(3);
				String grade = course.getString(4);
				data.add(new StudentCourses(courseID,name,credits,grade));
			}
			tblStudentCourses.setItems(data);
			ResultSet student = this.getStudent(studentID);
			student.next();
			txtStudentID.setText(student.getString(1));
			txtStudentName.setText(student.getString(2));
			txtStudentSsn.setText(student.getString(3));
			txtStudentAddress.setText(student.getString(4));
			txtStudentEmail.setText(student.getString(5));
		}
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
