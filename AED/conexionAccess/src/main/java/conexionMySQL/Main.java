package conexionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	protected Connection conectar = null;
	private final String url = "jdbc:ucanaccess:///home/fernando/Documentos/0_DocumentosTrabajo/Programacion_22_23/resources/RecetasCocina/Recetario.mdb";
	private final String usuario = "";
	private final String password = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola");
		new Main().abrirConexion();

	}
	public void abrirConexion(){
	    try{
	        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
	        conectar = DriverManager.getConnection(url, usuario, password);
	        
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conectar.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM tblReceta LIMIT 0,9");
	        while (rs.next()) 
	            System.out.println(rs.getString(2));	        
	        
	        
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
