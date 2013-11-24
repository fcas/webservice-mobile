package resources;


import javax.ws.rs.*;




@Produces("text/html")
@Path("teste")  
public class RecursoTeste {  
   @GET  
   public String teste(/*@QueryParam("nome") String nome*/) {   
      return "Hello World";
    }  
}

