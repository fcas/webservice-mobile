
import dao.DAOUsuario;
import excecoes.DadosIncompletosException;
import excecoes.UsuarioJaExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Usuario;




@Produces("text/html")
@Path("usuario")  
public class RecursoUsuario {  
    
    DAOUsuario dao;
    public RecursoUsuario(){
        
    }
    
   @GET  
   @Produces(MediaType.TEXT_PLAIN)
   @Path("getAll")  
   public String getAll() {   
       dao = DAOUsuario.getInstance();
       Usuario usuario;
       StringBuilder string = new StringBuilder();
       List<Usuario> result = dao.getAllUsers();
       
       for(int i = 0; i < result.size(); i++){
           usuario =  result.get(i);
           string.append(usuario.getLogin());
           string.append("--");
           string.append(usuario.getSenha());
           string.append("--");
           string.append(usuario.getNome());
           string.append("--");
           string.append(usuario.getCurso());
           string.append("--");
           string.append(usuario.getSobreMim());    
           string.append(System.getProperty("line.separator"));
       }
       dao.close();
       return string.toString();
    }  
   
   @GET  
   @Produces(MediaType.APPLICATION_XML)
   @Path("getAll2")  
   public List<Usuario> getAll2() {   
       dao = DAOUsuario.getInstance();
       Usuario usuario;
       StringBuilder string = new StringBuilder();
       List<Usuario> lista = dao.getAllUsers();
       dao.close();
       return lista;
    }  
   
   @POST
   @Consumes(value={"text/xml", "application/json"})
   @Produces("text/plain")
   @Path("createUsuario")  
   public String createUsuario(Usuario usuario) {   
       dao = DAOUsuario.getInstance();
        try {
            dao.createUsuario(usuario);
            return "usuario adicionado";
        } catch (DadosIncompletosException ex) {
            
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "usuario nao adicionado";
        } catch (UsuarioJaExisteException ex) {
            
            Logger.getLogger(RecursoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return "usuario nao adicionado";
        }finally{
            dao.close();
        }
       
    }  
}

