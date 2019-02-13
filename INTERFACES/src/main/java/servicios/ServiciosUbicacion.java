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
    
     public int añadirUbicacion(Ubicacion u) {
        DaoUbicacion du = new DaoUbicacion();
        return du.añadirUbicacion(u);
    }

    public int borrarUbicacion(Ubicacion u) {
      DaoUbicacion du = new DaoUbicacion();
        return du.borrarUbicacion(u);
    }

    public Ubicacion cargarUbicacionPorID(int id) {
        DaoUbicacion du = new DaoUbicacion();
        return du.cargarUbicacionPorID(id);
    }

    public int modificarUbicacion(Ubicacion u) {
        DaoUbicacion du = new DaoUbicacion();
        return du.modificarUbicacion(u);
    }
}
