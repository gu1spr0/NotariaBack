package com.yorisapp.notaria.service.dto.domainValue;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DomainValueUpdateDto {
    private String codeValue;
    private String titleDescription;
    private String charValue;
    private Long numericValue;
    private String charValueExtra;
    private Long numericValueExtra;
    //private Domain domain;
    private Long idDomain;
    private String state;
}
