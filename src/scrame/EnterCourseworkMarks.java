package scrame;

import java.util.Scanner;

public class EnterCourseworkMarks {

	final static String studentCourseFile = "data/StudentCourse.txt";

	public static void run() {
		Scanner sc = new Scanner(System.in);

		String studentMatric = getStudentMatric(sc);
		String courseCode = getCourseCode(sc, studentMatric);
		
		Course course = CourseManager.getCourse(courseCode);
		StudentCourse studentCourse = StudentCourseManager.createStudentCourse(studentMatric, courseCode);

		if (course.hasAssignment()) enterAssignmentMarks(sc,studentCourse );
		if (course.hasClassPart()) enterClassPartMarks(sc,studentCourse );
		
		StudentCourseManager.amendStudentCourse(studentCourse);
	}

	private static String getStudentMatric(Scanner sc) {
		try {
			System.out.println("Enter Student Matriculation Number");
			String studentMatric = sc.nextLine();
			//validate format
			FormatValidator.validateMatricNo(studentMatric);
			//validate existence
			if (!StudentManager.checkStudentExistence(studentMatric)) throw new Exception("Student does not exist!");
			return studentMatric;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return getStudentMatric(sc);
		}
	}
	private static String getCourseCode(Scanner sc, String studentMatric) {
		try {
			System.out.println("Enter Course Code");
			String courseCode = sc.nextLine();
			FormatValidator.validateCourseCode(courseCode);
			if (!CourseManager.checkCourseExistence(courseCode)) throw new Exception("Course does not exist!");
			if (!CourseManager.getCourse(courseCode).hasStudent(studentMatric)) throw new Exception("Student not in course!");
			return courseCode;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return getCourseCode(sc, studentMatric);
		}
	}
	private static void enterAssignmentMarks(Scanner sc, StudentCourse sCourse) {
		try {
			System.out.println("Enter Assignment Marks:");
			int marks = sc.nextInt();
			validateMark(marks);
			sCourse.setAssigmentMarks(marks);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterAssignmentMarks(sc, sCourse);
		}
	}
	private static void enterClassPartMarks(Scanner sc, StudentCourse sCourse) {
		try {
			System.out.println("Enter Class Participation Marks:");
			int marks = sc.nextInt();
			validateMark(marks);
			sCourse.setClassPartMarks(marks);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			enterClassPartMarks(sc,sCourse );
		}
	}
	private static void validateMark(int mark) throws Exception {
		if (mark > 100 || mark < 0)
			throw new Exception("A mark must be between 1-100");
	}
}
