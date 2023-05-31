/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Api.DTO.CategoriaDTO;
import Modelo.DAO.CategoriaDAO;
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
@Path("/categoria")
public class CategoriaResource {

    CategoriaDAO categoriaDAO = new CategoriaDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar() {
        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriaDAO.consultar();
        return Response
                .status(200)
                .entity(categorias)
                .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") Integer id) {
        Categoria categoria = new Categoria(id);
        return Response
                .status(200)
                .entity(categoriaDAO.consultarPorId(categoria))
                .build();
    }
    
    @GET
    @Path("/producto/{idProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("idProducto") Integer idProducto) {
        Producto producto = new Producto(idProducto);
        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriaDAO.consultarPorProducto(producto);
        return Response
                .status(200)
                .entity(categorias)
                .build();
    }



    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CategoriaDTO categoriaDTO) {
        try {
            categoriaDAO.insertar(categoriaDTO.getCategoria(), categoriaDTO.getCatalogo());
            return Response.status(Response.Status.CREATED).entity(categoriaDTO).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Integer id) {
        Categoria categoria = new Categoria(id);
        int i = categoriaDAO.borrar(categoria);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Categoria not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Categoria categoria) {
       try{
            categoriaDAO.actualizar(categoria);
            return Response.status(Response.Status.CREATED).entity(categoria).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
    
}
