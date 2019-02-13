/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Empleado;
import servicios.ServiciosEmpleado;
import util.PasswordHash;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaLoginController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    @FXML
    private TextField fxDNIUsuario;
    @FXML
    private PasswordField fxContra;
    @FXML
    private Alert alert;

    @FXML
    public void hacerLogin() {
        ServiciosEmpleado se = new ServiciosEmpleado();
        if (fxDNIUsuario.getText().equals("") || fxContra.getText().equals("")) {
            alert.setContentText("Completa todos los campos");
            alert.showAndWait();
        } else {
            PasswordHash ph = new PasswordHash();
            Empleado empleadoLogin = new Empleado(fxDNIUsuario.getText(), fxContra.getText());

            Empleado empleadoRecibido = se.cargarEmpleadoPorDNI(empleadoLogin);
            if (null == empleadoRecibido || empleadoRecibido.getNombre().equals("")) {
                alert.setContentText("Usuario incorrecto");
                alert.showAndWait();
            } else {
                try {
                    if (!ph.validatePassword(empleadoLogin.getPass(), empleadoRecibido.getPass())) {
                        alert.setContentText("La contraseña es incorrecta.");
                        alert.showAndWait();
                    } else {
                        switch (empleadoRecibido.getTipo_empleado()) {
                            case 1:
                                inicio.setTipo_usuario(1);
                                inicio.setNombreEmpleado(empleadoRecibido.getNombre() + " (ADMIN)");

                                inicio.cargarPantallaBienvenida();
                                break;
                            case 2:
                                inicio.setTipo_usuario(2);
                                inicio.setNombreEmpleado(empleadoRecibido.getNombre() + " (INVENTARIADOR)");

                                inicio.cargarPantallaBienvenida();
                                break;
                            case 3:
                                alert.setContentText("No eres ni inventariador ni administrador, por lo tanto no puedes entrar en esta aplicación.");
                                alert.showAndWait();
                                break;
                            default:
                                break;
                        }
                        fxDNIUsuario.setText("");
                        fxContra.setText("");

                    }
                } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                    Logger.getLogger(FXMLPantallaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Información");
        alert.setHeaderText(null);
    }

}
