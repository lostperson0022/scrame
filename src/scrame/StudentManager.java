package scrame;

import java.io.*;
import java.util.*;

public class StudentManager implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String studentFile = "data/Students.txt";

	public static boolean checkStudent(String studentName) {
		boolean checkStudent = false;
		ArrayList<Student> studentList = DatabaseManager.read(studentFile);
		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			if (s.getStudentName().equals(studentName)) {
				checkStudent = true;
			}
		}
		return checkStudent;
	}
}
