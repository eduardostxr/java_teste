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
        try {
            User emailUser = userRepository.findByEmail(email);
            if (emailUser != null) {
                return ServiceResponseHandler.conflict("E-mail já cadastrado");
            }

            Profissao profissao = userRepository.createProfissao(profissaoNome);
            // Por segurança, porque deve ser criado ou vai retornar uma exception no Repository
            if (profissao == null) {
                return ServiceResponseHandler.notFound("Profissão não encontrada.");
            }

            User user = userRepository.createUser(email, senha, profissao.getId());
            return ServiceResponseHandler.created(user, "Usuário criado com sucesso.");

        } catch (DataAccessException e) {
            // ServiceResponseHandler tem os diferentes tipos de exceptios com status definido
            return ServiceResponseHandler.internalError("Erro no banco de dados.", e.getMessage(), e);
        } catch (Exception e) {
            return ServiceResponseHandler.internalError("Erro inesperado ao criar usuário.", e.getMessage(), e);
        }
    }

}