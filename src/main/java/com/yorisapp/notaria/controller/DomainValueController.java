package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.dto.domainValue.DomainValueQuerySelectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/domainValue")
public class DomainValueController {
    private final DomainValueService domainValueService;
    public DomainValueController(DomainValueService domainValueService){
        this.domainValueService = domainValueService;
    }

    @GetMapping(path = "select")
    @ResponseStatus(HttpStatus.OK)
    public List<DomainValueQuerySelectDto> getDomainValueSelectByDomainCode(@RequestParam String domainCode){
        return domainValueService.getDomainValueSelectByDomainCode(domainCode);
    }
}
