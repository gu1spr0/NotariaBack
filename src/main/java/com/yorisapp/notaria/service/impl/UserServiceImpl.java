package com.yorisapp.notaria.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.LoginResource;
import com.yorisapp.notaria.model.entity.Role;
import com.yorisapp.notaria.model.entity.User;
import com.yorisapp.notaria.model.repository.LoginResourceRepository;
import com.yorisapp.notaria.model.repository.RoleRepository;
import com.yorisapp.notaria.model.repository.UserRepository;
import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.UserService;
import com.yorisapp.notaria.service.dto.resource.ResourceGroupLoginQueryDto;
import com.yorisapp.notaria.service.dto.resource.ResourceGroupQuerySelectDto;
import com.yorisapp.notaria.service.dto.resource.ResourceLoginQueryDto;
import com.yorisapp.notaria.service.dto.user.UserQueryDto;
import com.yorisapp.notaria.service.dto.user.UserQueryPageableDto;
import com.yorisapp.notaria.service.dto.user.UserRoleQueryDto;
import com.yorisapp.notaria.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoginResourceRepository loginResourceRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           LoginResourceRepository loginResourceRepository,
                           RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.loginResourceRepository = loginResourceRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    private DomainValueService domainValueService;

    @Override
    public UserQueryPageableDto getUsersPageable(String pState, int pPage, int pRowsNumber) {
        UserQueryPageableDto vUserQueryPageableDto = new UserQueryPageableDto();
        if(Strings.isNullOrEmpty(pState)){
            throw Message.GetBadRequest(MessageDescription.stateNullOrEmpty);
        }
        if(!(pState.equals(Constants.STATE_ACTIVE) || pState.equals(Constants.STATE_INACTIVE) || pState.equals(Constants.STATE_BLOCKED) || pState.equals(Constants.STATE_DELETED))){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        long vTotalRows = userRepository.getCountUsersByState(pState);
        if (vTotalRows>0){
            List<User> vUserList = userRepository.getUsersPageableByState(pState, PageRequest.of(pPage, pRowsNumber));
            List<UserQueryDto> vUserQueryDtoList = new ArrayList<>();
            for (User vUser: vUserList){
                UserQueryDto vUserQueryDto = new UserQueryDto();
                BeanUtils.copyProperties(vUser, vUserQueryDto);
                vUserQueryDtoList.add(vUserQueryDto);
            }
            vUserQueryPageableDto.setTotalRows(vTotalRows);
            vUserQueryPageableDto.setUserQueryDtoList(vUserQueryDtoList);
        }else{
            vUserQueryPageableDto.setTotalRows(0);
        }
        return vUserQueryPageableDto;
    }

    @Override
    public User getUserById(long pUserId) {
        User vUser = userRepository.getUserById(pUserId).orElse(null);
        if (vUser == null){
            Object[] obj = {"usuario", "id", String.valueOf(pUserId)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        return vUser;
    }

    @Override
    public User getUserByUserName(String pUserName) {
        User user = userRepository.getUserByUsername(pUserName).orElse(null);
        if (user == null){
            Object[] obj = {"usuario", "nombre de usuario", String.valueOf(pUserName)};
            throw Message.GetBadRequest(MessageDescription.notExists, obj);
        }
        return user;
    }

    @Override
    public List<ResourceGroupLoginQueryDto> getMenuByResources(Long[] pResourceIdList) {
        List<LoginResource> vResourceLoginList = loginResourceRepository.getResourcesById(pResourceIdList);
        if (vResourceLoginList.size()>0){
            List<ResourceGroupLoginQueryDto> resourceGroupLoginQueryDtoList = new ArrayList<>();
            Map<ResourceGroupQuerySelectDto, List<LoginResource>> rev = vResourceLoginList.stream()
                    .collect(Collectors.groupingBy(resourceGroup -> new ResourceGroupQuerySelectDto(resourceGroup.getGroupResourceTitle(), resourceGroup.getGroupResourceIcon(), resourceGroup.getGroupResourceOrderDeploy()), Collectors.toList()));

            rev = rev.entrySet().stream().sorted(Comparator.comparing(p -> p.getKey().getGroupResourceOrderDeploy()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            for (Map.Entry<ResourceGroupQuerySelectDto, List<LoginResource>> entry : rev.entrySet()) {
                ResourceGroupLoginQueryDto resourceGroupLoginQueryDto = new ResourceGroupLoginQueryDto();
                resourceGroupLoginQueryDto.setGroupResourceTitle(entry.getKey().getGroupResourceTitle());
                resourceGroupLoginQueryDto.setGroupResourceIcon(entry.getKey().getGroupResourceIcon());
                resourceGroupLoginQueryDto.setGroupResourceOrderDeploy(entry.getKey().getGroupResourceOrderDeploy());
                List<ResourceLoginQueryDto> resourceLoginQueryDtoList = new ArrayList<>();
                List<LoginResource> resourceLoginList = entry.getValue().stream().sorted(Comparator.comparing(LoginResource::getGroupResourceOrderDeploy)).collect(toList());

                for (LoginResource resourceLogin : resourceLoginList) {
                    ResourceLoginQueryDto resourceLoginQueryDto = new ResourceLoginQueryDto();
                    resourceLoginQueryDto.setTitle(resourceLogin.getResourceTitle());
                    resourceLoginQueryDto.setRoute(resourceLogin.getRoute());
                    resourceLoginQueryDtoList.add(resourceLoginQueryDto);
                }
                resourceGroupLoginQueryDto.setResourceLoginQueryDtoList(resourceLoginQueryDtoList);
                resourceGroupLoginQueryDtoList.add(resourceGroupLoginQueryDto);
            }
            return resourceGroupLoginQueryDtoList;
        } else {
            throw Message.GetBadRequest(MessageDescription.UserWithoutResources);
        }
    }

    @Override
    public List<UserRoleQueryDto> getUserRolesByUserId(long pUserId) {
        List<Role> vRoleList = roleRepository.getRolesByState(Constants.STATE_ACTIVE);
        List<Role> vUserRoleList = roleRepository.getUserRolesByUsername(pUserId);
        List<UserRoleQueryDto> vUserRoleQueryDtoList = new ArrayList<>();
        for (Role vRole: vRoleList){
            UserRoleQueryDto vUserRoleQueryDto = new UserRoleQueryDto();
            vUserRoleQueryDto.setId(vRole.getId());
            vUserRoleQueryDto.setRole(vRole.getRole());
            vUserRoleQueryDto.setDescription(vRole.getDescription());
            Role vUserRole = vUserRoleList.stream().filter(x -> x.getId()==vRole.getId()).
                    findFirst().orElse(null);
            if (vUserRole == null){
                vUserRoleQueryDto.setAssigned(false);
            }else {
                vUserRoleQueryDto.setAssigned(true);
            }
            vUserRoleQueryDtoList.add(vUserRoleQueryDto);
        }
        return vUserRoleQueryDtoList;
    }

    @Override
    public void addRolesByUserId(long pUserId, Long[] roleIds) {
        User vUser = this.getUserById(pUserId);
        List<Role> vRoleList = roleRepository.getRolesByIds(roleIds);
        vUser.setRoleList(vRoleList);
        userRepository.save(vUser);
    }
}
