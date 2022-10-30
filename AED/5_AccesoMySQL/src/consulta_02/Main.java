package consulta_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		Properties conexion = new Properties();
		conexion.load(Main.class.getResourceAsStream("/resources/conexion.prop"));
		Class.forName(conexion.getProperty("driver"));
		Scanner entrada = new Scanner(System.in);
		ResultSet rs;
		try (Connection conn = DriverManager.getConnection(conexion.getProperty("url"), conexion.getProperty("user"),
				conexion.getProperty("password"));
				Statement stmt = conn.createStatement();
				PreparedStatement stmtReservas = conn.prepareStatement("SELECT DISTINCT nombre FROM Estancias WHERE codHotel = ? ORDER BY nombre ") ) 
		{
			rs = stmt.executeQuery("SELECT DISTINCT codHotel FROM Habitaciones ORDER BY codHotel");
			while (rs.next()) {
				System.out.println(rs.getString("codHotel"));
			}
			stmtReservas.setString(1, entrada.next());
			rs = stmtReservas.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("nombre"));
			}
			rs.close();
			entrada.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
