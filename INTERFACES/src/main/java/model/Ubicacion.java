/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dam2
 */
public class Ubicacion {
    private int idubicaciones;
    private String nombre_ubicacion;

    public Ubicacion() {
    }

    public Ubicacion(int idubicaciones, String nombre_ubicacion) {
        this.idubicaciones = idubicaciones;
        this.nombre_ubicacion = nombre_ubicacion;
    }

    public int getIdubicaciones() {
        return idubicaciones;
    }

    public void setIdubicaciones(int idubicaciones) {
        this.idubicaciones = idubicaciones;
    }

    public String getNombre_ubicacion() {
        return nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        this.nombre_ubicacion = nombre_ubicacion;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "idubicaciones=" + idubicaciones + ", nombre_ubicacion=" + nombre_ubicacion + '}';
    }
    
    
    
}
