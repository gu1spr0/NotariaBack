package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_recursos")
public class Resource extends BaseConfigurationEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rec_grecodigo")
    private GroupResource groupResource;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rec_rutcodigo")
    private Route route;

    @Column(name="rec_titulo", length = 30)
    private String title;

    @Column(name="rec_descripcion", length = 100)
    private String description;

    @Column(name="rec_orden_despliegue")
    private Long orderDeploy;

    @ManyToMany(mappedBy = "resourceList")
    private List<Role> roleList;
}
