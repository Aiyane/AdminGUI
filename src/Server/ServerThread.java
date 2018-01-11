package Server;
/*
 * ���������̴߳�����
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
	// �ͱ��߳���ص�Socket
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	// �߳�ִ�еĲ���, ��Ӧ�ͻ��˵�����
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
		    // ��ȡ������, ����ȡ�ͻ�����Ϣ
			is = socket.getInputStream();  // �ֽ�������
			//=================ʵ��==============================//
			ois = new ObjectInputStream(is);
			NetTransfer nt =  (NetTransfer) ois.readObject();
			//=================ʵ��==============================//
//			isr = new InputStreamReader(is);  // ���ֽ���ת�����ַ���
//			br = new BufferedReader(isr);  // Ϊ��������ӻ���
//			String info = null;
//			while((info = br.readLine()) != null) {  // ѭ����ȡ�ͻ�����Ϣ
//				System.out.println("���Ƿ�����, �ͻ���˵: " + info);
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
					socket.shutdownInput();  // �ر�������
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
					socket.shutdownInput();  // �ر�������
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(nt);
					break;
				
				case "getAllUser":
					Enumeration<User> e = DataProcessing.getAllUser();
					String name_list = "";
					while(e.hasMoreElements()){
						User user = e.nextElement();//����nextElement�������Ԫ��
						name_list = name_list + "\n�û���: " + user.getName() + "\t���: " + user.getRole();
			        }
					nt.ifRun = true;
					nt.strRes = name_list;
					socket.shutdownInput();  // �ر�������
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
					socket.shutdownInput();  // �ر�������
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
						Doc file = f.nextElement();//����nextElement�������Ԫ��
						doc_list = doc_list + "\n�ļ���: " + file.doc_name + "\n�ļ�id: "+ file.num + "\n�ϴ���: " 
						+ file.name + "\n�ϴ�ʱ��" + file.time + "\n�ļ�����" + file.desc;
			        }
					nt.ifRun = true;
					nt.strRes = doc_list;
					socket.shutdownInput();  // �ر�������
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
	                // �����ļ�
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
//			socket.shutdownInput();  // �ر�������
			
			// ��ȡ�����, ��Ӧ�ͻ��˵�����
//			os = socket.getOutputStream();
//			pw = new PrintWriter(os);  // ��װΪ��ӡ��
//			pw.write("��ӭ��!");
//			pw.flush();  // ����flush()����, ���������
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				// �ر���Դ
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
