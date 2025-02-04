package com.example.teste.model;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class User {
    public int id;
    public String email;
    public String senha;
    public int profissao;

    public User(int id, String email, String senha, int profissao) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.profissao = profissao;
    }

    public static User fromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("senha"),
                rs.getInt("profissao")
        );
    }

    public static User fromJson(JsonObject json) throws ParseException {
        return new User(
                json.get("id").getAsInt(),
                json.get("email").getAsString(),
                json.get("senha").getAsString(),
                json.get("profissao").getAsInt()
        );
    }

    public static JsonObject toJson(User user) {
        JsonObject json = new JsonObject();
        json.addProperty("id", user.id);
        json.addProperty("email", user.email);
        json.addProperty("senha", user.senha);
        json.addProperty("profissao", user.profissao);
        return json;
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                " email: " + this.email +
                " senha: " + this.senha +
                " profissao: " + this.profissao;
    }
}
