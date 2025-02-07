package com.example.teste.repository;

import com.example.teste.model.Profissao;
import com.example.teste.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Profissao createProfissao(String nome) {
        String query = "INSERT INTO profissaoo (nome) VALUES (?) RETURNING id, nome";
        return jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                new Profissao(rs.getInt("id"), rs.getString("nome")), nome);
    }

    public User createUser(String email, String senha, int profissaoId) {
        String query = """
            INSERT INTO users (email, senha, profissao_id) 
            VALUES (?, ?, ?) 
            RETURNING id, email, senha, profissao_id, nome AS profissao_nome
            FROM profissao WHERE id = ?
        """;
        return jdbcTemplate.queryForObject(query, (rs, rowNum) -> User.fromResultSet(rs), email, senha, profissaoId, profissaoId);
    }

    public User findByEmail(String email) {
        String query = """
            SELECT u.id, u.email, u.senha, u.profissao_id, p.nome AS profissao_nome
            FROM users u
            LEFT JOIN profissao p ON u.profissao_id = p.id
            WHERE u.email = ?
        """;
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> User.fromResultSet(rs), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
