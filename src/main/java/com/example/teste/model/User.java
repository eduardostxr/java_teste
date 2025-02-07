package com.example.teste.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String email;
    private String senha;
    private Profissao profissao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public static User fromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setSenha(rs.getString("senha"));

        Profissao profissao = new Profissao(rs.getInt("profissao_id"), rs.getString("profissao_nome"));
        user.setProfissao(profissao);

        return user;
    }

}
