package com.yorisapp.notaria.service;

import com.yorisapp.notaria.service.dto.CommonResponseDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public interface DocumentService {
    CommonResponseDto uploadDocument(MultipartFile file, HttpServletRequest httpServletRequest);
}
