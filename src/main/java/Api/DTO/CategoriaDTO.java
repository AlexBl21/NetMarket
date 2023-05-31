/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api.DTO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Categoria;

/**
 *
 * @author juanafanador07
 */
public class CategoriaDTO {
    private Categoria categoria;
    private Catalogo catalogo;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria, Catalogo catalogo) {
        this.categoria = categoria;
        this.catalogo = catalogo;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
