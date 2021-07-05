package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "no_clientes")
public class  Client extends BaseConfigurationEntity {
    @NotNull(message = "El nombre de cliente no puede ser nulo")
    @Column(name = "nombre", length = 60)
    private String name;

    @NotNull(message = "El apellido de cliente no puede ser nulo")
    @Column(name = "paterno", length = 60)
    private String lastaname1;

    @Column(name = "materno", length = 60)
    private String lastname2;

    @NotNull(message = "La cedula del cliente no puede ser nulo")
    @Column(name = "cedula")
    private Long ci;

    @NotNull(message = "La extension no puede ser nulo")
    @Column(name = "expedido", length = 10)
    private String exp;

    @NotNull(message = "La direccion del cliente no puede ser nulo")
    @Column(name = "direccion", length = 300)
    private String address;

    @NotNull(message = "Número de celular no puede ser nulo")
    @Column(name = "celular")
    private Long cellphone;

    @Column(name = "localidad", length = 300)
    private String locality;

    @Column(name = "fecha_tramite")
    @Temporal(TemporalType.DATE)
    private Date dateProcess;

    @Column(name = "hora_tramite")
    @Temporal(TemporalType.TIME)
    private Date timeProcess;

    @ManyToMany(mappedBy = "clientList")
    private List<Document> documentList;
}
