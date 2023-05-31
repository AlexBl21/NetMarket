/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api.DTO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Producto;

public class ProductoDTO {

    private Catalogo catalogo;
    private Producto producto;

    public ProductoDTO() {
    }

    public ProductoDTO(Catalogo catalogo, Producto producto) {
        this.catalogo = catalogo;
        this.producto = producto;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Producto getProducto() {
        return producto;

    }
}
