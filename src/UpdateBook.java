import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//UpdateBook class which extends class Book
class UpdateBook extends Book {
	private String BID;
	
	//Default constructor
	UpdateBook(){}

	//Parameterised constructor 1
	UpdateBook(String id){
		this.BID = id;
	}
	
	//Parameterised constructor 2
	UpdateBook(String name, String genre, String author){
		super(name, genre, author);
	}

	public void setBID(String bid) {
		BID = bid;
	}
	public String getBID() {
		return BID;
	}
	public void initComponents() {
		ViewBook view = new ViewBook();
		//create a frame for BID on which book will be updated
		JFrame f = new JFrame("Update Book");

		JLabel l1 = new JLabel("Enter Book ID (BID)");
		l1.setBounds(95,15, 200,30);

		JTextField bid = new JTextField();
		bid.setBounds(50, 65, 200, 30);

		JButton submit=new JButton("Submit");  
		submit.setBounds(60,115,80,25);
		submit.addActionListener(new ActionListener() { //Add action listener to "Submit" button
			public void actionPerformed(ActionEvent e) {
				UpdateBook update = new UpdateBook(bid.getText());
				Connection connection = Conn.getConnection();
				//Geting BID,NAME,GENRE, and AUTHOR from books table where the BID match the number that user has entered 
				String sql="SELECT BID,BNAME,GENRE,AUTHOR FROM books where BID =" + update.getBID(); 
				try {
					Statement stmt = connection.createStatement();
					ResultSet rs= stmt.executeQuery(sql);
					rs.next();
					
					//Create the frame for entering new book information
					JFrame f2 = new JFrame("Update Book Details");
					JLabel l1,l2,l3;  
					JTextField bookName, bookGenre, bookAuthor;
					
					l1=new JLabel("Book Name"); 
					l1.setBounds(30,15, 100,30); 
					l2=new JLabel("Genre"); 
					l2.setBounds(30,53, 100,30); 
					l3=new JLabel("Author"); 
					l3.setBounds(30, 91, 100,30); 

					bookName = new JTextField();
					bookName.setBounds(110, 15, 200, 30);
					bookName.setText(rs.getString(2));
					bookGenre=new JTextField();
					bookGenre.setBounds(110, 53, 200, 30);
					bookGenre.setText(rs.getString(3));
					bookAuthor=new JTextField();
					bookAuthor.setBounds(110, 91, 200, 30);
					bookAuthor.setText(rs.getString(4));

					JButton updateBtn=new JButton("Update");
					updateBtn.setBounds(130,130,80,25);
					updateBtn.addActionListener(new ActionListener() { //Add action listener to "Update" button
						public void actionPerformed(ActionEvent e){
							//set the new book information into parameterised constructor
							UpdateBook add = new UpdateBook(bookName.getText(),bookGenre.getText(), bookAuthor.getText());

							Connection connection = Conn.getConnection();

							try {
								Statement stmt =  connection.createStatement();
								//Update the new book information SQL
								stmt.executeUpdate("UPDATE BOOKS SET BNAME ='"+add.getBookName()+"',GENRE = '"+add.getBookGenre()+"',AUTHOR='"+add.getBookAuthor()+"' WHERE BID = "+update.getBID());
								JOptionPane.showMessageDialog(null,"Book updated!"); //Update success message
								f.dispose();
								f2.dispose();
								view.initComponents(); //Open the viewBookTab back

							}

							catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, e1);
							}
						}
					});

					f2.add(updateBtn);
					f2.add(l1);
					f2.add(l2);
					f2.add(l3);
					f2.add(bookName);
					f2.add(bookGenre);
					f2.add(bookAuthor);
					f2.setSize(350,200);
					f2.setLayout(null);
					f2.setVisible(true);
					f2.setLocationRelativeTo(null);
				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				} 
			}
		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(160,115,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {view.initComponents(); f.dispose();}});

		f.add(submit);
		f.add(cancel);
		f.add(l1);
		f.add(bid);
		f.setSize(300,200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}

