package logicaNegocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstanciasDB {

	private final Connection conn;
	private Statement stm;
	private PreparedStatement stmReservas, stmHabitaciones, stmDelete, stmInsert, stmUpdate;

	public EstanciasDB() throws Exception {
		Properties conexion = new Properties();
		conexion.load(getClass().getResourceAsStream("/conexion.prop"));
		Class.forName(conexion.getProperty("driver"));
		conn = DriverManager.getConnection(conexion.getProperty("url"), conexion.getProperty("user"),
				conexion.getProperty("password"));
		stmHabitaciones = conn.prepareStatement(
				"SELECT DISTINCT numHabitacion FROM Habitaciones WHERE codHotel = ? ORDER BY numHabitacion ");
		stmReservas = conn.prepareStatement(
				"SELECT DISTINCT id, nombre, fechaInicio, fechaFin, numHabitacion FROM Estancias WHERE codHotel = ? ORDER BY nombre ");
		stmDelete = conn.prepareStatement("DELETE FROM Estancias WHERE id = ?");
		stmInsert = conn.prepareStatement(
				"INSERT INTO Estancias (nombre, fechaInicio, fechaFin, numHabitacion, codHotel) VALUES(?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		stmUpdate = conn.prepareStatement(
				"UPDATE Estancias SET nombre=?, fechaInicio=?, fechaFin=?, numHabitacion=? WHERE id=?");
	}

	public List<String> getHoteles() {
		ResultSet rsHoteles;
		ObservableList<String> items = FXCollections.observableArrayList();

		try {
			stm = conn.createStatement();
			rsHoteles = stm.executeQuery("SELECT DISTINCT codHotel FROM Habitaciones ORDER BY codHotel");
			while (rsHoteles.next()) {
				items.add(rsHoteles.getString("codHotel"));
			}

		} catch (SQLException e) {
			Logger.getGlobal().severe(e.getMessage());
		}
		return items;
	}
	public List<String> getHabDeHotel(String hotel) {
		ResultSet rsHabitaciones;
		ObservableList<String> habitaciones = FXCollections.observableArrayList();

		try {
			stmHabitaciones.setString(1, hotel);
			rsHabitaciones = stmHabitaciones.executeQuery();
			while (rsHabitaciones.next()) {
				habitaciones.add(rsHabitaciones.getString("numHabitacion"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return habitaciones;
	}
	public List<Estancia> getReservasDeHotel(String hotel) {
		ResultSet rsEstancias;
//		List<Estancia> estancias = new ArrayList<>();
		ObservableList<Estancia> estancias = FXCollections.observableArrayList();

		try {
			stmReservas.setString(1, hotel);
			rsEstancias = stmReservas.executeQuery();
			while (rsEstancias.next()) {
				estancias.add(new Estancia(rsEstancias.getInt("id"), rsEstancias.getString("nombre"),
						rsEstancias.getString("fechaInicio"), rsEstancias.getString("fechaFin"),
						rsEstancias.getString("numHabitacion")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estancias;
	}

	public void delete(int id) throws SQLException {
		stmDelete.setInt(1, id);
		stmDelete.execute();
	}

	public Estancia insert(Estancia est, String codHotel) throws SQLException {
		ResultSet rs;
		Integer id=-5;
		stmInsert.setString(1, est.getNombre());
		stmInsert.setString(2, est.getFechaInicio());
		stmInsert.setString(3, est.getFechaFin());
		stmInsert.setString(4, est.getNumHabitacion());
		stmInsert.setString(5, codHotel);
		stmInsert.execute();
		rs = stmInsert.getGeneratedKeys();
		if (rs != null && rs.next()) {
			id = rs.getInt(1);
			est.setId(id);
			return est;
		}
		return null;
	}
	public Estancia update(Estancia est) throws SQLException {
		stmUpdate.setString(1, est.getNombre());
		stmUpdate.setString(2, est.getFechaInicio());
		stmUpdate.setString(3, est.getFechaFin());
		stmUpdate.setString(4, est.getNumHabitacion());
		stmUpdate.setInt(5, est.getId());
		stmUpdate.execute();
		return est;
	}
}