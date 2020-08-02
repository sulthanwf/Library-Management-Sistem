import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//Class ReturnBook that extends IssueBook class
public class ReturnBook extends IssueBook{
	private String IID,returnDate;
	private int borrowDays,fine;
	private Date dateOne, dateTwo;
	
	//Default constructor
	ReturnBook(){
		this.borrowDays=0;
	}
	
	//Parameterised constructor
	ReturnBook(String bid, String uid, String issue, int period){
		super(bid, uid, issue, period);
	}
	
	//Getters and setters method
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getIID() {
		return IID;
	}
	public int getBorrowDays() {
		return borrowDays;
	}
	public void setBorrowDays(int borrowDays) {
		this.borrowDays = borrowDays;
	}
	public void setIID(String iID) {
		IID = iID;
	}
	public Date getDateOne() {
		return dateOne;
	}
	public void setDateOne(Date dateOne) {
		this.dateOne = dateOne;
	}
	public Date getDateTwo() {
		return dateTwo;
	}
	public void setDateTwo(Date dateTwo) {
		this.dateTwo = dateTwo;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	
	//Translating String issue and return date from format dd-mm-yyyy to milliseconds with java.util.date
	public long dateDiff() throws ParseException {
		this.setDateOne((Date) new SimpleDateFormat("dd-MM-yyyy").parse(this.getIssueDate()));
		this.setDateTwo((Date) new SimpleDateFormat("dd-MM-yyyy").parse(this.getReturnDate()));
		//subtract the second date with the first date and stored them in long diff
		return this.getDateTwo().getTime() - this.getDateOne().getTime();
	}
	
	public boolean pastReturnDate() {
		boolean pastReturnDate;
		if (this.getBorrowDays() > this.getPeriod()) {
			pastReturnDate = true;
		}else {
			pastReturnDate = false;
		}
		return pastReturnDate;
	}
	
	public int calculateFine() {
		int fine = (this.getBorrowDays()-this.getPeriod())*10;
		return fine;
	}
	
	public void initComponents() {
		ReturnBook returnBook = new ReturnBook();
		//JFrame for user to enter return book details
		JFrame f = new JFrame("Enter Details");
		JLabel l1,l2;  
		JTextField iid, returnDate;
		
		l1=new JLabel("Issue ID(IID)"); 
		l1.setBounds(30,15, 100,30); 
		l2=new JLabel("Return Date(DD-MM-YYYY)");  
		l2.setBounds(30,50, 150,30); 

		iid = new JTextField();
		iid.setBounds(110, 15, 200, 30);

		returnDate=new JTextField();
		returnDate.setBounds(180, 50, 130, 30);


		JButton returnBtn=new JButton("Return");
		returnBtn.setBounds(130,170,80,25);
		returnBtn.addActionListener(new ActionListener() { //Add action listener to "Return" button

			public void actionPerformed(ActionEvent e){ 
				returnBook.setIID(iid.getText());
				returnBook.setReturnDate(returnDate.getText());

				Connection connection = Conn.getConnection();

				try {
					Statement stmt =  connection.createStatement();
					//Getting ISSUED_DATE and BID from the row that has matching IID as what user has entered
					ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE,BID FROM ISSUED WHERE IID="+returnBook.getIID());
					while (rs.next()) {
						//set issued date from result string 1 and bid from result string 2
						returnBook.setIssueDate(rs.getString(1));
						returnBook.setBID(rs.getString(2));
					}

					try {
						//convert dateDiff from milliseconds to days
						returnBook.setBorrowDays((int)(TimeUnit.DAYS.convert(returnBook.dateDiff(), TimeUnit.MILLISECONDS)));

					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					//Update return date and borrow status to issued table and books table respectively
					stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+returnBook.getReturnDate()+"' WHERE IID="+returnBook.getIID());
					stmt.executeUpdate("UPDATE BOOKS SET BORROW = 0 WHERE BID ="+returnBook.getBID());
					f.dispose();

					Connection connection1 = Conn.getConnection();
					Statement stmt1 =  connection1.createStatement();       
					//Getting PERIOD from the row that has matching IID that user has enter in issued table
					ResultSet rs1 = stmt1.executeQuery("SELECT PERIOD FROM ISSUED WHERE IID="+returnBook.getIID()); 
					while (rs1.next()) {
						//setting PERIOD to setPeriod method
						returnBook.setPeriod(rs1.getInt(1));
					}
					//Deciding whether total days borrowed is more that the duration that has been predetermined
					if(returnBook.pastReturnDate()) {
						//setting fine amount by subtract total borrowed days with period with setFine method
						returnBook.setFine(returnBook.calculateFine());
						//Update fine in issued table with the getFine method
						stmt1.executeUpdate("UPDATE ISSUED SET FINE="+returnBook.getFine()+" WHERE IID="+returnBook.getIID());  
						String fineToString = ("Fine: $"+returnBook.getFine()); 
						JOptionPane.showMessageDialog(null,fineToString);

					}

					JOptionPane.showMessageDialog(null,"Book Returned!");

				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}   

			}

		}); 
		f.add(l2);
		f.add(returnBtn);
		f.add(l1);
		f.add(iid);
		f.add(returnDate);
		f.setSize(350,250);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}
}
