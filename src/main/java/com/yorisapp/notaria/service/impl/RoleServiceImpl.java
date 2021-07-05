package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.Role;
import com.yorisapp.notaria.model.repository.RoleRepository;
import com.yorisapp.notaria.service.RoleService;
import com.yorisapp.notaria.service.dto.role.RoleQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public List<RoleQueryDto> getRoles(String pState) {
        if(Strings.isNullOrEmpty(pState)){
            Object[] obj = {pState};
            throw Message.GetBadRequest(MessageDescription.stateNotValid, obj);
        }
        List<RoleQueryDto> pRoleQueryDtoList = new ArrayList<>();
        List<Role> pRoleList = roleRepository.getRolesByState(pState);
        for(Role pRole: pRoleList){
            RoleQueryDto pRoleQueryDto = new RoleQueryDto();
            BeanUtils.copyProperties(pRole, pRoleQueryDto);
            pRoleQueryDtoList.add(pRoleQueryDto);
        }
        return pRoleQueryDtoList;
    }
}
