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
    private String descripcion;

    public Ubicacion() {
    }

    public Ubicacion(int idubicaciones, String nombre_ubicacion, String descripcion) {
        this.idubicaciones = idubicaciones;
        this.nombre_ubicacion = nombre_ubicacion;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    

    @Override
    public String toString() {
        return nombre_ubicacion;
    }
    
    
    
}
