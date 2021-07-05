package com.yorisapp.notaria.service.dto.domainValue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DomainValueQueryPageableDto {
    private List<DomainValueQueryDto> domainValueQueryDtoList;
    private long totalRows;
}
