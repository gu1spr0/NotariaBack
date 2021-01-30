package com.yorisapp.notaria.security.filter;

import com.yorisapp.notaria.security.service.JWTService;
import com.yorisapp.notaria.security.service.JWTServiceImpl;
import com.yorisapp.notaria.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private JWTService jwtService;
    private UserService userService;
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);

        if (!requiresAuthentication(header)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;

        if(jwtService.validate(header)) {
            authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null, jwtService.getRoles(header));
        }

        HashMap<String, Long> info = new HashMap<String, Long>();
//        info.put("Id", userService.obtenerUsuarioLogeado(header).getId());
        authentication.setDetails(info);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    protected boolean requiresAuthentication(String header) {

        if (header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
            return false;
        }
        return true;
    }


}
