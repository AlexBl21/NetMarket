/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.Entity.Categoria;
import java.util.List;

/**
 *
 * @author juanafanador07
 */
public interface ICategoria {
    public int insertar(Categoria categoria);
    public List<Categoria> consultar();
    public Categoria consultarPorId(Categoria categoria);
    public int borrar(Categoria categoria);
    public int actualizar(Categoria categoria);
}
