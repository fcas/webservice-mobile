package resources;


import dao.DAOTarefa;
import dao.DAOUsuario;
import excecoes.DadosIncompletosException;
import excecoes.UsuarioJaExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Tarefas;
import model.Usuario;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author augusto
 */

@Produces("text/html")
@Path("tarefa")  
public class RecursoTarefa {  
    
    DAOTarefa dao;
    public RecursoTarefa(){
        dao = new DAOTarefa();
    }
    
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll")  
   public List<Tarefas> getAll() {   
       
       Tarefas tarefa;
       StringBuilder string = new StringBuilder();
       List<Tarefas> result = dao.getAllTasks();
       dao.close();
       return result;
    }  
   
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getByUser")  
   public List<Tarefas> getByUser(@QueryParam("usuario") String usuario) {   
       
       Tarefas tarefa;
       StringBuilder string = new StringBuilder();
       List<Tarefas> result = dao.getTasksByUser(usuario);
       dao.close();
       return result;
    }  
      
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getFurureTasksByUser")  
   public List<Tarefas> getFurureTasksByUser(@QueryParam("usuario") String usuario) {   
       
       Tarefas tarefa;
       StringBuilder string = new StringBuilder();
       List<Tarefas> result = dao.getFutureTasksByUser(usuario);
       dao.close();
       return result;
    }  
   
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("createTarefa")  
   public String createTarefa(Tarefas tarefa) {   
       System.out.println("Recebendo WS criando tarefa");
       String id; 
       try {
            id = String.valueOf(dao.createTarefa(tarefa)); 
        }finally{
            dao.close();
        }
       return id;
    }  
   
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("updateTarefa")  
   public String updateTarefa(Tarefas tarefa) {   
       System.out.println("Recebendo WS updando Tarefa");
       String id; 
       try {
            id = String.valueOf(dao.updateTarefa(tarefa)); 
        }finally{
            dao.close();
        }
       return id;
    }  
   
   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/deleteTarefa/{id_tarefa}")
   public void deleteTarefa(@PathParam("id_tarefa") int id_tarefa) {   
       System.out.println("Recebendo WS Deletando Tarefa");
       try {
           dao.deleteTarefa(id_tarefa);
        }finally{
            dao.close();
        }
    }  
}

