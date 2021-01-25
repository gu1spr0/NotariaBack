package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_notarias")
public class Notary extends BaseConfigurationEntity {
    @Column(name="nro")
    @NotNull(message="El número no puede ser nulo")
    private Integer nro;

    @Column(name="direccion", length = 150)
    @NotNull(message="La direccion no puede ser nula")
    private String address;

    @Column(name="celular")
    @NotNull(message="El numero de celular no puede ser nulo")
    private Long cellphone;

    @Column(name="telefono")
    private Long telephone;

    @OneToOne(cascade= CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Domain domain;

    @OneToMany(mappedBy="notary",cascade= CascadeType.ALL)
    private List<Repo> repositoryList;
}
