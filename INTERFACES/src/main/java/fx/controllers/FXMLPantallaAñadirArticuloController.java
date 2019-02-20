/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.Categoria;
import model.Articulo;
import model.Empleado;
import model.Ubicacion;
import servicios.ServiciosArticulos;
import servicios.ServiciosCategoria;
import servicios.ServiciosEmpleado;
import servicios.ServiciosUbicacion;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaAñadirArticuloController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }
    private String nombreDeImagen;

    @FXML
    private TextField fxNombre;
    @FXML
    private ComboBox fxComboBoxCategoria;
    @FXML
    private ComboBox fxComboBoxResponsable;
    @FXML
    private ComboBox fxComboBoxUbicacion;
    @FXML
    private TextArea fxTextAreaDescripcion;

    @FXML
    public void cargarComboBoxCategoria() {
        fxComboBoxCategoria.getItems().clear();
        servicios.ServiciosCategoria sc = new ServiciosCategoria();
        List<Categoria> categorias = sc.cargarTodosLasCategorias();
        fxComboBoxCategoria.getItems().addAll(categorias);

    }

    @FXML
    public void cargarComboBoxResponsable() {
        fxComboBoxResponsable.getItems().clear();
        ServiciosEmpleado se = new ServiciosEmpleado();
        List<Empleado> empl = se.cargarTodosLosEmpleados();
        fxComboBoxResponsable.getItems().addAll(empl);
    }

    @FXML
    public void cargarComboBoxUbicacion() {
        fxComboBoxUbicacion.getItems().clear();
        ServiciosUbicacion su = new ServiciosUbicacion();
        fxComboBoxUbicacion.getItems().addAll(su.cargarTodasLasUbicaciones());
    }

    @FXML
    public void añadirArticulo() {
        servicios.ServiciosArticulos sa = new ServiciosArticulos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        if (!fxNombre.getText().equals("") && fxComboBoxCategoria.getSelectionModel().getSelectedItem() != null && !fxTextAreaDescripcion.getText().equals("")) {

            Categoria cat = (Categoria) fxComboBoxCategoria.getSelectionModel().getSelectedItem();
            Articulo articuloAñadir = new Articulo(fxNombre.getText(), cat.getId_categoria(), fxTextAreaDescripcion.getText(),Date.valueOf(LocalDate.now()));
            
            //MISHA AQUI COMPRUEBAS LO DE LAS IMAGENES A TU MANERA
            
            articuloAñadir.setImagenes("imagenes");
            
            
            
            if (null != fxComboBoxUbicacion.getSelectionModel().getSelectedItem()) {
                articuloAñadir.setUbicacion(((Ubicacion) fxComboBoxUbicacion.getSelectionModel().getSelectedItem()).getIdubicaciones());
            }
            if (null != fxComboBoxResponsable.getSelectionModel().getSelectedItem()) {
                articuloAñadir.setId_responsable(((Empleado) fxComboBoxResponsable.getSelectionModel().getSelectedItem()).getId_empleado());
            }

            int num = sa.añadirArticulo(articuloAñadir);
            if (num > 0) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Se ha añadido el artículo correctamente");
                alert.showAndWait();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                switch (num) {
                    case -1:
                        alert.setContentText("Ha ocurrido un error en BBDD");
                        alert.showAndWait();
                        break;
                    case -2:
                        alert.setContentText("Este artículo ya existe en la BBDD.");
                        alert.showAndWait();
                        break;
                    default:
                        throw new AssertionError();
                }
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Debe Rellenar los campos obligatorios");
            alert.showAndWait();
        }
    }
    
    public void limpiarCampos(){
        //HOLA SOY UN MÉTODO QUE QUIERE SER RELLENADO
    }

//    private byte[] loadImageData(Image image) {
//        byte[] imageData = null;
//        try {
//
//            //creating a byte array output stream from the Image           
//            BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
//            ImageIO.write(bi, "jpg", baos);
//
//            baos.flush();
//            imageData = baos.toByteArray();
//            baos.close();
//            System.out.println(imageData);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return imageData;
//    }
//
//    public Image restoreMapData(byte[] data) {
//        BufferedImage bi = null;
//        try {
//            //converting back to an image
//            InputStream in = new ByteArrayInputStream(data);
//            bi = ImageIO.read(in);
//
//        } catch (IOException e) {
//        }
//        return SwingFXUtils.toFXImage(bi, null);
//    }
//
//    public void saveToFile(Image image) {
//        //  Image image = new Image(this.getClass().getResource("/images/" + imagenesSeparadas[num - 1]).toString());
//        File OutputFile = new File(getClass().getResource("/images/torreGaming.jpg").toString());
//        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
//        try {
//
//            ImageIO.write(bImage, "png", OutputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        //   System.out.println(getClass().getResource("/images/torre.png").toString());
//    }
//
//    /*
//    String currentDir = System.getProperty("user.home");
//file = new File(currentDir);
//     */
    @FXML
    public void añadirImagenes() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Seleccion de Imagenes");
        alert.setHeaderText(null);
        alert.setContentText("Las imagenes tienen que estar solo en la carpeta que se le abrirá a continuación. Copie su imagen a esa carpeta o seleccione una que ya está");
        alert.showAndWait();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = new File(this.getClass().getResource("/images").getFile());
        fileChooser.setInitialDirectory(file);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image file", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            if (selectedFile.getAbsolutePath().contains(fileChooser.getInitialDirectory().toString())) {
                //Añadir el nombre de la imagen al Articulo
                //Crear Lista de String e ir añadiendo nombre de las imagenes a la lista separados por ";"
                selectedFile.getName();
            } else {
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("Las imagenes tienen que estar solo en la carpeta que se le abrirá a continuación. Copie su imagen a esa carpeta o seleccione una que ya está");
                alert.showAndWait();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
