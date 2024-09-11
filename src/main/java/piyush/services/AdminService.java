package piyush.services;

import piyush.models.Admin;
import piyush.models.Course;
import java.util.HashMap;
import java.util.Map;

public class AdminService {
	// Using Map to store for now
	private Map<String, Admin> adminDatabase = new HashMap<>();
	private Admin loggedInAdmin = null;
	private CourseService courseService = new CourseService();
	
	public AdminService(CourseService courseService) {
        this.courseService = courseService;
    }

	// Registration method
	public boolean register(String username, String password) {
		if (adminDatabase.containsKey(username)) {
			System.out.println("Admin already exists.");
			return false;
		} else {
			Admin newAdmin = new Admin(username, password);
			adminDatabase.put(username, newAdmin);
			System.out.println("Admin registered successfully.");
			return true;
		}
	}

	// Login method
	public boolean login(String username, String password) {
		Admin admin = adminDatabase.get(username);
		if (admin != null && admin.getPassword().equals(password)) {
			loggedInAdmin = admin;
			System.out.println("Admin logged in successfully.");
			return true;
		} else {
			System.out.println("Invalid username or password.");
			return false;
		}
	}

	// Logout method
	public void logout() {
		if (loggedInAdmin != null) {
			System.out.println("Admin " + loggedInAdmin.getUsername() + " logged out successfully.");
			loggedInAdmin = null;
		} else {
			System.out.println("No admin is currently logged in.");
		}
	}

	// Check if an admin is logged in
	public boolean isAdminLoggedIn() {
		return loggedInAdmin != null;
	}

	// Get the currently logged-in admin
	public Admin getLoggedInAdmin() {
		return loggedInAdmin;
	}

	public void createCourse(String courseId, String courseName, String courseCode, int numberOfSeats) {
		if (isAdminLoggedIn()) {
			courseService.createCourse(courseId, courseName, courseCode, numberOfSeats);
		} else {
			System.out.println("No admin is currently logged in.");
		}
	}

	public void updateCourse(String courseId, String courseName, String courseCode, int numberOfSeats) {
		if (isAdminLoggedIn()) {
			courseService.updateCourse(courseId, courseName, courseCode, numberOfSeats);
		} else {
			System.out.println("No admin is currently logged in.");
		}
	}

	public void deleteCourse(String courseId) {
		if (isAdminLoggedIn()) {
			courseService.deleteCourse(courseId);
		} else {
			System.out.println("No admin is currently logged in.");
		}
	}

	public void listCourses() {
		if (isAdminLoggedIn()) {
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
			System.out.println("No admin is currently logged in.");
		}
	}
}
