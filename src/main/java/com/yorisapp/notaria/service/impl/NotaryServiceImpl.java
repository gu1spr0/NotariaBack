package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.entity.Notary;
import com.yorisapp.notaria.model.repository.NotaryRepository;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.NotaryService;
import com.yorisapp.notaria.service.dto.notary.NotaryAddDto;
import com.yorisapp.notaria.service.dto.notary.NotaryQueryDto;
import com.yorisapp.notaria.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotaryServiceImpl implements NotaryService {
    private final NotaryRepository notaryRepository;
    Logger logger = LoggerFactory.getLogger(NotaryServiceImpl.class);
    public NotaryServiceImpl(NotaryRepository notaryRepository){
        this.notaryRepository = notaryRepository;
    }

    @Autowired
    private DomainValueService domainValueService;

    @Override
    public List<NotaryQueryDto> getAllNotaryByState(String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        List<Notary> vNotaryList = this.notaryRepository.getNotaryByState(pState);
        List<NotaryQueryDto> vNotaryQueryDtoList = new ArrayList<>();
        if(vNotaryList != null){
            for(Notary vNotary : vNotaryList){
                NotaryQueryDto vNotaryQueryDto = new NotaryQueryDto();
                BeanUtils.copyProperties(vNotary, vNotaryQueryDto);
                vNotaryQueryDtoList.add(vNotaryQueryDto);
            }
        }
        return vNotaryQueryDtoList;

    }

    @Override
    public NotaryQueryDto addNotary(NotaryAddDto pNotaryAddDto) {
        DomainValue vDomainValuePathNotary = this.domainValueService.getDomainValueByDomainCodeAndCodeValue(Constants.PATH_RESOURCES,Constants.PATH_DOCUMENT,Constants.STATE_ACTIVE);
        if(vDomainValuePathNotary == null){
            Object[] obj = {Constants.PATH_DOCUMENT};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        if(pNotaryAddDto==null){
            Object[] obj = { "Notaria" };
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        Notary vNotary = new Notary();
        BeanUtils.copyProperties(pNotaryAddDto, vNotary);
        Notary vNewNotary = this.notaryRepository.save(vNotary);
        NotaryQueryDto vNotaryQueryDto = new NotaryQueryDto();
        BeanUtils.copyProperties(vNewNotary,vNotaryQueryDto);
        logger.info(vDomainValuePathNotary.getCharValue());
        File folder = new File(vDomainValuePathNotary.getCharValue()+vNotaryQueryDto.getId()+vNotaryQueryDto.getNro());
        folder.mkdirs();
        return vNotaryQueryDto;
    }

    @Override
    public NotaryQueryDto getNotaryById(long pNotaryId) {
        Notary vNotary = this.notaryRepository.getNotaryByIdAndState(pNotaryId, Constants.STATE_ACTIVE).orElse(null);

        if(vNotary == null){
            Object[] obj = { "Notaria" };
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        NotaryQueryDto vNotaryQueryDto = new NotaryQueryDto();
        BeanUtils.copyProperties(vNotary, vNotaryQueryDto);
        return vNotaryQueryDto;
    }
}
