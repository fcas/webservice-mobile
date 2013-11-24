package resources;


import dao.DAOComentario;
import dao.DAOLugar;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Comentarios;
import model.Lugar;
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
@Path("lugar")  
public class RecursoLugar {
    DAOLugar dao;
    public RecursoLugar(){
        dao = new DAOLugar();
    }
    
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll")  
   public List<Lugar> getAll() {   
       List<Lugar> result = dao.listarLugares();
       dao.close();
       return result;
    }  
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("createLugar")  
   public String createLugar(String lugar) {   
       System.out.println("Recebendo WS criando Lugar");
       String id; 
       try {
            id = String.valueOf(dao.salvarLugar(lugar)); 
        }finally{
            dao.close();
        }
       return id;
    }  
         
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   //@Produces(MediaType.TEXT_PLAIN)
   @Path("updateLugar")  
   public String updateLugar(Lugar lugar) {   
       System.out.println("Recebendo WS atualizando Lugar");
       String id; 
       try {
            id = String.valueOf(dao.updateLugar(lugar)); 
        }finally{
            dao.close();
        }
       return id;
    }  
}
