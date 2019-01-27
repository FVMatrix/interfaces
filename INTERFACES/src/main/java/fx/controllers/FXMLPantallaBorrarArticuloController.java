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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Articulo;
import model.Empleado;
import servicios.ServiciosArticulos;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaBorrarArticuloController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }

    @FXML
    private ComboBox fxComboBox;
    @FXML
    private TableView fxTableView;

    @FXML
    public void cargarTabla() {
        fxTableView.getColumns().clear();
        fxTableView.getItems().clear();
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn responsable = new TableColumn("Responsable");
        TableColumn ubicacion = new TableColumn("Ubicación");
        TableColumn descipcion = new TableColumn("Descripción");
        nombre.setPrefWidth(140);
        categoria.setPrefWidth(140);
        responsable.setPrefWidth(140);
        ubicacion.setPrefWidth(140);
        descipcion.setPrefWidth(140);
        fxTableView.getColumns().addAll(nombre, categoria, responsable, ubicacion, descipcion);
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        categoria.setCellValueFactory(new PropertyValueFactory("id_categoria"));
        responsable.setCellValueFactory(new PropertyValueFactory("id_responsable"));
        ubicacion.setCellValueFactory(new PropertyValueFactory("ubicacion"));
        descipcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        Articulo a = (Articulo) fxComboBox.getSelectionModel().getSelectedItem();
        fxTableView.getItems().add(a);
    }

    public void limpiarTabla() {
        fxTableView.getColumns().clear();
        fxTableView.getItems().clear();
    }

    @FXML
    public void cargarComboBox() {
        fxComboBox.getItems().clear();
        ServiciosArticulos sa = new ServiciosArticulos();
        List<Articulo> art;
        art = sa.cargarTodosLosArticulos();
        fxComboBox.getItems().addAll(art);
    }

    @FXML
    public void borrarArticulo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        ServiciosArticulos sa = new ServiciosArticulos();
        if (fxComboBox.getSelectionModel().getSelectedItem() != null) {
            Articulo a = (Articulo) fxComboBox.getSelectionModel().getSelectedItem();
            int num = sa.borrarArticulo(a);
            if (num > 0) {
                limpiarTabla();
                fxComboBox.getItems().remove(a);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Se ha borrado correctamente");
                alert.showAndWait();
            } else if (num == -2) {
                alert.setContentText("Este Articulo no se puede borrar.");
                alert.showAndWait();
            }

        } else {
            alert.setContentText("Seleccione un articulo");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
