package com.yorisapp.notaria.service.dto.resource;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ResourceGroupLoginQueryDto {
    private String groupResourceTitle;
    private String groupResourceIcon;
    private Long groupResourceOrderDeploy;
    private List<ResourceLoginQueryDto> resourceLoginQueryDtoList;


    public ResourceGroupLoginQueryDto(){}
    public ResourceGroupLoginQueryDto(String title, String icon, Long deploymentOrder){
        this.groupResourceTitle = title;
        this.groupResourceIcon = icon;
        this.groupResourceOrderDeploy = deploymentOrder;
    }
}
