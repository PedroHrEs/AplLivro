/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.livro.dao;

import br.com.livro.model.Editora;
import br.com.livro.model.Editora;
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
public class EditoraDAO implements GenericDAO{
    
    private Connection conexao;
    
    public EditoraDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Editora oEditora = (Editora) objeto;
        Boolean retorno=false;
        if (oEditora.getIdEditora() == 0){
            retorno = this.inserir(oEditora);
        }else{
            retorno = this.alterar(oEditora);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
         Editora oEditora = (Editora) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into editora(nomeeditora) values(?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEditora.getNomeEditora());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao cadastrar a Editora! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Editora oEditora = (Editora) objeto;
        PreparedStatement stmt = null;
        String sql = "update editora set nomeeditora=? where ideditora=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEditora.getNomeEditora());
            stmt.setInt(2, oEditora.getIdEditora());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            try {
                System.out.println("Problemas ao alterar a Editora! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
       int idEditora = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from editora where ideditora=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEditora);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir a Editora! Erro: "
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
        int idEditora = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Editora oEditora = null;
        String sql = "select * from editora where ideditora=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEditora);
            rs=stmt.executeQuery();
            while (rs.next()) {
                oEditora = new Editora();
                oEditora.setIdEditora(rs.getInt("idEditora"));
                oEditora.setNomeEditora(rs.getString("nomeEditora"));
            }
            return oEditora;
        } catch (Exception ex){
            System.out.println("Problemas ao carregar Editora!"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from editora order by ideditora";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Editora oEditora = new Editora();
                oEditora.setIdEditora(rs.getInt("idEditora"));
                oEditora.setNomeEditora(rs.getString("nomeEditora"));
                resultado.add(oEditora);
            }
            
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Editora! Erro: "
            +ex.getMessage());
        }
        return resultado;
    }
    
}

