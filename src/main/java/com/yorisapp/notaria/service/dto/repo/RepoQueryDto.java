package com.yorisapp.notaria.service.dto.repo;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RepoQueryDto {
    private Long id;
    private String name;
    private String routeBase;
    private String description;
    private Long notaryId;
    private Date createdDate;
    private Long createdBy;
    private String state;
}
