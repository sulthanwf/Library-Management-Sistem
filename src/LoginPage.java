import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage {
	public static void login() {

		JFrame f=new JFrame("Login"); //Login frame
		JLabel l1,l2;  
		
		//JLabel to label the text field
		l1=new JLabel("Username");  
		l1.setBounds(30,15, 100,30); 
		l2=new JLabel("Password");  
		l2.setBounds(30,50, 100,30);    

		//JTextFields for user to enter username and password
		JTextField user = new JTextField(); 
		user.setBounds(110, 15, 200, 30);
		JPasswordField pass=new JPasswordField(); 
		pass.setBounds(110, 50, 200, 30);

		//login JButton
		JButton loginBtn=new JButton("Login");
		loginBtn.setBounds(130,90,80,25);
		loginBtn.addActionListener(new ActionListener() {  //adding action listener to the login button

			public void actionPerformed(ActionEvent e){ 

				//define username and password by retrieving it from JTextField
				String username = user.getText(); 
				@SuppressWarnings("deprecation")
				String password = pass.getText();

				//Checking if one of the JTextField is empty
				if(username.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter username");
				} 
				else if(password.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please enter password");
				}
				else { 
					Connection connection= Conn.getConnection(); //get connection from Conn class

					try
					{
						Statement stmt = connection.createStatement();
						String sql = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'"); //checking if the user exist in the database
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next()==false) { 
							System.out.print("No user");  
							JOptionPane.showMessageDialog(null,"Wrong Username/Password!");

						}
						else {
							f.dispose();
							rs.beforeFirst(); 
							while(rs.next())
							{
								//checking if the user is an admin
								String admin = rs.getString("ADMIN"); 
								String UID = rs.getString("UID"); 
								if(admin.equals("1")) { 
									LibrarianMenu lm = new LibrarianMenu();
									lm.librarianMenu();
								}
								else{
									System.out.println("Librarian does not exist!");
								}
							}
						}
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}               
		});

		f.add(loginBtn);
		f.add(pass);
		f.add(user); 
		f.add(l1); 
		f.add(l2);

		f.setSize(400,180);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);

	}
}
