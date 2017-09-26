package lab04;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {
	Type holder;
	GregorianCalendar dueDate;
	

	public LibraryBookGeneric (long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
	}
	public Type getHolder() {
		return holder;
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	public GregorianCalendar getCheckIn() {
		return null;
	}
	public long getIsbn() {
		return super.getIsbn();
	}
	public void checkIn(){
		this.holder = null;
		this.dueDate = null;
	}
	public void checkout(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = new GregorianCalendar(dueDate.get(GregorianCalendar.YEAR),
				dueDate.get(GregorianCalendar.MONTH),
				dueDate.get(GregorianCalendar.DAY_OF_MONTH));
	}
}
