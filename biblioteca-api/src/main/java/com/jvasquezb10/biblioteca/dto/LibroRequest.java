package com.jvasquezb10.biblioteca.dto;

public class LibroRequest {

    private String titulo;
    private String autor;
    private String isbn;
    private Integer anioPublicacion;
    private String estado;
    

    public LibroRequest() {
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
