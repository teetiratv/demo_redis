package com.training.platform.services;

import com.training.platform.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll() throws Exception;
}
