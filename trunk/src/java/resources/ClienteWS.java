/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comentarios;
import model.Lugar;
import model.Tarefas;
import model.Usuario;

/**
 *
 * @author augusto
 */

public class ClienteWS {
    public static void criarUsuario(){
        Usuario u = new Usuario();
            u.setLogin("adgrg");
            u.setSenha("rgea");
            u.setNome("grrgo");
            u.setCurso("EngrrrSoft");
            u.setSobreMim("Estrrdo de ewfa");
            Gson gson = new Gson();
            String requestJson  = gson.toJson(u);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/usuario/createUsuario");
            
            ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, requestJson);
            
            System.out.println("Output from Server .... \n");
	    String output = response.getEntity(String.class);
	    System.out.println(output);       
    }
    public static void criarLugar(){
            String lugar = "Banca de Cartoes";
            Gson gson = new Gson();
            String requestJson  = gson.toJson(lugar);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/lugar/createLugar");
            
            ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, requestJson);
            
            System.out.println("Output from Server .... \n");
	    String output = response.getEntity(String.class);
	    System.out.println(output);              
    }

    public static void updateLugar(){
            Lugar l = new Lugar();
            l.setNome("Lugar Atualziado");
            l.setId_local(4);
            Gson gson = new Gson();
            String requestJson  = gson.toJson(l);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/lugar/updateLugar");
            
            ClientResponse response = webResource.type("application/json")
		   .put(ClientResponse.class, requestJson);
    }    
    
    public static void updateUsuario(){
            Usuario u = new Usuario();
            u.setNome("Nome Atualziado");
            u.setLogin("firzen");
            u.setSenha("Senha");
            Gson gson = new Gson();
            String requestJson  = gson.toJson(u);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/usuario/updateUsuario");
            
            ClientResponse response = webResource.type("application/json")
		   .put(ClientResponse.class, requestJson);
    }        
    
    public static void updateTarefa(){
            Tarefas t = new Tarefas();
            t.setId(1);
            t.setData("22/11/2013");
            t.setUsuario("firzen");
            Lugar l = new Lugar();
            l.setId_local(1);
            t.setLugar(l);
            
            Gson gson = new Gson();
            String requestJson  = gson.toJson(t);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/tarefa/updateTarefa");
            
            ClientResponse response = webResource.type("application/json")
		   .put(ClientResponse.class, requestJson);
    }        
    
        public static void criarTarefa(){
            
            Tarefas t = new Tarefas();
            t.setData("22/11/2014");
            t.setUsuario("firzen");
            Lugar l = new Lugar();
            l.setId_local(1);
            t.setLugar(l);
            
            Gson gson = new Gson();
            String requestJson  = gson.toJson(t);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/tarefa/createTarefa");
            
            ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, requestJson);
    }        
    
    
        public static void criarComentario(){
            Comentarios c = new Comentarios();
            c.setComentario("Esse lugar eh otimo cara");
            Lugar l = new Lugar();
            l.setId_local(1);
            l.setNome("Biblioteca");
            c.setLugar(l);
            c.setAutor("firzen");
            
            Gson gson = new Gson();
            String requestJson  = gson.toJson(c);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/comentario/createComentario");

            ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, requestJson);
            
            System.out.println("Output from Server .... \n");
	    String output = response.getEntity(String.class);
	    System.out.println(output);           
    }
        
        public static void deletarComentario(){
            Gson gson = new Gson();
            String requestJson  = gson.toJson(1);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/comentario/deleteComentario");
            ClientResponse response =  webResource.path("1").delete(ClientResponse.class);
            //ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
                  
        }
        
        public static void deletarTarefa(){
            Gson gson = new Gson();
            String requestJson  = gson.toJson(4);
            System.out.println("Request: "+requestJson);
            Client client = Client.create();
            WebResource webResource = client
		   .resource("http://localhost:8080/WebServiceMobile/resources/tarefa/deleteTarefa");
            ClientResponse response =  webResource.path("4").delete(ClientResponse.class);
            //ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
                  
        }
        

    public static void main(String[] args) {
        criarTarefa();
        //criarUsuario();
            //updateUsuario();
            //updateTarefa();
        //updateLugar();
            //deletarTarefa();
            //deletarComentario();
            //criarLugar();
            //criarComentario();
            
            /*
         try {
             
             
            URL url = new URL("http://localhost:8080/WebServiceMobile/resources/usuario/createUsuario"); 
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));
            
            DataOutputStream stream = new DataOutputStream(con.getOutputStream());
            stream.write(requestJson.getBytes("UTF-8"));
            stream.flush();
            stream.close();
            
             con.connect();
            
            
             con.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        */
    }
}
