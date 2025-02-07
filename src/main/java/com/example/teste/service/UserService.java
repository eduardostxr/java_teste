package com.example.teste.service;

import com.example.teste.model.Profissao;
import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import com.example.teste.repository.UserRepository;
import com.example.teste.utils.ServiceResponseHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Retorno<User> createUserProfissao(String email, String senha, String profissaoNome) {
        if (userRepository.findByEmail(email) != null) {
            return ServiceResponseHandler.badRequest(
                    "E-mail já está em uso.",
                    "E-mail informado já existe"
            );
        }
        Profissao profissao = userRepository.createProfissao(profissaoNome);
        User user = userRepository.createUser(email, senha, profissao.getId());

        return ServiceResponseHandler.created(user, "Usuário criado com sucesso.");
    }
}
