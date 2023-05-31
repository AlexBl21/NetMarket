/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Usuario;
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
public class UsuarioDAO implements IUsuario {

    final static String SQL_CONSULTAR = "SELECT * FROM usuario";
    final static String SQL_INSERTAR = "INSERT INTO usuario(id,nombre,correo) Value(?,?,?)";
    final static String SQL_BORRAR = "DELETE FROM usuario WHERE id = ?";
    final static String SQL_CONSULTAR_ID = "SELECT * FROM usuario WHERE id=?";
    final static String SQL_ACTUALIZAR = "UPDATE usuario SET nombre=?, correo=? WHERE id=?";

    @Override
    public int insertar(Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setInt(1, usuario.getId());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getCorreo());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<Usuario> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Usuario> usuarios = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR); //esto sirve para que prepare la tabla de datos
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String correo = resultado.getString("correo");

                Usuario usuario = new Usuario(id, nombre, correo);

                CatalogoDAO catalogoDAO = new CatalogoDAO();
                List<Catalogo> catalogos = catalogoDAO.consultarPorUsuario(usuario);
                usuario.setCatalogos(catalogos);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario consultarPorId(Usuario usuario) {
        Connection connection = null;//conecta
        PreparedStatement sentencia = null;//prepara la sentencia
        ResultSet resultado = null;
        Usuario rUsuario = null;//el resultado a sacar

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY); //esto sirve para que prepare la tabla de datos(lo de resultset garantiza que devuelva un solo resultadoo)
            sentencia.setInt(1, usuario.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            resultado.absolute(1);

            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String correo = resultado.getString("correo");

            rUsuario = new Usuario(id, nombre, correo);

            CatalogoDAO catalogoDAO = new CatalogoDAO();
            List<Catalogo> catalogos = catalogoDAO.consultarPorUsuario(rUsuario);
            rUsuario.setCatalogos(catalogos);

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rUsuario;
    }

    @Override
    public int borrar(Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setInt(1, usuario.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public int actualizar(Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setInt(3, usuario.getId());
            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getCorreo());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

}
