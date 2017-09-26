package lab03;
import java.util.GregorianCalendar;

public class LibraryBook extends Book {
	
	String holder;
	GregorianCalendar dueDate;
	

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
	}
	public String getHolder() {
		return holder;
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	public GregorianCalendar getCheckIn() {
		return null;
	}
	public void checkIn(){
		this.holder = null;
		this.dueDate = null;
	}
	public void checkout(String holder, GregorianCalendar dueDate) {
		this.holder = new String(holder);
		this.dueDate = new GregorianCalendar(dueDate.get(GregorianCalendar.YEAR),
				dueDate.get(GregorianCalendar.MONTH),
				dueDate.get(GregorianCalendar.DAY_OF_MONTH));
	}

}
