package com.example.teste.model;

public class Profissao {
    private int id;
    private String nome;

    public Profissao() {}

    public Profissao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
