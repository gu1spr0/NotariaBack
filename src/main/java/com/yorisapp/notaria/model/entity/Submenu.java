package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "no_submenus")
public class Submenu extends BaseConfigurationEntity {
    @NotNull(message="No puede ser nulo el titulo del submenu")
    @Column(name="titulo", length = 60)
    private String title;

    @NotNull(message="No puede ser nulo el icono del submenu")
    @Column(name="icono", length = 30)
    private String icon;

    @NotNull(message="No puede ser nulo el orden del submenu")
    @Column(name="orden")
    private Long order;

    @NotNull(message="No puede ser nulo la dirección del submenu")
    @Column(name="url", length = 250)
    private String url;

    @ManyToOne
    @JoinColumn(name="id_menu")
    private Menu menu;
}
