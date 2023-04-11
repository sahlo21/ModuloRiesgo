package co.uniquindio.edu.co.moduloriesgo;

import java.io.IOException;

import co.uniquindio.edu.co.moduloriesgo.controller.LoginController;
import co.uniquindio.edu.co.moduloriesgo.controller.ModelFactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Aplicacion extends Application {

	private Stage primaryStage;

	LoginController logIn;


	ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

	private int numInicios=0; 

	@Override
	public void start(Stage primaryStage) {

		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Fakebook marketplace");
			showLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * carga la vista del loggin
	 */
	public void showLogin() {
		

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Aplicacion.class.getResource("view/Login.fxml"));

			BorderPane rootLayout = (BorderPane) loader.load();
			logIn = loader.getController();
		
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Inicio de sesion");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/iconMarketplace.png")));

			/*
			 * "C://Users//kssm1//OneDrive//Documentos//GitHub//Marketplace//info//iconMarketplace.png
			 * ../../../../../../../../../info/iconMarketplace.png
			 */

			primaryStage.show();
			numInicios++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



}