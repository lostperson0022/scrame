package scrame;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {

	public static Scanner sc = new Scanner(System.in);

	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String courseCode;

	private String courseCoordinator;

	private int tutNumber;
	private int labNumber;

	ArrayList<Session> session;
	ArrayList<Components> components;
	
	public Course(
			String courseName,
			String courseCode,
			String courseCoordinator,
			int courseVacancy,
			int tutNumber,
			int labNumber,
			int examPercent,
			int courseWorkPercent,
			int assignmentPercent,
			int classPartPercent
	) {
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.courseCoordinator = courseCoordinator;
		this.session = constructSessionsList(courseVacancy, tutNumber, labNumber);
		this.tutNumber = tutNumber;
		this.labNumber = labNumber;
		this.components = constructComponentsList(examPercent, courseWorkPercent, assignmentPercent, classPartPercent);
	}

	private static ArrayList<Session> constructSessionsList(
			int courseVacancy,
			int tutNumber,
			int labNumber
	) {
		// Create arraylist of lecture, tutorial and lab
		ArrayList<Session> session = new ArrayList<>(1 + tutNumber + labNumber);
		// Create new instance of lecture
		Session lecture = new Lecture(courseVacancy);
		session.add(lecture);
		// Create new instance of tutorial
		for (int i = 1; i < 1 + tutNumber; i++) {
			session.add(new Tutorial((int) (Math.ceil(courseVacancy / tutNumber))));
		}
		// Create new instance of lab
		for (int i = 1; i < 1 + labNumber; i++) {
			session.add(new Laboratory((int) (Math.ceil(courseVacancy / labNumber))));
		}
		return session;
	}

	private static ArrayList<Components> constructComponentsList(
			int examPercent,
			int courseWorkPercent,
			int assignmentPercent,
			int classPartPercent
	) {
		ArrayList<Components> components = new ArrayList<>(3);
		Exam exam = new Exam(examPercent);
		components.add(exam);
		Assignment assignment = new Assignment(assignmentPercent*courseWorkPercent/100);
		components.add(assignment);
		ClassPart classPart = new ClassPart(classPartPercent*courseWorkPercent/100);
		components.add(classPart);

		return components;
	}

	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseCoordinator() {
		return courseCoordinator;
	}

	public int getTutNumber() {
		return tutNumber;
	}

	public int getLabNumber() {
		return labNumber;
	}

	public ArrayList<Session> getSession() {
		return session;
	}

	public ArrayList<Components> getComponents() {
		return components;
	}

	public boolean hasStudent(String matricNo) {
		try {
			 if (getLectures().get(0).hasStudent(matricNo)) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean hasVacancy() {
		try {
			if (getLectures().size() > 0) {
				return getLectures().get(0).getVacancy() > 0;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public int getExamPercent() {
		if (components != null) return components.get(0).getPercentage();
		return 0;
	}

	public int getCourseWorkPercent() {
		if (components != null) return components.get(1).getPercentage() + components.get(1).getPercentage();
		return 0;
	}

	public int getAssignmentPercent() {
		if (components != null) return components.get(1).getPercentage();
		return 0;
	}

	public int getClassPartPercent() {
		if (components != null) return components.get(2).getPercentage();
		return 0;
	}

	public ArrayList<Tutorial> getTutorials() {
		ArrayList<Tutorial> tutorials = new ArrayList<>(0);
		if (session != null) {
			for (Session aSession : session) {
				if (aSession instanceof Tutorial) tutorials.add((Tutorial) aSession);
			}
		}
		return tutorials;
	}

	public ArrayList<Lecture> getLectures() {
		ArrayList<Lecture> lectures = new ArrayList<>(0);
		if (session != null) {
			for (Session aSession : session) {
				if (aSession instanceof Lecture) lectures.add((Lecture) aSession);
			}
		}
		return lectures;
	}

	public ArrayList<Laboratory> getLaboratories() {
		ArrayList<Laboratory> labs = new ArrayList<>(0);
		if (session != null) {
			for (Session aSession : session) {
				if (aSession instanceof Laboratory) labs.add((Laboratory) aSession);
			}
		}
		return labs;
	}

}
