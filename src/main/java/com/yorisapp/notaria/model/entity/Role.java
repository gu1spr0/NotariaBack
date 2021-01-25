package com.yorisapp.notaria.model.entity;

import com.yorisapp.notaria.model.entity.base.BaseConfigurationEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name="no_roles")
public class Role extends BaseConfigurationEntity {
    @NotNull(message="El nombre del rol no puede ser nulo")
    @Column(name="rol", length = 40)
    private String role;

    @NotNull(message="La descripcion no puede ser nulo")
    @Column(name="descripcion", length = 150)
    private String description;

    @OneToMany(mappedBy="role",cascade = CascadeType.ALL)
    private List<Menu> menuList;
}
