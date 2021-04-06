package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.LoginPermission;
import com.yorisapp.notaria.model.entity.Permission;
import com.yorisapp.notaria.model.repository.LoginPermissionRepository;
import com.yorisapp.notaria.model.repository.PermissionRepository;
import com.yorisapp.notaria.service.PermissionService;
import com.yorisapp.notaria.service.dto.permission.PermissionGroupQuerySelectDto;
import com.yorisapp.notaria.service.dto.role.RolePermissionGroupQueryDto;
import com.yorisapp.notaria.service.dto.role.RolePermissionQueryDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final LoginPermissionRepository loginPermissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository,
                                 LoginPermissionRepository loginPermissionRepository){
        this.permissionRepository = permissionRepository;
        this.loginPermissionRepository = loginPermissionRepository;
    }

    @Override
    public List<RolePermissionGroupQueryDto> getRolePermissionsGroupsByRolesId(String[] pRoles) {
        List<LoginPermission> vRolePermissionList = loginPermissionRepository.getPermissions();
        List<Permission> vPermissionList = permissionRepository.getPermissionsByRoleIds(pRoles);

        if (vRolePermissionList.size()>0){
            List<RolePermissionGroupQueryDto> vRolePermissionGroupQueryDtoList = new ArrayList<>();
            Map<PermissionGroupQuerySelectDto, List<LoginPermission>> rev = vRolePermissionList.stream()
                    .collect(Collectors.groupingBy(permissionGroup -> new PermissionGroupQuerySelectDto(permissionGroup.getGroupPermissionTitle(), permissionGroup.getGroupPermissionCode(), permissionGroup.getGroupPermissionOrderDeploy()), Collectors.toList()));

            rev = rev.entrySet().stream().sorted(Comparator.comparing(p -> p.getKey().getDeploymentOrder()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            for (Map.Entry<PermissionGroupQuerySelectDto, List<LoginPermission>> entry : rev.entrySet()) {
                RolePermissionGroupQueryDto vRolePermissionGroupQueryDto = new RolePermissionGroupQueryDto();
                vRolePermissionGroupQueryDto.setTitle(entry.getKey().getTitle());
                vRolePermissionGroupQueryDto.setPermissionGroupCode(entry.getKey().getPermissionGroupCode());
                List<RolePermissionQueryDto> vPermissionLoginQueryDtoList = new ArrayList<>();
                List<LoginPermission> PermissionLoginList = entry.getValue().stream().sorted(Comparator.comparing(LoginPermission::getPermissionOrderDeploy)).collect(toList());

                for (LoginPermission permissionLogin : PermissionLoginList) {
                    RolePermissionQueryDto vRolePermissionQueryDto = new RolePermissionQueryDto();
                    vRolePermissionQueryDto.setTitle(permissionLogin.getPermissionTitle());
                    vRolePermissionQueryDto.setPermissionCode(permissionLogin.getPermissionCode());
                    vRolePermissionQueryDto.setId(permissionLogin.getPermissionId());
                    Permission vPermission = vPermissionList.stream().filter(x -> x.getId()==permissionLogin.getPermissionId()).
                            findFirst().orElse(null);
                    if (vPermission==null){
                        vRolePermissionQueryDto.setAssign(false);
                    }else{
                        vRolePermissionQueryDto.setAssign(true);
                    }
                    vPermissionLoginQueryDtoList.add(vRolePermissionQueryDto);
                }
                vRolePermissionGroupQueryDto.setRolePermissionQueryDtoList(vPermissionLoginQueryDtoList);
                vRolePermissionGroupQueryDtoList.add(vRolePermissionGroupQueryDto);
            }
            return vRolePermissionGroupQueryDtoList;
        } else {
            throw Message.GetBadRequest(MessageDescription.UserWithoutPermissions);
        }
    }
}
