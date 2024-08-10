/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.livro.model;

/**
 *
 * @author pedro
 */
public class Livro {
    private int idLivro;
    private String titulo;
    private String isbn;
    private String numPaginas;
    private String tipoCapa;
    private Autor Autor;
    private Editora Editora;

    public Livro() {
        this.idLivro = 0;
        this.titulo = "";
        this.isbn = "";
        this.numPaginas = "";
        this.tipoCapa = "";
        this.Autor = new Autor();
        this.Editora = new Editora();
    }

    public Livro(int idLivro, String titulo, String isbn, String numPaginas, String tipoCapa, Autor Autor, Editora Editora) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.numPaginas = numPaginas;
        this.tipoCapa = tipoCapa;
        this.Autor = Autor;
        this.Editora = Editora;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(String numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getTipoCapa() {
        return tipoCapa;
    }

    public void setTipoCapa(String tipoCapa) {
        this.tipoCapa = tipoCapa;
    }

    public Autor getAutor() {
        return Autor;
    }

    public void setAutor(Autor Autor) {
        this.Autor = Autor;
    }

    public Editora getEditora() {
        return Editora;
    }

    public void setEditora(Editora Editora) {
        this.Editora = Editora;
    }
    
    
}
