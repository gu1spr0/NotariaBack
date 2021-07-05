package com.yorisapp.notaria.service.dto.user;

import com.yorisapp.notaria.model.entity.Document;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class UserQueryDto {
    private String username;
    private String password;
    private String name;
    private String lastname1;
    private String lastname2;
    private String ci;
    private DomainValue domainValue;
    private Role role;
    private Long cellphone;
    private String grade;
    private List<Document> documentList;
    private long id;
    private Date createdDate;
    private String createdBy;
    private String state;
}
