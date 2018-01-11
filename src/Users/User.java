package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable{

	String name;
	String password;
	String role;
	public User(String name, String password, String role) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void showMenu() {
		
	}
	public void showFileList() {
		
	}
	public void copyFile(File fromFile,File toFile) throws IOException{
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
	public void downloadFile() {
//		Scanner in = new Scanner(System.in);
//		System.out.println("请输入文件ID: ");
//		String id = in.next();
//		System.out.println("请输入下载到哪个路径: ");
//		String doc_dir = in.next();
//		File file2 = new File(doc_dir);
//		while(true) {
//			try {
//				Doc temp = DataProcessing.searchDoc(id);
//				String doc_name = temp.doc_name;
//				File file1 = new File("D://code//java//Pan//"+doc_name);
//				copyFile(file1, file2);
//				break;
//				
//			} catch (Exception e) {
//				System.out.println("出现错误, 正在重试!");
//			}
//		}
		new Download_file();
	}
	public void changeSelfInfo() {
		new Change_self(this.name, this.role);
	}
	public void exitSystem() {
		System.out.println("再会!" + this.name+'.');
		return ;
	}
	public Object getPassword() {
		return this.password;
	}
}
