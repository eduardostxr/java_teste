package com.example.teste.controllers;

import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import com.example.teste.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Retorno<User>> createUser(@RequestParam String email,
                                                    @RequestParam String senha,
                                                    @RequestParam String profissao) {
        // Retorno mais completo que não interessa para o front
        Retorno<User> rtService = userService.createUserProfissao(email, senha, profissao);
        // Apenas valor e mensagem
        Retorno<User> rtController = new Retorno<>(rtService.getValue(), rtService.getMessage(), HttpStatus.valueOf(rtService.getStatus()));
        return ResponseEntity.status(rtService.getStatus()).body(rtController);
    }

}
