package com.yorisapp.notaria.service.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleQueryDto {
    private long id;
    private String role;
    private String description;
    private boolean assigned;
}
