package com.example.teste.model;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Profissao {
    public int id;
    public String nome;

    public static Profissao fromResultSet(ResultSet rs) throws SQLException {
        Profissao profissao = new Profissao();
        profissao.id = rs.getInt("id");
        profissao.nome = rs.getString("nome");
        return profissao;
    }

    public static Profissao fromJson(JsonObject json) throws ParseException {
        Profissao profissao = new Profissao();
        profissao.id = json.get("id").getAsInt();
        profissao.nome = json.get("nome").getAsString();
        return profissao;
    }

    public static JsonObject toJson(Profissao profissao) {
        JsonObject json = new JsonObject();
        json.addProperty("id", profissao.id);
        json.addProperty("nome", profissao.nome);
        return json;
    }

    @Override
    public String toString(){
        return " id: " + this.id + " descricao: " + this.nome;
    }
}