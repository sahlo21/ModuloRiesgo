package co.uniquindio.edu.co.moduloriesgo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PlanRiesgoController {
	private static Stage stg;

    @FXML
    private TextArea txtPlan;

    @FXML
    private DatePicker dateFin;

    @FXML
    private DatePicker dateInicio;

    @FXML
    void generarPlanEvent(ActionEvent event) {
    	if(!txtPlan.getText().isEmpty()&&dateFin.getValue()!=null&&dateInicio.getValue()!=null){
    		stg = (Stage) txtPlan.getScene().getWindow();
    		stg.close();
    	}

    }

	public TextArea getTxtPlan() {
		return txtPlan;
	}

	public void setTxtPlan(TextArea txtPlan) {
		this.txtPlan = txtPlan;
	}

	public DatePicker getDateFin() {
		return dateFin;
	}

	public void setDateFin(DatePicker dateFin) {
		this.dateFin = dateFin;
	}

	public DatePicker getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(DatePicker dateInicio) {
		this.dateInicio = dateInicio;
	}

}