public class Book {
	//Strings for book name, genre, and author
	private String bookName, bookGenre, bookAuthor; 
	private boolean borrow;
	
	//Default constructor
	Book(){
		this.borrow = false;
	}

	//Parameterised constructor
	Book(String name, String genre, String author){
		this.bookName = name;
		this.bookGenre = genre;
		this.bookAuthor = author;

	}

	//Getter methods
	public String getBookName() {
		return bookName;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}
	public boolean isBorrow() {
		return borrow;
	}

	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}

	public String addBook() {
		String sql ="INSERT INTO BOOKS(BNAME,GENRE,AUTHOR,BORROW) VALUES ('"+this.getBookName()+"','"+this.getBookGenre()+"','"+this.getBookAuthor()+"',"+this.isBorrow()+")";
		return sql;
	}
}


