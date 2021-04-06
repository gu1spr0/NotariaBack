package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_permisos")
public class Permission extends BaseConfigurationEntity{
    @Column(name="per_titulo", length = 100)
    private String titulo;

    @Column(name="per_codigo_permiso", length = 30)
    private String permissionCode;

    @Column(name="per_orden_despliegue")
    private Long orderDeploy;

    @ManyToMany(mappedBy = "permissionList")
    private List<Role> roleList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "per_gpecodigo")
    private GroupPermission groupPermission;

}
