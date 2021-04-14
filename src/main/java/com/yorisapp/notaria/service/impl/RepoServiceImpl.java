package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.DomainValue;
import com.yorisapp.notaria.model.entity.Notary;
import com.yorisapp.notaria.model.entity.Repo;
import com.yorisapp.notaria.model.repository.NotaryRepository;
import com.yorisapp.notaria.model.repository.RepoRepository;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.NotaryService;
import com.yorisapp.notaria.service.RepoService;
import com.yorisapp.notaria.service.dto.notary.NotaryQueryDto;
import com.yorisapp.notaria.service.dto.repo.RepoAddDto;
import com.yorisapp.notaria.service.dto.repo.RepoQueryDto;
import com.yorisapp.notaria.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {
    private final RepoRepository repoRepository;
    Logger logger = LoggerFactory.getLogger(NotaryServiceImpl.class);
    public RepoServiceImpl(RepoRepository repoRepository){
        this.repoRepository = repoRepository;
    }

    @Autowired
    private NotaryService notaryService;

    @Autowired
    private DomainValueService domainValueService;

    @Override
    public List<RepoQueryDto> getAllRepositoryByState(String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        List<Repo> vRepoList = this.repoRepository.getRepoByState(pState);
        List<RepoQueryDto> vRepoQueryDtoList = new ArrayList<>();
        if(vRepoList != null){
            for(Repo vRepo : vRepoList){
                RepoQueryDto vRepoQueryDto = new RepoQueryDto();
                BeanUtils.copyProperties(vRepo, vRepoQueryDto);
                vRepoQueryDto.setNotaryId(vRepo.getNotary().getId());
                vRepoQueryDtoList.add(vRepoQueryDto);
            }
        }
        return vRepoQueryDtoList;
    }

    @Override
    public RepoQueryDto addRepository(RepoAddDto pRepoAddDto) {
        DomainValue vDomainValuePathNotary = this.domainValueService.getDomainValueByDomainCodeAndCodeValue(Constants.PATH_RESOURCES,Constants.PATH_DOCUMENT,Constants.STATE_ACTIVE);
        if(vDomainValuePathNotary == null){
            Object[] obj = {Constants.PATH_DOCUMENT};
            throw Message.GetBadRequest(MessageDescription.DataEmptyOrNull, obj);
        }
        if(pRepoAddDto==null){
            Object[] obj = { "Repositorio" };
            throw Message.GetBadRequest(MessageDescription.objectNull, obj);
        }
        NotaryQueryDto vNotaryQueryDto = this.notaryService.getNotaryById(pRepoAddDto.getNotaryId());
        Notary vNotary = new Notary();
        BeanUtils.copyProperties(vNotaryQueryDto, vNotary);
        Date vDate = new Date();
        String vBaseRoute = vDomainValuePathNotary.getCharValue()+vNotaryQueryDto.getId()+vNotaryQueryDto.getNro()+"/REP"+vDate.getTime();
        Repo vRepo = new Repo();
        BeanUtils.copyProperties(pRepoAddDto, vRepo);
        vRepo.setRouteBase(vBaseRoute);
        vRepo.setNotary(vNotary);
        Repo vNewRepo = this.repoRepository.save(vRepo);
        File folder = new File(vBaseRoute);
        folder.mkdirs();
        RepoQueryDto vRepoQueryDto = new RepoQueryDto();
        BeanUtils.copyProperties(vNewRepo, vRepoQueryDto);
        return vRepoQueryDto;
    }
}
