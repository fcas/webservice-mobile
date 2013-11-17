/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comentarios;
import model.Tarefas;
import model.Usuario;

/**
 *
 * @author augusto
 */
public class DAOComentario {
    
              Connection conn;  
              DAOLugar daoLugar;
	  private static DAOComentario instance;
	  @SuppressWarnings("unused")
	  private DAOComentario() {
              daoLugar = DAOLugar.getInstance();
	      conn = ConnectionFactory.getConnection(ConnectionFactory.MYSQL);
	  }
               
          public static DAOComentario getInstance(){
            if(instance == null){
                instance = new DAOComentario();
            }
            return instance;
          }
              
    public boolean createComentarios(Comentarios comment){
                            try{
                                String sql = "insert into comentarios (autor, comentario, id_lugar) values(?, ? ,?)";
                                PreparedStatement stmt = conn.prepareStatement(sql); 
                                stmt.setString(1, comment.getAutor());
                                stmt.setString(2, comment.getComment());
                                stmt.setInt(3, comment.getLugar().getId_local());
                                stmt.execute();
                                stmt.close();
                                return true;
                            }catch(SQLException e){
                                e.printStackTrace();
                                return false;
                            }        
    }
    
    public void deleteComentarios(Comentarios comment) {
        
    }
    
    public List<Comentarios> getAllComments() {
            List<Comentarios> comentarios = new ArrayList<Comentarios>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from comentarios");
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Comentarios comentario = resultSetToComentario(rs);
                    comentarios.add(comentario);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return comentarios;  
    }
    
    public List<Comentarios> getComentariosByLugar(String lugar){
            List<Comentarios> comentarios = new ArrayList<Comentarios>();
            
            ResultSet rs;
            try {
        
                int idLugar = daoLugar.idLugar(lugar);
                PreparedStatement ps = conn.prepareStatement("select * from comentarios where id_lugar = ?");
                ps.setInt(1, idLugar);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Comentarios comentario = resultSetToComentario(rs);
                    comentarios.add(comentario);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return comentarios;          
    }
    
    public boolean updateComentario(Comentarios comentario){
                    try{
                                String sql = "update comentarios set autor = ?, comentario = ?, id_lugar = ? where id_comentario = "+comentario.getId();
                                PreparedStatement stmt = conn.prepareStatement(sql); 
                                stmt.setString(1, comentario.getAutor());
                                stmt.setString(2, comentario.getComment());
                                stmt.setInt(3, comentario.getLugar().getId_local());
                                stmt.executeUpdate();
                                System.out.println("lol");
                                stmt.close();
                                return true;
                            }catch(SQLException e){
                                e.printStackTrace();
                                return false;
                            }        
    }
    
    
    public void close(){
        try {
            conn.close();
            daoLugar.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        private Comentarios resultSetToComentario(ResultSet result) {
            Comentarios comentario = new Comentarios();
            try{
                comentario.setId(result.getLong(1));
                comentario.setAutor(result.getString(2));
                comentario.setComment(result.getString(3));
                comentario.setLugar(daoLugar.getLugarById(result.getInt(4)));
            }catch(SQLException e){
                return null;
            }
	    return comentario;
    }
}
