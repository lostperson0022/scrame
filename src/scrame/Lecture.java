package scrame;

public class Lecture extends Session{

	private static final long serialVersionUID = 1L;
	
	public Lecture(int capacity) {
		this.capacity = capacity;
		this.vacancy = capacity;
		studentList = new String[vacancy];
	}
}
