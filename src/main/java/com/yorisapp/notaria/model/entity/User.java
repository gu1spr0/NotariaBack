package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "no_usuarios")
public class User extends BaseConfigurationEntity {
    @NotNull(message="El usuario no puede ser nulo")
    @Column(name="username", length = 150)
    private String username;

    @NotNull(message="La contraseña no puede ser nulo")
    @Column(name="password", length = 300)
    private String password;

    @NotNull(message="El nombre del usuario no puede ser nulo")
    @Column(name="nombre", length = 60)
    private String name;

    @NotNull(message="El apellido paterno del usuario no puede ser nulo")
    @Column(name="paterno", length = 60)
    private String lastname;

    @NotNull(message="El apellido materno del usuario no puede ser nulo")
    @Column(name="materno", length = 60)
    private String lastname2;

    @NotNull(message="El numero de cedula no puede ser nulo")
    @Column(name="cedula", length = 10)
    private String ci;

    @NotNull(message="El campo expedido no puede ser nulo")
    @Column(name="expedido", length = 10)
    private String exp;

    @NotNull(message="El numero de celular no puede ser nulo")
    @Column(name="celular")
    private Long cellphone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_notaria")
    private Notary notary;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="no_usuarios_roles",
            joinColumns = @JoinColumn(name="uro_usucodigo", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "uro_rolcodigo", referencedColumnName = "id"))
    private List<Role> roleList;
}
