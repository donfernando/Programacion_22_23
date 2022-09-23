package conexion.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	protected Connection conexion = null;
	private final String url = "jdbc:mysql://localhost/gestion_educativa";
	private final String usuario = "root";
	private final String password = "donfer";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola");
		new Main().abrirConexion();

	}
	public void abrirConexion(){
	    try{
//DEPRECATED	        Class.forName("com.mysql.jdbc.Driver"); 
	        Class.forName("com.mysql.cj.jdbc.Driver"); // De esta forma cargamos la clase Driver de MySQL.
	        conexion = DriverManager.getConnection(url, usuario, password);
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM alumnos");
	        while (rs.next()) 
	            System.out.printf("*-> %s, %s\n",rs.getString(1),rs.getString(2));	        
	        
	        
	        
	        
	    }catch(SQLException | ClassNotFoundException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }
	}
}
