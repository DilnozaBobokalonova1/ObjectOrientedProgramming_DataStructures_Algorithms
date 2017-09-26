package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Comparator;

public class LibraryGeneric<Type> {
	private ArrayList<LibraryBookGeneric<Type>> library;

	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be added
	 * @param author
	 *            -- author of the book to be added
	 * @param title
	 *            -- title of the book to be added
	 */
	public void add(long isbn, String author, String title) {
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list
	 *            -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN,
	 * author, and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				Scanner lineIn = new Scanner(line);
				lineIn.useDelimiter("\\t");

				if (!lineIn.hasNextLong())
					throw new ParseException("ISBN", lineNum);
				long isbn = lineIn.nextLong();

				if (!lineIn.hasNext())
					throw new ParseException("Author", lineNum);
				String author = lineIn.next();

				if (!lineIn.hasNext())
					throw new ParseException("Title", lineNum);
				String title = lineIn.next();

				toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));

				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN. If no
	 * book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be looked up
	 */
	public Type lookup(long isbn) {
		Type holder = null;
		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				holder = library.get(i).getHolder();
			}
		}
		return holder;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * If no book with the specified ISBN is in the library, returns false. If
	 * the book with the specified ISBN is already checked out, returns false.
	 * Otherwise, returns true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked out
	 * @param holder
	 *            -- new holder of the library book
	 * @param month
	 *            -- month of the new due date of the library book
	 * @param day
	 *            -- day of the new due date of the library book
	 * @param year
	 *            -- year of the new due date of the library book
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {

		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				if (library.get(i).getHolder() == null) {
					GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
					library.get(i).checkout(holder, dueDate);
					return true;
				} else {
					return false;
				}
			}

		}
		return false;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder
	 *            -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		ArrayList<LibraryBookGeneric<Type>> list = new ArrayList<LibraryBookGeneric<Type>>();
		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getHolder() != null) {
				if (holder.equals(library.get(i).getHolder())) {
					list.add(library.get(i));
				}
			}
		}
		return list;
	}

	/**
	 * Unsets the holder and due date of the library book. If no book with the
	 * specified ISBN is in the library, returns false. If the book with the
	 * specified ISBN is already checked in, returns false. Otherwise, returns
	 * true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {
		for (int i = 0; i < library.size(); i++) {
			if (isbn == library.get(i).getIsbn()) {
				if (library.get(i).getHolder() != null) {
					library.get(i).checkIn();
					return true;
				} else {
					return false;
				}
			}

		}
		return false;
	}

	/**
	 * Unsets the holder and due date for all library books checked out by the
	 * specified holder. If no books with the specified holder are in the
	 * library, returns false; Otherwise, returns true.
	 * 
	 * @param holder
	 *            -- holder of the library books to be checked in
	 */
	public boolean checkin(Type holder) {
		boolean havecheckedabookin = false;
		for (int i = 0; i < library.size(); i++) {
			if (holder.equals(library.get(i).getHolder())) {
				library.get(i).checkIn();
				havecheckedabookin = true;
			}
		}
		return havecheckedabookin;
	}

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {
		ArrayList<LibraryBookGeneric<Type>> libraryByIsbn = new ArrayList<LibraryBookGeneric<Type>>();
		libraryByIsbn.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryByIsbn, comparator);

		return libraryByIsbn;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 1. Find the smallest
	 * item in the list. 2. Swap the smallest item with the first item in the
	 * list. 3. Now let the list be the remaining unsorted portion (second item
	 * to Nth item) and repeat steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> isbn1, LibraryBookGeneric<Type> isbn2) {
			int result;
			if (isbn1.getIsbn() > isbn2.getIsbn()) {
				result = 1;
			} else if (isbn1.getIsbn() == isbn2.getIsbn()) {
				result = 0;
			} else {
				result = -1;
			}
			return result;
		}

		// FILL IN - write the compare method
	}

	/**
	 * Returns the list of library books, sorted by author
	 */

	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
		ArrayList<LibraryBookGeneric<Type>> libraryByAuthor = new ArrayList<LibraryBookGeneric<Type>>();
		libraryByAuthor.addAll(library);

		OrderByAuthor comparator = new OrderByAuthor();

		sort(libraryByAuthor, comparator);
		return libraryByAuthor;
		// FILL IN -- do not return null
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 */

	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		ArrayList<LibraryBookGeneric<Type>> libraryOverdue = new ArrayList<LibraryBookGeneric<Type>>();
		GregorianCalendar today = new GregorianCalendar(year, month, day);
		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getDueDate() != null && library.get(i).getDueDate().before(today)) {
				libraryOverdue.add(library.get(i));
			}
		}
		OrderByDueDate comparator = new OrderByDueDate();
		sort(libraryOverdue, comparator);
		return libraryOverdue;
		// FILL IN -- do not return null
	}

	/**
	 * Comparator that defines an ordering among library books using the author,
	 * and book title as a tie-breaker.
	 */

	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> author1, LibraryBookGeneric<Type> author2) {

			String a1 = author1.getAuthor();
			String a2 = author2.getAuthor();

			int theResult = a1.compareTo(a2);
			if (theResult == 0) {
				theResult = author1.getTitle().compareTo(author2.getTitle());
			}
			return theResult;
		}
	}
	// FILL IN - write the compare method

	/**
	 * Comparator that defines an ordering among library books using the due
	 * date.
	 */

	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {
		public int compare(LibraryBookGeneric<Type> duedate1, LibraryBookGeneric<Type> duedate2) {
			GregorianCalendar d1 = duedate1.getDueDate();
			GregorianCalendar d2 = duedate2.getDueDate();
			return (int) d1.compareTo(d2);
			// FILL IN - write the compare method
		}
	}

	public String toString() {
		long isbn1;
		String author1;
		String title1;
		String result = "";
		for (int i = 0; i < library.size(); i++) {
			isbn1 = library.get(i).getIsbn();
			author1 = library.get(i).getAuthor();
			title1 = library.get(i).getTitle();
			result += (isbn1 + ", " + author1 + ", \"" + title1 + "\", ");
		}

		return result;
	}
}
