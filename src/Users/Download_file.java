package Users;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import client.Client;

public class Download_file extends JApplet implements ActionListener, WindowListener {
	JFrame f = new JFrame("文件下载");
	JLabel lab= new JLabel("请输入文件ID");
	JLabel file_label = new JLabel("ID: ");
	JTextArea file_text = new JTextArea();
	JLabel path_label = new JLabel("路径: ");
	JButton path_btn = new JButton("选择路径");
//	JLabel role_label = new JLabel("新的用户身份: ");
//	JTextField role_text = new JTextField();
	JButton btn = new JButton("提交");
	FileDialog fd1;
	String doc_dir,doc_name;
	public Download_file() {
		Container ct = f.getContentPane();
		ct.setLayout(null); // 手工布局
		lab.setLocation(50, 10);
		lab.setSize(150, 20);
//		name_label.setLocation(50, 50);
//		name_label.setSize(100, 20);		
//		name_text.setLocation(130, 50);
//		name_text.setSize(130, 20);		
		file_label.setLocation(50, 50);
		file_label.setSize(100, 20);		
		file_text.setLocation(130,50);
		file_text.setSize(130, 20);		
		path_label.setLocation(50, 130);
		path_label.setSize(100, 20);		
		path_btn.setLocation(130,130);
		path_btn.setSize(130, 20);
		btn.setLocation(110, 180);
		btn.setSize(70, 20);
		ct.add(lab);
//		ct.add(name_label);
//		ct.add(name_text);
		ct.add(file_label);
		ct.add(file_text);
		ct.add(path_label);
		ct.add(path_btn);
		ct.add(btn);
		fd1 = new FileDialog(f, "保存", FileDialog.SAVE);
		f.addWindowListener(this);
		path_btn.addActionListener(this);
		btn.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setSize(300, 280); // 设置窗口的大小
		f.setVisible(true);
		f.setResizable(false);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource() == path_btn) {
//			Frame fe = null;
			fd1.setVisible(true);//显示打开文件对话框
            doc_dir = fd1.getDirectory();//获取打开文件路径并保存到字符串中。
            doc_name = fd1.getFile();
		}
		else if (arg0.getSource() == btn) {
			String id = file_text.getText();
	//		String role = role_text.getText();
			doc_dir = doc_dir + "//"+ doc_name;
//			File file2 = new File(doc_dir);
			while(true) {
				try {
//					Doc temp = DataProcessing.searchDoc(Integer.valueOf(id));
//					String doc_name = temp.doc_name;
//					File file1 = new File("D://code//java//Pan//"+doc_name);
//					copyFile(file1, file2);
					Client temp1 = new Client();
					String[] aStrings = {"download", id, doc_dir};
					temp1.Start(aStrings);
					int rs = JOptionPane.showConfirmDialog(null, "下载成功", "成功", JOptionPane.YES_OPTION);
					f.dispose();
					break;
				} catch (Exception e) {
					System.out.println("出现错误, 正在重试!");
				}
			}
		}
	}
//	public void copyFile(File fromFile,File toFile) throws IOException{
//        FileInputStream ins = new FileInputStream(fromFile);
//        FileOutputStream out = new FileOutputStream(toFile);
//        byte[] b = new byte[1024];
//        int n=0;
//        while((n=ins.read(b))!=-1){
//            out.write(b, 0, n);
//        }
//        
//        ins.close();
//        out.close();
//    }
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
