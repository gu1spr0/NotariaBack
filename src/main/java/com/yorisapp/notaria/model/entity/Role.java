package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_roles")
public class Role extends BaseConfigurationEntity {
    @NotNull(message="El nombre del rol no puede ser nulo")
    @Column(name="rol_rol", length = 50)
    private String role;

    @Column(name="rol_descripcion", length = 100)
    private String description;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="no_roles_recursos",
            joinColumns = @JoinColumn(name="ror_rolcodigo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ror_reccodigo", referencedColumnName = "id"))
    private List<Resource> resourceList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="no_roles_permisos",
            joinColumns = @JoinColumn(name="rop_rolcodigo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rop_percodigo", referencedColumnName = "id"))
    private List<Permission> permissionList;
}
