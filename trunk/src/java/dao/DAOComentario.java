/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
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
    
          static Connection conn;  
          DAOLugar daoLugar;
	  private static DAOComentario instance;
	  @SuppressWarnings("unused")
	  public DAOComentario() {
              daoLugar = new DAOLugar();
	      conn = ConnectionFactory.getConnection(ConnectionFactory.MYSQL);
	  }
                             
    public int createComentarios(Comentarios comment){
        int idcomentario = 0;
            try{
                System.out.println(comment.getAutor()+ "-"+comment.getComentario()+"-"+comment.getLugar().getId_local());
                String sql = "insert into comentarios (autor, comentario, id_lugar) values(?, ? ,?)";
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                stmt.setString(1, comment.getAutor());
                stmt.setString(2, comment.getComentario());
                stmt.setInt(3, comment.getLugar().getId_local());
                
                stmt.execute();
                
                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()){
                                    idcomentario = rs.getInt(1);
                                } 
                stmt.close();
                    }catch(SQLException e){
                    e.printStackTrace();
                    }     
                return idcomentario;
    }
    
    public void deleteComentarios(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from comentarios where id_comentario = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOComentario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public int updateComentario(Comentarios comentario){
        int idcomentario = 0;
        try {
            String sql = "update comentarios set autor = ?, id_lugar = ?, comentario = ?, id_lugar = ? where id_comentario = ?";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
            stmt.setString(1, comentario.getAutor());
            stmt.setLong(2, comentario.getId());
            stmt.setString(3, comentario.getComentario());
            stmt.setInt(4, comentario.getLugar().getId_local());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    idcomentario = rs.getInt(1);
                }      
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idcomentario;
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
    
    public void close(){
            ConnectionFactory.closeConnection();
            daoLugar.close();
    }
    
        private Comentarios resultSetToComentario(ResultSet result) {
            Comentarios comentario = new Comentarios();
            try{
                comentario.setId(result.getLong(1));
                comentario.setAutor(result.getString(2));
                comentario.setComentario(result.getString(3));
                comentario.setLugar(daoLugar.getLugarById(result.getInt(4)));
            }catch(SQLException e){
                return null;
            }
	    return comentario;
    }
}
