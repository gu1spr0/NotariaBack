package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.RepoService;
import com.yorisapp.notaria.service.UserService;
import com.yorisapp.notaria.service.dto.repo.RepoAddDto;
import com.yorisapp.notaria.service.dto.repo.RepoQueryDto;
import com.yorisapp.notaria.service.dto.user.UserQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<UserQueryDto> getAllUsers(String state){
        return this.userService.getUserByState(state);
    }
}
