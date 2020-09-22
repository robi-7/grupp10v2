package application;
	
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;




public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(this.getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {		
		Controller controller = new Controller();
		try {
			controller.registerHasStudied("A", "C005", "S004");
			ResultSet student = controller.getStudent("S004");
			
			ResultSet course = controller.getCourse("C005");
			ResultSet studies = controller.getStudies("C001", "S002");
			ResultSet hasStudied = controller.getHasStudied("C005","S004");
			
			while(student.next()) {
				System.out.println(student.getString(1) + " - " + student.getString(2) + " - " + student.getString(3) + " - " + student.getString(4) + " - " + student.getString(5));
			}
			while(course.next()) {
				System.out.println(course.getString(1) + " - " + course.getString(2) + " - " + course.getString(3));
			}
			while(studies.next()) {
				System.out.println(studies.getString(1) + " - " + studies.getString(2));
			}
			while(hasStudied.next()) {
				System.out.println(hasStudied.getString(1) + " - " + hasStudied.getString(2) + " - " + hasStudied.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		launch(args);
	}
}
