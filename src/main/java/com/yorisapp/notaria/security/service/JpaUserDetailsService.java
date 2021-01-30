package com.yorisapp.notaria.security.service;

import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;
import com.yorisapp.notaria.model.entity.Role;
import com.yorisapp.notaria.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailService")
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        com.yorisapp.notaria.model.entity.User user = userService.getUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException(Message.GetNotFound(MessageDescription.UsernameNoEncontrado, username).getMessage());
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException(Message.GetNotFound(MessageDescription.UserWithoutRoles, username).getMessage());
        }
        boolean bandera = true;
        if (user.getState().equals("BLOQUEADO"))
            bandera = false;
        else
            bandera = true;
        //return new User(user.getUsername(), user.getPassword(), bandera, true, true, true, authorities);
        return new User(user.getUsername(), user.getPassword(), bandera, true, true, true, authorities);
    }
}
