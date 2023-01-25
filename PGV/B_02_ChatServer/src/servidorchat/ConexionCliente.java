package servidorchat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Objects;


public class ConexionCliente extends Thread {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private Servidor servidor;

    public ConexionCliente(Servidor servidor, Socket socket) throws IOException {
        this.servidor = servidor;
        this.socket = socket;
        salida = new PrintWriter(socket.getOutputStream(), true, Charset.forName("UTF8"));
        entrada = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
    }

    @Override
    public void run() {

        salida.println("Bienvenido al Chat");
        salida.println("Puedes enviar mensajes de texto");

        String mensaje = null;
        try {
            while ((mensaje = entrada.readLine()) != null && !mensaje.equals(":bye")) {
                servidor.enviarATodos(mensaje);
            }
            servidor.cortarCliente(this);
            salida.println("** ¡¡ Hasta Pronto !! **");
            entrada.close();
            salida.close();
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void enviar(String mensaje) {
        salida.println(mensaje);
    }

	@Override
	public int hashCode() {
		return Objects.hash(socket);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConexionCliente other = (ConexionCliente) obj;
		return Objects.equals(socket.getLocalPort(), other.socket.getLocalPort());
	}
    
}
