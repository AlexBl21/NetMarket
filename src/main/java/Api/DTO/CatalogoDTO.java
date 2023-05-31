/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api.DTO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Usuario;

/**
 *
 * @author juanafanador07
 */
public class CatalogoDTO {
    private Catalogo catalogo;
    private Usuario usuario;

    public CatalogoDTO() {
    }

    public CatalogoDTO(Catalogo catalogo, Usuario usuario) {
        this.catalogo = catalogo;
        this.usuario = usuario;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
