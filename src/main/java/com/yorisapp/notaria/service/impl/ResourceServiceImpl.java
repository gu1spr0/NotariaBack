package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.GroupResource;
import com.yorisapp.notaria.model.entity.Resource;
import com.yorisapp.notaria.model.entity.Route;
import com.yorisapp.notaria.model.repository.GroupResourceRepository;
import com.yorisapp.notaria.model.repository.ResourceRepository;
import com.yorisapp.notaria.model.repository.RouteRepository;
import com.yorisapp.notaria.service.ResourceService;
import com.yorisapp.notaria.service.dto.resource.ResourceAddDto;
import com.yorisapp.notaria.service.dto.resource.ResourceQueryDto;
import com.yorisapp.notaria.service.dto.resource.ResourceQueryPageableDto;
import com.yorisapp.notaria.service.dto.resource.ResourceUpdateDto;
import com.yorisapp.notaria.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final GroupResourceRepository groupResourceRepository;
    private final RouteRepository routeRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, GroupResourceRepository groupResourceRepository, RouteRepository routeRepository){
        this.resourceRepository = resourceRepository;
        this.groupResourceRepository = groupResourceRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public ResourceQueryPageableDto getResourcePageable(Long pResourceGroupId, String pState, int pPage, int pRowsNumber) {
        ResourceQueryPageableDto vResourceQueryPageableDto = new ResourceQueryPageableDto();
        Long vTotalRows = resourceRepository.getCountResourcesValuesByIdAndState(pResourceGroupId, pState);
        if(Strings.isNullOrEmpty(pState)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pState.equals(Constants.STATE_ACTIVE) || pState.equals(Constants.STATE_INACTIVE) || pState.equals(Constants.STATE_BLOCKED))){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }

        if(vTotalRows>0){
            List<Resource> vResourceList = resourceRepository.getResourcesPageableByIdAndState(pResourceGroupId, pState, PageRequest.of(pPage,pRowsNumber));
            List<ResourceQueryDto> vResourceQueryDtoList = new ArrayList<>();
            for(Resource vResource: vResourceList){
                ResourceQueryDto vResourceQueryDto = new ResourceQueryDto();
                BeanUtils.copyProperties(vResource, vResourceQueryDto);
                vResourceQueryDto.setIdResourcesGroup(vResource.getGroupResource().getId());
                vResourceQueryDto.setIdRoute(vResource.getRoute().getId());
                vResourceQueryDtoList.add(vResourceQueryDto);
            }
            vResourceQueryPageableDto.setTotalRows(vTotalRows);
            vResourceQueryPageableDto.setResourceQueryDtoList(vResourceQueryDtoList);
        }else{
            vResourceQueryPageableDto.setTotalRows(0);
        }
        return vResourceQueryPageableDto;
    }

    @Override
    public ResourceQueryDto addResource(ResourceAddDto pResourceAddDto) {
        Resource vResource = new Resource();
        GroupResource vResourcesGroup = groupResourceRepository.getGroupResourceById(pResourceAddDto.getIdResourcesGroup()).orElse(null);//I
        Route vRoute = routeRepository.getRouteById(pResourceAddDto.getIdRoute()).orElse(null);//I
        BeanUtils.copyProperties(pResourceAddDto, vResource);
        vResource.setGroupResource(vResourcesGroup);//I
        vResource.setRoute(vRoute);
        resourceRepository.save(vResource);
        ResourceQueryDto vResourceQueryDto = new ResourceQueryDto();
        BeanUtils.copyProperties(vResource, vResourceQueryDto);
        return vResourceQueryDto;
    }

    @Override
    public void deleteResource(long pResourceId) {
        ResourceQueryDto vResourceQueryDto = this.getResourceById(pResourceId);
        vResourceQueryDto.setDeletedDate(new Date());
        vResourceQueryDto.setState(Constants.STATE_DELETED);
        Resource vResource = new Resource();
        BeanUtils.copyProperties(vResourceQueryDto, vResource);
        resourceRepository.save(vResource);
    }

    @Override
    public ResourceQueryDto getResourceById(long pResourceId) {
        Resource vResource = resourceRepository.getResourceById(pResourceId).orElse(null);
        if(vResource == null){
            Object[] obj = {"recurso", "id", String.valueOf(pResourceId)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        ResourceQueryDto vResourceQueryDto = new ResourceQueryDto();
        BeanUtils.copyProperties(vResource, vResourceQueryDto);
        return vResourceQueryDto;
    }

    @Override
    public Long[] getResourcesByRoles(String[] pRoles) {
        List<Resource> pResourceList = resourceRepository.getResourcesByRoles(pRoles);
        Long[] vResourceIdList = pResourceList.stream().map(x -> x.getId()).toArray(Long[]::new);
        return vResourceIdList;
    }
}
