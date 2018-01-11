package Users;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import client.Client;

public class Administrator extends User implements ActionListener, WindowListener{
	JFrame f = new JFrame("Administratorҳ��");
	JTextArea text = new JTextArea();
	JMenuBar mBar = new JMenuBar();
	JMenu menu1 = new JMenu("�ļ�");
	JMenu menu2 = new JMenu("����");
	JMenuItem mi11 = new JMenuItem("�鿴ȫ���ļ�");
	JMenuItem mi12 = new JMenuItem("�����ļ�");
	JMenuItem mi21 = new JMenuItem("�鿴ȫ���û�");
	JMenuItem mi22 = new JMenuItem("�ı������Ϣ");
	JMenuItem mi23 = new JMenuItem("�ı��û���Ϣ");
	JMenuItem mi24 = new JMenuItem("�����û�");
	JMenuItem mi25 = new JMenuItem("ɾ���û�");
	JMenuItem mi26 = new JMenuItem("ע��");
	JMenuItem mi27 = new JMenuItem("�˳�");
	
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
		text.setText("��ӭ����! "+this.name);
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
				System.out.println("����ʧ��, ��ȴ�!");
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
		case "�ı��û���Ϣ":changeUserInfo(); break;
		case "�����û�":addUser(); break;
		case "ɾ���û�":delUser(); break;
		case "�鿴ȫ���ļ�":showFileList(); break;
		case "�����ļ�":downloadFile(); break;
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
