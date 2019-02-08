/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Articulo;
import servicios.ServiciosArticulos;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaVerArticulosController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }
    @FXML
    private ComboBox fxComboBox;
    @FXML
    private TableView fxTableView;
    @FXML
    private ImageView fxImageView;

    public void cargarComboBox() {
        servicios.ServiciosArticulos sa = new ServiciosArticulos();
        fxComboBox.getItems().clear();
        fxComboBox.getItems().addAll(sa.cargarTodosLosArticulos());
    }

    @FXML
    public void cargarTabla() {
        Articulo a = (Articulo) fxComboBox.getSelectionModel().getSelectedItem();
        fxTableView.getColumns().clear();
        fxTableView.getItems().clear();
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn responsable = new TableColumn("Responsable");
        TableColumn ubicacion = new TableColumn("Ubicación");
        TableColumn descipcion = new TableColumn("Descripción");
        nombre.setPrefWidth(140);
        categoria.setPrefWidth(140);
        responsable.setPrefWidth(140);
        ubicacion.setPrefWidth(140);
        descipcion.setPrefWidth(140);
        fxTableView.getColumns().addAll(nombre, categoria, responsable, ubicacion, descipcion);
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        categoria.setCellValueFactory(new PropertyValueFactory("id_categoria"));
        responsable.setCellValueFactory(new PropertyValueFactory("id_responsable"));
        ubicacion.setCellValueFactory(new PropertyValueFactory("ubicacion"));
        descipcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        fxTableView.getItems().add(a);
        cargarImageView();
    }

    public void cargarImageView() {
        File file;

        // File file = new File("");
//            file = new File(this.getClass().getResource("/src/main/resources/images/bienvenido.png").toURI());
        Image image = new Image(this.getClass().getResource("/images/bienvenido.png").toString());

        //  Image image = new Image(this.getClass().getResource("images/bienvenido.png").toURI().toString());
        fxImageView.setImage((image));
    }

    public void mostrarImagenSiguiente() {
        List<String> imagenes = new LinkedList();
        
        
    }

    public void mostrarImagenAnterior() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
