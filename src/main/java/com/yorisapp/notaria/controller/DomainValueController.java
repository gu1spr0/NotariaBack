package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.DomainValueService;
import com.yorisapp.notaria.service.dto.domainValue.DomainValueQueryDto;
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
    @GetMapping(path="")
    @ResponseStatus(HttpStatus.OK)
    public List<DomainValueQueryDto> getAllDomainValueByState(@RequestParam String state){
        return domainValueService.getAllDomainValueByState(state);
    }
    @GetMapping(path="/{domainValueId}")
    @ResponseStatus(HttpStatus.OK)
    public DomainValueQueryDto getDomainValueByIdAndState(@PathVariable("domainValueId") long domainValueId,
                                                          @RequestParam String state){
        return domainValueService.getDomainValueByIdAndState(domainValueId,state);
    }
    @GetMapping(path="/code")
    @ResponseStatus(HttpStatus.OK)
    public DomainValueQueryDto getDomainValueByCodeAndState(@RequestParam String code,
                                                            @RequestParam String state){
        return domainValueService.getDomainValueByCodeAndState(code,state);
    }
    @GetMapping(path="/domain/code")
    @ResponseStatus(HttpStatus.OK)
    public DomainValueQueryDto getDomainValueByCodeAndState(@RequestParam String domainName,
                                                            @RequestParam String code,
                                                            @RequestParam String state){
        return domainValueService.getDomainValueByDomainAndCodeAndState(domainName, code, state);
    }

}
