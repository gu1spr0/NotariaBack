package com.yorisapp.notaria.service.dto.notary;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class NotaryQueryDto {
    private long id;
    private Integer nro;
    private String address;
    private Long cellphone;
    private Long telephone;
    private Date createdDate;
    private Long createdBy;
    private String state;
}
