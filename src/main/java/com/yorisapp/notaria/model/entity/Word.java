package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "no_palabras")
public class Word extends BaseConfigurationEntity {
    @NotNull(message="No puede ser nulo la palabra")
    @Column(name="valor", length = 50)
    private String value;

    @ManyToOne
    @JoinColumn(name="id_documento")
    private Document document;
}
