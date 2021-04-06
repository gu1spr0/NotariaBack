package com.yorisapp.notaria.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "nov_grupos_recursos")
public class LoginResource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private long id;

    @Column(name = "gre_titulo")
    private String groupResourceTitle;

    @Column(name = "gre_icono")
    private String groupResourceIcon;

    @Column(name = "gre_orden_despliegue")
    private Long groupResourceOrderDeploy;

    @Column(name = "codigo_recurso")
    private long resourceId;

    @Column(name = "rec_titulo")
    private String resourceTitle;

    @Column(name = "rec_orden_despliegue")
    private Long resourceOrderDeploy;

    @Column(name = "rut_ruta")
    private String route;
}
