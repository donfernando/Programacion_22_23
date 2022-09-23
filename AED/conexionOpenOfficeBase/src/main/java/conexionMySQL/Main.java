package conexionMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
	protected Connection conectar = null;
	
  
    private final String db_path = "/home/fernando/Documentos/0_DocumentosTrabajo/Programacion_22_23/resources/ficheroOfficeBase/";
    private final String db_name = "canciones.odb";
//    private final String db_path = "/home/fernando/Documentos/0_DocumentosTrabajo/Programacion_22_23/resources/cancionesDescomprimidas/";
//    private final String db_name = "canciones.data";
 	private final String url = "jdbc:hsqldb:file:" + db_path + db_name;
	private final String usuario = "sa";
	private final String password = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola");
		new Main().abrirConexion();

	}
	public void abrirConexion(){
	    try{
	    	
	        Class.forName("org.hsqldb.jdbcDriver"); 
	        conectar = DriverManager.getConnection(url, usuario, password);
	        
	        System.out.println("Conexión Exitosa");
	        
	        Statement s = conectar.createStatement();
	        ResultSet rs = s.executeQuery("SELECT * FROM \"grabaciones\"");
	        while (rs.next()) 
	            System.out.printf("-> %s, %s\n",rs.getString(1),rs.getString(2));	        
	        
	    }catch(SQLException ex){
	        System.out.println("Error al abrir Conexión: " + ex.getMessage());
	    }catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
