package scrame;

public class Tutorial extends Session{

	private int vacancy;
	
	public Tutorial(int vacancy) {
		this.vacancy = vacancy;
	}
	
	@Override
	public int getVacancy() {
		return vacancy;
	}

	
}
