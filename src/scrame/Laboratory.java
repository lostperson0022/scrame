package scrame;

public class Laboratory extends Session{

	private static final long serialVersionUID = 1L;

	public Laboratory(int capacity) {
		this.capacity = capacity;
		this.vacancy = capacity;
		studentList = new String[vacancy];
	}
}
