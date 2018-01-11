package Server;
/*
 * 服务器端线程处理类
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Enumeration;

import Users.DataProcessing;
import Users.Doc;
import Users.NetTransfer;
import Users.User;

public class ServerThread extends Thread {
	// 和本线程相关的Socket
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	// 线程执行的操作, 相应客户端的请求
	public void run() {
		
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		ObjectInputStream ois=null;
		ObjectOutputStream oos=null;
		FileInputStream fis=null;
		DataOutputStream dos=null;
		FileOutputStream fos=null;
		DataInputStream dis=null;
		try {
		    // 获取输入流, 并读取客户端信息
			is = socket.getInputStream();  // 字节输入流
			//=================实验==============================//
			ois = new ObjectInputStream(is);
			NetTransfer nt =  (NetTransfer) ois.readObject();
			//=================实验==============================//
//			isr = new InputStreamReader(is);  // 将字节流转换成字符流
//			br = new BufferedReader(isr);  // 为输入流添加缓冲
//			String info = null;
//			while((info = br.readLine()) != null) {  // 循环读取客户端信息
//				System.out.println("我是服务器, 客户端说: " + info);
//			}
			switch (nt.action){
			
				case "login": 
					String[] arrs =  nt.paras.split("\\|");
					User u = DataProcessing.searchUser(arrs[0], arrs[1]);
					if (u!=null)
					{
						nt.ifRun = true;
						nt.strRes = u.getName();
						nt.objRes = u;
					}
					else{
						nt.ifRun = false;
					}
					socket.shutdownInput();  // 关闭输入流
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
					
				case "register":
					String[] arrs1 =  nt.paras.split("\\|");
					if(DataProcessing.insertUser(arrs1[0],  arrs1[1], arrs1[2])) {
						nt.ifRun = true;
					}else {
						nt.ifRun = false;
					}
					socket.shutdownInput();  // 关闭输入流
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
				
				case "getAllUser":
					Enumeration<User> e = DataProcessing.getAllUser();
					String name_list = "";
					while(e.hasMoreElements()){
						User user = e.nextElement();//调用nextElement方法获得元素
						name_list = name_list + "\n用户名: " + user.getName() + "\t身份: " + user.getRole();
			        }
					nt.ifRun = true;
					nt.strRes = name_list;
					socket.shutdownInput();  // 关闭输入流
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
					
				case "changeSelf":
					String[] arrs2 =  nt.paras.split("\\|");
					if(DataProcessing.updateUser(arrs2[0],  arrs2[1], arrs2[2])) {
						nt.ifRun = true;
					}else {
						nt.ifRun = false;
					}
					socket.shutdownInput();  // 关闭输入流
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
					
				case "delUser":
					String str = nt.paras;
					if(DataProcessing.deleteUser(str)) {
						nt.ifRun = true;
					}else {
						nt.ifRun = false;
					}
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
					
				case "getAllDoc":
					Enumeration<Doc> f = DataProcessing.getAllDocs();
					String doc_list = "";
					while(f.hasMoreElements()){
						doc_list = doc_list + "\n============================";
						Doc file = f.nextElement();//调用nextElement方法获得元素
						doc_list = doc_list + "\n文件名: " + file.doc_name + "\n文件id: "+ file.num + "\n上传者: " 
						+ file.name + "\n上传时间" + file.time + "\n文件描述" + file.desc;
			        }
					nt.ifRun = true;
					nt.strRes = doc_list;
					socket.shutdownInput();  // 关闭输入流
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
					
				case "download":
					int id = nt.id;
					Doc temp = DataProcessing.searchDoc(id);
					File file =new File("D://code//java//Pan//"+temp.doc_name);
	                fis =new FileInputStream(file);
	                nt.ifRun = true;
	                oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
	                
					byte[] sendBytes =new byte[1024];
	                int length =0;
	                dos =new DataOutputStream(socket.getOutputStream());
	                // 传输文件
	                while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
	                    dos.write(sendBytes,0, length);
	                    dos.flush();
	                }
					break;
					
				case "upDoc":
					String[] arrs3 =  nt.paras.split("\\|");
					String name = arrs3[0];
					String desc = arrs3[1];
					String doc_name = arrs3[2];
					String path = arrs3[3];
					Timestamp timestamp = (Timestamp) nt.obj;
					nt.ifRun = true;
	                oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					
					fos = new FileOutputStream(new File("D://code//java//Pan//" + doc_name));
					dis =new DataInputStream(socket.getInputStream());
					byte[] sendBytes1 =new byte[1024];
		            while(true){
		                int read =0;
		                read = dis.read(sendBytes1);
		                if(read == -1)
		                    break;
		                fos.write(sendBytes1,0, read);
		                fos.flush();
		            }
					DataProcessing.insertDoc(name, timestamp, desc, doc_name);
					break;
				default: break;
			}
//			socket.shutdownInput();  // 关闭输入流
			
			// 获取输出流, 相应客户端的请求
//			os = socket.getOutputStream();
//			pw = new PrintWriter(os);  // 包装为打印流
//			pw.write("欢迎您!");
//			pw.flush();  // 调用flush()方法, 将缓冲输出
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// 关闭资源
				if(fos!=null)
					fos.close();
				if(dis!=null)
					dis.close();
				if(fis!=null)
					fis.close();
				if(dos!=null)
					dos.close();
				if(oos!=null)
					oos.close();
				if(ois!=null)
					ois.close();
				if(br!=null)
					br.close();
				if(isr!=null)
					isr.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
