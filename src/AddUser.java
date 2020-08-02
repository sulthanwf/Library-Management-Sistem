import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//Class AddUser that extends User class
class AddUser extends User{
	//Default constructor
	AddUser(){}
	
	//Parameterised constructor
	AddUser(String username, String password, String address, String phone){
		super(username, password, address, phone);
	}
	
	//initComponents of AddBook class
	public void initComponents() {
		ViewUser view = new ViewUser();
		//create a frame for entering user details
		JFrame f = new JFrame("Enter User Details"); 
		JTextField user, phone, addr;
		JPasswordField pass;
		JLabel l1,l2,l3,l4;  
		JRadioButton a1, a2;

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
		pass=new JPasswordField();
		pass.setBounds(110, 50, 200, 30);
		addr = new JTextField();
		addr.setBounds(110, 85, 200, 30);
		phone = new JTextField();
		phone.setBounds(110, 120, 200, 30);

		a1 = new JRadioButton("Admin");
		a1.setBounds(55, 150, 200,30);
		a2 = new JRadioButton("User");
		a2.setBounds(130, 150, 200,30);

		ButtonGroup bg=new ButtonGroup();    
		bg.add(a1);bg.add(a2);  

		JButton createBtn =new JButton("Create"); 
		createBtn.setBounds(80,180,80,25);
		createBtn.addActionListener(new ActionListener() {// Action listener of "Create" button

			public void actionPerformed(ActionEvent e){
				AddUser a = new AddUser(user.getText(), String.valueOf(pass.getPassword()), addr.getText(), phone.getText());

				if(a1.isSelected()) {
					a.setAdmin(true);
				}

				Connection connection = Conn.getConnection();

				try {
					Statement stmt = connection.createStatement();
					//Create new user SQL statement
					stmt.executeUpdate(a.addUser());
					JOptionPane.showMessageDialog(null,"User added!");
					f.dispose();
					view.initComponents(); //Open the viewUserTab back
				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}   

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(180,180,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {view.initComponents(); f.dispose();}});

		f.add(createBtn);
		f.add(cancel);
		f.add(a2);
		f.add(a1);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(user);
		f.add(pass);
		f.add(addr);
		f.add(phone);
		f.setSize(350,300); 
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}