package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 5-2.메시지 수신 Stream
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 5-3.메시지 송신 Stream
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {
				// 5-2.메시지 수신
				String msg = br.readLine();// Client에서 수신한 메시지

				if (msg == null) {
					break;
				}
				System.out.println("[Client]: " + msg);

				// 5-3.메시지 송신
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
