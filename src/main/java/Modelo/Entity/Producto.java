/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entity;

/**
 *
 * @author Alex Blanco
 */
public class Producto {
    private int id;
    private String nombre;
    private String foto;
    private float precio;
    private String descripcion;

    public Producto() {
    }

    public Producto(int id) {
        this.id = id;
    }

    public Producto(int id, String nombre, String foto, float precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", precio=" + precio + ", descripcion=" + descripcion + '}';
    }


}
