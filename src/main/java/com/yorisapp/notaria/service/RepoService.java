package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.repo.RepoAddDto;
import com.yorisapp.notaria.service.dto.repo.RepoQueryDto;

import java.util.List;

public interface RepoService {
    List<RepoQueryDto> getAllRepositoryByState(String pState);
    RepoQueryDto addRepository(RepoAddDto pRepoAddDto);
}
