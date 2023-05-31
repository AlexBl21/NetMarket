/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Api.DTO.ProductoCategoriaDTO;
import Modelo.DAO.ProductoCategoriaDAO;
import Modelo.Entity.Categoria;
import Modelo.Entity.Producto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author alope
 */
@Path("/productocategoria")
public class ProductoCategoriaResource {

    ProductoCategoriaDAO productoCategoriaDAO = new ProductoCategoriaDAO();

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(ProductoCategoriaDTO productoCategoriaDTO) {
        try {
            productoCategoriaDAO.insertar(productoCategoriaDTO.getProducto(), productoCategoriaDTO.getCategoria());
            return Response.status(Response.Status.CREATED).entity(productoCategoriaDTO).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idProducto}/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("idProducto") Integer idProducto, @PathParam("idCategoria") Integer idCategoria) {
        Producto producto = new Producto(idProducto);
        Categoria categoria = new Categoria(idCategoria);
        int i = productoCategoriaDAO.borrar(producto, categoria);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Producto or Categoria not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    
}
