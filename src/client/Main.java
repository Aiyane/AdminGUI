package client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Users.Add_user;

public class Main extends JApplet implements ActionListener, WindowListener{
	public static void main(String[] args) {
		new Main();
	}
	JFrame f = new JFrame("主页面");
	
	JLabel name_label = new JLabel("用户名: ");
	JTextField name_text = new JTextField();
	
	JLabel password_label = new JLabel("密码: ");
	JPasswordField password_text = new JPasswordField();
	
	JButton login_btn = new JButton("登录");
	JButton register_btn = new JButton("注册");
	
	public Main() {
		Container ct = f.getContentPane();
		ct.setLayout(null); // 手工布局
		
		name_label.setLocation(50, 50);
		name_label.setSize(100, 20);
		
		name_text.setLocation(130, 50);
		name_text.setSize(130, 20);
		
		password_label.setLocation(50, 90);
		password_label.setSize(100, 20);
		
		password_text.setLocation(130,90);
		password_text.setSize(130, 20);
		
		login_btn.setLocation(50, 150);
		login_btn.setSize(70, 20);
		
		register_btn.setLocation(180, 150);
		register_btn.setSize(70, 20);
		
		ct.add(name_label);
		ct.add(name_text);
		ct.add(password_label);
		ct.add(password_text);
		ct.add(login_btn);
		ct.add(register_btn);
		
		f.addWindowListener(this);
		login_btn.addActionListener(this);
		register_btn.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setSize(300, 280); // 设置窗口的大小
		f.setVisible(true);
		f.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login_btn){
			String name = name_text.getText();
			name = name.replaceAll(".*([';]+|(--)+).*", " ");
			char[] password = password_text.getPassword();
			try {
				
				Client temp1 = new Client();
				String[] aStrings = {"login", name, String.valueOf(password)};
				temp1.Start(aStrings);
				//User temp = DataProcessing.searchUser(name, String.valueOf(password));
				//temp.showMenu();
			}catch (Exception e1) {
				int rs = JOptionPane.showConfirmDialog(null, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if(e.getSource() == register_btn) {
			new Add_user();
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