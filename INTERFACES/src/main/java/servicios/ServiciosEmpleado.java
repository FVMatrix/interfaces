/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.DaoEmpleados;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Empleado;
import util.PasswordHash;

/**
 *
 * @author mykha
 */
public class ServiciosEmpleado {

    public List<Empleado> cargarTodosLosEmpleados() {
        dao.DaoEmpleados de = new DaoEmpleados();
        return de.cargarTodosLosEmpleados();
    }

    public int añadirEmpleado(Empleado emp) {
        dao.DaoEmpleados de = new DaoEmpleados();
        PasswordHash ph = new PasswordHash();
        try {
            emp.setPass(ph.createHash(emp.getPass()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(ServiciosEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return de.añadirEmpleado(emp);
    }

    public int borrarEmpleado(Empleado emp) {
        dao.DaoEmpleados de = new DaoEmpleados();
        return de.borrarEmpleado(emp);
    }

    public Empleado cargarEmpleadoPorID(Empleado emp) {
        dao.DaoEmpleados de = new DaoEmpleados();
        return de.cargarEmpleadoPorID(emp);
    }

    public Empleado cargarEmpleadoPorDNI(Empleado emp) {
        dao.DaoEmpleados de = new DaoEmpleados();
        return de.cargarEmpleadoPorDNIDeUsuario(emp);
    }

    public int modificarEmpleado(Empleado emp) {
        dao.DaoEmpleados de = new DaoEmpleados();
        PasswordHash ph = new PasswordHash();
        try {
            emp.setPass(ph.createHash(emp.getPass()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(ServiciosEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return de.modificarEmpleado(emp);
    }
}
