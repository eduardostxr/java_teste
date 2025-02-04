package com.example.teste.repository;

import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate poolUser;

    public Retorno<ArrayList<User>> allUsers() {
        String query = "SELECT * FROM users;";
        Retorno<ArrayList<User>> retorno = new Retorno<>();
        ArrayList<User> users = new ArrayList<>();
        try (
                Connection connection = poolUser.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);) {
            while (resultSet.next()) {
                users.add(User.fromResultSet(resultSet));
            }
            retorno.value = users;
            retorno.message = "Usuários buscados com sucesso";

        } catch (SQLException e) {
            retorno.value = null;
            retorno.message = "Erro ao buscar usuários";
        }
        return retorno;
    }
}
