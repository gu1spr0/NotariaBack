package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_repositorios")
public class Repo extends BaseConfigurationEntity {
    @NotNull(message="El nombre de repositorio no puede ser nulo")
    @Column(name="nombre", length = 150)
    private String name;

    @NotNull(message="No puede ser nulo la ruta base")
    @Column(name="ruta_base", length = 200)
    private String routeBase;

    @NotNull(message="No puede ser nulo la descripcion del repositorio")
    @Column(name="descripcion", length = 300)
    private String description;

    /*@ManyToOne
    @JoinColumn(name="id_dominio")
    private Domain domain;*/

    @ManyToOne
    @JoinColumn(name="id_notaria")
    private Notary notary;

    @OneToMany(mappedBy="repository",cascade = CascadeType.ALL)
    private List<Document> documentList;
}
