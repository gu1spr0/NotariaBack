package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.role.RoleQueryDto;

import java.util.List;

public interface RoleService {
    List<RoleQueryDto> getRoles(String pState);
}
