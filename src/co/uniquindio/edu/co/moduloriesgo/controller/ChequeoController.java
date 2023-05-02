package co.uniquindio.edu.co.moduloriesgo.controller;


import java.util.ArrayList;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import co.uniquindio.edu.co.moduloriesgo.model.ListaChequeo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ChequeoController {
	static ObservableList<ListaChequeo> data_table;
	int maxColumns = 0;
	ArrayList<ListaChequeo> listaChequeo = new ArrayList<>();
    @FXML
    private TableColumn<ListaChequeo, String> columnNo;

    @FXML
    private TableColumn<ListaChequeo, String> columnComentario;

    @FXML
    private TableColumn<ListaChequeo, String> columnItem;

    @FXML
    private TableColumn<ListaChequeo, String> columnSi;

    @FXML
    private TableColumn<ListaChequeo, String> columnNivelRiesgo;

    @FXML
    private TableView<ListaChequeo> tableMatriz;

    @FXML
    void agregarCampoEvent(ActionEvent event) {
    	listaChequeo = new ArrayList<>();
    	if(maxColumns!=0){
    		for (int i = 0; i < maxColumns; i++) {
				listaChequeo.add(tableMatriz.getItems().get(i));
			}
    		listaChequeo.add(new ListaChequeo("", "", "", "", ""));
    		tableMatriz.getItems().clear();
    		for (int i = 0; i < listaChequeo.size(); i++) {
				data_table.add(listaChequeo.get(i));
				System.out.println(listaChequeo.get(i).getCodigoRiesgo());
			}

    	}else{
    		data_table=FXCollections.observableArrayList();

        	data_table.add(new ListaChequeo("", "", "", "", ""));
    	}
    	actualizarListaChequeo();
    	editableCols();
    	maxColumns++;
    }
    @FXML
    void EnviarMatrizEvent(ActionEvent event) {
    	try {
			writePdf();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void actualizarListaChequeo(){

		this.columnItem.setCellValueFactory(new PropertyValueFactory<>("codigoRiesgo"));
		this.columnSi.setCellValueFactory(new PropertyValueFactory<>("si"));
		this.columnNo.setCellValueFactory(new PropertyValueFactory<>("no"));
		this.columnNivelRiesgo.setCellValueFactory(new PropertyValueFactory<>("nivelRiesgo"));
		this.columnComentario.setCellValueFactory(new PropertyValueFactory<>("comentario"));
		tableMatriz.setItems(data_table);

	}

    public void writePdf() throws Exception {
    	listaChequeo = new ArrayList<>();
    	if(maxColumns!=0){
    		for (int i = 0; i < maxColumns; i++) {
				listaChequeo.add(tableMatriz.getItems().get(i));
			}
    	}
    	String filePath = "C:/Users/"+System.getProperty("user.name")+"/Downloads/matriz.pdf";
        File file = new File(filePath);
        int numArchivo = 0;

        if (isFileExists(file)) {
        	System.out.println("El archivo ya existe");
            numArchivo++;
            filePath = "C:/Users/"+System.getProperty("user.name")+"/Downloads/matriz"+numArchivo+".pdf";
            file = new File(filePath);
            while(isFileExists(file)){
            	numArchivo++;
            	filePath = "C:/Users/"+System.getProperty("user.name")+"/Downloads/matriz"+numArchivo+".pdf";
            	file = new File(filePath);
            }

        	PDDocument pdfdoc= new PDDocument();
        	pdfdoc.addPage(new PDPage());
        	System.out.println("PDF creado");
        	PDPage page = pdfdoc.getPage(0);
        	PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page);
        	contentStream.beginText();
        	contentStream.setLeading(30f);
        	 contentStream.setFont(PDType1Font.TIMES_ROMAN, 26);
        	contentStream.newLineAtOffset(25, 700);
        	for (int i = 0; i < listaChequeo.size(); i++) {
        		String say = "Item: "+ listaChequeo.get(i).getCodigoRiesgo()+" Si: ["+listaChequeo.get(i).getSi()+"]"
        				+ " No: ["+listaChequeo.get(i).getNo()+"]"+" Nivel de riesgo: "+ listaChequeo.get(i).getNivelRiesgo();
        		System.out.println(say);
        		contentStream.showText(say);
        		contentStream.newLine();
        		contentStream.showText(" Comentario: "+listaChequeo.get(i).getComentario());
        		contentStream.newLine();
        		contentStream.newLine();
			}
        	contentStream.endText();
        	contentStream.close();
        	pdfdoc.save("C:/Users/"+System.getProperty("user.name")+"/Downloads/matriz"+numArchivo+".pdf");
        	pdfdoc.close();

        	System.out.println("PDF creado");

        	pdfdoc.close();


        }
        else {
        	System.out.println("El archivo no existe");
        	PDDocument pdfdoc= new PDDocument();
        	pdfdoc.addPage(new PDPage());
        	System.out.println("PDF creado");
        	PDPage page = pdfdoc.getPage(0);
        	PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page);
        	contentStream.beginText();
        	contentStream.newLineAtOffset(25, 700);
        	contentStream.setLeading(30f);
        	  contentStream.setFont(PDType1Font.TIMES_ROMAN, 16 );
        	for (int i = 0; i < listaChequeo.size(); i++) {
        		String say = "Item: "+ listaChequeo.get(i).getCodigoRiesgo()+" Si: ["+listaChequeo.get(i).getSi()+"]"
        				+ " No: ["+listaChequeo.get(i).getNo()+"]"+" Nivel de riesgo: "+ listaChequeo.get(i).getNivelRiesgo();
        		System.out.println(say);
        		contentStream.showText(say);
        		contentStream.newLine();
        		contentStream.showText(" Comentario: "+listaChequeo.get(i).getComentario());
        		contentStream.newLine();
        		contentStream.newLine();
			}
        	contentStream.endText();
        	contentStream.close();
        	pdfdoc.save("C:/Users/"+System.getProperty("user.name")+"/Downloads/matriz.pdf");
        	pdfdoc.close();

        }
    }

    	private boolean isFileExists(File file) {
    		return file.exists() && !file.isDirectory();
	}

    private void editableCols(){
    	columnItem.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnItem.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setCodigoRiesgo(e.getNewValue()));

    	columnSi.setCellFactory(TextFieldTableCell.forTableColumn());

    	columnNo.setCellFactory(TextFieldTableCell.forTableColumn());

    	columnNo.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setNo("X"));

    	columnSi.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setSi("X"));

    	columnNivelRiesgo.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnNivelRiesgo.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setNivelRiesgo(e.getNewValue()));

    	columnComentario.setCellFactory(TextFieldTableCell.forTableColumn());
    	columnComentario.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setComentario(e.getNewValue()));

    	tableMatriz.setEditable(true);
    }

}