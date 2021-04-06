package com.yorisapp.notaria.service.dto.role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolePermissionQueryDto {
    private long id;
    private String title;
    private String permissionCode;
    private boolean assign;
}
