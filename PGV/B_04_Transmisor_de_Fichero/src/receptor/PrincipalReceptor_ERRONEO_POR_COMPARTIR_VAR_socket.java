package receptor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PrincipalReceptor_ERRONEO_POR_COMPARTIR_VAR_socket {
	public static final int PUERTO = 50505;
	public static final int KBYTES = 1024;

	public static void main(String args[]) {
		Executor ejecutador = Executors.newFixedThreadPool(5);
		try (ServerSocket sSocket = new ServerSocket(PUERTO);) {
			while (true) {
				Socket socket = sSocket.accept();
				ejecutador.execute(
//				new Thread(
				() -> {
					File myFile;
					try (InputStream is = socket.getInputStream(); DataInputStream dis = new DataInputStream(is);) {
						byte[] mybytearray = new byte[10 * KBYTES];
						int nBytesLeidos;
						myFile = new File(dis.readUTF());

						FileOutputStream fos = new FileOutputStream(myFile);
						nBytesLeidos = dis.read(mybytearray);
						while (nBytesLeidos > 0) {
							fos.write(mybytearray, 0, nBytesLeidos);
							nBytesLeidos = dis.read(mybytearray);
						}
						fos.close();
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
//				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
