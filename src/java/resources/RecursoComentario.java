package resources;


import dao.DAOComentario;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Comentarios;
import model.Tarefas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author augusto
 */

@Produces("text/html")
@Path("comentario")  
public class RecursoComentario {
    DAOComentario dao;
    public RecursoComentario(){
        dao = new DAOComentario();
    }
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getByLugar")  
   public List<Comentarios> getByLugar(@QueryParam("lugar") String lugar) {   
       
       Tarefas tarefa;
       StringBuilder string = new StringBuilder();
       List<Comentarios> result = dao.getComentariosByLugar(lugar);
       dao.close();
       return result;
    }  
   
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll")  
   public List<Comentarios> getAll() {   
       System.out.println("Recebendo WS getall Comentario");
       StringBuilder string = new StringBuilder();
       List<Comentarios> result = dao.getAllComments();
       dao.close();
       return result;
    }  
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("createComentario")  
   public int createComentario(Comentarios comentario) {   
       System.out.println("Recebendo WS criando Comentario");
       int id; 
       try {
            id = dao.createComentarios(comentario); 
        }finally{
            dao.close();
        }
       return id;
    }  
   
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("updateComentario")  
   public String updateComentario(Comentarios comentario) {   
       System.out.println("Recebendo WS upando Comentario");
       String id; 
       try {
            id = String.valueOf(dao.updateComentario(comentario)); 
        }finally{
            dao.close();
        }
       return id;
    }  
   
   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   //@Path("deleteComentario")  
   @Path("/deleteComentario/{idComentario}")
   public void deleteComentario(@PathParam("idComentario") int idComentario) {   
       System.out.println("Recebendo WS deletando Comentario");
       String result; 
       try {
           dao.deleteComentarios(idComentario);
        }finally{
            dao.close();
        }
    }  
}
