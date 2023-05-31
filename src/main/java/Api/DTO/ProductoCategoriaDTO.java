/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api.DTO;

import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;

/**
 *
 * @author juanafanador07
 */
public class ProductoCategoriaDTO {
    private Producto producto;
    private Categoria categoria;

    public ProductoCategoriaDTO() {
    }

    public ProductoCategoriaDTO(Producto producto, Categoria categoria) {
        this.producto = producto;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }    
    
}
