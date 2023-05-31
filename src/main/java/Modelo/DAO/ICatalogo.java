/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Catalogo;
import Modelo.Entity.Usuario;
import java.util.List;

/**
 *
 * @author juanafanador07
 */
public interface ICatalogo {
    public int insertar(Catalogo catalogo, Usuario usuario);
    public List<Catalogo> consultar();
    public List<Catalogo> consultarPorUsuario(Usuario usuario);
    public Catalogo consultarPorId(Catalogo catalogo);
    public int borrar(Catalogo catalogo);
    public int actualizar(Catalogo catalogo);
}
