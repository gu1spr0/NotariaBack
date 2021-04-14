package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.notary.NotaryAddDto;
import com.yorisapp.notaria.service.dto.notary.NotaryQueryDto;

import java.util.List;

public interface NotaryService {
    List<NotaryQueryDto> getAllNotaryByState(String pState);
    NotaryQueryDto addNotary(NotaryAddDto pNotaryAddDto);
    NotaryQueryDto getNotaryById(long pNotaryId);
}
