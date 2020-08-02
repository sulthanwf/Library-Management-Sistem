public class User {
	//Strings for username, password, address, and phone number. boolean admin also declared
	private String username,password, address, phoneNo;
	private boolean admin;

	//Default constructor
	User(){
		this.admin = false; //default admin value is false
	}

	//Parameterised constructor
	User(String username, String password, String address, String phone){
		this.username = username;
		this.password = password;
		this.address = address;
		this.phoneNo = phone;
	}

	//Getters and setters method
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNo() {

		return phoneNo;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String addUser() {
		String sql ="INSERT INTO USERS(USERNAME,PASSWORD,ADDRESS,PHONE,ADMIN) VALUES ('"+this.getUsername()+"','"+this.getPassword()+"','"+this.getAddress()+"','"+this.getPhoneNo()+"',"+this.isAdmin()+")";
		return sql;
	}

}