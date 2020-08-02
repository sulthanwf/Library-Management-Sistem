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

//Class DeleteBook that extends UpdateBook
class DeleteBook extends UpdateBook {
	//Default constructor	
	DeleteBook(){}
	
	//Parameterised constructor that accepts String bid
	DeleteBook(String bid){
		super(bid);
	}

	//initComponents for DeleteBook class
	public void initComponents() {
		ViewBook view = new ViewBook();
		//Create a frame to delete a book
		JFrame f = new JFrame("Delete Book");
		
		JLabel l1 =new JLabel("Enter Book ID (BID)"); 
		l1.setBounds(95,15, 200,30); 
		
		JTextField bid = new JTextField();
		bid.setBounds(50, 65, 200, 30);
		
		JButton deleteBtn=new JButton("Delete");  
		deleteBtn.setBounds(60,115,80,25);
		deleteBtn.addActionListener(new ActionListener() { //Action listener for "Delete" button
			public void actionPerformed(ActionEvent e){
				DeleteBook delete = new DeleteBook(bid.getText());
				
				Connection connection = Conn.getConnection();

				try {
					Statement stmt =  connection.createStatement();
					//Delete book SQL statement with the where condition
					stmt.executeUpdate("DELETE FROM BOOKS WHERE BID ="+delete.getBID());
					JOptionPane.showMessageDialog(null,"Book deleted!");
					f.dispose();
					view.initComponents(); //Open the viewBookTab back

				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Book cannot be deleted because it is issued to somebody");
				}   

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(160,115,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {view.initComponents(); f.dispose();}});
		
		f.add(deleteBtn);
		f.add(cancel);
		f.add(l1);
		f.add(bid);
		f.setSize(300,200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}
