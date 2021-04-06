package com.yorisapp.notaria.service;

import com.yorisapp.notaria.model.entity.User;
import com.yorisapp.notaria.service.dto.resource.ResourceGroupLoginQueryDto;
import com.yorisapp.notaria.service.dto.user.UserQueryPageableDto;
import com.yorisapp.notaria.service.dto.user.UserRoleQueryDto;

import java.util.List;

public interface UserService {
    UserQueryPageableDto getUsersPageable(String pState, int pPage, int pRowsNumber);
    User getUserById(long pUserId);
    User getUserByUserName(String pUserName);
    List<ResourceGroupLoginQueryDto> getMenuByResources(Long[] pResourceIdList);
    List<UserRoleQueryDto> getUserRolesByUserId(long pUserId);
    void addRolesByUserId(long pUserId, Long[] roleIds);
}
