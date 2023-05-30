/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Entity;

import java.util.List;

/**
 *
 * @author Alex Blanco
 */
public class Catalogo {
    private int id;
    private String nombre;
    private String descripcion;
    private String logo;
    private String banner;
    private String telefono;
    private String direccion;
    private String twitter;
    private String facebook;
    private String whatsapp;
    private String instagram;
    private List<Producto> productos;
    private List<Categoria> categorias;
            
    public Catalogo() {
    }

    public Catalogo(int id, String nombre, String descripcion, String logo, String banner, String telefono, String direccion, String twitter, String facebook, String whatsapp, String instagram) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.banner = banner;
        this.telefono = telefono;
        this.direccion = direccion;
        this.twitter = twitter;
        this.facebook = facebook;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
    }

    public Catalogo(int id, String nombre, String descripcion, String logo, String banner, String telefono, String direccion, String twitter, String facebook, String whatsapp, String instagram, List<Producto> productos, List<Categoria> categorias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.banner = banner;
        this.telefono = telefono;
        this.direccion = direccion;
        this.twitter = twitter;
        this.facebook = facebook;
        this.whatsapp = whatsapp;
        this.instagram = instagram;
        this.productos = productos;
        this.categorias = categorias;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", logo=" + logo + ", banner=" + banner + ", telefono=" + telefono + ", direccion=" + direccion + ", twitter=" + twitter + ", facebook=" + facebook + ", whatsapp=" + whatsapp + ", instagram=" + instagram + ", productos=" + productos + ", categorias=" + categorias + '}';
    }

}
