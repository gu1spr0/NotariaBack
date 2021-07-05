package com.yorisapp.notaria.controller;

import com.yorisapp.notaria.service.NotaryService;
import com.yorisapp.notaria.service.RepoService;
import com.yorisapp.notaria.service.dto.notary.NotaryAddDto;
import com.yorisapp.notaria.service.dto.notary.NotaryQueryDto;
import com.yorisapp.notaria.service.dto.repo.RepoAddDto;
import com.yorisapp.notaria.service.dto.repo.RepoQueryDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/repo")
public class RepoController {
    private final RepoService repoService;
    public RepoController(RepoService repoService){
        this.repoService = repoService;
    }
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<RepoQueryDto> getAllRepository(String state){
        return this.repoService.getAllRepositoryByState(state);
    }
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public RepoQueryDto saveRepository(@Valid @RequestBody RepoAddDto repoAddDto){
        return this.repoService.addRepository(repoAddDto);
    }
}
