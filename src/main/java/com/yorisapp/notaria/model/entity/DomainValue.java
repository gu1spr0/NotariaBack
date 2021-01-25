package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "no_dominios_valores")
public class DomainValue extends BaseConfigurationEntity {

    @NotNull(message = "El codigo del dominio valor no puede ser nulo")
    @Column(name = "codigo", length = 60, unique = true)
    private String code;

    @NotNull(message = "La descripcion del titulo no debe ser nulo")
    @Column(name = "titulo", length = 300)
    private String title;

    @Column(name = "descripcion", length = 300)
    private String description;

    @Column(name = "valor_caracter", length = 300)
    private String charValue;

    @Column(name = "valor_numerico")
    private Long numericValue;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dominio")
    private Domain domain;
}
