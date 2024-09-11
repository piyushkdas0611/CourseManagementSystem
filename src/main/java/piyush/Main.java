package piyush;

import java.util.Scanner;
import piyush.services.AdminService;
import piyush.services.StudentService;
import piyush.services.CourseService;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CourseService courseService = new CourseService();
		AdminService adminService = new AdminService(courseService);
		StudentService studentService = new StudentService(courseService);

		System.out.println("=== Welcome to the Course Management System ===");

		boolean isRunning = true;

		while (isRunning) {
			System.out.println("\nMain Menu:");
			System.out.println("1. Admin Register");
			System.out.println("2. Admin Login");
			System.out.println("3. Student Register");
			System.out.println("4. Student Login");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int mainChoice = getValidIntegerInput(sc);

			switch (mainChoice) {
			case 1:
				adminRegister(sc, adminService);
				break;
			case 2:
				if (adminLogin(sc, adminService)) {
					adminMenu(sc, adminService);
				}
				break;
			case 3:
				studentRegister(sc, studentService);
				break;
			case 4:
				if (studentLogin(sc, studentService)) {
					studentMenu(sc, studentService);
				}
				break;
			case 5:
				System.out.println("Thank you for using the Course Management System. Goodbye!");
				isRunning = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
		sc.close();
	}

	// Method for Admin Registration
	private static void adminRegister(Scanner sc, AdminService adminService) {
		System.out.println("\n--- Admin Registration ---");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		boolean success = adminService.register(username, password);
		if (success) {
			System.out.println("Admin registered successfully. You can now log in.");
		} else {
			System.out.println("Registration failed. Username may already exist.");
		}
	}

	// Method for Admin Login
	private static boolean adminLogin(Scanner sc, AdminService adminService) {
		System.out.println("\n--- Admin Login ---");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		boolean success = adminService.login(username, password);
		if (success) {
			System.out.println("Logged in as Admin: " + username);
			return true;
		} else {
			System.out.println("Login failed. Incorrect username or password.");
			return false;
		}
	}

	// Admin Menu
	private static void adminMenu(Scanner sc, AdminService adminService) {
		boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Create Course");
			System.out.println("2. Update Course");
			System.out.println("3. Delete Course");
			System.out.println("4. List Courses");
			System.out.println("5. Logout");
			System.out.print("Choose an option: ");

			int choice = getValidIntegerInput(sc);

			switch (choice) {
			case 1:
				createCourse(sc, adminService);
				break;
			case 2:
				updateCourse(sc, adminService);
				break;
			case 3:
				deleteCourse(sc, adminService);
				break;
			case 4:
				adminService.listCourses();
				break;
			case 5:
				adminService.logout();
				isLoggedIn = false;
				System.out.println("Logged out successfully.");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	// Method to create a course
	private static void createCourse(Scanner sc, AdminService adminService) {
		System.out.println("\n--- Create Course ---");
		System.out.print("Enter course ID: ");
		String courseId = sc.nextLine();
		System.out.print("Enter course name: ");
		String courseName = sc.nextLine();
		System.out.print("Enter course code: ");
		String courseCode = sc.nextLine();
		System.out.print("Enter number of seats: ");
		int numberOfSeats = getValidIntegerInput(sc);

		adminService.createCourse(courseId, courseName, courseCode, numberOfSeats);
	}

	// Method to update a course
	private static void updateCourse(Scanner sc, AdminService adminService) {
		System.out.println("\n--- Update Course ---");
		System.out.print("Enter course ID: ");
		String courseId = sc.nextLine();
		System.out.print("Enter new course name: ");
		String courseName = sc.nextLine();
		System.out.print("Enter new course code: ");
		String courseCode = sc.nextLine();
		System.out.print("Enter new number of seats: ");
		int numberOfSeats = getValidIntegerInput(sc);

		adminService.updateCourse(courseId, courseName, courseCode, numberOfSeats);
	}

	// Method to delete a course
	private static void deleteCourse(Scanner sc, AdminService adminService) {
		System.out.println("\n--- Delete Course ---");
		System.out.print("Enter course ID to delete: ");
		String courseId = sc.nextLine();

		adminService.deleteCourse(courseId);
	}

	// Method for Student Registration
	private static void studentRegister(Scanner sc, StudentService studentService) {
		System.out.println("\n--- Student Registration ---");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		boolean success = studentService.register(username, password);
		if (success) {
			System.out.println("Student registered successfully. You can now log in.");
		} else {
			System.out.println("Registration failed. Username may already exist.");
		}
	}

	// Method for Student Login
	private static boolean studentLogin(Scanner sc, StudentService studentService) {
		System.out.println("\n--- Student Login ---");
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		boolean success = studentService.login(username, password);
		if (success) {
			System.out.println("Logged in as Student: " + username);
			return true;
		} else {
			System.out.println("Login failed. Incorrect username or password.");
			return false;
		}
	}

	// Student Menu
	private static void studentMenu(Scanner sc, StudentService studentService) {
		boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("\n--- Student Menu ---");
			System.out.println("1. Apply for Course");
			System.out.println("2. Search Courses");
			System.out.println("3. List All Courses");
			System.out.println("4. View Enrolled Courses");
			System.out.println("5. Logout");
			System.out.print("Choose an option: ");

			int choice = getValidIntegerInput(sc);

			switch (choice) {
			case 1:
				applyForCourse(sc, studentService);
				break;
			case 2:
				searchCourses(sc, studentService);
				break;
			case 3:
				studentService.listCourses();
				break;
			case 4:
				studentService.viewEnrolledCourses();
				break;
			case 5:
				studentService.logout();
				isLoggedIn = false;
				System.out.println("Logged out successfully.");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	// Method for applying to a course
	private static void applyForCourse(Scanner sc, StudentService studentService) {
		System.out.println("\n--- Apply for Course ---");
		System.out.print("Enter course ID to apply: ");
		String courseId = sc.nextLine();

		studentService.applyForCourse(courseId);
	}

	// Method for searching courses
	private static void searchCourses(Scanner sc, StudentService studentService) {
		System.out.println("\n--- Search Courses ---");
		System.out.print("Enter course name or code to search: ");
		String searchTerm = sc.nextLine();

		studentService.searchCourses(searchTerm);
	}

	// Utility method to handle integer input validation
	private static int getValidIntegerInput(Scanner sc) {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid number: ");
			}
		}
	}
}
