package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_grupos_permisos")
public class GroupPermission extends BaseConfigurationEntity {
    @Column(name="gpe_titulo", length = 100)
    @NotNull(message="El titulo de grupos de permiso no puede ser nulo")
    private String title;

    @OneToMany(mappedBy="groupPermission",cascade= CascadeType.ALL)
    private List<Permission> permissionList;

    @Column(name="orden_despliegue")
    private Long orderDeploy;

}
