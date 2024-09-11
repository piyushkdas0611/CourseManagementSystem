package piyush.services;

import piyush.models.Course;
import java.util.HashMap;
import java.util.Map;

public class CourseService {
	private Map<String, Course> courseDatabase = new HashMap<>();

	// Create a new course
	public boolean createCourse(String courseId, String courseName, String courseCode, int numberOfSeats) {
		if (courseDatabase.containsKey(courseId)) {
			System.out.println("Course with ID " + courseId + " already exists.");
			return false;
		} else {
			Course newCourse = new Course(courseId, courseName, courseCode, numberOfSeats);
			courseDatabase.put(courseId, newCourse);
			System.out.println("Course created successfully.");
			return true;
		}
	}

	// Read a course by ID
	public Course getCourseById(String courseId) {
		return courseDatabase.get(courseId);
	}

	// Update a course
	public boolean updateCourse(String courseId, String courseName, String courseCode, int numberOfSeats) {
		Course course = courseDatabase.get(courseId);
		if (course != null) {
			course.setCourseName(courseName);
			course.setCourseCode(courseCode);
			course.setNumberOfSeats(numberOfSeats);
			System.out.println("Course updated successfully.");
			return true;
		} else {
			System.out.println("Course with ID " + courseId + " not found.");
			return false;
		}
	}

	// Delete a course
	public boolean deleteCourse(String courseId) {
		if (courseDatabase.containsKey(courseId)) {
			courseDatabase.remove(courseId);
			System.out.println("Course deleted successfully.");
			return true;
		} else {
			System.out.println("Course with ID " + courseId + " not found.");
			return false;
		}
	}

	// List all courses
	public Map<String, Course> listAllCourses() {
		return courseDatabase;
	}
}
