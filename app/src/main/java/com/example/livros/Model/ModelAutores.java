package com.example.livros.Model;

public class ModelAutores {

    private int idAutor;
    private String Autor;

    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    @Override
    public String toString(){
        return this.Autor;
    }
}
