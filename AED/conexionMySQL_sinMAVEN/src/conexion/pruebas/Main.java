package conexion.pruebas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Main {
	protected Connection conectar;
	private String url;
	private String usuario;
	private String password;
	
	public static void main(String[] args) {
		System.out.println("Hola");
		
		try {
			new Main().abrirConexion();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirConexion() throws IOException{
	    try{
	    	Properties conexion = new Properties();
	    	conexion.load(getClass().getResourceAsStream("/resources/conexionDB.properties"));
	    	url = conexion.getProperty("url");
	    	usuario = conexion.getProperty("usuario");
	    	password = conexion.getProperty("password");
	    	
//	        Class.forName("com.mysql.cj.jdbc.Driver"); 
	    	Class.forName(conexion.getProperty("driver"));
	    	
	        conectar = DriverManager.getConnection(url, usuario, password);
	        
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conectar.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM Habitaciones");
	        while (rs.next()) 
	            System.out.printf("-> %s, %s\n",rs.getString(1),rs.getString(2));	        
	        
	        	        
	        
	        
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
