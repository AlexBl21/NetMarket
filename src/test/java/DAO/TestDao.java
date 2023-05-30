/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.DAO.CatalogoDAO;
import Modelo.DAO.CategoriaDAO;
import Modelo.DAO.ProductoCategoriaDAO;
import Modelo.DAO.ProductoDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Entity.Catalogo;
import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;
import Modelo.Entity.Usuario;
import java.util.Random;

/**
 *
 * @author juanafanador07
 */
public class TestDao {
    
    public static int randomID(){
        return new Random().nextInt(999999);
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CatalogoDAO catalogoDAO = new CatalogoDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ProductoCategoriaDAO productoCategoriaDAO = new ProductoCategoriaDAO();
    
        // test usuarios
        Usuario usuario = new Usuario(randomID(), "nombre de usuario", "correo electronico");
        System.out.println("USUARIO LOCAL: " + usuario);
        
        usuarioDAO.insertar(usuario);
        
        // mostrar todos
        System.out.println(usuarioDAO.consultar()); 
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println("USUARIO DB: " + usuario);

        
        usuario.setNombre("nombre 2");
        usuarioDAO.actualizar(usuario);
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println("USUARIO DB: " + usuario);
        
        // test catalogos
        Catalogo catalogo = new Catalogo(randomID(), "nombre", "descripcion", "logo", "banner", "telefono", "direccion", "twitter", "facebook", "whatsapp", "instagram");
        catalogoDAO.insertar(catalogo, usuario);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
        
        catalogo.setNombre("nombre 2");
        catalogoDAO.actualizar(catalogo);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
      
        System.out.println("Borrar catalogo:" + catalogoDAO.borrar(catalogo));
        catalogoDAO.insertar(catalogo, usuario);
        
        // test productos
        Producto producto = new Producto(randomID(), "nombre", "foto", 0, "descripcion");
        productoDAO.insertar(producto, catalogo);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
        
        producto.setNombre("nombre 2");
        productoDAO.actualizar(producto);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
        
        System.out.println("Borrar producto:" + productoDAO.borrar(producto));
        productoDAO.insertar(producto, catalogo);
        
        // test categorias
        Categoria categoria = new Categoria(randomID(), "nombre");
        categoriaDAO.insertar(categoria, catalogo);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
        
        categoria.setNombre("nombre 2");
        categoriaDAO.actualizar(categoria);
        
        usuario = usuarioDAO.consultarPorId(usuario);
        System.out.println(usuario);
        
        System.out.println("Borrar categoria:" + categoriaDAO.borrar(categoria));
        categoriaDAO.insertar(categoria, catalogo);

        // test producto categoria
        System.out.println("Insertar ProductoCategoria: " + productoCategoriaDAO.insertar(producto, categoria));
        System.out.println("Borrar ProductoCategoria: " + productoCategoriaDAO.borrar(producto, categoria));
        productoCategoriaDAO.insertar(producto, categoria);
        
        System.out.println(productoDAO.consultarPorCategoria(categoria));
        System.out.println(categoriaDAO.consultarPorProducto(producto));
        
        //System.out.println(usuarioDAO.borrar(usuario));
    }
}
