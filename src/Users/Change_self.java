package Users;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import client.Client;

public class Change_self extends JApplet implements ActionListener, WindowListener {
	JFrame f = new JFrame("���ĸ�����Ϣ");
	JLabel lab= new JLabel("�������µ��û���Ϣ");
	JLabel password_label = new JLabel("�µ��û�����: ");
	JPasswordField password_text = new JPasswordField();
//	JLabel password_label = new JLabel("����: ");
//	JPasswordField password_text = new JPasswordField();
//	JLabel role_label = new JLabel("�µ��û����: ");
//	JTextField role_text = new JTextField();
	JButton btn = new JButton("�ύ");
	String name;
	String role;
	public Change_self(String name, String role) {
		this.name = name;
		this.role = role;
		Container ct = f.getContentPane();
		ct.setLayout(null); // �ֹ�����
		lab.setLocation(50, 10);
		lab.setSize(150, 20);
//		name_label.setLocation(50, 50);
//		name_label.setSize(100, 20);		
//		name_text.setLocation(130, 50);
//		name_text.setSize(130, 20);		
		password_label.setLocation(50, 80);
		password_label.setSize(100, 20);		
		password_text.setLocation(130,80);
		password_text.setSize(130, 20);		
//		role_label.setLocation(50, 130);
//		role_label.setSize(100, 20);		
//		role_text.setLocation(130,130);
//		role_text.setSize(130, 20);
		btn.setLocation(110, 180);
		btn.setSize(70, 20);
		ct.add(lab);
//		ct.add(name_label);
//		ct.add(name_text);
		ct.add(password_label);
		ct.add(password_text);
//		ct.add(role_label);
//		ct.add(role_text);
		ct.add(btn);
		f.addWindowListener(this);
		btn.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setSize(300, 280); // ���ô��ڵĴ�С
		f.setVisible(true);
		f.setResizable(false);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		char[] password = password_text.getPassword();
//		String role = role_text.getText();
		while(true) {
			try{
				// DataProcessing.updateUser(name,  String.valueOf(password), role)
				Client temp1 = new Client();
				String[] aStrings = {"changeSelf", name,  String.valueOf(password), role};
				boolean res = (boolean) temp1.Start(aStrings);
				if (res) {
					int rs = JOptionPane.showConfirmDialog(null, "���ĳɹ�", "�ɹ�", JOptionPane.YES_OPTION);
					f.dispose();
					break;
				}
				else {
					int rs = JOptionPane.showConfirmDialog(null, "����ʧ��", "����", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}catch (Exception e) {
				System.out.println("�ı������Ϣʧ��, ��ȴ�!");
			}
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
