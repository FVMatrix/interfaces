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
public class FXMLPantallaAñadirEmpleadoController implements Initializable {

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

    private FXMLPantallaPrincipalController inicio;
    private Alert alertError;

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

    @FXML
    public void añadirEmpleado() {

        if (fxDni.getText().equals("") || fxNombre.getText().equals("")
                || fxApellidos.getText().equals("") || fxTelefono.getText().equals("")
                || fxUbicacion.getSelectionModel().getSelectedItem() == null || fxContraseña.getText().equals("")
                || fxContraseña2.getText().equals("") || fxEmail.getText().equals("")) {
            alertError.setContentText("Introduzca todos los datos");
            alertError.showAndWait();
        } else if (fxContraseña.getText().equals(fxContraseña2.getText())) {
            String ubicacion = fxUbicacion.getSelectionModel().getSelectedItem().toString();
            Empleado emp = new Empleado(0, fxDni.getText(), fxNombre.getText(), fxApellidos.getText(), Integer.parseInt(fxTelefono.getText()), ubicacion, 3, fxContraseña.getText(), fxEmail.getText());
            ServiciosEmpleado sc = new ServiciosEmpleado();
            int filas = sc.añadirEmpleado(emp);
            if (filas > 0) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Empleado creado", ButtonType.CLOSE);
                a.showAndWait();
                fxDni.clear();
                fxNombre.clear();
                fxApellidos.clear();
                fxTelefono.clear();
                fxContraseña.clear();
                fxContraseña2.clear();
                fxEmail.clear();

            } else {
                alertError.setContentText("Ha ocurrido un error");
                alertError.showAndWait();
            }

        } else {
            alertError.setContentText("La contraseña no es igual");
            alertError.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertError = new Alert(Alert.AlertType.ERROR);
    }

}
