package scrame;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	private String studentName;
	private String studentMatricNo;
	
//	private HashMap<String, ArrayList<CourseSession>> courseRegistered;
	
	public Student(String studentName, String studentMatricNo){
		this.studentName = studentName;
		this.studentMatricNo = studentMatricNo;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public String getStudentMatricNo() {
		return studentMatricNo;
	}
	
	public void registerCourse(String registerCourseName) {
		
	}
	
}
