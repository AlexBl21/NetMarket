/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Producto;
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
public class CatalogoDAO implements ICatalogo {

    final static String SQL_INSERTAR = "INSERT INTO catalogo(id,nombre,descripcion,logo,banner,telefono,direccion,twitter,facebook,whatsapp,instagram, id_usuario) Value(?,?,?,?,?,?,?,?,?,?,?,?)";
    final static String SQL_CONSULTAR = "SELECT * FROM catalogo";
    final static String SQL_CONSULTAR_ID = "SELECT * FROM catalogo WHERE id=?";
    final static String SQL_CONSULTAR_USUARIO = "SELECT * FROM catalogo WHERE id_usuario = ?";
    final static String SQL_BORRAR = "DELETE FROM catalogo WHERE id = ?";
    final static String SQL_ACTUALIZAR = "UPDATE catalogo SET nombre=?, descripcion=?, logo=?, banner=?, telefono=?, direccion=?, twitter=?, facebook=?, whatsapp=?, instagram=? WHERE id=?";

    @Override
    public int insertar(Catalogo catalogo, Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_INSERTAR);
            sentencia.setInt(1, catalogo.getId());
            sentencia.setString(2, catalogo.getNombre());
            sentencia.setString(3, catalogo.getDescripcion());
            sentencia.setString(4, catalogo.getLogo());
            sentencia.setString(5, catalogo.getBanner());
            sentencia.setString(6, catalogo.getTelefono());
            sentencia.setString(7, catalogo.getDireccion());
            sentencia.setString(8, catalogo.getTwitter());
            sentencia.setString(9, catalogo.getFacebook());
            sentencia.setString(10, catalogo.getWhatsapp());
            sentencia.setString(11, catalogo.getInstagram());
            sentencia.setInt(12, usuario.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

    @Override
    public List<Catalogo> consultar() {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Catalogo> catalogos = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR); //esto sirve para que prepare la tabla de datos
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                String logo = resultado.getString("logo");
                String banner = resultado.getString("banner");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String twitter = resultado.getString("twitter");
                String facebook = resultado.getString("facebook");
                String whatsapp = resultado.getString("whatsapp");
                String instagram = resultado.getString("instagram");

                Catalogo catalogo = new Catalogo(id, nombre, descripcion, logo, banner, telefono, direccion, twitter, facebook, whatsapp, instagram);

                ProductoDAO productoDAO = new ProductoDAO();
                List<Producto> productos = productoDAO.consultarPorCatalogo(catalogo);

                catalogo.setProductos(productos);

                catalogos.add(catalogo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return catalogos;
    }

    @Override
    public List<Catalogo> consultarPorUsuario(Usuario usuario) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        List<Catalogo> catalogos = new ArrayList();

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_USUARIO); //esto sirve para que prepare la tabla de datos
            sentencia.setInt(1, usuario.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            while (resultado.next()) {//recorre los registros
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                String logo = resultado.getString("logo");
                String banner = resultado.getString("banner");
                String telefono = resultado.getString("telefono");
                String direccion = resultado.getString("direccion");
                String twitter = resultado.getString("twitter");
                String facebook = resultado.getString("facebook");
                String whatsapp = resultado.getString("whatsapp");
                String instagram = resultado.getString("instagram");

                Catalogo catalogo = new Catalogo(id, nombre, descripcion, logo, banner, telefono, direccion, twitter, facebook, whatsapp, instagram);

                ProductoDAO productoDAO = new ProductoDAO();
                List<Producto> productos = productoDAO.consultarPorCatalogo(catalogo);

                catalogo.setProductos(productos);

                catalogos.add(catalogo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return catalogos;
    }

    @Override
    public Catalogo consultarPorId(Catalogo catalogo) {
        Connection connection = null;//conecta
        PreparedStatement sentencia = null;//prepara la sentencia
        ResultSet resultado = null;
        Catalogo rCatalogo = null;//el resultado a sacar

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_CONSULTAR_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.TYPE_FORWARD_ONLY); //esto sirve para que prepare la tabla de datos(lo de resultset garantiza que devuelva un solo resultadoo)
            sentencia.setInt(1, catalogo.getId());
            resultado = sentencia.executeQuery();//toma la forma del registro
            resultado.absolute(1);

            int id = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String descripcion = resultado.getString("descripcion");
            String logo = resultado.getString("logo");
            String banner = resultado.getString("banner");
            String telefono = resultado.getString("telefono");
            String direccion = resultado.getString("direccion");
            String twitter = resultado.getString("twitter");
            String facebook = resultado.getString("facebook");
            String whatsapp = resultado.getString("whatsapp");
            String instagram = resultado.getString("instagram");

            rCatalogo = new Catalogo(id, nombre, descripcion, logo, banner, telefono, direccion, twitter, facebook, whatsapp, instagram);

            ProductoDAO productoDAO = new ProductoDAO();
            List<Producto> productos = productoDAO.consultarPorCatalogo(catalogo);

            rCatalogo.setProductos(productos);
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {//este bloque cierra lo que se abrió.no olvidar que se cierra en sentido opuestto al que se abre
            try {
                BaseDeDatos.close(resultado);
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rCatalogo;
    }

    @Override
    public int borrar(Catalogo catalogo) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {            
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_BORRAR);
            sentencia.setInt(1, catalogo.getId());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }
    
    @Override
    public int actualizar(Catalogo catalogo) {
        Connection connection = null;
        PreparedStatement sentencia = null;
        int resultado = 0;

        try {
            connection = BaseDeDatos.getConnection();
            sentencia = connection.prepareStatement(SQL_ACTUALIZAR);
            sentencia.setInt(11, catalogo.getId());
            sentencia.setString(1, catalogo.getNombre());
            sentencia.setString(2, catalogo.getDescripcion());
            sentencia.setString(3, catalogo.getLogo());
            sentencia.setString(4, catalogo.getBanner());
            sentencia.setString(5, catalogo.getTelefono());
            sentencia.setString(6, catalogo.getDireccion());
            sentencia.setString(7, catalogo.getTwitter());
            sentencia.setString(8, catalogo.getFacebook());
            sentencia.setString(9, catalogo.getWhatsapp());
            sentencia.setString(10, catalogo.getInstagram());
            resultado = sentencia.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                BaseDeDatos.close(sentencia);
                BaseDeDatos.close(connection);
            } catch (SQLException ex) {
                Logger.getLogger(CatalogoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultado;
    }

}
