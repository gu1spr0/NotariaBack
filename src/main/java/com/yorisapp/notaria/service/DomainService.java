package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.domain.DomainQueryDto;

import java.util.List;

public interface DomainService {
    List<DomainQueryDto> getAllDomainByState(String pState);
    DomainQueryDto getDomainByIdAndState(long pDomainId, String pState);
}
