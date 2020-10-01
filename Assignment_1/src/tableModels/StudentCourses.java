package tableModels;

public class StudentCourses {
	private String courseID;
	private String name;
	private String credits;
	private String grade;
	
	public StudentCourses(String courseID, String name, String credits, String grade) {
		this.courseID = courseID;
		this.name = name;
		this.credits = credits;
		this.grade = grade;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
