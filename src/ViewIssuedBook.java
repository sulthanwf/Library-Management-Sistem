import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ViewIssuedBook {

	//ViewIssuedBook initComponents
	public void initComponents() {
		JFrame f = new JFrame("Issued Book List");

		Connection connection = Conn.getConnection();
		//Getting IID, UID, BID, BNAME, ISSUED_DATE, RETURN_DATE, PERIOD, and FINE from issued and books table SQL
		String sql="SELECT i.IID, i.UID, i.BID, b.BNAME, i.ISSUED_DATE, i.RETURN_DATE, i.PERIOD, i.FINE\r\n" + 
				"FROM issued i, books b\r\n" + 
				"WHERE i.BID = b.BID;";
		try {
			Statement stmt =  connection.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			JTable issuedBookList= new JTable(); //A JTable for issued book list
			issuedBookList.setModel(DbUtils.resultSetToTableModel(rs)); //Show the table content in DbUtils widget
			//Set column 0,1,2, and 3 sizes to custom sizes
			issuedBookList.getColumnModel().getColumn(0).setPreferredWidth(20);
			issuedBookList.getColumnModel().getColumn(1).setPreferredWidth(20);
			issuedBookList.getColumnModel().getColumn(2).setPreferredWidth(20);
			issuedBookList.getColumnModel().getColumn(3).setPreferredWidth(150);

			JScrollPane scrollPane = new JScrollPane(issuedBookList);

			f.add(scrollPane);
			f.setSize(800, 400);
			f.setVisible(true);
			f.setLocationRelativeTo(null);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
		}       
	}
}
