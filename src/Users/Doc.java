package Users;

import java.io.Serializable;
import java.sql.*;

public class Doc implements Serializable{
	public int num;
	public String name;
	public Timestamp time;
	public String desc;
	public String doc_name;
	public Doc(int num, String name, Timestamp time, String desc, String doc_name) {
		this.num = num;
		this.name = name;
		this.time = time;
		this.desc = desc;
		this.doc_name = doc_name;
	}
}
