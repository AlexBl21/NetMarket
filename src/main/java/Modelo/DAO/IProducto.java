/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Producto;
import java.util.List;

/**
 *
 * @author juanafanador07
 */
public interface IProducto {
    public int insertar(Producto producto, Catalogo catalogo);
    public List<Producto> consultar();
    public List<Producto> consultarPorCatalogo(Catalogo catalogo);
    public Producto consultarPorId(Producto producto);
    public int borrar(Producto producto);
    public int actualizar(Producto producto);
}
