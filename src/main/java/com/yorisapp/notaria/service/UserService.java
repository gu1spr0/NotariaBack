package com.yorisapp.notaria.service;

import com.yorisapp.notaria.model.entity.User;

public interface UserService {
    User getUserByUserName(String pUserName);
}
