package consulta_01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main_v2 {

	public static void main(String[] args) {
		Properties conexion = new Properties();

		try {
			conexion.load(Main.class.getResourceAsStream("/resources/conexion.prop"));
			Class.forName(conexion.getProperty("driver"));

			ResultSet rs;
			try (Connection conn = DriverManager.getConnection(
					conexion.getProperty("url"),
					conexion.getProperty("user"),
					conexion.getProperty("password"));
				 Statement stmt = conn.createStatement();) {
				rs = stmt.executeQuery("SELECT DISTINCT nombre FROM Estancias ORDER BY nombre");

				while (rs.next()) {
					System.out.println(rs.getString("nombre"));
				}
//			rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
