package com.yorisapp.notaria.service.dto.permission;

import lombok.Data;

@Data
public class PermissionGroupQuerySelectDto {
    private String title;
    private String permissionGroupCode;
    private int deploymentOrder;

    public PermissionGroupQuerySelectDto(String title, String permissionGroupCode, int deploymentOrder){
        this.title = title;
        this.deploymentOrder = deploymentOrder;
        this.permissionGroupCode = permissionGroupCode;
    }
}
