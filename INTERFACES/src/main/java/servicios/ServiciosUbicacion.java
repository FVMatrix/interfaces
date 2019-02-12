/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.DaoUbicacion;
import java.util.List;
import model.Ubicacion;

/**
 *
 * @author Ragnar
 */
public class ServiciosUbicacion {
    public List<Ubicacion> cargarTodasLasUbicaciones(){
        DaoUbicacion du = new DaoUbicacion();
        return du.cargarTodasLasUbicaciones();
    }
}
