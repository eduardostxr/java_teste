package com.example.teste.repository;

import com.example.teste.model.Profissao;
import com.example.teste.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Profissao createProfissao(String nome) {
        String query = "INSERT INTO profissao (nome) VALUES (?) RETURNING id, nome";
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                    new Profissao(rs.getInt("id"), rs.getString("nome")), nome);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao inserir profissão no banco de dados.", e);
        }
    }

    public User createUser(String email, String senha, int profissaoId) {
        String query = """
            INSERT INTO users (email, senha, profissao_id) 
            VALUES (?, ?, ?) 
            RETURNING id, email, senha, profissao_id, 
            (SELECT nome FROM profissao WHERE id = users.profissao_id) AS profissao_nome
        """;
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> User.fromResultSet(rs), email, senha, profissaoId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao inserir usuário no banco de dados.", e);
        }
    }

    public User findByEmail(String email) {
        String query = "SELECT id, email, senha, profissao_id, (SELECT nome FROM profissao WHERE id = users.profissao_id) AS profissao_nome " +
                "FROM users WHERE email = ? LIMIT 1";
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> User.fromResultSet(rs), email);
        } catch (EmptyResultDataAccessException e) {
            // Catch para o caso de não encontre nada, e isso não é uma exception necessáriamente
            return null;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar usuário no banco de dados.", e);
        }
    }

}