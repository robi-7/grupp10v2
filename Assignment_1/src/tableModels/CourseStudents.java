package tableModels;

public class CourseStudents {
		private String studentID;
		private String name;
		private String grade;
		
		public CourseStudents(String studentID, String name, String grade) {
			this.studentID = studentID;
			this.name = name;
			this.grade = grade;
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

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}
		
		
}
