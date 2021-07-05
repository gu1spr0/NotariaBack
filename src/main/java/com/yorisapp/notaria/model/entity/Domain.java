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
    @Column(name = "dom_tipo_dominio", length = 30)
    private String domainType;

    @NotNull(message = "El codigo de dominio no debe ser nulo")
    @Column(name = "dom_codigo_dominio", length = 100)
    private String domainCode;

    @NotNull(message = "El nombre de dominio no debe ser nulo")
    @Column(name = "dom_nombre_dominio", length = 100)
    private String domainName;

    @NotNull(message = "La descripcion del dominio no debe ser nulo")
    @Column(name = "dom_descripcion", length = 300)
    private String domainDescription;

    @OneToMany(mappedBy = "domain",fetch = FetchType.LAZY)
    private List<DomainValue> domainValueList;
}
