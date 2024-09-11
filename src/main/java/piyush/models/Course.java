package piyush.models;

public class Course {
	private String courseId;
	private String courseName;
	private String courseCode;
	private int numberOfSeats;

	public Course(String courseId, String courseName, String courseCode, int numberOfSeats) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.numberOfSeats = numberOfSeats;
	}

	// Getters and Setters
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
}
