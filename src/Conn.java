import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	//getConnection method to simplify getting connection to MySQL database
	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", ""); //use library database
		}catch(Exception e){System.out.println(e);}
		return con;
	}
}
