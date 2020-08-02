import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LibrarianMenu{
	public void librarianMenu() {
		//Create tab objects for each view class as well as issue and return book
		ViewBook bookTab = new ViewBook();
		ViewUser userTab = new ViewUser();
		IssueBook issueTab = new IssueBook();
		ViewIssuedBook viewIssuedBookTab = new ViewIssuedBook();
		ReturnBook returnBookTab = new ReturnBook();
		
		JButton issueBook, returnBook, viewBooks, viewUsers, viewIssuedBook, logout;


		JFrame f = new JFrame("Librarian Menu");
		f.setLayout(new GridLayout(3,2));

		//Issue a Book button with action listener that will trigger IssueBook initComponents method
		issueBook = new JButton("Issue a Book");
		issueBook.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {issueTab.initComponents();}});

		//Return Book button with action listener that will trigger ReturnBook initComponents method
		returnBook = new JButton("Return Book");
		returnBook.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {returnBookTab.initComponents();}}); 

		//View Book button with action listener that will trigger ViewBook iniComponents method
		viewBooks = new JButton("View Book");
		viewBooks.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {bookTab.initComponents(); }}); 
		
		//View User button with action listener that will trigger ViewUser initComponents method
		viewUsers = new JButton("View User");
		viewUsers.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {userTab.initComponents(); }}); 

		//View Issued Books button with action listener that will trigger ViewIssuedBook initComponents method
		viewIssuedBook = new JButton("View Issued Books");
		viewIssuedBook.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {viewIssuedBookTab.initComponents();}}); 

		//Logout button to exit the system
		logout = new JButton("Log out");
		logout.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {System.exit(0);}});
		logout.setForeground(Color.RED);
		
		f.add(viewBooks);
		f.add(viewUsers);
		f.add(issueBook);
		f.add(viewIssuedBook);
		f.add(returnBook);
		f.add(logout);
	
		f.setSize(400, 225); 
		f.setVisible(true);
		f.setLayout(null);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

