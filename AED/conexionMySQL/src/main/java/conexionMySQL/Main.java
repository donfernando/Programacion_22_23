package conexionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	protected Connection conectar = null;
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
//	        Class.forName("com.mysql.jdbc.Driver"); // De esta forma cargamos la clase Driver de MySQL.
	        Class.forName("com.mysql.cj.jdbc.Driver"); 
	        conectar = DriverManager.getConnection(url, usuario, password);
	        
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conectar.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM alumnos");
	        while (rs.next()) 
	            System.out.printf("-> %s, %s\n",rs.getString(1),rs.getString(2));	        
	        
	        	        
	        
	        
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
