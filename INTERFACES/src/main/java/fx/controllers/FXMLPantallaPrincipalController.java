/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaPrincipalController implements Initializable {

    @FXML
    private BorderPane fxRoot;

    private int tipo_usuario;
    private String nombreEmpleado;

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    @FXML
    private MenuBar fxMenuBar;

    @FXML
    private Menu fxMenuArticulos;

    @FXML
    private Menu fxMenuEmpleados;

    @FXML
    private Menu fxMenuCategorias;

    @FXML
    private Menu fxMenuGeneral;

    @FXML
    private MenuItem fxMenuItemActualizarCategoria;

    @FXML
    private MenuItem fxMenuItemBorrarCategoria;

    @FXML
    private AnchorPane pantallaActualizarArticulo;
    private FXMLPantallaActualizarArticuloController controllerActualizarArticulo;

    private AnchorPane pantallaActualizarEmpleado;
    private FXMLPantallaActualizarEmpleadoController controllerActualizarEmpleado;

    private AnchorPane pantallaActualizarCategoria;
    private FXMLPantallaActualizarCategoriaController controllerActualizarCategoria;

    private AnchorPane pantallaAñadirArticulo;
    private FXMLPantallaAñadirArticuloController controllerAñadirArticulo;

    private AnchorPane pantallaAñadirEmpleado;
    private FXMLPantallaAñadirEmpleadoController controllerAñadirEmpleado;

    private AnchorPane pantallaAñadirCategoria;
    private FXMLPantallaAñadirCategoriaController controllerAñadirCategoria;

    private AnchorPane pantallaBorrarArticulo;
    private FXMLPantallaBorrarArticuloController controllerBorrarArticulo;

    private AnchorPane pantallaBorrarEmpleado;
    private FXMLPantallaBorrarEmpleadoController controllerBorrarEmpleado;

    private AnchorPane pantallaBorrarCategoria;
    private FXMLPantallaBorrarCategoriaController controllerBorrarCategoria;

    private AnchorPane pantallaLogin;
    private FXMLPantallaLoginController controllerLogin;

    private AnchorPane pantallaBienvenida;
    private FXMLPantallaBienvenidaController controllerBienvenida;

    private AnchorPane pantallaVerArticulo;
    private FXMLPantallaVerArticulosController controllerVerArticulo;

    // <editor-fold defaultstate="collapsed" desc="Precargas de Pantallas">  
    private void preCargaLogin() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaLogin.fxml"));
            pantallaLogin = loaderMenu.load();
            controllerLogin = loaderMenu.getController();
            controllerLogin.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaActualizarArticulo() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaActualizarArticulo.fxml"));
            pantallaActualizarArticulo = loaderMenu.load();
            controllerActualizarArticulo = loaderMenu.getController();
            controllerActualizarArticulo.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaVerArticulo() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaVerArticulos.fxml"));
            pantallaVerArticulo = loaderMenu.load();
            controllerVerArticulo = loaderMenu.getController();
            controllerVerArticulo.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaActualizarEmpleado() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaActualizarEmpleado.fxml"));
            pantallaActualizarEmpleado = loaderMenu.load();
            controllerActualizarEmpleado = loaderMenu.getController();
            controllerActualizarEmpleado.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaActualizarCategoria() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaActualizarCategoria.fxml"));
            pantallaActualizarCategoria = loaderMenu.load();
            controllerActualizarCategoria = loaderMenu.getController();
            controllerActualizarCategoria.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaAñadirArticulo() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaAñadirArticulo.fxml"));
            pantallaAñadirArticulo = loaderMenu.load();
            controllerAñadirArticulo = loaderMenu.getController();
            controllerAñadirArticulo.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaAñadirEmpleado() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaAñadirEmpleado.fxml"));
            pantallaAñadirEmpleado = loaderMenu.load();
            controllerAñadirEmpleado = loaderMenu.getController();
            controllerAñadirEmpleado.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaAñadirCategoria() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaAñadirCategoria.fxml"));
            pantallaAñadirCategoria = loaderMenu.load();
            controllerAñadirCategoria = loaderMenu.getController();
            controllerAñadirCategoria.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaBorrarArticulo() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaBorrarArticulo.fxml"));
            pantallaBorrarArticulo = loaderMenu.load();
            controllerBorrarArticulo = loaderMenu.getController();
            controllerBorrarArticulo.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaBorrarEmpleado() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaBorrarEmpleado.fxml"));
            pantallaBorrarEmpleado = loaderMenu.load();
            controllerBorrarEmpleado = loaderMenu.getController();
            controllerBorrarEmpleado.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaBorrarCategoria() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaBorrarCategoria.fxml"));
            pantallaBorrarCategoria = loaderMenu.load();
            controllerBorrarCategoria = loaderMenu.getController();
            controllerBorrarCategoria.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preCargaBienvenida() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource("/fxml/FXMLPantallaBienvenida.fxml"));
            pantallaBienvenida = loaderMenu.load();
            controllerBienvenida = loaderMenu.getController();
            controllerBienvenida.setInicio(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPantallaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// </editor-fold>     

    // <editor-fold defaultstate="collapsed" desc="Cargas de Pantallas">  
    @FXML
    public void cargarPantallaActualizarArticulo() {
        controllerActualizarArticulo.cargarComboArticulos();
        controllerActualizarArticulo.cargarComboResponsable();
        controllerActualizarArticulo.cargarComboCategoria();
        controllerActualizarArticulo.cargarComboUbicacion();
        fxRoot.setCenter(pantallaActualizarArticulo);
    }

    @FXML
    public void cargarPantallaVerArticulo() {
        controllerVerArticulo.cargarComboBox();
        fxRoot.setCenter(pantallaVerArticulo);
        
    }

    @FXML
    public void cargarPantallaActualizarEmpleado() {
        controllerActualizarEmpleado.cargar();
        fxRoot.setCenter(pantallaActualizarEmpleado);
    }

    @FXML
    public void cargarPantallaActualizarCategoria() {
        fxRoot.setCenter(pantallaActualizarCategoria);
    }

    @FXML
    public void cargarPantallaAñadirArticulo() {
        controllerAñadirArticulo.cargarComboBoxCategoria();
        controllerAñadirArticulo.cargarComboBoxResponsable();
        controllerAñadirArticulo.cargarComboBoxUbicacion();
        fxRoot.setCenter(pantallaAñadirArticulo);
    }

    @FXML
    public void cargarPantallaAñadirEmpleado() {
        controllerAñadirEmpleado.cargarComboBoxUbicacion();
        fxRoot.setCenter(pantallaAñadirEmpleado);
    }

    @FXML
    public void cargarPantallaAñadirCategoria() {
        controllerAñadirCategoria.cargar();
        fxRoot.setCenter(pantallaAñadirCategoria);
    }

    @FXML
    public void cargarPantallaBorrarArticulo() {
        fxRoot.setCenter(pantallaBorrarArticulo);
        controllerBorrarArticulo.cargarComboBox();
    }

    @FXML
    public void cargarPantallaBorrarEmpleado() {
        fxRoot.setCenter(pantallaBorrarEmpleado);
        controllerBorrarEmpleado.cargarComboBox();
    }

    @FXML
    public void cargarPantallaBorrarCategoria() {
        fxRoot.setCenter(pantallaBorrarCategoria);
    }

    @FXML
    public void cargarPantallaLogin() {
        fxMenuArticulos.setVisible(true);
        fxMenuCategorias.setVisible(true);
        fxMenuEmpleados.setVisible(true);
        fxMenuBar.setVisible(false);
        fxRoot.setCenter(pantallaLogin);
    }

    @FXML
    public void cargarPantallaBienvenida() {
        switch (tipo_usuario) {
            case 1:
                fxMenuArticulos.setVisible(false);
                break;
            case 2:
                fxMenuCategorias.setVisible(false);
                fxMenuEmpleados.setVisible(false);
                break;
            default:
                //esto sobra; es un atajo para no estar haciendo el login todo el rato
                fxMenuCategorias.setVisible(true);
                fxMenuEmpleados.setVisible(true);
                fxMenuArticulos.setVisible(true);
        }
        fxMenuBar.setVisible(true);
        //   controllerBienvenida.cambiarNombreBienvenida();
        fxRoot.setCenter(pantallaBienvenida);
    }

    // </editor-fold>  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //BORRAR PANTALLAS DE ACTUALIZAR Y BORRAR CATEGORIA YA QUE ESTA TODO JUNTADO EN UNA(FXMLAÑADIRCATEGORIA)
        fxMenuBar.setVisible(false);
        fxMenuItemActualizarCategoria.setVisible(false);
        fxMenuItemBorrarCategoria.setVisible(false);

        preCargaLogin();
        preCargaBienvenida();
        preCargaVerArticulo();
        preCargaActualizarArticulo();
        preCargaActualizarCategoria();
        preCargaActualizarEmpleado();
        preCargaAñadirArticulo();
        preCargaAñadirCategoria();
        preCargaAñadirEmpleado();
        preCargaBorrarArticulo();
        preCargaBorrarCategoria();
        preCargaBorrarEmpleado();
        cargarPantallaLogin();
        //borrar de aqui cargarPantallaBienvenida y descomentar el login 
//        cargarPantallaBienvenida();

    }

}
