package conexionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	protected Connection conectar = null;
	private final String url = "jdbc:sqlite:/home/fernando/.local/share/DBeaverData/workspace6/.metadata/sample-database-sqlite-1/Chinook.db";
	private final String usuario = "";
	private final String password = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola");
		new Main().abrirConexion();

	}
	public void abrirConexion(){
	    try{
	        Class.forName("org.sqlite.JDBC"); 
	        conectar = DriverManager.getConnection(url, usuario, password);
	        
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conectar.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM Album LIMIT 0, 9");
	        while (rs.next()) 
	            System.out.printf("-> %s, %s\n",rs.getString(1),rs.getString(2));	        
	        
	        	        
	        
	        
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
