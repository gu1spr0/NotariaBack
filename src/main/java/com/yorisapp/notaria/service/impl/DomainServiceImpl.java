package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.Domain;
import com.yorisapp.notaria.model.repository.DomainRepository;
import com.yorisapp.notaria.service.DomainService;
import com.yorisapp.notaria.service.dto.domain.DomainQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DomainServiceImpl implements DomainService {
    private final DomainRepository domainRepository;
    public DomainServiceImpl(DomainRepository domainRepository){
        this.domainRepository = domainRepository;
    }
    @Override
    public List<DomainQueryDto> getAllDomainByState(String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        List<DomainQueryDto> vDomainQueryDtoList = new ArrayList<>();
        List<Domain> vDomainList = domainRepository.getDomainByState(pState).orElse(null);
        if(vDomainList != null){

            for(Domain vDomain : vDomainList){
                DomainQueryDto vDomainQueryDto = new DomainQueryDto();
                BeanUtils.copyProperties(vDomain, vDomainQueryDto);
                vDomainQueryDtoList.add(vDomainQueryDto);
            }
        }
        return vDomainQueryDtoList;

    }

    @Override
    public DomainQueryDto getDomainByIdAndState(long pDomainId, String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        if(pDomainId>0){
            Object[] obj = {pDomainId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }

        Domain vDomain = domainRepository.getDomainByIdAndState(pDomainId, pState).orElse(null);
        if(vDomain == null){
            Object[] obj = {"Dominio", "id", pDomainId};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        DomainQueryDto vDomainQueryDto = new DomainQueryDto();
        BeanUtils.copyProperties(vDomain, vDomainQueryDto);
        return vDomainQueryDto;
    }
}
