package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.domainValue.DomainValueQueryDto;

import java.util.List;

public interface DomainValueService {
    List<DomainValueQueryDto> getAllDomainValueByState(String pState);
    DomainValueQueryDto getDomainValueByIdAndState(long pDomainValueId, String pState);
    DomainValueQueryDto getDomainValueByCodeAndState(String pCode, String pState);
    DomainValueQueryDto getDomainValueByDomainAndCodeAndState(String pDomainName, String pCode, String pState);

}
