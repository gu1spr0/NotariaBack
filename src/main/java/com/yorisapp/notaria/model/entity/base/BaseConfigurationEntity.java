package com.yorisapp.notaria.model.entity.base;

import com.yorisapp.notaria.util.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseConfigurationEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @PastOrPresent(message = "La fecha de alta del registro debe ser actual")
    @NotNull(message = "La fecha de alta del registro no debe ser nula")
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @NotNull(message = "El usuario que dió de alta el registro no debe ser nula")
    @Column(name = "usuario_alta")
    private Long createdBy;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date deletedDate;

    @Column(name = "usuario_baja")
    private Long deletedBy;

    @Column(name = "fecha_modificacion", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;

    @Column(name = "usuario_modificacion", nullable = true)
    private Long lastModifiedBy;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado")
    private String state;

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        if (createdDate == null) {
            createdDate = now;
            //createdBy = Security.getUserOfAuthenticatedUser();
            createdBy = 0L;
            state = Constants.STATE_ACTIVE;
        }
    }

    @PreUpdate
    public void preUpdate(){
        //lastModifiedBy = Security.getUserOfAuthenticatedUser();
        lastModifiedDate = new Date();
        if  (deletedDate != null){
            //deletedBy = Security.getUserOfAuthenticatedUser();
            deletedBy = 0L;
            state = Constants.STATE_DELETED;
        }
    }
}
