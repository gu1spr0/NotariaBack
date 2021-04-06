package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.domainValue.*;

import java.util.List;

public interface DomainValueService {
    DomainValueQueryPageableDto getDomainValuePageable(Long pDomainId, String pState, int pPage, int pRowsNumber);
    DomainValueQueryDto addDomainValue(DomainValueAddDto pDomainValueAddDto);
    void deleteDomainValue(long pDomainValueId);
    DomainValueQueryDto getDomainValueById(long pDomainValueId);
    List<DomainValueQuerySelectDto> getDomainValueSelectByDomainCode(String pDomainCode);
    List<DomainValueQuerySelectDto> getRelation(String pDomainCode, String pCharValue, String pCharValueExtra);
    List<DomainValueQueryDto> getDomainValuesByDomainCode(String pDomainCode);

}
