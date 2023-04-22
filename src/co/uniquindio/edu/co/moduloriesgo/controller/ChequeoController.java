package co.uniquindio.edu.co.moduloriesgo.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChequeoController {

    @FXML
    private TableColumn<?, ?> columnNo;

    @FXML
    private TableColumn<?, ?> columnComentario;

    @FXML
    private TableColumn<?, ?> columnItem;

    @FXML
    private TableColumn<?, ?> columnSi;

    @FXML
    private TableColumn<?, ?> columnNivelRiesgo;

    @FXML
    private TableView<?> tableMatriz;

    @FXML
    void agregarCampoEvent(ActionEvent event) {

    }
    @FXML
    void EnviarMatrizEvent(ActionEvent event) {

    }
    
}