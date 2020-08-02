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

//Class ViewBook
public class ViewBook {	

	//ViewBook initComponents
	public void initComponents() {
		//Objects from the 3 functions are created
		AddBook addBook = new AddBook();
		DeleteBook deleteBook = new DeleteBook();
		UpdateBook updateBook = new UpdateBook();
		JFrame f = new JFrame("Books Available");

		Connection connection = Conn.getConnection();
		//Select everything from books table SQL
		String sql="select * from BOOKS"; 
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			
			JButton add = new JButton("Add");
			add.setBounds(0,0,100,25);
			//Add action listener to add button that will trigger addBook.initComponents
			add.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { addBook.initComponents(); f.dispose();  }});
			JButton update = new JButton("Update");
			update.setBounds(110, 0, 100, 25);
			//Add action listener to update button that will trigger updateBook.initComponents
			update.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { updateBook.initComponents(); f.dispose();}});
			JButton delete = new JButton("Delete");
			delete.setBounds(220, 0, 100, 25);
			//Add action listener to delete button that will trigger deleteBook.initComponents
			delete.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) { deleteBook.initComponents(); f.dispose();}});

			JTable bookList= new JTable();  // Create a new JTable for book list
			bookList.setModel(DbUtils.resultSetToTableModel(rs)); //Show the table content in DbUtils widget
			//Set column 0,1, and 3 sizes to custom sizes
			bookList.getColumnModel().getColumn(0).setPreferredWidth(30);
			bookList.getColumnModel().getColumn(1).setPreferredWidth(150);
			bookList.getColumnModel().getColumn(3).setPreferredWidth(150);
			JScrollPane scrollPane = new JScrollPane(bookList); 

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
