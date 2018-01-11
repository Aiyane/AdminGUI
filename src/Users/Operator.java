package Users;

import java.awt.event.*;
import javax.swing.*;

import client.Client;

public class Operator extends User implements ActionListener, WindowListener{
	JFrame f = new JFrame("Operatorҳ��");
	JTextArea text = new JTextArea();
	JMenuBar mBar = new JMenuBar();
	JMenu menu1 = new JMenu("�ļ�");
	JMenu menu2 = new JMenu("����");
	JMenuItem mi11 = new JMenuItem("�鿴ȫ���ļ�");
	JMenuItem mi12 = new JMenuItem("�����ļ�");
	JMenuItem mi13 = new JMenuItem("�ϴ��ļ�");
	JMenuItem mi21 = new JMenuItem("�ı������Ϣ");
	JMenuItem mi22 = new JMenuItem("ע��");
	JMenuItem mi23 = new JMenuItem("�˳�");
	public Operator(String name, String password, String role) {
		super(name, password, role);
	}
	public void uploadFile() {
//		Scanner in = new Scanner(System.in);
//		System.out.println("�������ļ�ID: ");
//		String id = in.next();
//		System.out.println("�������ļ�����: ");
//		String desc = in.next();
//		System.out.println("�������ϴ��ļ���·��: ");
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
//				System.out.println("�ϴ�����, ��������!");
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
//					User user = e.nextElement();//����nextElement�������Ԫ��
//					name_list = name_list + "\n�û���: " + user.name + "\t���: " + user.role;
//		        }
//				text.setText(name_list);
//				break;
//			}
//			catch (Exception e) {
//				System.out.println("����ʧ��, ��ȴ�!");
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
				System.out.println("����ʧ��, ��ȴ�!");
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
		text.setText("��ӭ����! "+this.name);
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
//					Doc file = f.nextElement();//����nextElement�������Ԫ��
//					doc_list = doc_list + "\n�ļ���: " + file.doc_name + "\n�ļ�id: "+ file.num + "\n�ϴ���: " 
//					+ file.name + "\n�ϴ�ʱ��" + file.time + "\n�ļ�����" + file.desc;
//		        }
//				text.setText(doc_list);
//				break;
//			}catch(Exception e){
//				System.out.println("����ļ����ִ���, ��Ⱥ�!");
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
//					Doc file = f.nextElement();//����nextElement�������Ԫ��
//					doc_list = doc_list + "\n�ļ���: " + file.doc_name + "\n�ļ�id: "+ file.num + "\n�ϴ���: " 
//					+ file.name + "\n�ϴ�ʱ��" + file.time + "\n�ļ�����" + file.desc;
//		        }
				
				text.setText(name_list);
				break;

			}catch(Exception e){
				System.out.println("����ļ����ִ���, ��Ⱥ�!");
			}
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "ע��":f.dispose(); break;
		case "�˳�":System.exit(0);
		case "�鿴ȫ���û�":listUser(); break;
		case "�ı������Ϣ":changeSelfInfo(); break;
		case "�鿴ȫ���ļ�":showFileList(); break;
		case "�����ļ�":downloadFile(); break;
		case "�ϴ��ļ�":uploadFile(); break;
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
