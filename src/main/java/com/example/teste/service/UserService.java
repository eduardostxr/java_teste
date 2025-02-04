package com.example.teste.service;

import com.example.teste.model.Retorno;
import com.example.teste.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.teste.repository.UserRepository;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Retorno<ArrayList<User>> allUsers() {
        return userRepository.allUsers();
    }
}
