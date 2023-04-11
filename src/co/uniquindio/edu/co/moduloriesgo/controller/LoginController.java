package co.uniquindio.edu.co.moduloriesgo.controller;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.edu.co.moduloriesgo.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{




	@FXML
	private TextField txtUsuarioIngreso;

	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonCargar;



	@FXML
	private TextField txtContrasenaIngreso;

	@FXML
	private CheckBox cbCondiciones;

	boolean flagLogin=false;
	char tipoLogin;
	Aplicacion aplicacion;
	private ModelFactoryController modelFactoryController; 
	
	Socket miSocket;


	ObjectOutputStream flujoSalidaObject;
	ObjectInputStream flujoEntradaObject;

	DataOutputStream flujoSalidaData;
	DataInputStream flujoEntradaData;
	int contador = 0;
	boolean estadoCargareServer=false;
	private int flagCargar;





	public LoginController() {

	}





	@FXML
	void cbCondiciones(ActionEvent event) {

	}

	@FXML
	private Label wrongLogIn;





	public void iniciarSesionAction(ActionEvent event) throws IOException{

		try {
			inicioSesion();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			mostrarMensajeError(e.getMessage());
		}

	}
	

	void inicioSesion() throws IOException {
		

	}


//	private boolean mostrarMensajeInformacion(String mensaje) {
//
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setHeaderText(null);
//		alert.setTitle("Informacion");
//		alert.setContentText(mensaje);
//		Optional<ButtonType> action = alert.showAndWait();
//
//		if (action.get() == ButtonType.OK) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	private void mostrarMensajeInformacion(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informacion");
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	private void mostrarMensajeError(String mensaje) {

		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(mensaje);
		alert.showAndWait();
		
	}
	private void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		modelFactoryController = ModelFactoryController.getInstance();

	}
	





}


