package com.training.platform.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;

    private static Gson gson = new Gson();

    @Override
    public List<User> findAll() throws Exception {
        List<User> resUserList = new ArrayList<>();

        if (!redisService.exists("findAll")) {

            resUserList = userRepository.findAll();

            redisService.set("findAll", gson.toJson(resUserList));
        } else {
            resUserList = gson.fromJson(redisService.get("findAll"), new TypeToken<ArrayList<User>>() {
            }.getType());
        }

        return resUserList;
    }


}
