package com.yorisapp.notaria.service.dto.repo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RepoAddDto {
    private String name;
    private String description;
    private Long notaryId;
}
