/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Empleado;
import model.Ubicacion;
import servicios.ServiciosEmpleado;
import servicios.ServiciosUbicacion;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaActualizarEmpleadoController implements Initializable {

    @FXML
    private ComboBox fxEmpleado;
    @FXML
    private TextField fxDni;
    @FXML
    private TextField fxNombre;
    @FXML
    private TextField fxApellidos;
    @FXML
    private TextField fxTelefono;
    @FXML
    private ComboBox fxUbicacion;
    @FXML
    private TextField fxEmail;

    private Alert alertError;

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    public void cargarComboBoxUbicacion() {
        ServiciosUbicacion su = new ServiciosUbicacion();
        fxUbicacion.getItems().addAll(su.cargarTodasLasUbicaciones());
    }

    public void cargarEmpleados() {
        ServiciosEmpleado sc = new ServiciosEmpleado();
        fxEmpleado.getItems().clear(); 
        ArrayList<Empleado> empleados = (ArrayList<Empleado>) sc.cargarTodosLosEmpleados();
        empleados.remove(0);
        fxEmpleado.getItems().addAll(empleados);
        fxDni.clear();
        fxNombre.clear();
        fxApellidos.clear();
        fxTelefono.clear();
        fxEmail.clear();

    }

    public void cargarActualizar() {
        Empleado pulsada = (Empleado) fxEmpleado.getSelectionModel().getSelectedItem();
        if (pulsada != null) {
            fxDni.setText(pulsada.getDni());
            fxNombre.setText(pulsada.getNombre());
            fxApellidos.setText(pulsada.getApellido());
            fxTelefono.setText(String.valueOf(pulsada.getTelefono()));
            fxEmail.setText(pulsada.getEmail());
        }
    }

    @FXML
    public void actualizarEmpleado() {
        ServiciosEmpleado sc = new ServiciosEmpleado();
        if (fxDni.getText().equals("") || fxNombre.getText().equals("")
                || fxApellidos.getText().equals("") || fxTelefono.getText().equals("")
                || fxUbicacion.getSelectionModel().getSelectedItem() == null 
                || fxEmail.getText().equals("")) {
            alertError.setContentText("Introduzca todos los datos");
            alertError.showAndWait();
        } else {
            Empleado c = (Empleado) fxEmpleado.getSelectionModel().getSelectedItem();
            c.setDni(fxDni.getText());
            c.setNombre(fxNombre.getText());
            c.setApellido(fxApellidos.getText());
            c.setTelefono(Integer.parseInt(fxTelefono.getText()));
            c.setUbicacion(((Ubicacion) fxUbicacion.getSelectionModel().getSelectedItem()).getIdubicaciones());
            c.setEmail(fxEmail.getText());

            int filas = sc.modificarEmpleado(c);

            if (filas > 0) {
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Empleado actualizado", ButtonType.CLOSE);
                aa.showAndWait();
                fxDni.clear();
                fxNombre.clear();
                fxApellidos.clear();
                fxTelefono.clear();
                fxEmail.clear();
                fxEmpleado.getItems().remove(c);
                fxEmpleado.getItems().add(c);

            } else {
                alertError.setContentText("Ha ocurrido un error");
                alertError.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertError = new Alert(Alert.AlertType.ERROR);
        cargarComboBoxUbicacion();
    }

}
