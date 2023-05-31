/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Api.DTO.ProductoDTO;
import Modelo.DAO.ProductoDAO;
import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alope
 */
@Path("/producto")
public class ProductoResource {

    ProductoDAO productoDAO = new ProductoDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar() {
        List<Producto> productos = new ArrayList<>();
        productos = productoDAO.consultar();
        return Response
                .status(200)
                .entity(productos)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") Integer id) {
        Producto producto = new Producto(id);
        return Response
                .status(200)
                .entity(productoDAO.consultarPorId(producto))
                .build();
    }
    
    @GET
    @Path("/categoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("idCategoria") Integer idCategoria) {
        Categoria categoria = new Categoria(idCategoria);
        List<Producto> productos = new ArrayList<>();
        productos = productoDAO.consultarPorCategoria(categoria);
        return Response
                .status(200)
                .entity(productos)
                .build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(ProductoDTO productoDTO) {
        try {
            productoDAO.insertar(productoDTO.getProducto(), productoDTO.getCatalogo());
            return Response.status(Response.Status.CREATED).entity(productoDTO).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Integer id) {
        Producto producto = new Producto(id);
        int i = productoDAO.borrar(producto);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Producto not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Producto producto) {
       try{
            productoDAO.actualizar(producto);
            return Response.status(Response.Status.CREATED).entity(producto).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
    
}
