package br.com.livro.filter;

import br.com.livro.utils.SingleConnection;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Filter;


@WebFilter(urlPatterns={"/*"})

/**
 *
 * @author Aluno
 */
public class FilterAutenticacao {
    
    private static Connection conexao;

    public void init(FilterConfig filterConfig) throws ServletException {
        conexao = SingleConnection.getConnection(); 
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException{
        try{
            chain.doFilter(request, response);
        }catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            e.printStackTrace();
        }
            
    }

    public void destroy() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro :" +ex.getMessage());
            ex.printStackTrace();
        }
    }
}