package com.yorisapp.notaria.util;

import com.yorisapp.notaria.model.entity.User;
import com.yorisapp.notaria.service.UserService;
import com.yorisapp.notaria.service.dto.user.UserQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Security {
    @Autowired
    private UserService userService;

    public Long getIdOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Map<String, Long> info = (Map<String, Long>) authentication.getDetails();
        Long idlog = info.get("Id");
        return idlog;
    }

    public String getUserOfAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return authentication.getPrincipal().toString();
    }

    public UserQueryDto getCurrentUser() {
        String pUserName = getUserOfAuthenticatedUser();
        User vUser= userService.getUserByUserName(pUserName);
        UserQueryDto vUserQueryDto = new UserQueryDto();
        BeanUtils.copyProperties(vUser, vUserQueryDto);
        return vUserQueryDto;
    }
}
