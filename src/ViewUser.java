import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ViewUser {
	
	//ViewUser initComponents
	public void initComponents(){
		AddUser addUser = new AddUser();
		UpdateUser updateUser = new UpdateUser();
		DeleteUser deleteUser = new DeleteUser();
		JFrame f = new JFrame("Users List");

		Connection connection = Conn.getConnection();
		//Getting UID, USERNAME, ADDRESS, PHONE, and ADMIN from users table SQL
		String sql="select UID,USERNAME,ADDRESS,PHONE,ADMIN from users"; 
		try {
			Statement stmt =  connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			
			JButton add = new JButton("Add");
			add.setBounds(0,0,100,25);
			//Add action listener to "Add" button that will trigger addUser.iniComponents
			add.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { addUser.initComponents(); f.dispose();}});
			JButton update = new JButton("Update");
			update.setBounds(110, 0, 100, 25);
			//Add action listener to "Update" button that will trigger updateUser.iniComponents
			update.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {updateUser.initComponents(); f.dispose();}});
			JButton delete = new JButton("Delete");
			delete.setBounds(220, 0, 100, 25);
			//Add action listener to "Delete" button that will trigger deleteUser.initComponents
			delete.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {deleteUser.initComponents(); f.dispose();}});
			
			JTable userList= new JTable(); //Create JTable for user list
			userList.setModel(DbUtils.resultSetToTableModel(rs)); //Show the table content in DbUtils widget
			JScrollPane scrollPane = new JScrollPane(userList);

			p1.setLayout(new GridLayout(1,3));
			p1.add(add);
			p1.add(update);
			p1.add(delete);
			p2.add(scrollPane);
			f.add(p1, "North"); 
			f.add(p2, "South");
			f.setSize(485, 500);
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}       
	}
}


