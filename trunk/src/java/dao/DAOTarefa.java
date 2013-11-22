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
import model.Tarefas;
import model.Usuario;

/**
 *
 * @author augusto
 */
public class DAOTarefa {
    private static Connection conn;  
    private static DAOTarefa instance;
    private DAOLugar daoLugar;
    	  public DAOTarefa() {
	      conn = ConnectionFactory.getConnection(ConnectionFactory.MYSQL);
              daoLugar = new DAOLugar();
	  }
              
     public int createTarefa(Tarefas tarefa){
         int idTarefa = 0;
              try{
                   String sql = "insert into tarefas (usuario, id_lugar, data, horario, descricao) values(?, ? ,?, ?, ?)";
                   PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                   stmt.setString(1, tarefa.getUsuario());
                   stmt.setInt(2, tarefa.getLugar().getId_local());
                   stmt.setString(3, DataCalculos.visaoToBanco(tarefa.getData()));
                   stmt.setString(4, tarefa.getHorario());
                   stmt.setString(5 ,tarefa.getDescricao());
                   stmt.execute();
                   ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()){
                                    idTarefa = rs.getInt(1);
                                } 
                   stmt.close();
                   }catch(SQLException e){
                        e.printStackTrace();
                   }
                   return idTarefa;
     }
     
     public int updateTarefa(Tarefas tarefa){
         int idtarefa = 0;
        try {
            String sql = "update tarefas set usuario = ?, id_lugar = ?, data = ?, horario = ?, descricao = ? where id_tarefa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
            stmt.setString(1, tarefa.getUsuario());
            stmt.setInt(2, tarefa.getLugar().getId_local());
            stmt.setString(3, DataCalculos.visaoToBanco(tarefa.getData()));
            stmt.setString(4, tarefa.getHorario());
            stmt.setString(5 , tarefa.getDescricao());
            stmt.setLong(6 , tarefa.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    idtarefa = rs.getInt(1);
                }      
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idtarefa;
     }
  	  public void deleteTarefa(int id) {
            try {
                    PreparedStatement ps = conn.prepareStatement("delete from tarefas where id_tarefa = ?", Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, id);
                    ps.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOTarefa.class.getName()).log(Level.SEVERE, null, ex);
                }
	  }
     
     public List<Tarefas> getFutureTasksByUser(String userLogin){
          List<Tarefas> tarefas = new ArrayList<Tarefas>();
            ResultSet rs;
            try {
                String now = DataCalculos.dataHoraAtual();
		String[] dataHora = new String[2];
                dataHora = now.split(" ");
                PreparedStatement ps = conn.prepareStatement("select * from tarefas where usuario = ? and Data >= ? order by Data, Horario");
                ps.setString(1, userLogin);
                ps.setString(2, dataHora[0]);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Tarefas tarefa = resultSetToTarefa(rs);
                    tarefas.add(tarefa);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return tarefas;             
     }
     
     public List<Tarefas> getAllTasks() {
            List<Tarefas> tarefas = new ArrayList<Tarefas>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from tarefas");
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Tarefas tarefa = resultSetToTarefa(rs);
                    tarefas.add(tarefa);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return tarefas;         
     }
             
     public List<Tarefas> getTasksByUser(String userLogin){
          List<Tarefas> tarefas = new ArrayList<Tarefas>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from tarefas where usuario = ?");
                ps.setString(1, userLogin);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    Tarefas tarefa = resultSetToTarefa(rs);
                    tarefas.add(tarefa);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return tarefas;    
     }    
     
	  private Tarefas resultSetToTarefas(ResultSet result) {
	    Tarefas tarefa = new Tarefas();
            try{
                tarefa.setId(result.getLong(1));
                tarefa.setUsuario(result.getString(2));
                tarefa.setLugar(daoLugar.getLugarById(result.getInt(2)));
                tarefa.setData(result.getString(4));
                tarefa.setHorario(result.getString(5));
            }catch(SQLException e){
                return null;
            }
	    return tarefa;
	  }
          
          public void close(){
                    ConnectionFactory.closeConnection();
                    daoLugar.close();
          }

    private Tarefas resultSetToTarefa(ResultSet result) {
            Tarefas tarefa = new Tarefas();
            try{
                tarefa.setId(result.getLong(1));
                tarefa.setUsuario(result.getString(2));
                tarefa.setLugar(daoLugar.getLugarById(result.getInt(3)));
                tarefa.setData(result.getString(4));
                tarefa.setHorario(result.getString(5));
                tarefa.setDescricao(result.getString(6));
            }catch(SQLException e){
                return null;
            }
	    return tarefa;
    }
}
