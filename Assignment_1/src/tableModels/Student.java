package tableModels;

public class Student {
	private String studentID;
	private String name;
	private String ssn;
	private String address;
	private String email;
	
	public Student(String studentID, String name, String ssn, String address, String email) {
		this.studentID = studentID;
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.email = email;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
