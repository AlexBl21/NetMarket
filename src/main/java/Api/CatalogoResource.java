/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Api.DTO.CatalogoDTO;
import Modelo.DAO.CatalogoDAO;
import Modelo.Entity.Catalogo;
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

@Path("/catalogo")
public class CatalogoResource {

    CatalogoDAO catalogoDAO = new CatalogoDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar() {
        List<Catalogo> catalogos = new ArrayList<>();
        catalogos = catalogoDAO.consultar();
        return Response
                .status(200)
                .entity(catalogos)
                .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") Integer id) {
        Catalogo catalogo = new Catalogo(id);
        return Response
                .status(200)
                .entity(catalogoDAO.consultarPorId(catalogo))
                .build();
    }
 
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(CatalogoDTO catalogoDTO) {
        try {
            catalogoDAO.insertar(catalogoDTO.getCatalogo(), catalogoDTO.getUsuario());
            return Response.status(Response.Status.CREATED).entity(catalogoDTO).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") Integer id) {
        Catalogo catalogo = new Catalogo(id);
        int i = catalogoDAO.borrar(catalogo);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Catalogo not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    
    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(Catalogo catalogo) {
       try{
            catalogoDAO.actualizar(catalogo);
            return Response.status(Response.Status.CREATED).entity(catalogo).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        } 
    }
    
}
