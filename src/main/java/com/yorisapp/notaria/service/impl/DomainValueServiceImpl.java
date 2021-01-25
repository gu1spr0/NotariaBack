package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.repository.DomainValueRepository;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.dto.domainValue.DomainValueQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainValueServiceImpl implements DomainValueService {
    private final DomainValueRepository domainValueRepository;
    public DomainValueServiceImpl(DomainValueRepository domainValueRepository){
        this.domainValueRepository = domainValueRepository;
    }
    @Override
    public List<DomainValueQueryDto> getAllDomainValueByState(String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        List<DomainValueQueryDto> vDomainValueQueryDtoList = new ArrayList<>();
        List<DomainValue> vDomainValueList = domainValueRepository.getDomainValueByState(pState).orElse(null);
        if(vDomainValueList != null){
            for(DomainValue vDomainValue : vDomainValueList){
                DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
                BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
                vDomainValueQueryDtoList.add(vDomainValueQueryDto);
            }
        }
        return vDomainValueQueryDtoList;
    }

    @Override
    public DomainValueQueryDto getDomainValueByIdAndState(long pDomainValueId, String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        DomainValue vDomainValue = domainValueRepository.getDomainValueByIdAndState(pDomainValueId, pState).orElse(null);

        if(vDomainValue == null){
            Object[] obj = {pDomainValueId};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
        BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);

        return vDomainValueQueryDto;
    }

    @Override
    public DomainValueQueryDto getDomainValueByCodeAndState(String pCode, String pState) {
        if(Strings.isNullOrEmpty(pCode)){
            Object[] obj = {"codigo dominio valor"};
            throw Message.GetBadRequest(MessageDescription.validacionCampoVacioNulo, obj);
        }
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        DomainValue vDomainValue = domainValueRepository.getDomainValueByCodeAndState(pCode, pState).orElse(null);
        if(vDomainValue == null){
            Object[] obj = {pCode};
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
        BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);

        return vDomainValueQueryDto;
    }

    @Override
    public DomainValueQueryDto getDomainValueByDomainAndCodeAndState(String pDomainName, String pCode, String pState) {
        if(Strings.isNullOrEmpty(pDomainName)){
            Object[] obj = {"domainName"};
            throw Message.GetBadRequest(MessageDescription.validacionCampoVacioNulo, obj);
        }
        if(Strings.isNullOrEmpty(pCode)){
            Object[] obj = {"codigo"};
            throw Message.GetBadRequest(MessageDescription.validacionCampoVacioNulo, obj);
        }
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        DomainValue vDomainValue = domainValueRepository.getDomainValueByDomainAndCodeAndState(pDomainName, pCode, pState).orElse(null);
        if (vDomainValue == null){
            Object[] obj = { "Dominio - Valor" };
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
        BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
         return vDomainValueQueryDto;
    }
}
