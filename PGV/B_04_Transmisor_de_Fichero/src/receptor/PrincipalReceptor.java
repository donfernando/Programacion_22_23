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

public class PrincipalReceptor {
	public static final int PUERTO = 50505;
	public static final int KBYTES = 1024;
	public static final int MAX_KSIZE_FILE = 200;

	private static int totalBytesRecibidos = 0;
	public static synchronized int incrementa(int x) {
			totalBytesRecibidos += x;
			return totalBytesRecibidos;
	}

	public static void main(String args[]) {
		Executor ejecutador = Executors.newFixedThreadPool(5);
		try (ServerSocket sSocket = new ServerSocket(PUERTO);) {
			while (true) {
				Socket socket = sSocket.accept();
				ejecutador.execute(new Hilo(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Hilo extends Thread {
	private Socket miSocket;
	public Hilo(Socket s) {
		miSocket = s;
	}
	public void run() {
		File myFile;
		int leidos = 0;
		try (InputStream is = miSocket.getInputStream();
			 DataInputStream dis = new DataInputStream(is);) {
			byte[] mybytearray = new byte[10 * PrincipalReceptor.KBYTES];
			int nBytesLeidos;
			myFile = new File(dis.readUTF());

			FileOutputStream fos = new FileOutputStream(myFile);
			nBytesLeidos = dis.read(mybytearray);
			while (nBytesLeidos > 0) {
				leidos += nBytesLeidos;
				if(leidos> PrincipalReceptor.MAX_KSIZE_FILE*PrincipalReceptor.KBYTES)
					throw new RuntimeException(String.format("Fichero '%s' demasiado grande [%s]",myFile,miSocket.getInetAddress()));
				PrincipalReceptor.incrementa(nBytesLeidos);
				fos.write(mybytearray, 0, nBytesLeidos);
				nBytesLeidos = dis.read(mybytearray);
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
};
