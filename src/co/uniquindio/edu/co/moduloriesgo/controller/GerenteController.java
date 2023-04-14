package co.uniquindio.edu.co.moduloriesgo.controller;

import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.moduloriesgo.model.Categoria;
import co.uniquindio.edu.co.moduloriesgo.model.Modulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GerenteController implements Initializable{

	@FXML
	private ComboBox<Categoria> cbCategoria;

	@FXML
	private TableColumn<?, ?> columnDescripcion;

	@FXML
	private TextField txtNombreRiesgo;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private TableColumn<?, ?> columnEstado;

	@FXML
	private TableColumn<?, ?> columnNombre;

	@FXML
	private TableColumn<?, ?> columnCategoria;

	@FXML
	private ComboBox<String> cbModuloRiesgo;

	@FXML
	private TableColumn<?, ?> columnModulo;

	@FXML
	private Label lblFecha;

	@FXML
	private Label lblUserAdmin;

	@FXML
	private TableView<?> tableVendedores;

	@FXML
	private Label lblHora;

	@FXML
	private TextField txtDescripcion;

	@FXML
	void nuevoRiesgoEvent(ActionEvent event) {

	}

	@FXML
	void actualizarRiesgoEvent(ActionEvent event) {

	}

	@FXML
	void agregarRiesgoEvent(ActionEvent event) {

	}


	@FXML
	void eliminarVendedorAction(ActionEvent event) {

	}

	@FXML
	void cerrarSesionAction(ActionEvent event) {

	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		lblFecha.setText(lblFecha.getText() + LocalDate.now(Clock.systemDefaultZone()));
		lblHora.setText(lblHora.getText() + LocalTime.now());
		
		cbCategoria.getItems().addAll(Categoria.Ambiental, Categoria.Financiero, Categoria.Físico,
				Categoria.Laboral, Categoria.Químico, Categoria.Seguridad);
		cbModuloRiesgo.getItems().addAll(Modulo.colaboracion.getNombre(), Modulo.facturacionYContabilidad.getNombre(), Modulo.gestionRecursos.getNombre(), Modulo.gestionRiesgos.getNombre(), Modulo.planificacion.getNombre(), Modulo.seguimiento.getNombre(), Modulo.seguridad.getNombre());
	}
}