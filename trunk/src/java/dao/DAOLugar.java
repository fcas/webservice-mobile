/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Statement;
import excecoes.DadosIncompletosException;
import excecoes.UsuarioJaExisteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lugar;
import model.Usuario;

/**
 *
 * @author augusto
 */
public class DAOLugar {
          static Connection conn;  
	  private static DAOLugar instance;
	  @SuppressWarnings("unused")
	  public DAOLugar() {
	     conn = ConnectionFactory.getConnection(ConnectionFactory.MYSQL);
	  }
                         
         public List<Lugar> listarLugares() {
            List<Lugar> lugares = new ArrayList<Lugar>();
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from lugar");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lugar l = new Lugar();
                    l.setId_local(rs.getInt(1));
                    l.setNome(rs.getString(2));
                    lugares.add(l);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return lugares;
	  }
         
         public int idLugar(String lugar){
            int id = -1;
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("Select * from lugar where nome = ?");
                ps.setString(1, lugar);
                rs = ps.executeQuery();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                }
                return id;
         }
         
         public Lugar getLugarById(int id){
            Lugar lugar = new Lugar();
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("Select * from Lugar where id_lugar = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lugar.setId_local((rs.getInt(1)));
                    lugar.setNome(rs.getString(2));
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                }
                return lugar;
         }
          
         public int salvarLugar(String lugar){
            int idlugar = 0;
             try {
                String sql = "insert into lugar (nome) values(?)";
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);       
                stmt.setString(1, lugar);
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()){
                                    idlugar = rs.getInt(1);
                                } 
                stmt.close();
            } catch (SQLException ex) {
                 System.out.println("SQL EXCEPTION!!!!!NAO PODE SALVAR "+lugar);
            }
             return idlugar;
         }
          
          public void close(){
                    ConnectionFactory.closeConnection();

          }

    public void deleteLugar(int id_local) {
       try {
            PreparedStatement ps = conn.prepareStatement("delete from lugar where id_lugar = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, id_local);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int updateLugar(Lugar lugar) {
        int idlugar = 0;
        try {
            String sql = "update lugar set nome = ? where id_lugar = ?";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
            stmt.setString(1, lugar.getNome());
            stmt.setInt(2, lugar.getId_local());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    idlugar = rs.getInt(1);
                }             
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idlugar;
    }
    
}
