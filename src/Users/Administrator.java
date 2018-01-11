package Users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import client.Client;

public class Administrator extends User implements ActionListener, WindowListener{
	JFrame f = new JFrame("Administrator页面");
	JTextArea text = new JTextArea();
	JMenuBar mBar = new JMenuBar();
	JMenu menu1 = new JMenu("文件");
	JMenu menu2 = new JMenu("操作");
	JMenuItem mi11 = new JMenuItem("查看全部文件");
	JMenuItem mi12 = new JMenuItem("下载文件");
	JMenuItem mi21 = new JMenuItem("查看全部用户");
	JMenuItem mi22 = new JMenuItem("改变个人信息");
	JMenuItem mi23 = new JMenuItem("改变用户信息");
	JMenuItem mi24 = new JMenuItem("增加用户");
	JMenuItem mi25 = new JMenuItem("删除用户");
	JMenuItem mi26 = new JMenuItem("注销");
	JMenuItem mi27 = new JMenuItem("退出");
	
	public Administrator(String name, String password, String role) {
		super(name, password, role);
		
	}
	public void showMenu() {
		
		menu1.add(mi11);
		menu1.add(mi12);
		menu2.add(mi21);
		menu2.add(mi22);
		menu2.add(mi23);
		menu2.add(mi24);
		menu2.add(mi25);
		menu2.add(mi26);
		menu2.addSeparator();
		menu2.add(mi27);
		mBar.add(menu1);
		mBar.add(menu2);
		
		mi11.addActionListener(this);
		mi12.addActionListener(this);
		mi21.addActionListener(this);
		mi22.addActionListener(this);
		mi23.addActionListener(this);
		mi24.addActionListener(this);
		mi25.addActionListener(this);
		mi26.addActionListener(this);
		mi27.addActionListener(this);
		
		f.setJMenuBar(mBar);
		f.add(text);
		text.setText("欢迎回来! "+this.name);
		f.setSize(300, 280);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.addWindowListener(this);
//		f.setResizable(false);
	}
	public void changeUserInfo() {
		new Change_face();
	}
	public void delUser() {
		new Del_user();
	}
	
	private void addUser() {
		new Add_user();
	}
	
	public void listUser() {
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
	public void showFileList() {
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
		case "改变用户信息":changeUserInfo(); break;
		case "增加用户":addUser(); break;
		case "删除用户":delUser(); break;
		case "查看全部文件":showFileList(); break;
		case "下载文件":downloadFile(); break;
		default:break;
		}
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
