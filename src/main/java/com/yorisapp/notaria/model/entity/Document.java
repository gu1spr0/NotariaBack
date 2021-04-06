package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_documentos")
public class Document extends BaseConfigurationEntity {
    @NotNull(message="Nombre original de documento no puede ser nulo")
    @Column(name="nombre_original", length = 150)
    private String originalName;

    @NotNull(message="El codigo de documento no puede ser nullo")
    @Column(name="codigo", length = 60)
    private String code;

    @NotNull(message="Nombre original de documento no puede ser nulo")
    @Column(name="formato", length = 10)
    private String format;

    @NotNull(message="Correlativo de tramite no puede ser nulo")
    @Column(name="correlativo")
    private Integer correlative;

    @NotNull(message="Estado del proceso de tramite no puede ser nulo")
    @Column(name="proceso", length = 20)
    private String process;

    @NotNull(message="Tipo de documento no puede ser nulo")
    @Column(name="tipo", length = 300)
    private String documentType;

    @ManyToOne
    @JoinColumn(name = "id_repositorio")
    private Repo repository;


    @NotNull(message="Ejecutivo encargado del documento no puede ser nulo")
    @Column(name="ejecutivo")
    private Long executive;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="no_documentos_clientes",
            joinColumns = @JoinColumn(name="id_documento", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id"))
    private List<Client> clientList;
}
