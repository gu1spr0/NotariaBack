package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_rutas")
public class Route extends BaseConfigurationEntity {
    @NotNull(message="No puede ser nulo la ruta base")
    @Column(name="rut_ruta", length = 100)
    private String route;

    @NotNull(message="No puede ser nulo la ruta base")
    @Column(name="rut_descripcion", length = 300)
    private String description;

    @OneToMany(mappedBy="route",cascade= CascadeType.ALL)
    private List<GroupResource> groupResourceList;

    @OneToMany(mappedBy="route",cascade= CascadeType.ALL)
    private List<Resource> resourceList;
}
