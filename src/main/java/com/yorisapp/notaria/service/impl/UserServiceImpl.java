package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.User;
import com.yorisapp.notaria.model.repository.UserRepository;
import com.yorisapp.notaria.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User getUserByUserName(String pUserName) {
        User user = userRepository.getUserByUsername(pUserName).orElse(null);
        if (user == null){
            Object[] obj = {"usuario", "nombre de usuario", String.valueOf(pUserName)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        return user;
    }
}
