package piyush.models;
import java.util.*;

public class Student extends User {
	private Map<String, Course> enrolledCourses = new HashMap<>();
	
	public Student(String username, String password) {
		super(username, password, "student");
	}
	public Map<String, Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.put(course.getCourseId(), course);
    }
}
