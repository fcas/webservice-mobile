package dao;

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
	      
	  public Usuario createUsuario(Usuario usuario) throws DadosIncompletosException, UsuarioJaExisteException {
              if(!usuario.getNome().equals("") && !usuario.getSenha().trim().equals("") && !usuario.getLogin().trim().equals("")){            
                  if(getUsuarioByLogin(usuario.getLogin()) == null){
                            try{
                                String sql = "insert into usuario ("+Usuario.COLUNA_LOGIN+","+Usuario.COLUNA_SENHA+","+Usuario.COLUNA_NOME+","+Usuario.COLUNA_CURSO+", "+Usuario.COLUNA_SOBRE+") values(?, ? ,?, ?, ?)";
                                PreparedStatement stmt = conn.prepareStatement(sql); 
                                stmt.setString(1, usuario.getLogin());
                                stmt.setString(2, usuario.getSenha());
                                stmt.setString(3, usuario.getNome());
                                stmt.setString(4, usuario.getCurso());
                                stmt.setString(5 , usuario.getSobreMim());
                                stmt.execute();
                                stmt.close();
                               
                            }catch(SQLException e){
                                e.printStackTrace();
                                return null;
                            }
			}
		    else{
		    	throw new UsuarioJaExisteException();
		    }
		  }else{
			  throw new DadosIncompletosException();
		  }
		  return usuario;
	  }
	  
            public Usuario updateUsuario(String login, Usuario usuario) throws DadosIncompletosException, UsuarioJaExisteException {
              if(!usuario.getNome().equals("") && !usuario.getSenha().trim().equals("") && !usuario.getLogin().trim().equals("")){
		    if(getUsuarioByLogin(usuario.getLogin()) == null){
                            try{
                                String sql = "update usuario set "+Usuario.COLUNA_LOGIN+" = ?, "+Usuario.COLUNA_SENHA+" = ?, "+Usuario.COLUNA_NOME+" = ?, "+Usuario.COLUNA_CURSO+" = ?, "+Usuario.COLUNA_SOBRE+" = ? where login = '"+login+"'";
                                PreparedStatement stmt = conn.prepareStatement(sql); 
                                stmt.setString(1, usuario.getLogin());
                                stmt.setString(2, usuario.getSenha());
                                stmt.setString(3, usuario.getNome());
                                stmt.setString(4, usuario.getCurso());
                                stmt.setString(5 , usuario.getSobreMim());
                                stmt.executeUpdate();
                                System.out.println("lol");
                                stmt.close();
                            }catch(SQLException e){
                                e.printStackTrace();
                                return null;
                            }
			}
		    else{
		    	throw new UsuarioJaExisteException();
		    }
		  }else{
			  throw new DadosIncompletosException();
		  }
		  return usuario;
	  }
	  
	  public void deleteUsuarios(Usuario usuario) {
            try {
                String login = usuario.getLogin();
                String sql = "delete from usuario where login=?";
                PreparedStatement stmt = conn.prepareStatement(sql); 
                stmt.setString(1, usuario.getLogin());
                System.out.println("Usuario deleted with login: " + login);
                stmt.execute();
                stmt.close();
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
