package Users;


import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.Client;

public class Upload_file extends JApplet implements ActionListener, WindowListener  {
	JFrame f = new JFrame("上传文件");
	JLabel lab= new JLabel("请输入上传文件的信息");
//	JLabel id_label = new JLabel("请输入文件ID: ");
//	JTextField id_text = new JTextField();
	JLabel desc_label = new JLabel("请输入文件描述: ");
	JTextField desc_text = new JTextField();
	JLabel path_label = new JLabel("请选择上传文件: ");
	JButton path_btn = new JButton("选择路径");
	JButton btn = new JButton("提交");
	FileDialog fd1;
	String doc_dir,doc_name, name;
	public Upload_file(String name) {
		this.name = name;
		Container ct = f.getContentPane();
		ct.setLayout(null); // 手工布局
		lab.setLocation(30, 10);
		lab.setSize(150, 20);
//		id_label.setLocation(30, 50);
//		id_label.setSize(100, 20);	
//		
//		id_text.setLocation(150, 50);
//		id_text.setSize(130, 20);	
		
		desc_label.setLocation(30, 90);
		desc_label.setSize(100, 20);	
		
		desc_text.setLocation(150,90);
		desc_text.setSize(130, 20);
	
		path_label.setLocation(30, 130);
		path_label.setSize(100, 20);	
		
		path_btn.setLocation(150,130);
		path_btn.setSize(130, 20);
		
		btn.setLocation(110, 180);
		btn.setSize(70, 20);
		
		ct.add(lab);
//		ct.add(id_label);
//		ct.add(id_text);
		ct.add(desc_label);
		ct.add(desc_text);
		ct.add(path_label);
		ct.add(path_btn);
		ct.add(btn);
		fd1 = new FileDialog(f, "加载", FileDialog.LOAD);
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
			fd1.setVisible(true);//显示打开文件对话框
			doc_dir = fd1.getDirectory();//获取打开文件路径并保存到字符串中。
            doc_name = fd1.getFile();
            doc_dir = doc_dir + "//"+doc_name;
		}
		else if (arg0.getSource() == btn) {
//			String id = id_text.getText();
			String desc = desc_text.getText();
//			File file2 = new File(doc_dir);
//		    String[] fileName = doc_dir.split("/");
//		    String doc_name = fileName[fileName.length-1];
		    File file1 = new File("D://code//java//Pan//"+doc_name);
			
			try{
				// DataProcessing.insertDoc(name, timestamp, desc, doc_name)
				Client temp1 = new Client();
				String[] aStrings = {"upDoc", name, desc, doc_name, doc_dir};
				temp1.Start(aStrings);
				
//						copyFile(file2, file1);
				int rs = JOptionPane.showConfirmDialog(null, "上传成功", "成功", JOptionPane.YES_OPTION);
				f.dispose();
				
			}
			catch(Exception e){
				int rs = JOptionPane.showConfirmDialog(null, "上传出错", "错误", JOptionPane.ERROR_MESSAGE);
				System.out.println("上传出错!");
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
