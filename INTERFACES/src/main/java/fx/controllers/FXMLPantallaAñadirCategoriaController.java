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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Categoria;
import servicios.ServiciosCategoria;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaAñadirCategoriaController implements Initializable {

    @FXML
    private TextField fxNombre;
    @FXML
    private TextArea fxDescripcion;
    @FXML
    private TextField fxNombreNuevo;
    @FXML
    private TextArea fxDescripcionNuevo;
    @FXML
    private ComboBox fxCategoria;
    @FXML
    private ComboBox fxCategoria2;
    @FXML
    private TableView fxTablaBorrar;

    private FXMLPantallaPrincipalController inicio;
    private Alert alertError;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    public void limpiarTabla() {
        fxTablaBorrar.getColumns().clear();
        fxTablaBorrar.getItems().clear();
    }

    public void cargar() {
        ServiciosCategoria sc = new ServiciosCategoria();
        fxCategoria.getItems().clear();
        fxCategoria2.getItems().clear();
        fxNombreNuevo.clear();
        fxDescripcionNuevo.clear();
        fxNombre.clear();
        fxDescripcion.clear();
        ArrayList<Categoria> categorias = (ArrayList<Categoria>) sc.cargarTodosLasCategorias();
        fxCategoria.getItems().addAll(categorias);
        fxCategoria2.getItems().addAll(categorias);

    }

    public void cargarActualizar() {
        Categoria pulsada = (Categoria) fxCategoria.getSelectionModel().getSelectedItem();
        if (pulsada != null) {
            fxNombreNuevo.setText(pulsada.getNombre());
            fxDescripcionNuevo.setText(pulsada.getDescripcion());
        }
    }

    public void cargarTablaBorrar() {
        fxTablaBorrar.getItems().clear();

        TableColumn nombre = new TableColumn("Nombre");
        TableColumn descripcion = new TableColumn("Descripcion");
        nombre.setPrefWidth(210);
        descripcion.setPrefWidth(210);

        fxTablaBorrar.getColumns().addAll(nombre, descripcion);
        nombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        descripcion.setCellValueFactory(new PropertyValueFactory("Descripcion"));
        Categoria c = (Categoria) fxCategoria2.getSelectionModel().getSelectedItem();
        fxTablaBorrar.getItems().add(c);
    }

    @FXML
    public void añadirCategoria() {

        if (fxNombre.getText().equals("") || fxDescripcion.getText().equals("")) {
            alertError.setContentText("Introduzca todos los datos");
            alertError.showAndWait();
        } else {
            Categoria c = new Categoria(0, fxNombre.getText(), fxDescripcion.getText());
            ServiciosCategoria sc = new ServiciosCategoria();
            int filas = sc.añadirCategoria(c);
            if (filas > 0) {
                c.setId_categoria(filas);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Categoria creada", ButtonType.CLOSE);
                a.showAndWait();
                fxNombre.clear();
                fxDescripcion.clear();
                fxTablaBorrar.getItems().clear();
                fxCategoria.getItems().add(c);
                fxCategoria2.getItems().add(c);

            } else {
                alertError.setContentText("Ha ocurrido un error");
                alertError.showAndWait();
            }

        }
    }

    @FXML
    public void actualizarCategoria() {
        ServiciosCategoria sc = new ServiciosCategoria();
        if (fxNombreNuevo.getText().equals("") || fxDescripcionNuevo.getText().equals("")) {
            alertError.setContentText("Introduzca todos los datos");
            alertError.showAndWait();
        } else {
            Categoria c = (Categoria) fxCategoria.getSelectionModel().getSelectedItem();
            c.setNombre(fxNombreNuevo.getText());
            c.setDescripcion(fxDescripcionNuevo.getText());
            int filas = sc.modificarCategoria(c);

            if (filas > 0) {
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Categoria actualizada", ButtonType.CLOSE);
                aa.showAndWait();
                fxNombreNuevo.clear();
                fxDescripcionNuevo.clear();
                fxCategoria2.getItems().remove(c);
                fxCategoria.getItems().remove(c);
                fxCategoria2.getItems().add(c);
                fxCategoria.getItems().add(c);

            } else {
                alertError.setContentText("Ha ocurrido un error");
                alertError.showAndWait();
            }
        }
    }

    @FXML
    public void borrarCategoria() {

        ServiciosCategoria sc = new ServiciosCategoria();
        if (fxCategoria2.getSelectionModel().getSelectedItem() == null) {
            alertError.setContentText("Seleccione una categoria");
            alertError.showAndWait();
        } else {
            Categoria c = (Categoria) fxCategoria2.getSelectionModel().getSelectedItem();
            int filas = sc.borrarCategoria(c);

            if (filas > 0) {
                Alert aa = new Alert(Alert.AlertType.INFORMATION, "Categoria borrada", ButtonType.CLOSE);
                aa.showAndWait();
                fxNombreNuevo.clear();
                fxDescripcionNuevo.clear();
                fxCategoria2.getItems().remove(c);
                fxCategoria.getItems().remove(c);
                limpiarTabla();

            } else if (filas == -2) {
                alertError.setContentText("No se puede borrar esta categoria");
                alertError.showAndWait();
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
