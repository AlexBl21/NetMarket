/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;
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
public class ProductoDAO implements IProducto {

    final static String SQL_INSERTAR = "INSERT INTO producto(id,nombre,foto,precio,descripcion,id_catalogo) Value(?,?,?,?,?,?)";
    final static String SQL_CONSULTAR = "SELECT * FROM producto";
    final static String SQL_CONSULTAR_ID = "SELECT * FROM producto WHERE id=?";
    final static String SQL_CONSULTAR_CATEGORIA = "SELECT p.* FROM producto p, producto_categoria pc WHERE pc.id_categoria = ?";
    final static String SQL_CONSULTAR_CATALOGO = "SELECT * FROM producto WHERE id_catalogo = ?";
    final static String SQL_BORRAR = "DELETE FROM producto WHERE id = ?";
    final static String SQL_ACTUALIZAR = "UPDATE producto SET nombre=?, foto=?, precio=?, descripcion=? WHERE id=?";

    @Override
    public int insertar(Producto producto, Catalogo catalogo) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setInt(1, producto.getId());
            sentencia.setString(2, producto.getNombre());
            sentencia.setString(3, producto.getFoto());
            sentencia.setFloat(4, producto.getPrecio());
            sentencia.setString(5, producto.getDescripcion());
            sentencia.setInt(6, catalogo.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<Producto> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Producto> productos = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR); //esto sirve para que prepare la tabla de datos
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String foto = resultado.getString("foto");
                float precio = resultado.getFloat("precio");
                String descripcion = resultado.getString("descripcion");

                Producto producto = new Producto(id, nombre, foto, precio, descripcion);
                productos.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productos;
    }
    
    @Override
    public List<Producto> consultarPorCategoria(Categoria categoria) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Producto> productos = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_CATEGORIA); //esto sirve para que prepare la tabla de datos
            sentencia.setInt(1, categoria.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String foto = resultado.getString("foto");
                float precio = resultado.getFloat("precio");
                String descripcion = resultado.getString("descripcion");

                Producto producto = new Producto(id, nombre, foto, precio, descripcion);
                productos.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productos;
    }
    
    @Override
    public Producto consultarPorId(Producto producto) {
        Connection connection = null;//conecta
        PreparedStatement sentencia = null;//prepara la sentencia
        ResultSet resultado = null;
        Producto rProducto = null;//el resultado a sacar

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY); //esto sirve para que prepare la tabla de datos(lo de resultset garantiza que devuelva un solo resultadoo)
            sentencia.setInt(1, producto.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            resultado.absolute(1);

            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String foto = resultado.getString("foto");
            float precio = resultado.getFloat("precio");
            String descripcion = resultado.getString("descripcion");

            rProducto = new Producto(id, nombre, foto, precio, descripcion);

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rProducto;
    }
    
    @Override
    public List<Producto> consultarPorCatalogo(Catalogo catalogo) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Producto> productos = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_CATALOGO); //esto sirve para que prepare la tabla de datos
            sentencia.setInt(1, catalogo.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String foto = resultado.getString("foto");
                float precio = resultado.getFloat("precio");
                String descripcion = resultado.getString("descripcion");

                Producto producto = new Producto(id, nombre, foto, precio, descripcion);
                productos.add(producto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productos;
    }

    @Override
    public int borrar(Producto producto) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setInt(1, producto.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Producto producto) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setInt(5, producto.getId());
            sentencia.setString(1, producto.getNombre());
            sentencia.setString(2, producto.getFoto());
            sentencia.setFloat(3, producto.getPrecio());
            sentencia.setString(4, producto.getDescripcion());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

}
