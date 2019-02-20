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
    private List<Categoria> cat;
    private List<Empleado> empl;
    private List<Ubicacion> ubicaciones;
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

    //  Articulo a = new Articulo();
//        fxComboBoxUbicacion.getSelectionModel().select(a);
    public void cargarCampos() {
        Articulo a = (Articulo) fxComboBoxArticulo.getSelectionModel().getSelectedItem();
        fxNombre.setText(a.getNombre());
        fxDescripcion.setText(a.getDescripcion());
        fxComboBoxUbicacion.getSelectionModel().select(devuelveUbicacionDelArticuloSeleccionado(a));
        fxComboBoxCategoria.getSelectionModel().select(devuelveCategoriaDelArticuloSeleccionado(a));
        fxComboBoxResponsable.getSelectionModel().select(devuelveEmpleadoDelArticuloSeleccionado(a));
    }

    public Categoria devuelveCategoriaDelArticuloSeleccionado(Articulo a) {
        Categoria cats = null;
        boolean salir = false;
        for (int i = 0; i < cat.size() && !salir; i++) {
            if (cat.get(i).getId_categoria() == a.getId_categoria()) {
                cats = cat.get(i);
                salir = true;
            }
        }
        return cats;
    }

    public Ubicacion devuelveUbicacionDelArticuloSeleccionado(Articulo a) {
        Ubicacion ub = null;
        boolean salir = false;
        for (int i = 0; i < ubicaciones.size() && !salir; i++) {
            if (ubicaciones.get(i).getIdubicaciones() == a.getUbicacion()) {
                ub = ubicaciones.get(i);
                salir = true;
            }
        }
        return ub;
    }

    public Empleado devuelveEmpleadoDelArticuloSeleccionado(Articulo a) {
        Empleado em = null;
        boolean salir = false;
        for (int i = 0; i < empl.size() && !salir; i++) {
            if (empl.get(i).getId_empleado() == a.getId_responsable()) {
                em = empl.get(i);
                salir = true;
            }
        }
        return em;
    }

    public void cargarComboCategoria() {
        cat = new LinkedList<>();
        fxComboBoxCategoria.getItems().clear();
        ServiciosCategoria sc = new ServiciosCategoria();
        cat.addAll(sc.cargarTodosLasCategorias());
        fxComboBoxCategoria.getItems().addAll(cat);
    }

    public void cargarComboResponsable() {
        empl = new LinkedList<>();
        fxComboBoxResponsable.getItems().clear();
        servicios.ServiciosEmpleado se = new ServiciosEmpleado();
        empl.addAll(se.cargarTodosLosEmpleados());
        fxComboBoxResponsable.getItems().addAll(empl);
    }

    public void cargarComboUbicacion() {
        ubicaciones = new LinkedList<>();
        fxComboBoxUbicacion.getItems().clear();
        ServiciosUbicacion su = new ServiciosUbicacion();
        ubicaciones.addAll(su.cargarTodasLasUbicaciones());
        fxComboBoxUbicacion.getItems().addAll(ubicaciones);
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
                    && fxComboBoxUbicacion.getSelectionModel().getSelectedItem() != null && fxComboBoxResponsable.getSelectionModel().getSelectedItem() != null 
                    && !fxDescripcion.getText().equals("")) {
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
                    limpiarCampos();
                } else {
                    switch (num) {
                        case -1:
                            alertError.setContentText("Ha ocurrido un error en BBDD");
                            alertError.showAndWait();
                            break;
                        case -2:
                            alertError.setContentText("Los datos que quiere introducir en este artículo ya existen en la BBDD.");
                            alertError.showAndWait();
                            break;
                        default:
                            throw new AssertionError();
                    }
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
