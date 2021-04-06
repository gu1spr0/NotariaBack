package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="no_log_conexiones")
public class LogConnection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull(message="El identificador el usuario no puede ser nulo")
    @Column(name="id_usuario")
    private Long idUsuario;

    @NotNull(message="La fecha de login no puede ser nulo")
    @Column(name = "lco_fecha_login")
    @Temporal(TemporalType.DATE)
    private Date loginDate;

    @Column(name = "lco_fecha_logout")
    @Temporal(TemporalType.DATE)
    private Date logoutDate;
}
