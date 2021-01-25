package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_dominios")
public class Domain extends BaseConfigurationEntity {
    @NotNull(message = "El tipo de dominio no debe ser nulo")
    @Column(name = "tipo", length = 20)
    private String type;

    @NotNull(message = "El nombre de dominio no debe ser nulo")
    @Column(name = "nombre", length = 100)
    private String name;

    @NotNull(message = "La descripción del dominio no puede ser nulo")
    @Column(name = "descripcion", length = 300)
    private String description;

    @OneToMany(mappedBy = "domain",fetch = FetchType.LAZY)
    private List<DomainValue> domainValueList;
}
