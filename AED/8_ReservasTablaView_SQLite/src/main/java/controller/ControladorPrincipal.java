package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import logicaNegocio.Estancia;
import logicaNegocio.EstanciasDB;
import model.DatosEstancias;

public class ControladorPrincipal implements Initializable {

	// model
	// -----
	private DatosEstancias datos;

	// view
	// ----
	@FXML
	private VBox view;
	@FXML
	private ComboBox<String> cbSelecHotel;
	@FXML
	private TextArea textoHabitaciones;
	@FXML
	private TableView<Estancia> tblReservas;
	@FXML
	private TableColumn<Estancia, String> colNombre;
	@FXML
	private TableColumn<Estancia, String> col_fIni, col_fFin;
	@FXML
	private Button btnDelete, btnUpdate, btnInsert;

	// logica de negocio
	// -----------------
	private EstanciasDB estanciasDB;

	public ControladorPrincipal() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventanaPrincipal.fxml"));
		loader.setController(this);
		loader.load();
	}

	public Parent getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Properties conexion = new Properties();
			conexion.load(getClass().getResourceAsStream("/conexion.prop"));

			estanciasDB = new EstanciasDB(conexion);
			datos = new DatosEstancias();

			// Establecer Bindins
			cbSelecHotel.itemsProperty().bind(datos.hotelesProperty());
			textoHabitaciones.textProperty().bind(datos.habitacionesProperty());

			datos.hotelSelecionadoProperty().bind(cbSelecHotel.getSelectionModel().selectedItemProperty());

			/* Ohú */
			btnDelete.disableProperty().bind(datos.estanciaActualProperty().lessThan(0));
			btnUpdate.disableProperty().bind(datos.estanciaActualProperty().lessThan(0));
			btnInsert.disableProperty().bind(datos.hotelSelecionadoProperty().isNull());

			// listeners

			// datos.hotelSelecionadoProperty().addListener((o, ov, nv) ->
			// onSelecHotelChanged(o, ov, nv));
			// ...resumido es:
			datos.hotelSelecionadoProperty().addListener(this::onSelecHotelChanged);

			tblReservas.setItems(datos.estanciasProperty());
			colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			col_fIni.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
			col_fFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

//			tblReservas.getSelectionModel().selectedItemProperty().addListener((o,ov,nv) -> {
//			Cambios en la estancia selecionada...
//		});

			tblReservas.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
				datos.estanciaActualProperty().set(tblReservas.getSelectionModel().getSelectedIndex());
			});

			// load data
			datos.hotelesProperty().addAll(estanciasDB.getHoteles());

			datos.estanciaActualProperty().set(-1);

		} catch (Exception e) {
			Logger.getGlobal().severe(e.getMessage());
		}
	}

	private void onSelecHotelChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		datos.estanciaActualProperty().set(-1);
		datos.estanciasProperty().setAll(estanciasDB.getReservasDeHotel(newValue));
		String strHabitaciones = "";
		List<String> habDeHotalActual = estanciasDB.getHabDeHotel(newValue);
		datos.listaHabitacionesProperty().setAll(habDeHotalActual);
		for (String numHab : habDeHotalActual) {
			strHabitaciones += numHab + "\n";
		}
		datos.habitacionesProperty().set(strHabitaciones);
	}

	@FXML
	private void onDelete(ActionEvent e) {
		String mensaje;
		mensaje = datos.estanciasProperty().get(datos.estanciaActualProperty().get()).toString();
		mensaje = "¿Acepta eliminar esta estancia?\n" + mensaje;
		Estancia paraEliminar;
		if (dialogoConfirmacion(mensaje) == ButtonType.OK) {
			paraEliminar = datos.estanciasProperty().get(datos.estanciaActualProperty().get());
			try {
				estanciasDB.delete(paraEliminar.getId());
				datos.estanciasProperty().remove(datos.estanciaActualProperty().get());
				tblReservas.getSelectionModel().clearSelection();
			} catch (SQLException e1) {
				dialogoError("El borrado ha fallado\n" + e1.getMessage());
			}

		}
	}

	@FXML
	private void onUpdate(ActionEvent e) {
		Estancia actual, modificada;
		actual = datos.estanciasProperty().get(datos.estanciaActualProperty().get());
		modificada = new Estancia(
				actual.getId(),
				actual.getNombre(),
				actual.getFechaInicio(),
				actual.getFechaFin(),
				actual.getNumHabitacion());
		try {
			ControladorActualizar cAct = new ControladorActualizar(view.getScene().getWindow(),modificada, datos.listaHabitacionesProperty());
			if (cAct.seConfirma()) {
				modificada = estanciasDB.update(cAct.getEstancia());
				datos.estanciasProperty().set(datos.estanciaActualProperty().get(), modificada);
			}
		} catch (SQLException e1) {
			dialogoError("La modificación ha fallado\n" + e1.getMessage());
		}

	}

	@FXML
	private void onInsert(ActionEvent e) {
		Estancia nueva;
		ControladorInsertar cIns = new ControladorInsertar(view.getScene().getWindow(), datos.listaHabitacionesProperty());
		if (cIns.seConfirma()) {
			try {
				nueva = estanciasDB.insert(cIns.getEstancia(), datos.hotelSelecionadoProperty().get());
				datos.estanciasProperty().add(nueva);
			} catch (SQLException e1) {
				dialogoError("La inserción ha fallado\n" + e1.getMessage());
			}
		}
	}

	private void dialogoError(String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
	private ButtonType dialogoConfirmacion(String mensaje) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setContentText(mensaje);
		return alerta.showAndWait().get();
	}

}
