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

//IssueBook class
public class IssueBook {
	private String BID, UID, issueDate;
	private int period;
	
	//Default constructor
	IssueBook(){}
	
	//Parameterised cosntructor
	IssueBook(String bid, String uid, String issue, int period){
		this.BID = bid;
		this.UID = uid;
		this.issueDate = issue;
		this.period = period;
	}
	
	//Getters and Setters method
	public String getBID() {
		return BID;
	}
	public String getUID() {
		return UID;
	}
	public int getPeriod() {
		return period;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setBID(String bID) {
		BID = bID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public void setPeriod(int period) {
		this.period = period;
	}

	//IssueBook initComponents
	public void initComponents() {
		//Declare a frame to enter details for issuing a book
		JFrame f = new JFrame("Enter Details");
		JLabel l1,l2,l3,l4;  
		JTextField bid, uid, period, issue;
		
		l1=new JLabel("Book ID(BID)");
		l1.setBounds(30,15, 100,30); 
		l2=new JLabel("User ID(UID)");
		l2.setBounds(30,53, 100,30); 
		l3=new JLabel("Period(days)");
		l3.setBounds(30,90, 100,30); 
		l4=new JLabel("Issued Date(DD-MM-YYYY)");
		l4.setBounds(30,127, 150,30); 

		bid = new JTextField();
		bid.setBounds(110, 15, 200, 30);
		uid =new JTextField();
		uid.setBounds(110, 53, 200, 30);
		period =new JTextField();
		period.setBounds(110, 90, 200, 30);
		issue =new JTextField();
		issue.setBounds(180, 130, 130, 30);   

		JButton issueBtn=new JButton("Issue");
		issueBtn.setBounds(80,170,80,25);
		issueBtn.addActionListener(new ActionListener() { //Add action listener for "Issue" button

			public void actionPerformed(ActionEvent e){
				int periodInt = Integer.parseInt(period.getText());
				IssueBook issueBook = new IssueBook(bid.getText(), uid.getText(), issue.getText(), periodInt);

				Connection connection = Conn.getConnection();

				try {
					Statement stmt = connection.createStatement();
					//Insert IssueBook SQL statement 
					stmt.executeUpdate("INSERT INTO ISSUED(UID,BID,ISSUED_DATE,PERIOD) VALUES ('"+issueBook.getUID()+"','"+issueBook.getBID()+"','"+issueBook.getIssueDate()+"',"+issueBook.getPeriod()+")");
					stmt.executeUpdate("UPDATE BOOKS SET BORROW = 1 WHERE BID ="+issueBook.getBID());
					JOptionPane.showMessageDialog(null,"Book Issued!");
					f.dispose();

				}

				catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1);
				}   

			}

		});

		JButton cancel = new JButton("Cancel");
		cancel.setBounds(180,170,80,25);
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {f.dispose();}});
		
		f.add(issueBtn);
		f.add(cancel);
		f.add(l3);
		f.add(l4);
		f.add(l1);
		f.add(l2);
		f.add(uid);
		f.add(bid);
		f.add(period);
		f.add(issue);
		f.setSize(350,250);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}
