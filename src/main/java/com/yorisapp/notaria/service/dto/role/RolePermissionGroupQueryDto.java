package com.yorisapp.notaria.service.dto.role;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RolePermissionGroupQueryDto {
    private String title;
    private String permissionGroupCode;
    private List<RolePermissionQueryDto> rolePermissionQueryDtoList;
}
