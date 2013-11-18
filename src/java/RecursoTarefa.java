
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
}

