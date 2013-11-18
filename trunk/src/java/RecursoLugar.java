
import dao.DAOComentario;
import dao.DAOLugar;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
   public List<String> getAll() {   
       List<String> result = dao.listarLugares();
       dao.close();
       return result;
    }  
}
