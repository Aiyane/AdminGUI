package client;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import Users.NetTransfer;
import Users.User;

public class Client {
	public NetTransfer Login(String name, String password) {
		NetTransfer tem = new NetTransfer();
		tem.action = "login";
		tem.paras = name + "|" + password;
		return tem;
	}
	public NetTransfer Register(String name, String password, String role) {
		NetTransfer tem = new NetTransfer();
		tem.action = "register";
		tem.paras = name + "|" + password + "|" + role;
		return tem;
	}
	public NetTransfer GetAllUser() {
		NetTransfer tem = new NetTransfer();
		tem.action = "getAllUser";
		return tem;
	}
	private NetTransfer ChangeSelf(String name, String password, String role) {
		NetTransfer tem = new NetTransfer();
		tem.action = "changeSelf";
		tem.paras = name + "|" + password + "|" + role;
		return tem;
	}
	public NetTransfer DelUser(String name) {
		NetTransfer tem = new NetTransfer();
		tem.action = "delUser";
		tem.paras = name;
		return tem;
	}
	private NetTransfer GetAllDoc() {
		NetTransfer tem = new NetTransfer();
		tem.action = "getAllDoc";
		return tem;
	}
	public NetTransfer Downlosd(String id, String path) {
		NetTransfer tem = new NetTransfer();
		tem.action = "download";
		tem.id = Integer.valueOf(id);
		tem.paras = path;
		return tem;
	}
	public NetTransfer UpDoc(String name, String desc, String doc_name, String path) {
		NetTransfer tem = new NetTransfer();
		tem.action = "upDoc";
		tem.paras = name + "|" + desc + "|" + doc_name + "|" + path;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tem.obj = timestamp;
		return tem;
	}
	public Object Start(String[] args) throws Exception {
		
		
			// 1. �����ͻ���Socket, ָ����������ַ�Ͷ˿�
			Socket socket = new Socket("localhost", 8888);
			// 2. ��ȡ�����, ��������˷�����Ϣ
			OutputStream os = socket.getOutputStream();  // �ֽ������
//			PrintWriter pw = new PrintWriter(os);  // ���������װΪ��ӡ��
			//====================ʵ��=====================//
			ObjectOutputStream oos = new ObjectOutputStream(os);
			NetTransfer res = null;
			switch (args[0]) {
			case "login":
				res = Login(args[1], args[2]);
				break;
			case "register":
				res = Register(args[1], args[2], args[3]);
				break;
			case "getAllUser":
				res = GetAllUser();
				break;
				
			case "changeSelf":
				res = ChangeSelf(args[1], args[2], args[3]);
				break;
				
			case "delUser":
				res = DelUser(args[1]);
				break;
				
			case "getAllDoc":
				res = GetAllDoc();
				break;
				
			case "download":
				res = Downlosd(args[1], args[2]);
				break;
				
			case "upDoc":
				res = UpDoc(args[1], args[2], args[3], args[4]);
				break;

			default:
				break;
			}
			
			oos.writeObject(res);
			oos.flush();
			//============================================//
//			pw.write("�û���: admin;����: 123");
//			pw.flush();  // �������
//			socket.shutdownOutput();  // �ر������
			
			// 3. ��ȡ������, ����ȡ�������˵���Ӧ��Ϣ
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			NetTransfer nt =  (NetTransfer) ois.readObject();
			
			switch (nt.action) {
			case "login":
				if (nt.ifRun) {
					User temp = (User)nt.objRes;
					temp.showMenu();
					break;
				}else {
					throw new Exception();
				}
				
			case "register":
				if (nt.ifRun) {
					break;
				}else {
					throw new Exception();
				}
			
			case "getAllUser":
				if (nt.ifRun) {
					return nt.strRes;
				}else {
					throw new Exception();
				}
				
			case "changeSelf":
				if (nt.ifRun) {
					return true;
				}else {
					return false;
				}
				
			case "delUser":
				if (nt.ifRun) {
					return true;
				}else {
					return false;
				}
				
			case "getAllDoc":
				if (nt.ifRun) {
					return nt.strRes;
				}else {
					throw new Exception();
				}
				
			case "download":
				if(nt.ifRun) {
					FileOutputStream fos = new FileOutputStream(new File(nt.paras));
					DataInputStream dis =new DataInputStream(socket.getInputStream());
					byte[] sendBytes =new byte[1024];
		            while(true){
		                int read =0;
		                read = dis.read(sendBytes);
		                if(read == -1)
		                    break;
		                fos.write(sendBytes,0, read);
		                fos.flush();
		            }
		            break;
				}else {
					throw new Exception();
				}
			
			case "upDoc":
				if(nt.ifRun) {
					byte[] sendBytes =new byte[1024];
	                int length =0;
	                DataOutputStream dos =new DataOutputStream(socket.getOutputStream());
	                File file = new File(args[4]);
	                FileInputStream fis =new FileInputStream(file);
	                // �����ļ�
	                while((length = fis.read(sendBytes,0, sendBytes.length)) >0){
	                    dos.write(sendBytes,0, length);
	                    dos.flush();
	                }
					break;
				}else {
					throw new Exception();
				}
				
			default:break;
			}
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));   // ת��Ϊ�ַ���, ��Ϊ��������ӻ���
//			String info = null;
//			while((info = br.readLine()) != null) {  // ѭ����ȡ�������Ӧ��Ϣ
//				System.out.println("���ǿͻ���, ������˵: " + info);
//			}
			
			socket.shutdownInput();  // �ر�������
			// 4. �ر���Դ
			//====================ʵ��=====================//
			oos.close();
			//============================================//
//			br.close();
			is.close();
//			pw.close();
			os.close();
			return null;
			
		
	}
}
