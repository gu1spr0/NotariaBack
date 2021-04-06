package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_grupos_recursos")
public class GroupResource extends BaseConfigurationEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gre_rutcodigo")
    private Route route;

    @NotNull(message="El titulo del grupo recurso no puede ser nulo")
    @Column(name="gre_titulo", length = 30)
    private String groupResourceTitle;

    @NotNull(message="La descripcion del grupo recurso no puede ser nulo")
    @Column(name="gre_descripcion", length = 100)
    private String groupResourceDescription;

    @Column(name="gre_icono", length = 30)
    private String groupResourceIcon;

    @Column(name="gre_orden_despliegue")
    private Long groupResourceOrderDeploy;

    @OneToMany(mappedBy="groupResource",cascade = CascadeType.ALL)
    private List<Resource> resourceList;

}
