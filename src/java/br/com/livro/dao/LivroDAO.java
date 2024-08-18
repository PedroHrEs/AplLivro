/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.livro.dao;

import br.com.livro.model.Autor;
import br.com.livro.model.Editora;
import br.com.livro.model.Livro;
import br.com.livro.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class LivroDAO implements GenericDAO{
     private Connection conexao;
    
    public LivroDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Livro oLivro = (Livro) objeto;
       Boolean retorno = false;
       if(oLivro.getIdLivro()==0) {
            retorno = this.inserir(oLivro);
       }
       else{
            retorno = this.alterar(oLivro);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
       Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into livro (titulo, isbn, numpaginas, tipocapa, idautor, ideditora) values (?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTitulo());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getNumPaginas());
            stmt.setString(4, oLivro.getTipoCapa());
            stmt.setInt(5, oLivro.getAutor().getIdAutor());
            stmt.setInt(6, oLivro.getEditora().getIdEditora());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar o Livro! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch(SQLException e){
                System.out.println("Erro: "+ex.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "update livro set titulo=?, isbn=?, numpaginas=?, tipocapa=?, idautor=?,ideditora=? where idlivro=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTitulo());
            stmt.setString(2, oLivro.getIsbn());
            stmt.setString(3, oLivro.getNumPaginas());
            stmt.setString(4, oLivro.getTipoCapa());
            stmt.setInt(5, oLivro.getAutor().getIdAutor());
            stmt.setInt(6, oLivro.getEditora().getIdEditora());
            stmt.setInt(7, oLivro.getIdLivro());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao alterar o Livro! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
                    
            }catch(SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
                
            }
            return false;
        }
        
    }
    
    @Override
    public Boolean excluir(int numero) {
         int idLivro = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from livro where idlivro=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Livro! Erro: "
                    +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro rollback"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }
    
    @Override
    public Object carregar(int numero) {
       int idLivro = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Livro oLivro = null;
        String sql = "select * from livro where idlivro=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            rs =stmt.executeQuery();
            while(rs.next()){
                oLivro = new Livro();
                //letra maiuscula??
                oLivro.setIdLivro(rs.getInt("idlivro"));
                oLivro.setTitulo(rs.getString("titulo"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setNumPaginas(rs.getString("numpaginas"));
                oLivro.setTipoCapa(rs.getString("tipocapa"));
                
                AutorDAO oAutorDAO = new AutorDAO();
                oLivro.setAutor((Autor) oAutorDAO.carregar(rs.getInt("idautor"))); 
                EditoraDAO oEditoraDAO = new EditoraDAO();
                oLivro.setEditora((Editora) oEditoraDAO.carregar(rs.getInt("ideditora"))); 
                
            }
            return oLivro;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Livro! Erro:"+ex.getMessage());
            return false;
        }
        
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from livro order by titulo";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Livro oLivro = new Livro();
                oLivro.setIdLivro(rs.getInt("idlivro"));
                oLivro.setTitulo(rs.getString("titulo"));
                oLivro.setIsbn(rs.getString("isbn"));
                oLivro.setNumPaginas(rs.getString("numpaginas"));
                oLivro.setTipoCapa(rs.getString("tipocapa"));
                
                try {
                    AutorDAO oAutorDAO = new AutorDAO();
                    oLivro.setAutor((Autor) oAutorDAO.carregar(rs.getInt("idautor")));
                } catch (Exception ex) {
                    System.out.println("Erro ao buscar autor: " + ex.getMessage());
                    ex.printStackTrace();
                }
                
                try {
                    EditoraDAO oEditoraDAO = new EditoraDAO();
                    oLivro.setEditora((Editora) oEditoraDAO.carregar(rs.getInt("ideditora")));
                } catch (Exception ex) {
                    System.out.println("Erro ao buscar editora: " + ex.getMessage());
                    ex.printStackTrace();
                }
                resultado.add(oLivro);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar Livros! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
