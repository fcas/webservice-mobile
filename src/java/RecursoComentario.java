
import dao.DAOComentario;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
       
       Tarefas tarefa;
       StringBuilder string = new StringBuilder();
       List<Comentarios> result = dao.getAllComments();
       dao.close();
       return result;
    }  
}
