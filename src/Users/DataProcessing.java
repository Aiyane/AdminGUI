package Users;

import java.util.Enumeration;
import java.util.Hashtable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;



public  class DataProcessing {
	private static Connection con;
	private static Statement st;
	private ResultSet rs;
//	private int numberofRows;
	private static boolean connectToDB=true;
	static final String USER = "root";
	static final String PASS = "zhangzhiqiang199";
	static String sql;
	
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
//		users = new Hashtable<String, User>();
//		users.put("jack", new Operator("jack","123","operator"));
//		users.put("rose", new Browser("rose","123","browser"));
//		users.put("kate", new Administrator("kate","123","administrator"));
//		Init();
//		
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
//		docs = new Hashtable<String,Doc>();
//		docs.put("0001",new Doc("0001","jack",timestamp,"Age Source Java","Age.java"));
//		docs.put("0002",new Doc("0002","kate",timestamp,"Age Source C++","Age.cpp"));
		// 编号, 上传者, 上传时间, 文件描述, 文件名
		
		
	}
	
	public static  void init(){
		// connect to database
		//con = DriverManager.getConnection( "url", "username", "password" );

		// create Statement to query database
		//st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );

		// update database connection status
//		if (Math.random()>0.3)
//			connectToDB = true;
//		else
//			connectToDB = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/java_admin";
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection( url,USER,PASS);
		} catch (Exception e) {
			System.out.println("连接数据库出错");
		}
		
	}
	
	public static Doc searchDoc(int ID) throws SQLException,IllegalStateException {
		init();
		sql = "SELECT * FROM docs";
		st = con.createStatement() ;
	    ResultSet rs = st.executeQuery(sql);
	    while(rs.next()) {
	    	int num = rs.getInt("id");
	    	if (num == ID) {
	    		String name = rs.getString("name");
				Timestamp time = rs.getTimestamp("time");
				String desc = rs.getString("file_desc");
				String doc_name = rs.getString("doc_name");
				Doc temp =new Doc(num, name, time, desc, doc_name);
				return temp;
			}
	    }	
		return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException,IllegalStateException{	
		init();
		sql = "SELECT * FROM docs";
		st = con.createStatement() ;
	    ResultSet rs = st.executeQuery(sql);
	    docs = new Hashtable<String,Doc>();
		while(rs.next()) {
			int num = rs.getInt("id");
			String name = rs.getString("name");
			Timestamp time = rs.getTimestamp("time");
			String desc = rs.getString("file_desc");
			String doc_name = rs.getString("doc_name");
			docs.put(String.valueOf(num),new Doc(num,name,time,desc,doc_name));
		}
		Enumeration<Doc> e = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String creator, Timestamp timestamp, String description, String filename) throws SQLException,IllegalStateException{
		init();
		st = con.createStatement();
		sql = "insert into docs (name, time, doc_name, file_desc) values ('"+creator+"', '"+timestamp+"', '"+filename+"', '"+description+"')";
		try {
			int rs = st.executeUpdate(sql);
			if(rs>0)
				return true;
		} catch (Exception e) {
			e.printStackTrace() ;  
			return false;
		}
//		Doc doc;		
//		
//		if (docs.containsKey(ID))
//			return false;
//		else{
//			doc = new Doc(ID,creator,timestamp,description,filename);
//			docs.put(String.valueOf(ID), doc);
//			return true;
//		}
		return false;
	}
	public static void copyFile(File fromFile,File toFile) throws IOException{
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }
        
        ins.close();
        out.close();
    }
	
	
	public static User searchUser(String name, String password) throws SQLException,IllegalStateException {
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Query" );
//		
		init();
		sql = "SELECT * FROM users";
		st = con.createStatement() ;
	    ResultSet rs = st.executeQuery(sql);
	    while(rs.next()) {
	    	if(rs.getString("name").equals(name)&&rs.getString("password").equals(password)) {
	    		switch (rs.getString("role")) {
				case "operator":Operator temp = new Operator(name, password, rs.getString("role"));return temp;
				case "administrator":Administrator temp1 = new Administrator(name, password, rs.getString("role"));return temp1;
				case "browser":Browser temp11 = new Browser(name, password, rs.getString("role"));return temp11;
				default:break;
				}
	    	}
	    }
//		if (users.containsKey(name)) {
//			User temp =users.get(name);
//			if ((temp.getPassword()).equals(password))
//				return temp;
//		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Query" );
		init();
		sql = "SELECT * FROM users";
		st = con.createStatement() ;
	    ResultSet rs = st.executeQuery(sql);
		users = new Hashtable<String, User>();
		while(rs.next()) {
			String name = rs.getString("name");
			String password = rs.getString("password");
			String role = rs.getString("role");
			users.put(name, new Operator(name,password,role));
		}
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Update" );
		init();
		st = con.createStatement();
		sql = "SELECT * FROM users";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			if (rs.getString("name").equals(name)) {
				sql = "UPDATE users SET name = '"+name+"', role = '"+role+"', password = '"+password+"' WHERE id = '"+rs.getInt("id")+"' ";
				int rs1 = st.executeUpdate(sql);
				return true;
			}	
		}
		return false;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException,IllegalStateException{
		
		
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Insert" );
		
		init();
		st = con.createStatement();
		sql = "SELECT * FROM users";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			if(rs.getString("name")==name)
				return false;
		}
		if(role.equalsIgnoreCase("administrator")||role.equalsIgnoreCase("operator")||role.equalsIgnoreCase("browser")) {
			sql = "INSERT INTO users (name, role, password) VALUES ('"+name+"', '"+role+"', '"+password+"')";
			if(st.executeUpdate(sql)>0)
				return true;
		}
		return false;
//		if (users.containsKey(name))
//			return false;
//		else{
//			if (role.equalsIgnoreCase("administrator"))
//				user = new Administrator(name,password, role);
//			else if (role.equalsIgnoreCase("operator"))
//				user = new Operator(name,password, role);
//			else
//				user = new Browser(name,password, role);
//			users.put(name, user);
//			return true;
//		}
	}
	
	public static boolean deleteUser(String name) throws SQLException,IllegalStateException{
//		if ( !connectToDB ) 
//	        throw new IllegalStateException( "Not Connected to Database" );
//		
//		if (Math.random()>0.7)
//			throw new SQLException( "Error in excecuting Delete" );
		init();
		st = con.createStatement();
		sql = "DELETE FROM users WHERE name = '"+name+"' ";
		try {
			st.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			return false;
		}
		
//		if (users.containsKey(name)){
//			users.remove(name);
//			return true;
//		}else
//			return false;
		
	}	
            
	public void disconnectFromDB() {
		if ( connectToDB ){
			// close Statement and Connection            
			try{
				rs.close();                        
			    st.close();                        
			    con.close();                       
			}catch ( SQLException sqlException ){                                            
			    sqlException.printStackTrace();           
			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }           

	
	public static void main(String[] args) {		

	}
	
}
