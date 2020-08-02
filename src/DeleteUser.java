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

//Class DeleteUser that extends UpdateUser
class DeleteUser extends UpdateUser{
	//Default constructor
	DeleteUser(){}
	
	//Parameterised constructor
	DeleteUser(String id){
		super(id);
	}
	
	//DeteleBook class initComponents
	public void initComponents() {
		ViewUser view = new ViewUser();
		//Create a frame to delete a user
		JFrame f = new JFrame("Delete User");
		
		JLabel l1 =new JLabel("Enter User ID (UID)"); 
		l1.setBounds(95,15, 200,30); 
		
		JTextField uid = new JTextField();
		uid.setBounds(50, 65, 200, 30);
		
		JButton deleteBtn=new JButton("Delete");
		deleteBtn.setBounds(60,115,80,25);
		deleteBtn.addActionListener(new ActionListener() { //Add action listener for "Delete" button
			public void actionPerformed(ActionEvent e){
				DeleteUser delete = new DeleteUser(uid.getText()); 
				
				Connection connection = Conn.getConnection();

				try {
					Statement stmt =  connection.createStatement();
					//Delete book SQL statement with the where condition
					stmt.executeUpdate("DELETE FROM USERS WHERE UID ="+delete.getUID());
					JOptionPane.showMessageDialog(null,"User deleted!");
					f.dispose();
					view.initComponents(); //Open the viewUserTab back

				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "The user cannot be deleted because he's currently borrowing a book");
				}   

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(160,115,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {view.initComponents(); f.dispose();}});
		
		f.add(deleteBtn);
		f.add(cancel);
		f.add(l1);
		f.add(uid);
		f.setSize(300,200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}
