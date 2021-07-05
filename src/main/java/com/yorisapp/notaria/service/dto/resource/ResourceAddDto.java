package com.yorisapp.notaria.service.dto.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResourceAddDto {
    private String title;
    private String description;
    private Long orderDeploy;
    private Long idResourcesGroup;
    private Long idRoute;
}
