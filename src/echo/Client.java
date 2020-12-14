package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		// 4. Socket instance
		Socket socket = new Socket();

		System.out.println("[연결 시작]");
		System.out.println("=========================================");
		System.out.println("[서버에 연결 요청 중..]");

		socket.connect(new InetSocketAddress("192.168.35.145", 10001));

		System.out.println("[연결 완료]");

		// 5-1.메시지 송신 Stream
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 5-4.메시지 수신 Stream
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		Scanner sc = new Scanner(System.in);

		while (true) {
			String str = sc.nextLine();

			if ("/q".equals(str)) {// str.equals("/q") > nullPoint 방지
				break;
			}
			// 5-1.메시지 송신
			bw.write(str);
			bw.newLine();
			bw.flush();

			// 5-4.메시지 수신
			String remsg = br.readLine();// Server에서 수신한 메시지
			System.out.println("[Server]: " + remsg);
		}

		sc.close();
		bw.close();
		br.close();

		System.out.println("=========================================");
		System.out.println("[연결 종료]");

		socket.close();

	}

}
