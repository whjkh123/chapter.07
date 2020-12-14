package echo;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws Exception {

		// 1. ServerSocket instance
		ServerSocket severSocket = new ServerSocket();

		// 2. Bind
		// Ip, portNum setting
		// 192.168.35.145, 10001
		severSocket.bind(new InetSocketAddress("192.168.35.145", 10001));

		System.out.println("[서버 시작]");
		System.out.println("=========================================");
		System.out.println("[연결 대기 중..]");

		while (true) {
			// 3. Accept
			Socket socket = severSocket.accept();
			System.out.println("[연결 완료]");

			// 출장 보내기
			Thread thr = new ServerThread(socket);
			thr.start();
		}
		/*
		System.out.println("=========================================");
		System.out.println("[서버 종료]");

		severSocket.close();
		*/
	}

}
