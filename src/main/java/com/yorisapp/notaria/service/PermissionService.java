package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.role.RolePermissionGroupQueryDto;

import java.util.List;

public interface PermissionService {
    List<RolePermissionGroupQueryDto> getRolePermissionsGroupsByRolesId(String[] pRoles);
}
