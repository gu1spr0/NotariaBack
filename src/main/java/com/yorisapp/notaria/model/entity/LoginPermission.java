package com.yorisapp.notaria.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "nov_grupos_permisos")
public class LoginPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo_permiso")
    private long id;

    @Column(name = "gpe_titulo")
    private String groupPermissionTitle;

    @Column(name = "gpe_codigo")
    private String groupPermissionCode;

    @Column(name = "gpe_orden_despliegue")
    private int groupPermissionOrderDeploy;

    @Column(name = "id_permiso")
    private long permissionId;

    @Column(name = "permiso_titulo")
    private String permissionTitle;

    @Column(name = "permiso_codigo")
    private String permissionCode;

    @Column(name = "permiso_orden_despliegue")
    private Long permissionOrderDeploy;
}
