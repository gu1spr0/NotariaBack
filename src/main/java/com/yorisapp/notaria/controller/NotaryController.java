package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.NotaryService;
import com.yorisapp.notaria.service.dto.notary.NotaryAddDto;
import com.yorisapp.notaria.service.dto.notary.NotaryQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/notary")
public class NotaryController {
    private final NotaryService notaryService;
    public NotaryController(NotaryService notaryService){
        this.notaryService = notaryService;
    }
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<NotaryQueryDto> getAllNotary(String state){
        return this.notaryService.getAllNotaryByState(state);
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public NotaryQueryDto saveNotary(@Valid @RequestBody NotaryAddDto notaryAddDto){
        return this.notaryService.addNotary(notaryAddDto);
    }
}
