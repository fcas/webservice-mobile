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
                         
         public List<String> listarLugares() {
            List<String> labels = new ArrayList<String>();
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from lugar");
                rs = ps.executeQuery();
                while (rs.next()) {
                    labels.add(rs.getString(2));
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return labels;
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
          
         public void salvarLugar(String lugar){
            try {
                String sql = "insert into lugar (nome) values(?)";
                PreparedStatement stmt = conn.prepareStatement(sql);       
                stmt.setString(1, lugar);
                stmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(DAOLugar.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
          
          public void close(){
                    ConnectionFactory.closeConnection();

          }          
}
