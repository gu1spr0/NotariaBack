package com.yorisapp.notaria.service.dto.document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentAddDto {
    private String originalName;
    private String code;
    private String format;
    private Integer correlative;
    private Long documentType;
    private Long executive;
}
