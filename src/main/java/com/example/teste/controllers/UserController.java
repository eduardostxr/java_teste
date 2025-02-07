package com.example.teste.controller;

import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import com.example.teste.service.UserService;
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
        Retorno<User> retorno = userService.createUserProfissao(email, senha, profissao);
        return ResponseEntity.status(retorno.getStatus()).body(retorno);
    }
}
