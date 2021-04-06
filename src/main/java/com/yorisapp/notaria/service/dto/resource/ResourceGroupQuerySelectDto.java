package com.yorisapp.notaria.service.dto.resource;

import lombok.Data;

@Data
public class ResourceGroupQuerySelectDto {
    private String groupResourceTitle;
    private String groupResourceIcon;
    private Long groupResourceOrderDeploy;
    public ResourceGroupQuerySelectDto(String groupResourceTitle, String groupResourceIcon, long groupResourceOrderDeploy){
        this.groupResourceTitle = groupResourceTitle;
        this.groupResourceIcon = groupResourceIcon;
        this.groupResourceOrderDeploy = groupResourceOrderDeploy;
    }


}
