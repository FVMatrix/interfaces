/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;
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
    private String imagenesDeArticulo;
    private String imagenesDeprueba;
    private String imagenActual;
    private String[] imagenesSeparadas;

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
        cargarImageView(a);
    }

    public void cargarImageView(Articulo a) {
        if (null != a) {
            imagenesDeArticulo = a.getImagenes();
            imagenesDeprueba = "monitorAcer.jpg;monitorAcerPredator.jpg;monitorSamsung.png;";
            imagenesSeparadas = imagenesDeprueba.split(";");
            Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[0]).toString());
            setImagenActual(imagenesSeparadas[0]);
            fxImageView.setImage((image));
        }

    }

    public int devuelvemeLaPosicionDeLaImagenActual() {
        int num = 0;
        for (int i = 0; i < imagenesSeparadas.length; i++) {
            if (imagenesSeparadas[i].equals(getImagenActual())) {
                num = i;
            }
        }
        return num;
    }

    //Cuando sea la ultima imagen de la lista y el usuario pulsa "Siguiente Imagen"
    public void mostrarLaPrimeraImagen() {
        Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[0]).toString());
        setImagenActual(imagenesSeparadas[0]);
        fxImageView.setImage((image));
    }
    //Cuando sea la primera imagen de la lista y el usuario pulsa "Anterior Imagen"

    public void mostrarLaUltimaImagen() {
        Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[imagenesSeparadas.length - 1]).toString());
        setImagenActual(imagenesSeparadas[imagenesSeparadas.length - 1]);
        fxImageView.setImage((image));
    }

    @FXML
    public void mostrarImagenSiguiente() {

        String cual = getImagenActual();
        if (imagenesSeparadas[imagenesSeparadas.length - 1].equals(cual)) {
            mostrarLaPrimeraImagen();
        } else {
            int num = devuelvemeLaPosicionDeLaImagenActual();
            Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[num + 1]).toString());
            setImagenActual(imagenesSeparadas[num + 1]);
            fxImageView.setImage((image));
        }
    }

    @FXML
    public void mostrarImagenAnterior() {
        String cual = getImagenActual();
        if (imagenesSeparadas[0].equals(cual)) {
            mostrarLaUltimaImagen();
        } else {
            int num = devuelvemeLaPosicionDeLaImagenActual();
            Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[num - 1]).toString());
            setImagenActual(imagenesSeparadas[num - 1]);
            fxImageView.setImage((image));
        }
    }

    public String[] getImagenesSeparadas() {
        return imagenesSeparadas;
    }

    public void setImagenesSeparadas(String[] imagenesSeparadas) {
        this.imagenesSeparadas = imagenesSeparadas;
    }

    public String getImagenesDeArticulo() {
        return imagenesDeArticulo;
    }

    public void setImagenesDeArticulo(String imagenesDeArticulo) {
        this.imagenesDeArticulo = imagenesDeArticulo;
    }

    public String getImagenesDeprueba() {
        return imagenesDeprueba;
    }

    public void setImagenesDeprueba(String imagenesDeprueba) {
        this.imagenesDeprueba = imagenesDeprueba;
    }

    public String getImagenActual() {
        return imagenActual;
    }

    public void setImagenActual(String imagenActual) {
        this.imagenActual = imagenActual;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
