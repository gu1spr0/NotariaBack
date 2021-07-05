package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.RoleService;
import com.yorisapp.notaria.service.dto.role.RoleQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleQueryDto> getRoles(@RequestParam String state){
        return roleService.getRoles(state);
    }
}
