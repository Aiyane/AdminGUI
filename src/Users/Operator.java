package Users;

import java.awt.event.*;
import javax.swing.*;

import client.Client;

public class Operator extends User implements ActionListener, WindowListener{
	JFrame f = new JFrame("Operator页面");
	JTextArea text = new JTextArea();
	JMenuBar mBar = new JMenuBar();
	JMenu menu1 = new JMenu("文件");
	JMenu menu2 = new JMenu("操作");
	JMenuItem mi11 = new JMenuItem("查看全部文件");
	JMenuItem mi12 = new JMenuItem("下载文件");
	JMenuItem mi13 = new JMenuItem("上传文件");
	JMenuItem mi21 = new JMenuItem("改变个人信息");
	JMenuItem mi22 = new JMenuItem("注销");
	JMenuItem mi23 = new JMenuItem("退出");
	public Operator(String name, String password, String role) {
		super(name, password, role);
	}
	public void uploadFile() {
//		Scanner in = new Scanner(System.in);
//		System.out.println("请输入文件ID: ");
//		String id = in.next();
//		System.out.println("请输入文件描述: ");
//		String desc = in.next();
//		System.out.println("请输入上传文件的路径: ");
//		String doc_dir = in.next();
//		File file2 = new File(doc_dir);
//	    String[] fileName = doc_dir.split("/");
//	    String doc_name = fileName[fileName.length-1];
//	    File file1 = new File("D://code//java//Pan//"+doc_name);
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		while(true){
//			try{
//				DataProcessing.insertDoc(id, this.name, timestamp, desc, doc_name);
//				copyFile(file2, file1);
//				break;
//			}
//			catch(Exception e){
//				System.out.println("上传出错, 正在重试!");
//			}
//		}
		new Upload_file(this.name);
	}
	public void listUser() {
//		while(true) {
//			try {
//				Enumeration<User> e = DataProcessing.getAllUser();
//				String name_list = "";
//				while(e.hasMoreElements()){
//					User user = e.nextElement();//调用nextElement方法获得元素
//					name_list = name_list + "\n用户名: " + user.name + "\t身份: " + user.role;
//		        }
//				text.setText(name_list);
//				break;
//			}
//			catch (Exception e) {
//				System.out.println("操作失败, 请等待!");
//			}
//		}
		while(true) {
			try {
				//Enumeration<User> e = DataProcessing.getAllUser();
				Client temp1 = new Client();
				String[] aStrings = {"getAllUser"};
				String name_list = (String)temp1.Start(aStrings);
				
				text.setText(name_list);
				break;
			}
			catch (Exception e) {
				System.out.println("操作失败, 请等待!");
			}
		}
	}
	public void showMenu() {
		
		menu1.add(mi11);
		menu1.add(mi12);
		menu1.add(mi13);
		menu2.add(mi21);
		menu2.add(mi22);
		menu2.addSeparator();
		menu2.add(mi23);
		mBar.add(menu1);
		mBar.add(menu2);
		
		mi11.addActionListener(this);
		mi12.addActionListener(this);
		mi13.addActionListener(this);
		mi21.addActionListener(this);
		mi22.addActionListener(this);
		mi23.addActionListener(this);
		
		f.setJMenuBar(mBar);
		f.add(text);
		text.setText("欢迎回来! "+this.name);
		f.setSize(300, 280);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.addWindowListener(this);
//		f.setResizable(false);
	}
	public void showFileList() {
//		while(true){
//			try{
//				Enumeration<Doc> f = DataProcessing.getAllDocs();
//				String doc_list = "";
//				while(f.hasMoreElements()){
//					doc_list = doc_list + "\n============================";
//					Doc file = f.nextElement();//调用nextElement方法获得元素
//					doc_list = doc_list + "\n文件名: " + file.doc_name + "\n文件id: "+ file.num + "\n上传者: " 
//					+ file.name + "\n上传时间" + file.time + "\n文件描述" + file.desc;
//		        }
//				text.setText(doc_list);
//				break;
//			}catch(Exception e){
//				System.out.println("浏览文件出现错误, 请等候!");
//			}
//		}
		while(true){
			try{
				Client temp1 = new Client();
				String[] aStrings = {"getAllDoc"};
				String name_list = (String)temp1.Start(aStrings);
				
//				Enumeration<Doc> f = DataProcessing.getAllDocs();
//				String doc_list = "";
//				while(f.hasMoreElements()){
//					doc_list = doc_list + "\n============================";
//					Doc file = f.nextElement();//调用nextElement方法获得元素
//					doc_list = doc_list + "\n文件名: " + file.doc_name + "\n文件id: "+ file.num + "\n上传者: " 
//					+ file.name + "\n上传时间" + file.time + "\n文件描述" + file.desc;
//		        }
				
				text.setText(name_list);
				break;

			}catch(Exception e){
				System.out.println("浏览文件出现错误, 请等候!");
			}
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "注销":f.dispose(); break;
		case "退出":System.exit(0);
		case "查看全部用户":listUser(); break;
		case "改变个人信息":changeSelfInfo(); break;
		case "查看全部文件":showFileList(); break;
		case "下载文件":downloadFile(); break;
		case "上传文件":uploadFile(); break;
		default:break;
		}
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
