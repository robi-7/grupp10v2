package tableModels;

public class Course {
	private String courseID;
	private String name;
	private int credits;
	private String throughput;
	
	public Course(String courseID, String name, int credits, String throughput) {
		this.courseID = courseID;
		this.name = name;
		this.credits = credits;
		this.throughput = throughput;
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

	public String getThroughput() {
		return throughput;
	}

	public void setThroughput(String throughput) {
		this.throughput = throughput;
	}
	
	
}
