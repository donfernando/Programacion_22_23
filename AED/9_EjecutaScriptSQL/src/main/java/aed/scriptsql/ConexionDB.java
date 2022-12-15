package aed.scriptsql;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexionDB {

    private static Connection conn;
    private static Properties prop = new Properties();
    
    public void conectarBD(File properties) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
        prop.load(new FileReader(properties,Charset.forName("UTF8")));

        Class.forName(ConexionDB.prop.getProperty("driver"));

        conn = DriverManager.getConnection(ConexionDB.prop.getProperty("url"), ConexionDB.prop.getProperty("user"),
                ConexionDB.prop.getProperty("password"));

    }

    public void desconetarBD() throws SQLException {
        conn.close();
    }

    public void ejecutar(String comando) throws SQLException {
		System.out.println(comando+";");
    	Statement stm = conn.createStatement(); 
        if(stm.execute(comando)) {
        	ResultSet rs = stm.getResultSet();
        	while(rs.next()) {
        		System.out.print("* ");
        		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					System.out.print(rs.getString(i)+"\t");
				}
        		System.out.println();
        	}
        }
//        else
//        	System.out.println("("+stm.getUpdateCount()+") filas afectadas.\n");
    }
}
