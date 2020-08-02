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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//UpdateUser class which extend User class
class UpdateUser extends User{
	private String UID;

	//Default constructor
	UpdateUser(){}
	
	//Parameterised constructor 1
	UpdateUser(String id){
		this.UID=id;
	}

	//Parameterised constructor 2
	UpdateUser(String username, String password, String address, String phone){
		super(username, password, address, phone);
	}

	public void setUID(String uid) {
		UID = uid;
	}
	public String getUID() {
		return UID;
	}
	
	//UpdateUser initCmponents
	public void initComponents() {
		ViewUser view = new ViewUser();
		//Create a frame to enter UID on which the user will be updated
		JFrame f = new JFrame("Update User");

		JLabel l1 = new JLabel("Enter User ID (UID)");
		l1.setBounds(95,15, 200,30);

		JTextField uid = new JTextField();
		uid.setBounds(50, 65, 200, 30);

		JButton submit=new JButton("Submit");  
		submit.setBounds(60,115,80,25);
		submit.addActionListener(new ActionListener() { //Add action listener to the "Submit" button
			public void actionPerformed(ActionEvent e) {
				//Set the UID that has been entered into a parameterised constructor
				UpdateUser update = new UpdateUser(uid.getText());
				Connection connection = Conn.getConnection();
				//Getting the UID,USERNAME,PASSWORD,ADDRESS,PHONE from table users where UID match the UID that has been entered
				String sql="SELECT UID,USERNAME,PASSWORD,ADDRESS,PHONE FROM users where UID =" + update.getUID(); 
				try {
					Statement stmt = connection.createStatement();
					ResultSet rs= stmt.executeQuery(sql);
					rs.next();
					//Create a frame to enter the new user details
					JFrame f2 = new JFrame("Update User Details");
					JLabel l1,l2,l3,l4;  
					JTextField user,addr,phone;
					JPasswordField pass;

					l1=new JLabel("Username");
					l1.setBounds(30,15, 100,30); 
					l2=new JLabel("Password"); 
					l2.setBounds(30,50, 100,30); 
					l3 = new JLabel("Address");
					l3.setBounds(30, 85, 100, 30);
					l4 = new JLabel("Phone No");
					l4.setBounds(30, 120, 100, 30);

					user = new JTextField();
					user.setBounds(110, 15, 200, 30);
					user.setText(rs.getString(2));

					pass=new JPasswordField();
					pass.setBounds(110, 50, 200, 30);
					pass.setText(rs.getString(3));

					addr =new JTextField();
					addr.setBounds(110, 85, 200, 30);
					addr.setText(rs.getString(4));

					phone=new JTextField();
					phone.setBounds(110, 120, 200, 30);
					phone.setText(rs.getString(5));

					JButton updateBtn=new JButton("Update");
					updateBtn.setBounds(130,180,80,25);
					updateBtn.addActionListener(new ActionListener() { //Add action listener to "Update" button
						public void actionPerformed(ActionEvent e){
							User add = new User(user.getText(), String.valueOf(pass.getPassword()), addr.getText(), phone.getText());

							Connection connection = Conn.getConnection();

							try {
								Statement stmt =  connection.createStatement();
								//Update the user details by the value that the user has entered
								stmt.executeUpdate("UPDATE USERS SET USERNAME ='"+add.getUsername()+"',PASSWORD = '"+add.getPassword()+"',ADDRESS='"+add.getAddress()+"',PHONE = '"+add.getPhoneNo()+"' WHERE UID = "+update.getUID());
								JOptionPane.showMessageDialog(null,"User updated!"); //Update success message
								f.dispose();
								f2.dispose();
								view.initComponents();//Open the viewUserTab back

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
					f2.add(l4);
					f2.add(user);
					f2.add(pass);
					f2.add(addr);
					f2.add(phone);
					f2.setSize(350,300);
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
		f.add(uid);
		f.setSize(300,200);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}
