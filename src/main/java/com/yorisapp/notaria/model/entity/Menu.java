package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_menus")
public class Menu extends BaseConfigurationEntity {
    @NotNull(message="Titulo de menu no puede ser nulo")
    @Column(name="titulo", length = 60)
    private String title;

    @NotNull(message="Nombre de icono no puede ser nulo")
    @Column(name="icono", length = 30)
    private String icon;

    @NotNull(message="Orden de menu no puede ser nulo")
    @Column(name="orden")
    private Long order;

    @NotNull(message="Url de menu no puede ser nulo")
    @Column(name="url", length = 250)
    private String url;

    @ManyToOne
    @JoinColumn(name="id_rol")
    private Role role;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL)
    private List<Submenu> submenuList;
}
