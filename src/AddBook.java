import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Class AddBook that extends Book class
class AddBook extends Book {
	//Defaul constructor
	AddBook(){}
	
	//Parameterised constructor
	AddBook(String name, String genre, String author){
		super(name, genre, author);
	}
	//initComponents of AddBook class
	public void initComponents() {
		ViewBook view = new ViewBook();
		//create a frame for entering book details
		JFrame f = new JFrame("Enter Book Details");
		JTextField bookName, bookGenre, bookAuthor;
		JLabel l1,l2,l3;  

		l1=new JLabel("Book Name"); 
		l1.setBounds(30,15, 100,30); 
		l2=new JLabel("Genre"); 
		l2.setBounds(30,53, 100,30); 
		l3=new JLabel("Author"); 
		l3.setBounds(30, 91, 100,30); 

		bookName = new JTextField();
		bookName.setBounds(110, 15, 200, 30);
		bookGenre=new JTextField();
		bookGenre.setBounds(110, 53, 200, 30);
		bookAuthor=new JTextField();
		bookAuthor.setBounds(110, 91, 200, 30);

		JButton addBtn=new JButton("Add");
		addBtn.setBounds(80,130,80,25);
		addBtn.addActionListener(new ActionListener() { //Adding action listener to "Add" button
			public void actionPerformed(ActionEvent e){
				AddBook add = new AddBook(bookName.getText(),bookGenre.getText(), bookAuthor.getText()); 

				Connection connection = Conn.getConnection();

				try {
					Statement stmt =  connection.createStatement();
					//Insert book information SQL statement
					stmt.executeUpdate(add.addBook());
					JOptionPane.showMessageDialog(null,"Book added!");
					f.dispose();
					view.initComponents(); //Open the viewBookTab back

				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}   

			}

		});

		//Cancel button if the librarian decide to cancel adding new book
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(180,130,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {view.initComponents(); f.dispose();}});

		f.add(addBtn);
		f.add(cancel);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(bookName);
		f.add(bookGenre);
		f.add(bookAuthor);
		f.setSize(350,200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}