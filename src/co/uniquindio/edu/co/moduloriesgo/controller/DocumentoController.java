package co.uniquindio.edu.co.moduloriesgo.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import co.uniquindio.edu.co.moduloriesgo.model.Empleado;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DocumentoController {


	ObservableList<String> texto;
	Empleado empleado;

		@FXML
	    private TableView<String> tableDocuments;

	    @FXML
	    private TableColumn<String, String> columnNombreDocumento;

	    @FXML
	    private ImageView imgViewDocumento = new ImageView();


	    public void pdfAImagen(String sourceDir){
	    	try {

	            String destinationDir = "C:/Users/"+System.getProperty("user.name")+"/Documents/Conversion/";

	            File sourceFile = new File(sourceDir);
	            File destinationFile = new File(destinationDir);
	            if (!destinationFile.exists()) {
	                destinationFile.mkdir();
	                System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
	            }
	            if (sourceFile.exists()) {
	                System.out.println("Images copied to Folder Location: "+ destinationFile.getAbsolutePath());
	                PDDocument document = PDDocument.load(sourceFile);
	                PDFRenderer pdfRenderer = new PDFRenderer(document);

	                int numberOfPages = document.getNumberOfPages();
	                String fileExtension= "png";
	                /*
	                 * 600 dpi give good image clarity but size of each image is 2x times of 300 dpi.
	                 * Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB
	                 *      2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB
	                 */
	                int dpi = 300;// use less dpi for to save more space in harddisk. For professional usage you can use more than 300dpi

	                for (int i = 0; i < numberOfPages; ++i) {
	                    File outPutFile = new File(destinationDir + empleado.getCedula()+"."+ fileExtension);
	                    BufferedImage bImage = pdfRenderer.renderImageWithDPI(i, dpi, ImageType.RGB);
	                    ImageIO.write(bImage, fileExtension, outPutFile);
	                }

	                document.close();
	                System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
	            } else {
	                System.err.println(sourceFile.getName() +" File not exists");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


	   public void inicializarEmpleado(Empleado empleadoSeleccionado){
		   empleado = empleadoSeleccionado;
		   columnNombreDocumento.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
	        ObservableList<String> items = FXCollections.observableArrayList();
		   if(!empleado.getArlpath().equalsIgnoreCase("")){
			   System.out.println(empleado.getArlpath());
			   items.add("ARL");

	    	}
	    	if(!empleado.getEpspath().equalsIgnoreCase("")){
	    		System.out.println(empleado.getEpspath());
		        items.add("EPS");
	    	}
	    	 tableDocuments.setItems(items);

	    	try{
				tableDocuments.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) ->{
					if(newSelection=="EPS"){
						try {
							pdfAImagen(empleado.getEpspath());
							InputStream stream = new FileInputStream("C:/Users/"+System.getProperty("user.name")+"/Documents/Conversion/"+empleado.getCedula()+".png");
							Image image = new Image(stream);
							imgViewDocumento.setImage(image);
							imgViewDocumento.setFitHeight(image.getHeight()/3);
							imgViewDocumento.setFitWidth(image.getWidth()/3);
						} catch (FileNotFoundException e) {
//							 TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					if(newSelection=="ARL"){
						try {
						pdfAImagen(empleado.getArlpath());
							InputStream stream = new FileInputStream("C:/Users/"+System.getProperty("user.name")+"/Documents/Conversion/"+empleado.getCedula()+".png");
							Image image = new Image(stream);
							imgViewDocumento.setImage(image);
							imgViewDocumento.setImage(image);
							imgViewDocumento.setFitHeight(image.getHeight()/5);
							imgViewDocumento.setFitWidth(image.getWidth()/5);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

		    	});
				}catch(NullPointerException e){
				}
	   }

	}
