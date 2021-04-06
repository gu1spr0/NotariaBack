package com.yorisapp.notaria.service.dto.domainValue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainValueQuerySelectDto {
    private long id;
    private String codeValue;
    private String titleDescription;
    private String charValue;
    private Long numericValue;
    private String charValueExtra;
    private Long numericValueExtra;
}
