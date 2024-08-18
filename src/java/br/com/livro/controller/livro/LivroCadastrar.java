/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.livro.controller.livro;

import br.com.livro.dao.GenericDAO;
import br.com.livro.dao.LivroDAO;
import br.com.livro.model.Autor;
import br.com.livro.model.Editora;
import br.com.livro.model.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedro
 */
@WebServlet(name = "LivroCadastrar", urlPatterns = {"/LivroCadastrar"})
public class LivroCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String idLivroParam = request.getParameter("idlivro");
    String idAutorParam = request.getParameter("idautor");
    String idEditoraParam = request.getParameter("ideditora");

    int idLivro = 0;
    int idAutor = 0;
    int idEditora = 0;

    if (idLivroParam != null && !idLivroParam.isEmpty()) {
        idLivro = Integer.parseInt(idLivroParam);
    }
    if (idAutorParam != null && !idAutorParam.isEmpty()) {
        idAutor = Integer.parseInt(idAutorParam);
    }
    if (idEditoraParam != null && !idEditoraParam.isEmpty()) {
        idEditora = Integer.parseInt(idEditoraParam);
    }

    String titulo = request.getParameter("titulo");
    String isbn = request.getParameter("isbn");
    String numPaginas = request.getParameter("numpaginas");
    String tipoCapa = request.getParameter("tipocapa");
    String mensagem = null;

    try {
        Livro oLivro = new Livro();
        oLivro.setIdLivro(idLivro);
        oLivro.setTitulo(titulo);
        oLivro.setIsbn(isbn);
        oLivro.setNumPaginas(numPaginas);
        oLivro.setTipoCapa(tipoCapa);
        oLivro.setAutor(new Autor(idAutor, ""));
        oLivro.setEditora(new Editora(idEditora, ""));

        GenericDAO dao = new LivroDAO();
        if (dao.cadastrar(oLivro)) {
            mensagem = "Livro cadastrado com sucesso!";
        } else {
            mensagem = "Problemas ao cadastrar Livro. Verifique os dados informados e tente novamente!";
        }
        request.setAttribute("mensagem", mensagem);
        response.sendRedirect("LivroListar");
    } catch (Exception ex) {
        System.out.println("Problemas no Servlet ao cadastrar Livro! Erro: " + ex.getMessage());
        ex.printStackTrace();
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
