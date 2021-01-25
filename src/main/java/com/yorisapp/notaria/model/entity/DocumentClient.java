package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "no_documentos_clientes")
public class DocumentClient extends BaseConfigurationEntity {
    @ManyToOne
    @JoinColumn(name="id_documento")
    private Document document;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Client client;


    @Column(name="observacion", length = 200)
    private String observation;
}
