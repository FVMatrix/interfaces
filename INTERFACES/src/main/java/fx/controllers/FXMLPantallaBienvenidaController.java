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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaBienvenidaController implements Initializable {

  private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }
    
    @FXML
    private Label fxBienvenida;
    
    
    public void cambiarNombreBienvenida(){
        String hola="BIENVENIDO A MATRIX AGENTE ";
        fxBienvenida.setText(hola+inicio.getNombreEmpleado().toUpperCase());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
