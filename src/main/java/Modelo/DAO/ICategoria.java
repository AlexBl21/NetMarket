/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;
import java.util.List;

/**
 *
 * @author juanafanador07
 */
public interface ICategoria {
    public int insertar(Categoria categoria, Catalogo catalogo);
    public List<Categoria> consultar();
    public List<Categoria> consultarPorCatalogo(Catalogo catalogo);
    public List<Categoria> consultarPorProducto(Producto producto);
    public Categoria consultarPorId(Categoria categoria);
    public int borrar(Categoria categoria);
    public int actualizar(Categoria categoria);
}
