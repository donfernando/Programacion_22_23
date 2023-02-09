package emisor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class PrincipalEmisor {
	public static final int PUERTO = 50505;
	public static final int KBYTES = 1024;

	public static void main(String args[]) {
		if (args.length < 2) {
			System.out.println("Por favor, introduzca el formato correcto:"
					+ " 'java -jar emisor.jar <pathname_fichero> <IP_destino>'");
			System.exit(1);
		}
		File file = new File(args[0]);
		transmitir(file, args[1]);

	}

	public static void transmitir(File myFile, String servidor) {
		try (FileInputStream fis = new FileInputStream(myFile);
			 Socket socket = new Socket(servidor, PUERTO);
			 OutputStream os = socket.getOutputStream();
			 DataOutputStream dos = new DataOutputStream(os);) {

			byte[] myByteArray = new byte[10 * KBYTES];
			int nBytesLeidos;

			dos.writeUTF(myFile.getName());
			nBytesLeidos = fis.read(myByteArray);
			while (nBytesLeidos > 0) {
				dos.write(myByteArray, 0, nBytesLeidos);
				nBytesLeidos = fis.read(myByteArray);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
