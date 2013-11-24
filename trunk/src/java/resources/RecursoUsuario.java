package resources;


import dao.DAOUsuario;
import excecoes.DadosIncompletosException;
import excecoes.UsuarioJaExisteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Usuario;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;




@Produces("text/html")
@Path("usuario")  
public class RecursoUsuario {  
    static DAOUsuario dao;
    public RecursoUsuario(){
        dao = new DAOUsuario();
        dao.open();
    }
    
    //Retornar ID do usuario
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("createUsuario")  
   public String createUsuario(Usuario usuario) {   
       System.out.println("Recebendo WS criando Usuario");
        try {
            return dao.createUsuario(usuario);
        } catch (DadosIncompletosException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "-";
        } catch (UsuarioJaExisteException ex) {
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "=";
        }finally{
            dao.close();
        }   
    }  
   
   @PUT
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.TEXT_PLAIN)
   @Path("updateUsuario")  
   public String updateUsuario(Usuario usuario) {   
       System.out.println("Upando WS criando Usuario");
        try {
            return dao.updateUsuario(usuario);
        
        }finally{
            dao.close();
        }   
    }

   
   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/deleteLugar/{login}")
   public void deleteUsuario(@PathParam("id_local") String login) {   
       System.out.println("Recebendo WS Deletando Usuario");
       try {
           dao.deleteUsuario(login);
        }finally{
            dao.close();
        }
    }  
    
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll")  
   public List<Usuario> getAll() {   
       System.out.println("Recebendo WS GETALL");
       Usuario usuario;
       StringBuilder string = new StringBuilder();
       List<Usuario> lista = dao.getAllUsers();
       dao.close();
       return lista;
    }  
   
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll2")  
   public List<Usuario> getAll2() {   
       //dao = new DAOUsuario();
       //dao.open();
       Usuario usuario;
       StringBuilder string = new StringBuilder();
       List<Usuario> lista = dao.getAllUsers();
       dao.close();
       return lista;
    }  
   
   

}

