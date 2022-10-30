package consulta_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws Exception {
		Properties conexion = new Properties();
		conexion.load(Main.class.getResourceAsStream("/resources/conexion.prop"));
		Class.forName(conexion.getProperty("driver"));

		Connection conn;
		Statement stmt;
		ResultSet rs;
		conn = DriverManager.getConnection(conexion.getProperty("url"), conexion.getProperty("user"),
				conexion.getProperty("password"));

		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT DISTINCT nombre FROM Estancias ORDER BY nombre");

		while (rs.next()) {
			System.out.println(rs.getString("nombre"));
		}
		rs.close();
		stmt.close();
		conn.close();
	}

}
