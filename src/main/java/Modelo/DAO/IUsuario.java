/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Usuario;
import java.util.List;

/**
 *
 * @author juanafanador07
 */
public interface IUsuario {
    public int insertar(Usuario usuario);
    public List<Usuario> consultar();
    public Usuario consultarPorId(Usuario usuario);
    public int borrar(Usuario usuario);
    public int actualizar(Usuario usuario);
}
