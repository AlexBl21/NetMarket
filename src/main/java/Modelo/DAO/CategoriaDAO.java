/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Categoria;
import Red.BaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanafanador07
 */
public class CategoriaDAO implements ICategoria {

    final static String SQL_CONSULTAR = "SELECT * FROM categoria";
    final static String SQL_INSERTAR = "INSERT INTO categoria(id,nombre) Value(?,?)";
    final static String SQL_BORRAR = "DELETE FROM categoria WHERE id = ?";
    final static String SQL_CONSULTAR_ID = "SELECT * FROM categoria WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE categoria SET nombre=? WHERE id=?";

    @Override
    public int insertar(Categoria categoria) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setInt(1, categoria.getId());
            sentencia.setString(2, categoria.getNombre());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<Categoria> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Categoria> categorias = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR); //esto sirve para que prepare la tabla de datos
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");

                Categoria categoria = new Categoria(id, nombre);

                categorias.add(categoria);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return categorias;
    }

    @Override
    public Categoria consultarPorId(Categoria categoria) {
        Connection connection = null;//conecta
        PreparedStatement sentencia = null;//prepara la sentencia
        ResultSet resultado = null;
        Categoria rCategoria = null;//el resultado a sacar

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY); //esto sirve para que prepare la tabla de datos(lo de resultset garantiza que devuelva un solo resultadoo)
            sentencia.setInt(1, categoria.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            resultado.absolute(1);

            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");

            rCategoria = new Categoria(id, nombre);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rCategoria;
    }

    @Override
    public int borrar(Categoria categoria) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setInt(1, categoria.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Categoria categoria) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setInt(2, categoria.getId());
            sentencia.setString(1, categoria.getNombre());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

}
