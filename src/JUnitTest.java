import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest{
	@BeforeClass  
	public static void setUpBeforeClass() throws Exception {  
		System.out.println("before class JunitExamples");  
	}

	@Before  
	public void setUp() throws Exception {  
		System.out.println("before test");  
	}
	@Test
	public void testAddStudent() {
		User user = new User("Sulthan","sulthan","Bayview","0284255214");
		assertEquals("INSERT INTO USERS(USERNAME,PASSWORD,ADDRESS,PHONE,ADMIN) VALUES ('Sulthan','sulthan','Bayview','0284255214',false)", user.addUser());
		System.out.println(user.addUser());
	}
	@Test
	public void testDateDiff() throws ParseException {
		ReturnBook re = new ReturnBook();
		re.setIssueDate("20-06-2020");
		re.setReturnDate("30-06-2020");
		assertEquals(864000000, re.dateDiff());
		System.out.println(re.dateDiff());
	}
	@Test
	public void testPastReturnDate() {
		ReturnBook re = new ReturnBook();
		re.setBorrowDays(10);
		re.setPeriod(9);
		assertEquals(true, re.pastReturnDate());
		re.setPeriod(11);
		assertEquals(false, re.pastReturnDate());
	}
	@Test
	public void testAddBook() {
		Book book = new Book("Harry Potter","Magic/Fantasy","J.K. Rowling");
		assertEquals("INSERT INTO BOOKS(BNAME,GENRE,AUTHOR,BORROW) VALUES ('Harry Potter','Magic/Fantasy','J.K. Rowling',false)", book.addBook());
		System.out.println(book.addBook());
	}
	@Test
	public void testCalculateFine() {
		ReturnBook re = new ReturnBook();
		re.setBorrowDays(10);
		re.setPeriod(5);
		assertEquals(50, re.calculateFine());
	}

	@After  
	public void tearDown() throws Exception {  
		System.out.println("after test ");  
	}  

	@AfterClass  
	public static void tearDownAfterClass() throws Exception {  
		System.out.println("after class JUnitTest");  
	}	
}
