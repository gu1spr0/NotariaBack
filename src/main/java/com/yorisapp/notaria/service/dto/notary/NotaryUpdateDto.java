package com.yorisapp.notaria.service.dto.notary;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotaryUpdateDto {
    private Integer nro;
    private String address;
    private Long cellphone;
    private Long telephone;
}
