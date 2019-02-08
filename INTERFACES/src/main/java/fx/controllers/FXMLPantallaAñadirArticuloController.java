/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import model.Categoria;
import model.Articulo;
import model.Empleado;
import servicios.ServiciosArticulos;
import servicios.ServiciosCategoria;
import servicios.ServiciosEmpleado;

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
        List<String> ubicaciones = new LinkedList();
        ubicaciones.add("Despacho Director");
        ubicaciones.add("Departamento Marketing");
        ubicaciones.add("Departamento Contabilidad");
        ubicaciones.add("Departamento Producción");
        fxComboBoxUbicacion.getItems().addAll(ubicaciones);
    }

    @FXML
    public void añadirArticulo() {
        servicios.ServiciosArticulos sa = new ServiciosArticulos();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        if (!fxNombre.getText().equals("") && fxComboBoxCategoria.getSelectionModel().getSelectedItem() != null && !fxTextAreaDescripcion.getText().equals("")) {
            Empleado u = null;
            String ubicacion = null;
            int idEmpleado = 0;
            if (fxComboBoxResponsable.getSelectionModel().getSelectedItem() != null) {
                u = (Empleado) fxComboBoxResponsable.getSelectionModel().getSelectedItem();
                idEmpleado = u.getId_empleado();
            }
            if (fxComboBoxUbicacion.getSelectionModel().getSelectedItem() != null) {
                ubicacion = fxComboBoxUbicacion.getSelectionModel().getSelectedItem().toString();
            }

            Categoria cat = (Categoria) fxComboBoxCategoria.getSelectionModel().getSelectedItem();
            Articulo o = new Articulo(0, fxNombre.getText(), cat.getId_categoria(), "imagenes", fxTextAreaDescripcion.getText(), ubicacion, idEmpleado, Date.valueOf(LocalDate.now()));
            int num = sa.añadirArticulo(o);
            if (num > 0) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("No se ha podido añadir");
                alert.showAndWait();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Debe Rellenar los campos obligatorios");
            alert.showAndWait();
        }
    }

//    public  void saveToFile(Image image) {
//        File inputFile = new File(this.getClass().getResource("/images").toString());
//        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
//        try {
//            ImageIO.write(bImage, "JPG", inputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @FXML
    public void añadirImagenes() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {

                String imageFile = selectedFile.toURI().toURL().toString();
                Image image = new Image(imageFile);
             //   saveToFile(image);
            } catch (MalformedURLException ex) {
                Logger.getLogger(FXMLPantallaAñadirArticuloController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(selectedFile.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
