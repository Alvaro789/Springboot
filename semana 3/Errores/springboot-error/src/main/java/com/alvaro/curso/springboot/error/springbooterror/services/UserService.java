package com.alvaro.curso.springboot.error.springbooterror.services;

import java.util.List;
import java.util.Optional;

import com.alvaro.curso.springboot.error.springbooterror.models.domain.User;

public interface UserService {

    List<User> findAll();
    Optional <User> findbyId(Long id);
}
