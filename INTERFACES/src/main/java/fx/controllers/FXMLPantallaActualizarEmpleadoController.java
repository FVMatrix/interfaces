/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Empleado;
import servicios.ServiciosEmpleado;

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
    private PasswordField fxContraseña;
    @FXML
    private PasswordField fxContraseña2;
    @FXML
    private TextField fxEmail;

    private Alert alertError;

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    public void cargarComboBoxUbicacion() {
        List<String> ubicaciones = new LinkedList();
        fxUbicacion.getItems().clear();
        ubicaciones.add("Despacho Director");
        ubicaciones.add("Departamento Marketing");
        ubicaciones.add("Departamento Contabilidad");
        ubicaciones.add("Departamento Producción");
        fxUbicacion.getItems().addAll(ubicaciones);
    }

    public void cargar() {
        ServiciosEmpleado sc = new ServiciosEmpleado();
        fxEmpleado.getItems().clear();
        ArrayList<Empleado> empleados = (ArrayList<Empleado>) sc.cargarTodosLosEmpleados();
        fxEmpleado.getItems().addAll(empleados);
        fxDni.clear();
        fxNombre.clear();
        fxApellidos.clear();
        fxTelefono.clear();
        fxContraseña.clear();
        fxContraseña2.clear();
        fxEmail.clear();

    }

    public void cargarActualizar() {
        Empleado pulsada = (Empleado) fxEmpleado.getSelectionModel().getSelectedItem();
        if (pulsada != null) {
            fxDni.setText(pulsada.getDni());
            fxNombre.setText(pulsada.getNombre());
            fxApellidos.setText(pulsada.getApellido());
            fxTelefono.setText(String.valueOf(pulsada.getTelefono()));
            fxContraseña.setText(pulsada.getContraseña());
            fxContraseña2.setText(pulsada.getContraseña());
            fxEmail.setText(pulsada.getEmail());
        }
    }

    @FXML
    public void actualizarEmpleado() {
        ServiciosEmpleado sc = new ServiciosEmpleado();
        if (fxDni.getText().equals("") || fxNombre.getText().equals("")
                || fxApellidos.getText().equals("") || fxTelefono.getText().equals("")
                || fxUbicacion.getSelectionModel().getSelectedItem() == null || fxContraseña.getText().equals("")
                || fxContraseña2.getText().equals("") || fxEmail.getText().equals("")) {
            alertError.setContentText("Introduzca todos los datos");
            alertError.showAndWait();
        } else if (fxContraseña.getText().equals(fxContraseña2.getText())) {
            Empleado c = (Empleado) fxEmpleado.getSelectionModel().getSelectedItem();
            c.setDni(fxDni.getText());
            c.setNombre(fxNombre.getText());
            c.setApellido(fxApellidos.getText());
            c.setTelefono(Integer.parseInt(fxTelefono.getText()));
            c.setUbicacion(fxUbicacion.getSelectionModel().getSelectedItem().toString());
            c.setContraseña(fxContraseña.getText());
            c.setEmail(fxEmail.getText());

            int filas = sc.modificarEmpleado(c);

            if (filas > 0) {
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Empleado actualizado", ButtonType.CLOSE);
                aa.showAndWait();
                fxDni.clear();
                fxNombre.clear();
                fxApellidos.clear();
                fxTelefono.clear();
                fxContraseña.clear();
                fxContraseña2.clear();
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
    }

}
