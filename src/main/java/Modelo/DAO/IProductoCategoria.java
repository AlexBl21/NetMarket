/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;

/**
 *
 * @author juanafanador07
 */
public interface IProductoCategoria {
    public int insertar(Producto producto, Categoria categoria);
    public int borrar(Producto producto, Categoria categoria);
}
