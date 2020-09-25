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
import java.util.regex.Pattern;

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
import tableModels.CourseStudents;
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
	@FXML
	private Label lblStudentSuccess;
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
	private Label lblCourseSuccess;
	//TABLE: COURSES
	@FXML
	private TableView<Course> tblCourses;
	@FXML
	private TableColumn<Course, String> tblColCourseID;
	@FXML
	private TableColumn<Course, String> tblColCourseName;
	@FXML
	private TableColumn<Course, String> tblColCourseCredits;
	@FXML
	private TableView<CourseStudents> tblCourseStudents;
	@FXML
	private TableColumn<CourseStudents, String> tvlColCourseStudentID;
	@FXML
	private TableColumn<CourseStudents, String> tblColCourseStudentName;
	@FXML
	private TableColumn<CourseStudents, String> tblColCourseStudentGrade;


	//TAB: REGISTER
	@FXML    
	private Tab tabRegister;
	@FXML
	private Label lblRegisterStatus;
	@FXML
	private Label lblRegisterSuccess;
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

		//Tab: Course, Table: Courses
		tblColCourseID.setCellValueFactory(new PropertyValueFactory<Course, String>("courseID"));
		tblColCourseName.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
		tblColCourseCredits.setCellValueFactory(new PropertyValueFactory<Course, String>("credits"));

		//Tab: Course, Table: Students
		tvlColCourseStudentID.setCellValueFactory(new PropertyValueFactory<CourseStudents, String>("studentID"));
		tblColCourseStudentName.setCellValueFactory(new PropertyValueFactory<CourseStudents, String>("name"));
		tblColCourseStudentGrade.setCellValueFactory(new PropertyValueFactory<CourseStudents, String>("grade"));

		try {
			this.viewStudents();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			this.viewCourses();
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
	@FXML
	public void findStudent()  {
		lblStudentStatus.setText("");
		lblStudentSuccess.setText("");
		try {
			String studentID = txtStudentID.getText().toUpperCase();
			ResultSet rs = database.getStudent(studentID);
			rs.next();
			txtStudentName.setText(rs.getString(2));		
			txtStudentSsn.setText(rs.getString(3));
			txtStudentAddress.setText(rs.getString(4));
			txtStudentEmail.setText(rs.getString(5));
			for (Student student : tblStudents.getItems()) {
				if (studentID.equals(student.getStudentID())) {
					tblStudents.getSelectionModel().select(student);
				}
			} 

			tblStudentSelected();
			lblStudentSuccess.setText("Successfully found student " + studentID);
		} catch(SQLException e) {
			lblStudentStatus.setText("Student ID does not exist");
			txtStudentName.setText("");		
			txtStudentSsn.setText("");
			txtStudentAddress.setText("");
			txtStudentEmail.setText("");
			tblStudentCourses.setItems(null);
			tblStudents.getSelectionModel().clearSelection();
		} 

	} 
	@FXML
	public void registerStudent()  {
		lblStudentStatus.setText("");
		lblStudentSuccess.setText("");
		
		try {
			String name = txtStudentName.getText();
			String ssn = txtStudentSsn.getText().replaceAll("-", ""); //DENNA TAR BORT BINDESTRECKET
			String address = txtStudentAddress.getText();
			String email = txtStudentEmail.getText();   

			if (!name.isEmpty() && !ssn.isEmpty() && !address.isEmpty() && !email.isEmpty()) {
				if (ssn.length() == 10) {
					Double.parseDouble(ssn); //DENNA TESTAR SÅ SSN BESTÅR AV SIFFROR, KOLLA NUMBERFORMATEXCEPTION CATCHEN LÄNGRE NER
					String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
					Pattern pattern = Pattern.compile(regex);
					if(pattern.matcher(email).matches()){
						String newStudentID = database.registerStudent(name,  ssn,  address, email);
						this.viewStudents();
						tblStudentCourses.setItems(null);
						tblStudents.getSelectionModel().selectLast();
						int lastIndex = tblStudents.getSelectionModel().getSelectedIndex();
						tblStudents.scrollTo(lastIndex); 
						txtStudentID.setText(newStudentID);
						lblStudentSuccess.setText("Successfully registered student " + newStudentID);
					} else {
						lblStudentStatus.setText("Please enter valid email");
					}
					
				} else { 
					lblStudentStatus.setText("Social security number must consist of 10 numbers");
				}
				
			} else {
				lblStudentStatus.setText("Please enter values, only student ID can remain empty");
			}
			
		}
		catch (SQLException e) {
			lblStudentStatus.setText("A student with that social security number already exists");
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			lblStudentStatus.setText("Social security number must consist of only numbers"); //DETTA HÄNDER NÄR DET BLIR ERROR I SSN PGA BOKSTÄVER
		}
	}
	@FXML
	public void deleteStudent() throws SQLException {
		lblStudentStatus.setText("");
		lblStudentSuccess.setText("");
		try {
			if (!tblStudents.getSelectionModel().isEmpty()) {
				String studentID = tblStudents.getSelectionModel().getSelectedItems().get(0).getStudentID(); 
				database.deleteStudent(studentID);
				tblStudents.setItems(null);
				tblStudentCourses.setItems(null);
				viewStudents();
				txtStudentID.setText("");	
				txtStudentName.setText("");		
				txtStudentSsn.setText("");
				txtStudentAddress.setText("");
				txtStudentEmail.setText("");
				lblStudentSuccess.setText("Successfully deleted student " + studentID);
			} else {
				lblStudentStatus.setText("Please select a student in the list below first");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void tblStudentSelected() throws SQLException {
		lblStudentStatus.setText("");
		lblStudentSuccess.setText("");
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
			ResultSet student = database.getStudent(studentID);
			student.next();
			txtStudentID.setText(student.getString(1));
			txtStudentName.setText(student.getString(2));
			txtStudentSsn.setText(student.getString(3));
			txtStudentAddress.setText(student.getString(4));
			txtStudentEmail.setText(student.getString(5));
		}
	}
	@FXML
	public void findCourse() {
		lblCourseStatus.setText("");
		lblCourseSuccess.setText("");
		try {
			String courseID = txtCourseID.getText().toUpperCase();
			ResultSet rs = database.getCourse(courseID);
			rs.next();
			txtCourseName.setText(rs.getString(2));
			txtCourseCredits.setText(Integer.toString(rs.getInt(3)));
			for (Course course : tblCourses.getItems()) {
				if (courseID.contentEquals(course.getCourseID())) {
					tblCourses.getSelectionModel().select(course);
				}
			}
			tblCourseSelected();
			lblCourseSuccess.setText("Successfully found course " + courseID);
		} catch (SQLException e) {
			lblCourseStatus.setText("A course with this ID does not exist");
			txtCourseName.setText("");
			txtCourseCredits.setText("");
			tblCourseStudents.setItems(null);
			tblCourses.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void tblCourseSelected() {
		lblCourseStatus.setText("");
		lblCourseSuccess.setText("");
		try {
			if(!tblCourses.getSelectionModel().isEmpty()) {
				int index = tblCourses.getSelectionModel().getSelectedIndex();
				String courseID = tblColCourseID.getCellData(index);

				ResultSet student = database.getCourseStudents(courseID);
				ObservableList<CourseStudents> data = FXCollections.observableArrayList();
				while(student.next()) {
					String studentID = student.getString(1);
					String name = student.getString(2);
					String grade = student.getString(3);
					data.add(new CourseStudents(studentID,name,grade));
				}
				tblCourseStudents.setItems(data);
				ResultSet course = database.getCourse(courseID);
				course.next();
				txtCourseID.setText(course.getString(1));
				txtCourseName.setText(course.getString(2));
				txtCourseCredits.setText(course.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// --- Printar ut credits i courseID, pga int:en?
	@FXML
	public void viewCourses() throws SQLException {
		ResultSet rs = database.viewCourses();

		ObservableList<Course> data = FXCollections.observableArrayList();
		while(rs.next()) {
			String courseID = rs.getString(1);
			String name = rs.getString(2);
			int credits = rs.getInt(3);
			data.add(new Course(courseID,name,credits));

		}
		tblCourses.setItems(data);
	}

	@FXML
	public void registerCourse() {
		lblCourseStatus.setText("");
		lblCourseSuccess.setText("");
		try {
			String name = txtCourseName.getText();
			int credits; 
			if (!name.isEmpty()) {
				credits = Integer.parseInt(txtCourseCredits.getText());
				if (credits < 46) {
					String newCourseID = database.registerCourse(name, credits);
					this.viewCourses();
					tblCourseStudents.setItems(null);
					tblCourses.getSelectionModel().selectLast();
					int lastIndex = tblCourses.getSelectionModel().getSelectedIndex();
					tblCourses.scrollTo(lastIndex);
					txtCourseID.setText(newCourseID);
					lblCourseSuccess.setText("Successfully registered course " + newCourseID);
				} else {
					lblCourseStatus.setText("Please enter valid value in credits, maximum allowed credits per course is 45");
				} 				
			} else {
				lblCourseStatus.setText("Please enter a name for the course");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			lblCourseStatus.setText("Please enter valid value in credits, it can only consist of numbers");
		}
	}
	@FXML
	public void deleteCourse() {
		lblCourseStatus.setText("");
		lblCourseSuccess.setText("");
		try {
			if (!tblCourses.getSelectionModel().isEmpty()) {
				String courseID = tblCourses.getSelectionModel().getSelectedItems().get(0).getCourseID(); 
				database.deleteCourse(courseID);
				tblCourses.setItems(null);
				tblCourseStudents.setItems(null);
				viewCourses();
				txtCourseID.setText("");	
				txtCourseName.setText("");		
				txtCourseCredits.setText("");
				lblCourseSuccess.setText("Successfully deleted course " + courseID);
			} else {
				lblCourseStatus.setText("Please select a course in the list below first");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
