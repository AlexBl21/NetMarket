/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Api;

import Modelo.DAO.UsuarioDAO;
import Modelo.Entity.Usuario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
@Path("/apiturista")
public class UsuarioResource {
    
        UsuarioDAO usuarioDAO = new UsuarioDAO();

    @GET
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)

    public Response consultar() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = usuarioDAO.consultar();
        return Response
                .status(200)
                .entity(usuarios)
                .build();
    }

    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarId(@PathParam("id") int id) {
        Usuario usuario = new Usuario(id);
        return Response
                .status(200)
                .entity(usuarioDAO.consultarPorId(usuario))
                .build();
    }//matias

    @POST
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Usuario usuario) {
        try {
            usuarioDAO.insertar(usuario);
            return Response.status(Response.Status.CREATED).entity(usuario).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrar(@PathParam("id") int id) {
        Usuario usuario = new Usuario(id);
        int i = usuarioDAO.borrar(usuario);
        if (i == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Usuario not found")
                    .build();
        } else {
            return Response.ok("Correcto").build();
        }
    }
    
}
