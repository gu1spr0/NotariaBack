package com.yorisapp.notaria.service.dto.role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleQueryDto {
    private long id;
    private String role;
    private String description;
    private String state;
}
