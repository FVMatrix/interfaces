/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Empleado;
import servicios.ServiciosEmpleado;

/**
 * FXML Controller class
 *
 * @author mykha
 */
public class FXMLPantallaBorrarEmpleadoController implements Initializable {

    private FXMLPantallaPrincipalController inicio;

    public void setInicio(FXMLPantallaPrincipalController inicio) {
        this.inicio = inicio;
    }
    @FXML
    private TableView fxTableView;
    @FXML
    private ComboBox fxComboBox;

    public void cargarComboBox() {
        fxComboBox.getItems().clear();
        ServiciosEmpleado se = new ServiciosEmpleado();
        fxComboBox.getItems().addAll(se.cargarTodosLosEmpleados());
    }

    @FXML
    public void cargarTabla() {
        fxTableView.getColumns().clear();
        fxTableView.getItems().clear();
        TableColumn dni = new TableColumn("DNI");
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn apellidos = new TableColumn("Apellidos");
        TableColumn telefono = new TableColumn("Teléfono");
        TableColumn ubicacion = new TableColumn("Ubicación");

        nombre.setPrefWidth(140);
        dni.setPrefWidth(140);
        apellidos.setPrefWidth(140);
        ubicacion.setPrefWidth(140);
        telefono.setPrefWidth(140);
        fxTableView.getColumns().addAll(dni, nombre, apellidos, telefono, ubicacion);
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        dni.setCellValueFactory(new PropertyValueFactory("dni"));
        apellidos.setCellValueFactory(new PropertyValueFactory("apellido"));
        ubicacion.setCellValueFactory(new PropertyValueFactory("ubicacion"));
        telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        Empleado e = (Empleado) fxComboBox.getSelectionModel().getSelectedItem();
        fxTableView.getItems().add(e);
    }

    public void limpiarTabla() {
        fxTableView.getColumns().clear();
        fxTableView.getItems().clear();
    }

    @FXML
    public void borrarEmpleado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        ServiciosEmpleado se = new ServiciosEmpleado();
        if (fxComboBox.getSelectionModel().getSelectedItem() != null) {
            Empleado e = (Empleado) fxComboBox.getSelectionModel().getSelectedItem();
            if (!e.getDni().equals("root")) {
                int num = se.borrarEmpleado(e);
                if (num > 0) {
                    limpiarTabla();
                    fxComboBox.getItems().remove(e);
                    alert.setContentText("Se ha borrado correctamente");
                    alert.showAndWait();
                    fxComboBox.getItems().remove(e);
                } else {

                    alert.setContentText("Ha ocurrido un error");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("No se puede borrar al administrador ROOT");
                alert.showAndWait();
            }

        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Debe seleccionar a un empleado");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
