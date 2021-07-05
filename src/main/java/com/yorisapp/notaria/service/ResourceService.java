package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.resource.ResourceAddDto;
import com.yorisapp.notaria.service.dto.resource.ResourceQueryDto;
import com.yorisapp.notaria.service.dto.resource.ResourceQueryPageableDto;
import com.yorisapp.notaria.service.dto.resource.ResourceUpdateDto;

public interface ResourceService {
    ResourceQueryPageableDto getResourcePageable(Long pResourceGroupId, String pState, int pPage, int pRowsNumber);
    ResourceQueryDto addResource(ResourceAddDto pResourceAddDto);
    void deleteResource(long pResourceId);
    ResourceQueryDto getResourceById(long pResourceId);
    Long[] getResourcesByRoles(String[] pRoles);
}
