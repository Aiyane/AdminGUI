package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCPЭ���Socketͨ��, ʵ���û���¼
 * ��������
 */

public class Server {
	public static void main(String[] args) {
		
		try {
			// 1. ����һ����������Socket, ��ServerSocket, ָ���󶨵Ķ˿�, �������˶˿�
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = null;
			// ��¼�ͻ��˵�����
			int count = 0;
			System.out.println("***��������������, �ȴ��ͻ��˵�����***");
			// 2. ѭ�������ȴ��ͻ��˵�����
			while(true) {
				// 3. ����accept()������ʼ����, �ȴ��ͻ��˵�����
				socket = serverSocket.accept();
				// 4. ����һ���µ��߳�
				ServerThread serverThread = new ServerThread(socket);
				// 5. �����߳�
				serverThread.start();
				
				count++;  // ͳ�ƿͻ��˵�����
				System.out.println("�ͻ�������Ϊ: " + count);
				InetAddress address = socket.getInetAddress();
				System.out.println("��ǰ�ͻ��˵�IP: " + address.getHostAddress());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
