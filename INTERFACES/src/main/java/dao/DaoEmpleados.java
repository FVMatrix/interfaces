/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Empleado;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author mykha
 */
public class DaoEmpleados {

    public int añadirEmpleado(Empleado emp) {
        int filas = -1;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            Number id = qr.insert(con,
                    "insert into empleados (nombre, apellido, telefono, ubicacion, tipo_empleado, pass, email, dni) values(?,?,?,?,?,?,?,?)",
                    new ScalarHandler<>(),
                    emp.getNombre(), emp.getApellido(), emp.getTelefono(), emp.getUbicacion(), emp.getTipo_empleado(), emp.getPass(), emp.getEmail(), emp.getDni());
            filas = id.intValue();
            emp.setId_empleado(filas);
        } catch (Exception ex) {
            //SI EL DNI YA EXISTE EN LA BBDD, HAZSELO SABER AL USUARIO
            if (ex.getMessage().contains("UNIQUE")) {
                filas = -2;
            }
            
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public List<Empleado> cargarTodosLosEmpleados() {
        List<Empleado> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Empleado>> handler = new BeanListHandler<>(Empleado.class);
            lista = qr.query(con, "select * FROM empleados", handler);

        } catch (Exception ex) {
           
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public int borrarEmpleado(Empleado emp) {
        int filas = -1;
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();

            con.setAutoCommit(false);

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM usuario_articulo WHERE id_empleado = ?", emp.getId_empleado());

            filas += qr.update(con,
                    "DELETE FROM empleados WHERE id_empleado = ?", emp.getId_empleado());

            con.commit();

        } catch (Exception ex) {
             filas = -2;
            try {
                if (con != null) {
                    con.rollback();
                     filas = -1;
                }
            } catch (SQLException ex1) {
                
                filas = -2;
            }
            
           
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public Empleado cargarEmpleadoPorID(Empleado emp) {
        Empleado emple = new Empleado();
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler handler = new BeanHandler<>(Empleado.class);
            emple = (Empleado) qr.query(con, "select * FROM empleados where id_empleado = ?", handler, emp.getId_empleado());
        } catch (Exception ex) {
           
        } finally {
            db.cerrarConexion(con);
        }
        return emple;
    }

    public Empleado cargarEmpleadoPorDNIDeUsuario(Empleado emp) {
        Empleado emple = new Empleado();
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler handler = new BeanHandler<>(Empleado.class);
            emple = (Empleado) qr.query(con, "select * FROM empleados where dni = ?", handler, emp.getDni());
        } catch (Exception ex) {
           
        } finally {
            db.cerrarConexion(con);
        }
        return emple;
    }

    public int modificarEmpleado(Empleado emp) {
        int filas = -1;
        Connection con = null;
        DBConnection db = new DBConnection();

        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE empleados SET nombre = ?, apellido = ?, telefono = ?, ubicacion = ? ,pass = ?,email = ?, dni = ? WHERE id_empleado = ?",
                    emp.getNombre(), emp.getApellido(), emp.getTelefono(), emp.getUbicacion(), emp.getPass(), emp.getEmail(), emp.getDni(), emp.getId_empleado());

        } catch (Exception ex) {
            if (ex.getMessage().contains("UNIQUE")) {
                filas = -2;
            }
          
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

}
