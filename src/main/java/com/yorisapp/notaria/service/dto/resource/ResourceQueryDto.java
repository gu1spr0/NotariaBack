package com.yorisapp.notaria.service.dto.resource;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ResourceQueryDto {
    private long id;
    private String title;
    private String description;
    private Long orderDeploy;
    private Long idResourcesGroup;
    private Long idRoute;
    private Date createdDate;
    private String createdBy;
    private Date deletedDate;
    private String deletedBy;
    private Date lastModifiedDate;
    private String lastModifiedBy;
    private String state;
}
