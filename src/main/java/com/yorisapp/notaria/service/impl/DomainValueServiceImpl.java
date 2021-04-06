package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.Domain;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.repository.DomainRepository;
import com.yorisapp.notaria.model.repository.DomainValueRepository;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.dto.domainValue.*;
import com.yorisapp.notaria.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DomainValueServiceImpl implements DomainValueService {
    private final DomainValueRepository domainValueRepository;
    private final DomainRepository domainRepository;
    public DomainValueServiceImpl(DomainValueRepository domainValueRepository, DomainRepository domainRepository){
        this.domainValueRepository = domainValueRepository;
        this.domainRepository = domainRepository;
    }

    @Override
    public DomainValueQueryPageableDto getDomainValuePageable(Long pDomainId, String pState, int pPage, int pRowsNumber) {
        DomainValueQueryPageableDto vDomainValueQueryPageableDto = new DomainValueQueryPageableDto();
        Long vTotalRows = domainValueRepository.getCountDomainsValuesByIdAndState(pDomainId,pState);
        if(Strings.isNullOrEmpty(pState)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }

        if(!(pState.equals(Constants.STATE_ACTIVE) || pState.equals(Constants.STATE_INACTIVE) || pState.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }

        if(vTotalRows>0){
            List<DomainValue> vDomainValueList = domainValueRepository.getDomainsValuesPageableByIdAndState(pDomainId, pState, PageRequest.of(pPage,pRowsNumber));
            List<DomainValueQueryDto> vDomainValueQueryDtoList = new ArrayList<>();
            for(DomainValue vDomainValue : vDomainValueList){
                DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
                BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
                vDomainValueQueryDto.setDomainId(vDomainValue.getDomain().getId());
                vDomainValueQueryDtoList.add(vDomainValueQueryDto);
            }
            vDomainValueQueryPageableDto.setTotalRows(vTotalRows);
            vDomainValueQueryPageableDto.setDomainValueQueryDtoList(vDomainValueQueryDtoList);
        }else{
            vDomainValueQueryPageableDto.setTotalRows(0);
        }
        return vDomainValueQueryPageableDto;
    }

    @Override
    public DomainValueQueryDto addDomainValue(DomainValueAddDto pDomainValueAddDto) {
        DomainValue vDomainValue = domainValueRepository.getDomainValueByDomainIdAndCodeValue(pDomainValueAddDto.getIdDomain(), pDomainValueAddDto.getCodeValue()).orElse(null);
        Domain vDomain = domainRepository.getDomainById(pDomainValueAddDto.getIdDomain()).orElse(null);
        if (vDomainValue != null){
            Object[] obj = {"dominio-valor", vDomainValue.getCodeValue()};
            throw Message.GetBadRequest(MessageDescription.repeated, obj);
        }
        if(vDomain == null){
            Object[] obj = {"dominio", vDomain.getId()};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }

        vDomainValue = new DomainValue();
        BeanUtils.copyProperties(pDomainValueAddDto, vDomainValue);
        vDomainValue.setDomain(vDomain);
        domainValueRepository.save(vDomainValue);
        DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
        BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
        return vDomainValueQueryDto;

    }

    @Override
    public void deleteDomainValue(long pDomainValueId) {
        DomainValueQueryDto vDomainValueQueryDto = this.getDomainValueById(pDomainValueId);
        vDomainValueQueryDto.setDeletedDate(new Date());
        vDomainValueQueryDto.setState(Constants.STATE_DELETED);
        DomainValue vDomainValue = new DomainValue();
        BeanUtils.copyProperties(vDomainValueQueryDto, vDomainValue);
        domainValueRepository.save(vDomainValue);
    }

    @Override
    public DomainValueQueryDto getDomainValueById(long pDomainValueId) {
        DomainValue vDomainValue = domainValueRepository.getDomainValueById(pDomainValueId).orElse(null);
        if(vDomainValue == null){
            Object[] obj = {"dominio-valor", "id", String.valueOf(pDomainValueId)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
        BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
        return vDomainValueQueryDto;
    }

    @Override
    public List<DomainValueQuerySelectDto> getDomainValueSelectByDomainCode(String pDomainCode) {
        List<DomainValueQuerySelectDto> vDomainValueQuerySelectDtoList = new ArrayList<>();
        List<DomainValue> vDomainValueList = domainValueRepository.getDomainValueByDomainCodeAndState(pDomainCode, Constants.STATE_ACTIVE);
        for (DomainValue vDomainValue: vDomainValueList){
            DomainValueQuerySelectDto vDomainValueQuerySelectDto = new DomainValueQuerySelectDto();
            BeanUtils.copyProperties(vDomainValue, vDomainValueQuerySelectDto);
            vDomainValueQuerySelectDtoList.add(vDomainValueQuerySelectDto);
        }
        return vDomainValueQuerySelectDtoList;
    }

    @Override
    public List<DomainValueQuerySelectDto> getRelation(String pDomainCode, String pCharValue, String pCharValueExtra) {
        List<DomainValueQuerySelectDto> vDomainValueQuerySelectDtoList = new ArrayList<>();
        List<DomainValue> vDomainValueList;
        if(Strings.isNullOrEmpty(pCharValueExtra) && !(Strings.isNullOrEmpty(pCharValue))){
            vDomainValueList = domainValueRepository.getDomainValueByDomainCodeAndCharValueAndState(pDomainCode, pCharValue, Constants.STATE_ACTIVE);
        }else{
            vDomainValueList = domainValueRepository.getDomainValueByDomainCodeAndCharValueExtraAndState(pDomainCode,pCharValueExtra,Constants.STATE_ACTIVE);
        }
        for (DomainValue vDomainValue: vDomainValueList){
            DomainValueQuerySelectDto vDomainValueQuerySelectDto = new DomainValueQuerySelectDto();
            BeanUtils.copyProperties(vDomainValue, vDomainValueQuerySelectDto);
            vDomainValueQuerySelectDtoList.add(vDomainValueQuerySelectDto);
        }
        return vDomainValueQuerySelectDtoList;
    }

    @Override
    public List<DomainValueQueryDto> getDomainValuesByDomainCode(String pDomainCode) {
        List<DomainValue> vDomainValueList = domainValueRepository.getDomainValueByDomainCodeAndState(pDomainCode, Constants.STATE_ACTIVE);
        List<DomainValueQueryDto> vDomainValueQueryDtoList = new ArrayList<>();
        for(DomainValue vDomainValue : vDomainValueList){
            DomainValueQueryDto vDomainValueQueryDto = new DomainValueQueryDto();
            BeanUtils.copyProperties(vDomainValue, vDomainValueQueryDto);
            vDomainValueQueryDtoList.add(vDomainValueQueryDto);
        }
        return vDomainValueQueryDtoList;
    }
}
