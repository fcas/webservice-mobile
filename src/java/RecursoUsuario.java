
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
import org.codehaus.jettison.json.JSONObject;




@Produces("text/html")
@Path("usuario")  
public class RecursoUsuario {  
    
    static DAOUsuario dao;
    public RecursoUsuario(){
        dao = new DAOUsuario();
        dao.open();
    }
    
  /* @GET  
   @Produces(MediaType.TEXT_PLAIN)
   @Path("getAll")  
   public String getAll() {   
              
        JSONArray jsonarr = new JSONArray();

        

       dao = DAOUsuario.getInstance();
       Usuario usuario;
       StringBuilder string = new StringBuilder();
       List<Usuario> result = dao.getAllUsers();
       if(result != null)
       for(int i = 0; i < result.size(); i++){
           usuario =  result.get(i);
           Map<String, String> map = new HashMap<String, String>();
            map.put("login", usuario.getLogin());
            map.put("senha", usuario.getSenha());
            map.put("nome", usuario.getNome());
            map.put("curso", usuario.getCurso());
            map.put("sobre", usuario.getSobreMim());
            jsonarr.put(map);
           /*string.append(string);
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
       //dao.close();
       return jsonarr.toString();
    }*/
   
   @GET  
   @Produces(MediaType.APPLICATION_JSON)
   @Path("getAll")  
   public List<Usuario> getAll() {   
       //dao = new DAOUsuario();
       //dao.open();
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

