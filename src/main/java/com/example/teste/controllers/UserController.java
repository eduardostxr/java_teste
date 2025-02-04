package com.example.teste.controllers;
import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.teste.service.UserService;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("allUsers")
    public ResponseEntity<Retorno<ArrayList<User>>> getUsers() {
        Retorno<ArrayList<User>> retorno = userService.allUsers();
        if (retorno.value != null) {
            return ResponseEntity.ok(retorno);
        } else {
            retorno.internalErrorMessage = null;
            return ResponseEntity.badRequest().body(retorno);
        }
    }
}