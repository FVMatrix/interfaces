/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Articulo;
import model.Categoria;
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
public class FXMLPantallaActualizarArticuloController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    @FXML
    private ComboBox fxComboBoxArticulo;

    @FXML
    private TextField fxNombre;
    @FXML
    private ComboBox fxComboBoxCategoria;
    @FXML
    private ComboBox fxComboBoxUbicacion;
    @FXML
    private ComboBox fxComboBoxResponsable;
    @FXML
    private TextArea fxDescripcion;
    private Alert alertError;

    public void cargarComboArticulos() {
        fxComboBoxArticulo.getItems().clear();
        ServiciosArticulos sa = new ServiciosArticulos();
        List<Articulo> art = sa.cargarTodosLosArticulos();
        fxComboBoxArticulo.getItems().addAll(art);
    }

    
    public void cargarCampos() {
        Articulo a = (Articulo) fxComboBoxArticulo.getSelectionModel().getSelectedItem();
        fxNombre.setText(a.getNombre());
        fxDescripcion.setText(a.getDescripcion());
    }

    public void cargarComboCategoria() {
        fxComboBoxCategoria.getItems().clear();
        ServiciosCategoria sc = new ServiciosCategoria();
        List<Categoria> cat = sc.cargarTodosLasCategorias();
        fxComboBoxCategoria.getItems().addAll(cat);
    }

    public void cargarComboResponsable() {
        fxComboBoxResponsable.getItems().clear();
        servicios.ServiciosEmpleado se = new ServiciosEmpleado();
        List<Empleado> empl = se.cargarTodosLosEmpleados();
        fxComboBoxResponsable.getItems().addAll(empl);
    }

    public void cargarComboUbicacion() {
        fxComboBoxUbicacion.getItems().clear();
        ServiciosUbicacion su = new ServiciosUbicacion();
        fxComboBoxUbicacion.getItems().addAll(su.cargarTodasLasUbicaciones());
    }

    public void limpiarCampos() {
        fxNombre.clear();
        fxDescripcion.clear();
    }

    @FXML
    public void actualizarArticulo() {
        ServiciosArticulos sa = new ServiciosArticulos();
        if (fxComboBoxArticulo.getSelectionModel().getSelectedItem() != null) {
            if (!fxNombre.getText().equals("") && fxComboBoxCategoria.getSelectionModel().getSelectedItem() != null
                    && fxComboBoxUbicacion.getSelectionModel().getSelectedItem() != null && fxComboBoxResponsable.getSelectionModel().getSelectedItem() != null && !fxDescripcion.getText().equals("")) {
                Articulo a = (Articulo) fxComboBoxArticulo.getSelectionModel().getSelectedItem();
                a.setDescripcion(fxDescripcion.getText());
                a.setNombre(fxNombre.getText());
                a.setId_categoria(((Categoria) fxComboBoxCategoria.getSelectionModel().getSelectedItem()).getId_categoria());
                a.setId_responsable(((Empleado) fxComboBoxResponsable.getSelectionModel().getSelectedItem()).getId_empleado());
                a.setUbicacion(((Ubicacion) fxComboBoxUbicacion.getSelectionModel().getSelectedItem()).getIdubicaciones());
                int num = sa.modificarArticulo(a);
                if (num > 0) {
                    alertError.setAlertType(Alert.AlertType.INFORMATION);
                    alertError.setContentText("Actualizado con exito");
                    alertError.showAndWait();
                } else {
                    alertError.setContentText("No se ha podido actualizar");
                    alertError.showAndWait();
                }
            } else {
                alertError.setContentText("Rellene todos los campos");
                alertError.showAndWait();
            }

        } else {
            alertError.setContentText("Debe seleccionar un articulo");
            alertError.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertError = new Alert(Alert.AlertType.ERROR);
    }

}
