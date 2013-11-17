package dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author llll
 */

/*
 * Essa classe é responsável por criar a conexão com o banco. Implementa o padrão de projeto Singleton para garantir que só seja criada uma
 * instância da classe conexão por vez
 */
public class ConnectionFactory {  

   public final static String MYSQL = "mysql";
     
   private static Connection conexao = null;  
 
   public ConnectionFactory() {  
   }  
        /**
         * Cria uma conexão com o banco de dados
         * @param tipo: Banco de dados que se deseja conectar. No momento, o único implementando é "postgre"
         * @return uma instância de Connection conectada ao BD
         */    
        public static Connection getConnection(String tipo){
            if(conexao == null){
                if(tipo == MYSQL){
                    String driver = "com.mysql.jdbc.Driver";  
                    String user   = "root";  
                    String senha = "123456";  
                    String url = "jdbc:mysql://localhost/dm_ufrn";  
                    Connection con = null;
                    try  
                    {  
                        Class.forName(driver);  
                        con = (Connection) DriverManager.getConnection(url, user, senha);  
                    }  
                    catch (ClassNotFoundException ex)  
                    {  
                        System.out.println("Erro de conexao com o BD");
                    }   
                    catch (SQLException e)  
                    {  
                        System.out.println("Erro de conexao com o BD");
                    }
                    conexao = con;
                }
            }
            return conexao;
        }
        
        /**
         * Fecha a conexão com o banco de dados
         */ 
        public static void closeConnection(){
            try {
                if(conexao != null){
                    conexao.close();
                    conexao = null;
                }
            } catch (SQLException ex) {

            }
        }
}  
