package lab03;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class Book {

  private long isbn;
  private String author;
  private String title;

  public Book(long isbn, String author, String title) {
    this.isbn = isbn;
    this.author = author;
    this.title = title;
  }

  public String getAuthor() {
    return this.author;
  }

  public long getIsbn() {
    return this.isbn;
  }

  public String getTitle() {
    return this.title;
  }

  /**
   * Two books are considered equal if they have the same ISBN, author, and
   * title.
   * 
   * @param other --
   *          the object begin compared with "this"
   * @return true if "other" is a Book and is equal to "this", false otherwise
   */
  
  public boolean equals(Object other) {
	  
	  if (!(other instanceof Book))
		  return false;
	  Book b1 = (Book)other; 
	  if (!(this.isbn == b1.getIsbn() 
			  && this.author.equals(b1.getAuthor())
			  && this.title.equals(b1.getTitle()))) {
		  return false;
	  }
	  return true;
  }
  
  /**
   * Returns a string representation of the book.
   */
  public String toString() {
    return isbn + ", " + author + ", \"" + title + "\"";
  }
}