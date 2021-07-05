package com.yorisapp.notaria.service.dto.domainValue;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DomainValueQueryDto {
    private long id;
    private String code;
    private String title;
    private String description;
    private String charValue;
    private Long numericValue;
    private Date createdDate;
    private String createdBy;
    private String state;
    private Long domainId;
    private Date deletedDate;
}
