/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excecoes.DadosIncompletosException;
import excecoes.UsuarioJaExisteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comentarios;
import model.Lugar;
import model.Tarefas;
import model.Usuario;

public class TesteConexao{
    public static void main(String[] args) {
         /*DAOUsuario dao = DAOUsuario.getInstance();
         Usuario u = new Usuario();
         u.setCurso("EngSoft");
         u.setLogin("b");
         u.setSenha("b");
         u.setNome("Bugusto");
         u.setSobreMim("Sou um aluno top");
        try {
            dao.createUsuario(u);
        } catch (DadosIncompletosException ex) {
            Logger.getLogger(TesteConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioJaExisteException ex) {
            Logger.getLogger(TesteConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
         //u = dao.getUsuarioByLogin("a");   
//         /System.out.println(u.getNome());
         dao.close();*/
        
        
        DAOUsuario dao = new DAOUsuario();
        System.out.println(dao.getAllUsers().get(1).getSobreMim());
        /*DAOLugar daoL = DAOLugar.getInstance();
        Lugar l = new Lugar();
        l.setLocalizacao("LOOOOOOL");
        l.setNome("Biblioteca");
        System.out.println(daoL.listarLugares().get(0));
        System.out.println(daoL.listarLugares().get(1));
        System.out.println(daoL.getLugarById(2).getNome());
        System.out.println(daoL.idLugar("Biblioteca"));
        //daoL.salvarLugar("Biblioteca");
        
        
        
        
        DAOTarefa daoT = DAOTarefa.getInstance();
        Tarefas t = new Tarefas();
        t.setId(1);
        t.setData("12/09/2013");
        t.setHorario("12:51:00");
        t.setLugar(daoL.getLugarById(1));
        t.setDescricao("Quero acabar com tudo!");
        t.setUsuario("b");
        daoT.updateTarefa(t);
        //daoT.createTarefa(t); 
        
        
        DAOComentario daoC = DAOComentario.getInstance();
        Comentarios c = new Comentarios();
        c.setId(1);
        c.setAutor("b");
        c.setLugar(daoL.getLugarById(1));
        c.setComment("Esse lugar eh muito bom, vamos sair   ");
        //daoC.createComentarios(c);
        System.out.println(daoC.getAllComments().get(0).getComment());
        daoC.updateComentario(c);
        daoL.close();
        daoT.close();
        daoC.close();*/
     }
}