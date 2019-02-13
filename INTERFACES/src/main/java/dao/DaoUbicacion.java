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
import model.Ubicacion;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 *
 * @author dam2
 */
public class DaoUbicacion {
    public List<Ubicacion> cargarTodasLasUbicaciones() {
        List<Ubicacion> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Ubicacion>> handler = new BeanListHandler<>(Ubicacion.class);
            lista = qr.query(con, "select * FROM ubicaciones", handler);

        } catch (Exception ex) {
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return lista;
    }

    public int añadirCategoria(Ubicacion u) {
        int filas = -1;
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            Number id = qr.insert(con,
                    "insert into ubicaciones (nombre_ubicacion) values(?)",
                    new ScalarHandler<>(),
                    u.getNombre_ubicacion());
            filas = id.intValue();
            u.setIdubicaciones(filas);
        } catch (Exception ex) {
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public int borrarCategoria(Ubicacion u) {
        int filas = -1;
        DBConnection db = new DBConnection();
        Connection con = null;

        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM ubicaciones WHERE idubicaciones = ?", u.getIdubicaciones());

        } catch (SQLException ex) {
            filas = -2;
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public Ubicacion cargarCategoriaPorID(int id) {
        Ubicacion cat = new Ubicacion();
        DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler handler = new BeanHandler<>(Ubicacion.class);
            cat = (Ubicacion) qr.query(con, "select * FROM ubicaciones where idubicaciones = ?", handler, id);
        } catch (Exception ex) {
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return cat;
    }

    public int modificarCategoria(Ubicacion u) {
        int filas = -1;
        Connection con = null;
        DBConnection db = new DBConnection();

        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE ubicaciones SET nombre_ubicacion = ? WHERE idubicaciones = ?",
                    u.getNombre_ubicacion(), u.getIdubicaciones());

        } catch (Exception ex) {
            Logger.getLogger(DaoUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.cerrarConexion(con);
        }
        return filas;
    }
}
