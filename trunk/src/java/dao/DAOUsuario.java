package dao;

import com.mysql.jdbc.Statement;
import java.util.ArrayList;
import java.util.List;
import excecoes.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class DAOUsuario {
          Connection conn;  
	  private static DAOUsuario instance;
	  private String[] allColumns = { Usuario.COLUNA_LOGIN, Usuario.COLUNA_SENHA,
			  Usuario.COLUNA_NOME , Usuario.COLUNA_CURSO, Usuario.COLUNA_SOBRE};
	  @SuppressWarnings("unused")

          public void open(){
              conn = ConnectionFactory.getConnection(ConnectionFactory.MYSQL);
          }
         public List<Usuario> getAllUsers() {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            ResultSet rs;
            try {
        
                PreparedStatement ps = conn.prepareStatement("select * from usuario");
                rs = ps.executeQuery();
               
                while (rs.next()) {
                    Usuario usuario = resultSetToUsuario(rs);
                    usuarios.add(usuario);
                }
                ps.close();
                
                }catch(SQLException e){
                    e.printStackTrace();
                    return null;
                }
                return usuarios;
	  }
         
          public Usuario autenticar(String login, String senha){
                  Usuario user = new Usuario();
                  ResultSet rs;              
                  try {
                        PreparedStatement ps = conn.prepareStatement("Select * from usuario where login = ? and senha = ?");
                        ps.setString(1, login);
                        ps.setString(2, senha);
                        rs = ps.executeQuery();
                        
                        if(rs.next()){
                            user.setLogin(rs.getString(1));
                            user.setSenha(rs.getString(2));
                            user.setNome(rs.getString(3));
                            user.setCurso(rs.getString(4));
                            user.setSobreMim(rs.getString(5));     
                        }
                        ps.close();
                  }catch(SQLException e){
                      e.printStackTrace();
                      return null;
                  }
		return user;
	  }
          
          
          public Usuario getUsuarioByLogin(String login){
                  Usuario user = new Usuario();
                  ResultSet rs;              
                  try {
                        PreparedStatement ps = conn.prepareStatement("Select * from Usuario where login = '"+login+"'");
                        //ps.setString(1, login);
                        rs = ps.executeQuery();
                        if(rs.next()){
                            user.setLogin(rs.getString(1));
                            user.setSenha(rs.getString(2));
                            user.setNome(rs.getString(3));
                            user.setCurso(rs.getString(4));
                            user.setSobreMim(rs.getString(5));      
                        }else{
                            user = null;
                        }
                        ps.close();
                  }catch(SQLException e){
                      e.printStackTrace();
                      return null;
                  }
		return user;
	  }
	      
	  public String createUsuario(Usuario usuario) throws DadosIncompletosException, UsuarioJaExisteException {
              String result = "";
              if(!usuario.getNome().equals("") && !usuario.getSenha().trim().equals("") && !usuario.getLogin().trim().equals("")){            
                  if(getUsuarioByLogin(usuario.getLogin()) == null){
                            try{
                                String sql = "insert into usuario ("+Usuario.COLUNA_LOGIN+","+Usuario.COLUNA_SENHA+","+Usuario.COLUNA_NOME+","+Usuario.COLUNA_CURSO+", "+Usuario.COLUNA_SOBRE+") values(?, ? ,?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
                                stmt.setString(1, usuario.getLogin());
                                stmt.setString(2, usuario.getSenha());
                                stmt.setString(3, usuario.getNome());
                                stmt.setString(4, usuario.getCurso());
                                stmt.setString(5 , usuario.getSobreMim());
                                stmt.execute();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()){
                                    result = rs.getString(1);
                                }
                                                
                                stmt.close();
                                       
                            }catch(SQLException e){
                                e.printStackTrace();
                                result = "";
                            }
			}
		    else{
		    	throw new UsuarioJaExisteException();
		    }
		  }else{
			  throw new DadosIncompletosException();
		  }
		  return result;
	  }
	  
          public String updateUsuario(Usuario usuario) {
          String login = "";
          try{
          String sql = "update usuario set senha = ?, nome = ?, curso = ?, sobre = ? where login = ?";
              PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
              stmt.setString(1, usuario.getSenha());
              stmt.setString(2, usuario.getNome());
              stmt.setString(3, usuario.getCurso());
              stmt.setString(4 , usuario.getSobreMim());
              stmt.setString(5 , usuario.getLogin());
              stmt.executeUpdate();
              System.out.println("Update executado.. Login = "+usuario.getLogin());
              ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    login = rs.getString(1);
                 }  
                 stmt.close();
                                
              }catch(SQLException e){
                e.printStackTrace();
                                
              }
              return login;
          }

	  
	  public void deleteUsuario(String login) {
            try {
                    PreparedStatement ps = conn.prepareStatement("delete from usuario where login = ?", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, login);
                    ps.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
	  }
          
	  private Usuario resultSetToUsuario(ResultSet result) {
	    Usuario usuario = new Usuario();
            try{
                usuario.setLogin(result.getString(1));
                usuario.setSenha(result.getString(2));
                usuario.setNome(result.getString(3));
                usuario.setCurso(result.getString(4));
                usuario.setSobreMim(result.getString(5));
            }catch(SQLException e){
                return null;
            }
	    return usuario;
	  }
          
          public void close(){
                    ConnectionFactory.closeConnection();
          }

}
