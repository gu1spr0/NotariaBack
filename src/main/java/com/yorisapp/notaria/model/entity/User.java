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
    private String lastname1;

    @NotNull(message="El apellido materno del usuario no puede ser nulo")
    @Column(name="materno", length = 60)
    private String lastname2;

    @NotNull(message="El numero de cedula no puede ser nulo")
    @Column(name="cedula", length = 10)
    private String ci;

    @NotNull(message="El campo expedido no puede ser nulo")
    @Column(name="expedido", length = 10)
    private String exp;

    /*@OneToOne(cascade= CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DomainValue domainValue;*/

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Role role;

    @NotNull(message="El numero de celular no puede ser nulo")
    @Column(name="celular")
    private Long cellphone;

    @Column(name="grado", length = 20)
    private String grade;

    @OneToMany(mappedBy="user",cascade= CascadeType.ALL)
    private List<Document> documentList;
}
