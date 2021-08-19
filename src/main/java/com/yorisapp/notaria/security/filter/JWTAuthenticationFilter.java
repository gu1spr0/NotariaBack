package com.yorisapp.notaria.security.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.yorisapp.notaria.security.service.JWTService;
import com.yorisapp.notaria.security.service.JWTServiceImpl;
import com.yorisapp.notaria.service.*;
import com.yorisapp.notaria.service.dto.resource.ResourceGroupLoginQueryDto;
import com.yorisapp.notaria.service.dto.role.RolePermissionGroupQueryDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;
    private UserService userService;
    private ResourceService resourceService;
    private PermissionService permissionService;
    private LogConnectionService logConnectionService;
    private DomainValueService domainValueService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JWTService jwtService,
                                   UserService userService,
                                   ResourceService resourceService,
                                   PermissionService permissionService,
                                   LogConnectionService logConnectionService,
                                   DomainValueService domainValueService){
        this.authenticationManager = authenticationManager;
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
        this.jwtService = jwtService;
        this.userService = userService;
        this.resourceService = resourceService;
        this.permissionService = permissionService;
        this.logConnectionService = logConnectionService;
        this.domainValueService = domainValueService;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        if(username != null && password !=null) {
            logger.info("Username desde request parameter (form-data): " + username);
            logger.info("Password desde request parameter (form-data): " + password);

        } else {
            com.yorisapp.notaria.model.entity.User user = null;
            try {
                user = new ObjectMapper().readValue(request.getInputStream(), com.yorisapp.notaria.model.entity.User.class);

                username = user.getUsername();
                password = user.getPassword();

                logger.info("Login desde request InputStream (raw): " + username);
//                logger.info("Password desde request InputStream (raw): " + password);

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authToken);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = jwtService.create(authResult);

        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

        String[] roles = authResult.getAuthorities().stream().map(x->x.getAuthority()).toArray(String[]::new);
        Long[] resources = resourceService.getResourcesByRoles(roles);
        List<ResourceGroupLoginQueryDto> resourceGroupLoginQueryDtoList = userService.getMenuByResources(resources);
        List<RolePermissionGroupQueryDto> rolePermissionGroupQueryDtoList = permissionService.getRolePermissionsGroupsByRolesId(roles);
        com.yorisapp.notaria.model.entity.User user = userService.getUserByUserName(((User) authResult.getPrincipal()).getUsername());
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("token", token);
        body.put("user", authResult.getPrincipal());
        body.put("name", (user.getName() + " " + user.getLastname()));
        body.put("phone", (user.getCellphone()));
        body.put("resources", resourceGroupLoginQueryDtoList);
        body.put("permissions", rolePermissionGroupQueryDtoList);
        body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito!", ((User)authResult.getPrincipal()).getUsername()) );
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
        response.setContentType("application/json");
        //logConnectionService.addConnectionLogin(0);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) {

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("message", "Username o password incorrecto o usuario bloqueado!");
        body.put("error", failed.getMessage());

        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setStatus(401);
        response.setContentType("application/json");
    }

}
