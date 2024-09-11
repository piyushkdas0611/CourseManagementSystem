package piyush.services;

import java.util.HashMap;
import java.util.Map;

import piyush.models.Course;
import piyush.models.Student;

public class StudentService {
	// Using Map now instead of Database
	private Map<String, Student> studentDatabase = new HashMap<>();
	private Student loggedInStudent = null;
	private CourseService courseService = new CourseService();
	
	public StudentService(CourseService courseService) {
        this.courseService = courseService;
    }

	// Registration method
	public boolean register(String username, String password) {
		if (studentDatabase.containsKey(username)) {
			System.out.println("Student already exists.");
			return false;
		} else {
			Student newStudent = new Student(username, password);
			studentDatabase.put(username, newStudent);
			System.out.println("Student registered successfully.");
			return true;
		}
	}

	// Login method
	public boolean login(String username, String password) {
		Student student = studentDatabase.get(username);
		if (student != null && student.getPassword().equals(password)) {
			loggedInStudent = student;
			System.out.println("Student logged in successfully.");
			return true;
		} else {
			System.out.println("Invalid username or password.");
			return false;
		}
	}

	// Logout method
	public void logout() {
		if (loggedInStudent != null) {
			System.out.println("Admin " + loggedInStudent.getUsername() + " logged out successfully.");
			loggedInStudent = null;
		} else {
			System.out.println("No admin is currently logged in.");
		}
	}

	// Check if a student is logged in
	public boolean isStudentLoggedIn() {
		return loggedInStudent != null;
	}

	// Get the currently logged-in student
	public Student getLoggedInStudent() {
		return loggedInStudent;
	}

	// To list the available courses
	public void listCourses() {
		if (isStudentLoggedIn()) {
			Map<String, Course> courses = courseService.listAllCourses();
			if (courses.isEmpty()) {
				System.out.println("No courses available.");
			} else {
				for (Course course : courses.values()) {
					System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getCourseName()
							+ ", Code: " + course.getCourseCode() + ", Seats: " + course.getNumberOfSeats());
				}
			}
		} else {
			System.out.println("No student is currently logged in.");
		}
	}

	// To apply for courses
	public void applyForCourse(String courseId) {
		if (isStudentLoggedIn()) {
			Course course = courseService.getCourseById(courseId);
			if (course != null && course.getNumberOfSeats() > 0) {
				course.setNumberOfSeats(course.getNumberOfSeats() - 1);
				System.out.println("Applied for course: " + course.getCourseName());
			} else {
				System.out.println("Course is full or does not exist.");
			}
		} else {
			System.out.println("No student is currently logged in.");
		}
	}

	// To search for courses
	public void searchCourses(String searchTerm) {
		if (isStudentLoggedIn()) {
			Map<String, Course> courses = courseService.listAllCourses();
			boolean found = false;
			for (Course course : courses.values()) {
				if (course.getCourseName().equalsIgnoreCase(searchTerm)
						|| course.getCourseCode().equalsIgnoreCase(searchTerm)) {
					System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getCourseName()
							+ ", Code: " + course.getCourseCode() + ", Seats: " + course.getNumberOfSeats());
					found = true;
				}
			}
			if (!found) {
				System.out.println("No courses found with the given search term.");
			}
		} else {
			System.out.println("No student is currently logged in.");
		}
	}

	public void viewEnrolledCourses() {
        if (isStudentLoggedIn()) {
            Map<String, Course> enrolledCourses = loggedInStudent.getEnrolledCourses();
            if (enrolledCourses.isEmpty()) {
                System.out.println("No courses enrolled.");
            } else {
                System.out.println("Enrolled Courses:");
                for (Course course : enrolledCourses.values()) {
                    System.out.println("Course ID: " + course.getCourseId() + ", Name: " + course.getCourseName());
                }
            }
        } else {
            System.out.println("No student is currently logged in.");
        }
    }

}
