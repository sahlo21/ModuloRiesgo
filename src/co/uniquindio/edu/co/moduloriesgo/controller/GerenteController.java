package co.uniquindio.edu.co.moduloriesgo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.moduloriesgo.apigmail.EmailSender;
import co.uniquindio.edu.co.moduloriesgo.model.Categoria;
import co.uniquindio.edu.co.moduloriesgo.model.Modulo;
import co.uniquindio.edu.co.moduloriesgo.model.Riesgo;
import co.uniquindio.edu.co.moduloriesgo.persistencia.ArchivoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GerenteController implements Initializable{
	private static Stage stg;
	ArrayList<Riesgo> listaRiesgos = new ArrayList<>();
	Riesgo riesgoSeleccionado;
	ObservableList<Riesgo> listaRiesgosData = FXCollections.observableArrayList();

	@FXML
	private ComboBox<Categoria> cbCategoria;

	@FXML
	private TableColumn<Riesgo, String> columnDescripcion;

	@FXML
	private TextField txtNombreRiesgo;

	@FXML
	private Button btnCerrarSesion;

	@FXML
	private TableColumn<Riesgo, String> columnEstado;

	@FXML
	private TableColumn<Riesgo, String> columnNombre;

	@FXML
	private TableColumn<Riesgo, String> columnCategoria;

	@FXML
	private ComboBox<Modulo> cbModuloRiesgo;

	@FXML
	private TableColumn<Riesgo, String> columnModulo;

	@FXML
	private Label lblFecha;

	@FXML
	private Label lblUserAdmin;

	@FXML
	private TableView<Riesgo> tableVendedores;

	@FXML
	private Label lblHora;

	@FXML
	private TextField txtDescripcion;

	@FXML
	void nuevoRiesgoEvent(ActionEvent event) {
		txtDescripcion.setText("");
		txtNombreRiesgo.setText("");
		cbCategoria.setValue(null);
		cbModuloRiesgo.setValue(null);
	}

	@FXML
	void actualizarRiesgoEvent(ActionEvent event) {
		for (int i = 0; i < listaRiesgos.size(); i++) {
			if(listaRiesgos.get(i).getIdentificador() == riesgoSeleccionado.getIdentificador()){
				listaRiesgos.remove(i);
				Riesgo nuevoriesgo = new Riesgo(txtNombreRiesgo.getText(), cbCategoria.getValue(), cbModuloRiesgo.getValue(), txtDescripcion.getText(), riesgoSeleccionado.getIdentificador(), LocalDate.now(Clock.systemDefaultZone()).toString());
				listaRiesgos.add(nuevoriesgo);
			}
		}
		actualizarDataRiesgos();
	}

	@FXML
	void agregarRiesgoEvent(ActionEvent event) {
		if(txtNombreRiesgo.getText().isEmpty() || txtDescripcion.getText().isEmpty()||cbCategoria.getValue() == null||cbModuloRiesgo.getValue()==null){
			System.out.println("Aqui deberia ir una noti de que hay error ya me dicen si lo hago con joption xd");
		}else{
			Riesgo nuevoriesgo = new Riesgo(txtNombreRiesgo.getText(), cbCategoria.getValue(), cbModuloRiesgo.getValue(), txtDescripcion.getText(), listaRiesgos.size()+1, LocalDate.now(Clock.systemDefaultZone()).toString());
			listaRiesgos.add(nuevoriesgo);
			try {
				ArchivoUtil.salvarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos", listaRiesgos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Hubo un error en el guardado del archivo local");
			}
			actualizarDataRiesgos();
		}

	}


	@FXML
	void eliminarVendedorAction(ActionEvent event) {
		for (int i = 0; i < listaRiesgos.size(); i++) {
			if(listaRiesgos.get(i).getIdentificador() == riesgoSeleccionado.getIdentificador() && riesgoSeleccionado != null){
				listaRiesgos.remove(i);
			}
		}
		actualizarDataRiesgos();
	}

	@FXML
	void cerrarSesionAction(ActionEvent event) {
		stg = (Stage) tableVendedores.getScene().getWindow();
		stg.close();
	}

	private void actualizarDataRiesgos(){
		tableVendedores.getItems().clear();
		for (int i = 0; i < listaRiesgos.size(); i++) {
			listaRiesgosData.add(listaRiesgos.get(i));
		}
		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		this.columnModulo.setCellValueFactory(new PropertyValueFactory<>("modulo"));
		this.columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableVendedores.setItems(listaRiesgosData);
	}

	private void enviarEmail(String correo, String asunto, String cuerpo){
		EmailSender sender = new EmailSender(correo, asunto, cuerpo);
		Thread hilo = new Thread(sender);
		hilo.start();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		EmailSender.enviarConGMail("hospondres23@gmail.com", "Prueba", "Si ves esto es que funciono");

		try {
			listaRiesgos = (ArrayList<Riesgo>) ArchivoUtil.cargarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos");
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo no existe");
		}
		lblFecha.setText(lblFecha.getText() + LocalDate.now(Clock.systemDefaultZone()));
		lblHora.setText(lblHora.getText() + LocalTime.now());

		actualizarDataRiesgos();

		try{
		tableVendedores.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
    		riesgoSeleccionado = newSelection;
    		if(riesgoSeleccionado != null){
    			txtDescripcion.setText(riesgoSeleccionado.getDescripcion());
        		txtNombreRiesgo.setText(riesgoSeleccionado.getNombre());
        		cbCategoria.setValue(riesgoSeleccionado.getCategoria());
        		cbModuloRiesgo.setValue(riesgoSeleccionado.getModulo());
    		}

    	});
		}catch(NullPointerException e){
		}

		cbCategoria.getItems().addAll(Categoria.Ambiental, Categoria.Financiero, Categoria.Fisico,
				Categoria.Laboral, Categoria.Quimico, Categoria.Seguridad);
		cbModuloRiesgo.getItems().addAll(Modulo.colaboracion, Modulo.facturacionYContabilidad, Modulo.gestionRecursos, Modulo.gestionRiesgos, Modulo.planificacion, Modulo.seguimiento, Modulo.seguridad);

	}
}