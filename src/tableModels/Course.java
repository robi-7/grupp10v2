package tableModels;

public class Course {
	private String courseID;
	private String name;
	private int credits;
	
	public Course(String courseID, String name, int credits) {
		this.courseID = courseID;
		this.name = name;
		this.credits = credits;
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

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	
}
