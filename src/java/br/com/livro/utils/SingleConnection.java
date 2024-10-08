/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.livro.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pedro
 */
public class SingleConnection {
    private static Connection conexao = null;
    private static String servidor = "jdbc:postgresql://localhost:5432/bdapllivro?autoReconnect=true";
    private static String usuario = "postgres";
    private static String senha = "postdba";
    
    static {
        try {
            conectar();
        }catch (Exception ex){
            System.out.println("Erro ao Conectar ao Banco de dados");
            ex.printStackTrace();
        }
    }
    
    public SingleConnection()throws Exception{
        conectar();
    }
    
    public static void conectar() throws Exception {
        try {
         if (conexao == null) {
             Class.forName("org.postgresql.Driver");
             conexao = DriverManager.getConnection(servidor, usuario, senha);
             conexao.setAutoCommit (false);
         }
        }catch (Exception ex) {
            throw new Exception (ex.getMessage());
        }
    }
    
    public static Connection getConnection(){
        return conexao;
    }
    
}
