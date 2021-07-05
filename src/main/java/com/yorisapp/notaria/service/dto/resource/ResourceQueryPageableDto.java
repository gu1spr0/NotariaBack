package com.yorisapp.notaria.service.dto.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResourceQueryPageableDto {
    private List<ResourceQueryDto> resourceQueryDtoList;
    private long totalRows;
}
