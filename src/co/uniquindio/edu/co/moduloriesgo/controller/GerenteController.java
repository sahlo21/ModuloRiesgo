package co.uniquindio.edu.co.moduloriesgo.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.moduloriesgo.apigmail.EmailSender;
import co.uniquindio.edu.co.moduloriesgo.model.Categoria;
import co.uniquindio.edu.co.moduloriesgo.model.Empleado;
import co.uniquindio.edu.co.moduloriesgo.model.Modulo;
import co.uniquindio.edu.co.moduloriesgo.model.PlanRiesgo;
import co.uniquindio.edu.co.moduloriesgo.model.Riesgo;
import co.uniquindio.edu.co.moduloriesgo.persistencia.ArchivoUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GerenteController implements Initializable{
	private static Stage stg;
	ArrayList<Riesgo> listaRiesgos = new ArrayList<>();
	ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	Empleado empleadoSeleccionado;
	Riesgo riesgoSeleccionado;
	Riesgo riesgoNotificacionSeleccionado;
	ObservableList<Riesgo> listaRiesgosData = FXCollections.observableArrayList();
	ObservableList<Empleado> listaEmpleadosData = FXCollections.observableArrayList();
	FXMLLoader fxmlLoader;


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
    private TableColumn<Riesgo, String> columnEstadoNotificacion;



    @FXML
    private TableColumn<Riesgo, String> columnNombreNotificacion;


    @FXML
    private TableColumn<Riesgo, String> columnModuloNotificacion;

    @FXML
    private TableColumn<Empleado, String> columnCedula;

    @FXML
    private TableView<Riesgo> tableRIesgosNotificacion;

    @FXML
    private TableColumn<Empleado, String> columnEstadoEmpleado;



    @FXML
    private TableColumn<Empleado, String> columnEps;


    @FXML
    private TableColumn<Empleado, String> columnNombreEmpleado;

    @FXML
    private TableColumn<Empleado, String> columnCargo;



    @FXML
    private TableColumn<Empleado, String> columnArl;

    @FXML
    private TableView<Empleado> tableEmpleados;




    @FXML
    private TableColumn<Riesgo, String> columnCategoriaNotificacion;


    @FXML
    void notificarRiesgoEvent(ActionEvent event) {
    	if(riesgoNotificacionSeleccionado!=null && riesgoNotificacionSeleccionado.getPlanRiesgo()!=null){
    		enviarEmail("andresf.garciat@uqvirtual.edu.co", "Notificacion de riesgos", LocalDate.now(Clock.systemDefaultZone())+"\nGerente.\nPara: "+riesgoNotificacionSeleccionado.getModulo()
    		+"\nRiesgo: "+riesgoNotificacionSeleccionado.getNombre()+" - Numero: "+riesgoNotificacionSeleccionado.getIdentificador()+"\nCategoria: "+riesgoNotificacionSeleccionado.getCategoria()+
    		"\n - Fecha: "+riesgoNotificacionSeleccionado.getFechaCreacion()+"\nDescripcion: "+riesgoNotificacionSeleccionado.getDescripcion()+
    		"\n\nPLAN DE RIESGO\nFecha inicio: "+riesgoNotificacionSeleccionado.getPlanRiesgo().getFechaInicio()+" - Fecha final: "+riesgoNotificacionSeleccionado.getPlanRiesgo().getFechaFinal()+
    		"\n"+riesgoNotificacionSeleccionado.getPlanRiesgo().getSolucion());

    		for (int i = 0; i < listaRiesgos.size(); i++) {
    			if(riesgoNotificacionSeleccionado.getIdentificador()==listaRiesgos.get(i).getIdentificador()){
    				listaRiesgos.get(i).setEstado("Completo");
    			}
    		}

    		try {
				ArchivoUtil.salvarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos", listaRiesgos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Hubo un error en el guardado del archivo local");
			}
    		actualizarDataNotificaciones();
    		actualizarDataRiesgos();
    	}

    }



    @FXML
    void realizarChequeoEvent(ActionEvent event) throws IOException {
    	cambiarVentana("/co/uniquindio/edu/co/moduloriesgo/view/ChequeoView.fxml", "Chequeo");


    }

    @FXML
    void generarPlanEvent(ActionEvent event) throws IOException {
    	cambiarVentanaYEsperar("/co/uniquindio/edu/co/moduloriesgo/view/PlanRiesgo.fxml", "Plan de riesgo");
    	PlanRiesgoController controller = fxmlLoader.getController();
    	if(!controller.getTxtPlan().getText().isEmpty()&&controller.getDateFin().getValue()!=null&&controller.getDateInicio().getValue()!=null){
    		for (int i = 0; i < listaRiesgos.size(); i++) {
    			if(riesgoNotificacionSeleccionado.getIdentificador()==listaRiesgos.get(i).getIdentificador()){
    				PlanRiesgo plan = new PlanRiesgo(controller.getTxtPlan().getText(), controller.getDateInicio().getValue(), controller.getDateFin().getValue());
    				listaRiesgos.get(i).setPlanRiesgo(plan);
    			}
    		}
    		for (int i = 0; i < listaRiesgos.size(); i++) {
    			if(riesgoNotificacionSeleccionado.getIdentificador()==listaRiesgos.get(i).getIdentificador()){
    				listaRiesgos.get(i).setEstado("Revisado");
    			}
    		}

    		try {
				ArchivoUtil.salvarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos", listaRiesgos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Hubo un error en el guardado del archivo local");
			}
    		actualizarDataNotificaciones();
    		actualizarDataRiesgos();

    	}
    }



    @FXML
    void revisarDocumentosEvent(ActionEvent event) throws IOException {

    	cambiarVentana("/co/uniquindio/edu/co/moduloriesgo/view/DocumentosView.fxml","Documentos");
    	DocumentoController controller = fxmlLoader.getController();
    	controller.inicializarEmpleado(empleadoSeleccionado);

    }

    @FXML
    void habilitarEmpleadoEvent(ActionEvent event){
    	for (int i = 0; i < listaEmpleados.size(); i++) {
			if(listaEmpleados.get(i).getCedula()==empleadoSeleccionado.getCedula()){
				listaEmpleados.get(i).setEstado("Habilitado");
			}
		}
    	actualizarEmpleados();
    }

    @FXML
    void deshabilitarEmpleadoEvent(ActionEvent event) {
    	for (int i = 0; i < listaEmpleados.size(); i++) {
			if(listaEmpleados.get(i).getCedula()==empleadoSeleccionado.getCedula()){
				listaEmpleados.get(i).setEstado("No habilitado");
			}
		}
    	actualizarEmpleados();

    }


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
		try {
			ArchivoUtil.salvarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos", listaRiesgos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Hubo un error en el guardado del archivo local");
		}
		actualizarDataRiesgos();
		actualizarDataNotificaciones();

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
			actualizarDataNotificaciones();
		}

	}


	@FXML
	void eliminarVendedorAction(ActionEvent event) {
		if(riesgoSeleccionado!=null){
		for (int i = 0; i < listaRiesgos.size(); i++) {
			if(listaRiesgos.get(i).getIdentificador() == riesgoSeleccionado.getIdentificador() && riesgoSeleccionado != null){
				listaRiesgos.remove(i);
			}
		}
		try {
			ArchivoUtil.salvarRecursoSerializado("src/co/uniquindio/edu/co/moduloriesgo/localsources/listaRiesgos", listaRiesgos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Hubo un error en el guardado del archivo local");
		}
		actualizarDataRiesgos();
		actualizarDataNotificaciones();
		}
	}

	@FXML
	void cerrarSesionAction(ActionEvent event) {
		stg = (Stage) tableVendedores.getScene().getWindow();
		stg.close();
	}

	private void actualizarDataRiesgos(){
		listaRiesgosData = FXCollections.observableArrayList();
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


	private void actualizarDataNotificaciones() {
		listaRiesgosData = FXCollections.observableArrayList();
		tableRIesgosNotificacion.getItems().clear();
		for (int i = 0; i < listaRiesgos.size(); i++) {
			listaRiesgosData.add(listaRiesgos.get(i));
		}
		this.columnEstadoNotificacion.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnNombreNotificacion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCategoriaNotificacion.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		this.columnModuloNotificacion.setCellValueFactory(new PropertyValueFactory<>("modulo"));

		tableRIesgosNotificacion.setItems(listaRiesgosData);

	}



	private void enviarEmail(String correo, String asunto, String cuerpo){
		EmailSender sender = new EmailSender(correo, asunto, cuerpo);
		Thread hilo = new Thread(sender);
		hilo.start();
	}

	private void actualizarEmpleados(){
		tableEmpleados.getItems().clear();
		for (int i = 0; i < listaEmpleados.size(); i++) {
			listaEmpleadosData.add(listaEmpleados.get(i));
		}
		this.columnEstadoEmpleado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		this.columnNombreEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		this.columnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		this.columnArl.setCellValueFactory(new PropertyValueFactory<>("arl"));
		this.columnEps.setCellValueFactory(new PropertyValueFactory<>("eps"));
		tableEmpleados.setItems(listaEmpleadosData);
	}

	private void cambiarVentana(String fxml, String titulo) throws IOException{
		fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
    	Parent root1 = (Parent) fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));
    	stage.setTitle(titulo);
    	stage.getIcons().add(new Image(getClass().getResourceAsStream("/co/uniquindio/edu/co/moduloriesgo/resources/1681237779354.png")));
		stage.show();

	}
	private void cambiarVentanaYEsperar(String fxml, String titulo) throws IOException{
		fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
    	Parent root1 = (Parent) fxmlLoader.load();
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root1));
    	stage.setTitle(titulo);
    	stage.getIcons().add(new Image(getClass().getResourceAsStream("/co/uniquindio/edu/co/moduloriesgo/resources/1681237779354.png")));
		stage.showAndWait();

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
		actualizarDataNotificaciones();
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

		Empleado empleado1 = new Empleado("Sin validar", "Pepe Martinez", "10345", "Programador", "SURA", "SURA");
		empleado1.setArlpath("C:/Users/"+System.getProperty("user.name")+"/Documents/arlsuraempleado.pdf");
		empleado1.setEpspath("C:/Users/"+System.getProperty("user.name")+"/Documents/documentoepssura.pdf");
		Empleado empleado2 = new Empleado("Sin validar", "Juan Valdez", "10311", "Programador", "SURA", "N/A");
		empleado2.setArlpath("C:/Users/"+System.getProperty("user.name")+"/Documents/arlsuraempleado.pdf");
		empleado2.setEpspath("");
		Empleado empleado3 = new Empleado("Sin validar", "Juan Sech", "10011", "Programador", "N/A", "N/A");
		empleado3.setArlpath("");
		empleado3.setEpspath("");

		listaEmpleados.add(empleado1);
		listaEmpleados.add(empleado2);
		listaEmpleados.add(empleado3);
		actualizarEmpleados();
		try{
			tableEmpleados.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
				empleadoSeleccionado = newSelection;
				if(empleadoSeleccionado!=null){
		    		System.out.println(empleadoSeleccionado.getNombre());
				}

	    	});
			}catch(NullPointerException e){
			}

		tableRIesgosNotificacion.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
			riesgoNotificacionSeleccionado = newSelection;
    	});




	}
}