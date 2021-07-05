package com.yorisapp.notaria.service.dto.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DomainQueryDto {
    private long id;
    private String type;
    private String name;
    private String description;
    private Date createdDate;
    private String createdBy;
    private String state;
}
